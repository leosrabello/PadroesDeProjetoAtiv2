package com.exemplo.nfe;

import java.util.HashMap;
import java.util.Map;

public class DocumentoNFe {
    public String xml;
    public String certificado;
    public String numero;
    public boolean simuladorSefazOk;
    public boolean bancoJaPossui;
    public static final Map<String, String> banco = new HashMap<>();

    public DocumentoNFe(String xml, String certificado, String numero) {
        this.xml = xml;
        this.certificado = certificado;
        this.numero = numero;
        this.simuladorSefazOk = true;
        this.bancoJaPossui = false;
    }
}
