package com.exemplo.banco;

/** Interface moderna (atualizada). */
public interface ProcessadorTransacoes {
    RespostaAutorizacao autorizar(String cartao, double valor, String moeda);
}
