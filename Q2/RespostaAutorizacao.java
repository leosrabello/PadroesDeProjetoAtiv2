package com.exemplo.banco;

/** DTO moderno para retorno da autorização. */
public record RespostaAutorizacao(
        boolean aprovado,
        String codigoAutorizacao,
        String moeda,   // "USD" | "EUR" | "BRL"
        double valor,   // em unidade monetária, ex.: 10.50
        String mensagem
) {}
