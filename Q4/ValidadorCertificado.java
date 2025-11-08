package com.exemplo.nfe;

public final class ValidadorCertificado extends BaseValidadorComTimeout {
    public ValidadorCertificado(){ super(700); }
    @Override public String nome(){ return "CertificadoDigital"; }
    @Override public ResultadoValidacao validar(DocumentoNFe doc) throws Exception {
        return executarComTimeout(() -> {
            if (doc.certificado == null || doc.certificado.isBlank())
                return ResultadoValidacao.falha("Certificado ausente.");
            if (doc.certificado.contains("REVOGADO"))
                return ResultadoValidacao.falha("Certificado revogado.");
            if (doc.certificado.contains("EXPIRADO"))
                return ResultadoValidacao.falha("Certificado expirado.");
            return ResultadoValidacao.ok("Certificado válido.");
        });
    }
}
