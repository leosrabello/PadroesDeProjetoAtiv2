package com.exemplo.nfe;

public class AplicacaoNFe {
    public static void main(String[] args){
        DocumentoNFe doc = new DocumentoNFe("<nfe>ok</nfe>", "CERT_OK", "NF123");
        CadeiaValidacao cadeia = CadeiaValidacao.padrao();
        ResultadoValidacao r = cadeia.executar(doc);
        System.out.println("Resultado: " + (r.sucesso() ? "SUCESSO" : "FALHA") + " - " + r.mensagem());
        System.out.println("Banco contém NF? " + DocumentoNFe.banco.containsKey(doc.numero));
    }
}
