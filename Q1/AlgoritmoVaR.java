package com.exemplo.risco;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public final class AlgoritmoVaR implements AlgoritmoRisco {
    private static final MathContext MC = new MathContext(10, RoundingMode.HALF_UP);

    @Override public String nome() { return "ValueAtRisk"; }

    @Override
    public String calcular(ContextoRisco contexto) {
        BigDecimal z = obterZ(contexto.nivelConfianca());
        BigDecimal resultado = contexto.valorCarteira()
                .multiply(contexto.volatilidade(), MC)
                .multiply(z, MC)
                .setScale(2, RoundingMode.HALF_UP);

        return "[VaR] Data=%s ~ R$ %s (z≈%s, σ=%s%%)".formatted(
                contexto.dataReferencia(),
                resultado.toPlainString(),
                z.toPlainString(),
                contexto.volatilidade().multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP)
        );
    }

    private static BigDecimal obterZ(BigDecimal confianca) {
        if (confianca.compareTo(new BigDecimal("0.99")) >= 0) return new BigDecimal("2.33");
        if (confianca.compareTo(new BigDecimal("0.95")) >= 0) return new BigDecimal("1.65");
        return new BigDecimal("1.28");
    }
}
