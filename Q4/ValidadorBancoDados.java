package com.exemplo.nfe;

public final class ValidadorBancoDados extends BaseValidadorComTimeout {
    public ValidadorBancoDados(){ super(600); }
    @Override public String nome(){ return "BancoDados"; }
    @Override public boolean modificaDocumento(){ return true; }
    @Override public ResultadoValidacao validar(DocumentoNFe doc) throws Exception {
        return executarComTimeout(() -> {
            if (doc.bancoJaPossui || DocumentoNFe.banco.containsKey(doc.numero))
                return ResultadoValidacao.falha("Duplicidade de número no banco.");
            DocumentoNFe.banco.put(doc.numero, "reserva");
            return ResultadoValidacao.ok("Número reservado (rollback se falhar depois).");
        });
    }
    @Override public void rollback(DocumentoNFe doc){ DocumentoNFe.banco.remove(doc.numero); }
}
