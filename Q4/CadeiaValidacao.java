package com.exemplo.nfe;

import java.util.*;

public final class CadeiaValidacao {
    private final List<Validador> ordem;
    private final Map<String, Set<String>> pularSeFalhar;
    private final List<Validador> modificadoresExecutados = new ArrayList<>();

    public CadeiaValidacao(List<Validador> ordem, Map<String, Set<String>> pularSeFalhar){
        this.ordem = List.copyOf(ordem);
        this.pularSeFalhar = pularSeFalhar == null ? Map.of() : pularSeFalhar;
    }

    public ResultadoValidacao executar(DocumentoNFe doc){
        int falhas = 0;
        boolean anterioresPassaram = true;
        Set<String> pular = new HashSet<>();

        for (Validador v : ordem){
            if (v instanceof ValidadorRegrasFiscais || v instanceof ValidadorSefaz)
                if (!anterioresPassaram) continue;
            if (pular.contains(v.nome())) continue;

            ResultadoValidacao res;
            try { res = v.validar(doc); }
            catch (Exception e){ res = ResultadoValidacao.falha(v.nome() + " falhou: " + e.getMessage()); }

            if (res.sucesso()){
                if (v.modificaDocumento()) modificadoresExecutados.add(v);
            } else {
                falhas++; anterioresPassaram = false;
                pular.addAll(pularSeFalhar.getOrDefault(v.nome(), Set.of()));
                if (falhas >= 3){
                    rollback(doc);
                    return ResultadoValidacao.falha("Circuit breaker após 3 falhas. Última: " + res.mensagem());
                }
            }
        }
        if (!anterioresPassaram){ rollback(doc); return ResultadoValidacao.falha("Falha geral. Rollback feito."); }
        return ResultadoValidacao.ok("Cadeia concluída com sucesso.");
    }

    private void rollback(DocumentoNFe doc){
        ListIterator<Validador> it = modificadoresExecutados.listIterator(modificadoresExecutados.size());
        while(it.hasPrevious()){
            try{ it.previous().rollback(doc); } catch(Exception ignored){}
        }
        modificadoresExecutados.clear();
    }

    public static CadeiaValidacao padrao(){
        List<Validador> lista = List.of(
            new ValidadorSchemaXML(),
            new ValidadorCertificado(),
            new ValidadorRegrasFiscais(),
            new ValidadorBancoDados(),
            new ValidadorSefaz()
        );
        Map<String, Set<String>> regras = Map.of(
            "CertificadoDigital", Set.of("SefazService"),
            "SchemaXML", Set.of("RegrasFiscais", "BancoDados", "SefazService")
        );
        return new CadeiaValidacao(lista, regras);
    }
}
