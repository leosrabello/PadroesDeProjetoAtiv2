package com.exemplo.nfe;

public final class ValidadorRegrasFiscais extends BaseValidadorComTimeout {
    public ValidadorRegrasFiscais(){ super(900); }
    @Override public String nome(){ return "RegrasFiscais"; }
    @Override public ResultadoValidacao validar(DocumentoNFe doc) throws Exception {
        return executarComTimeout(() -> {
            boolean ok = doc.xml != null && !doc.xml.contains("ERRO_IMPOSTO");
            return ok ? ResultadoValidacao.ok("Regras fiscais OK (simulado).")
                      : ResultadoValidacao.falha("Erro no cálculo de impostos.");
        });
    }
}
