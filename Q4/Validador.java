package com.exemplo.nfe;

public interface Validador {
    String nome();
    long timeoutMs();
    ResultadoValidacao validar(DocumentoNFe doc) throws Exception;
    default boolean modificaDocumento(){ return false; }
    default void rollback(DocumentoNFe doc) {}
}
