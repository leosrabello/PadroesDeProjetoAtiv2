package com.exemplo.nfe;

public final class ValidadorSchemaXML extends BaseValidadorComTimeout {
    public ValidadorSchemaXML(){ super(800); }
    @Override public String nome(){ return "SchemaXML"; }
    @Override public ResultadoValidacao validar(DocumentoNFe doc) throws Exception {
        return executarComTimeout(() -> {
            boolean ok = doc.xml != null && doc.xml.contains("<nfe>");
            return ok ? ResultadoValidacao.ok("XML válido contra XSD (simulado).")
                      : ResultadoValidacao.falha("XML inválido.");
        });
    }
}
