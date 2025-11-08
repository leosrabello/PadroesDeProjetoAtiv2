package com.exemplo.nfe;

public final class ValidadorSefaz extends BaseValidadorComTimeout {
    public ValidadorSefaz(){ super(1500); }
    @Override public String nome(){ return "SefazService"; }
    @Override public ResultadoValidacao validar(DocumentoNFe doc) throws Exception {
        return executarComTimeout(() -> {
            if (!doc.simuladorSefazOk)
                return ResultadoValidacao.falha("SEFAZ indisponível (simulado).");
            return ResultadoValidacao.ok("Consulta SEFAZ bem-sucedida (simulado).");
        });
    }
}
