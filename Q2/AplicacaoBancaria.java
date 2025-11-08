package com.exemplo.banco;

public class AplicacaoBancaria {
    public static void main(String[] args) {
        var legado = new SistemaBancarioLegado();
        var config = new ConfiguracaoLegado("AG1234", "ECOM");
        ProcessadorTransacoes processador = new AdaptadorProcessadorTransacoes(legado, config);

        var r1 = processador.autorizar("4111111111111111", 500.00, "BRL");
        System.out.println("Resp1: " + r1);

        var r2 = processador.autorizar("4111111111111111", 1500.00, "USD"); // deve negar pela regra dummy
        System.out.println("Resp2: " + r2);
    }
}
