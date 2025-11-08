package com.exemplo.risco;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public final class AlgoritmoPerdaEsperada implements AlgoritmoRisco {
    private static final MathContext MC = new MathContext(10, RoundingMode.HALF_UP);

    @Override public String nome() { return "PerdaEsperada"; }

    @Override
    public String calcular(ContextoRisco contexto) {
        BigDecimal fatorBase = (contexto.nivelConfianca().compareTo(new BigDecimal("0.99")) >= 0)
                ? new BigDecimal("1.20") : new BigDecimal("1.10");
        BigDecimal zAprox = new BigDecimal("1.65");
        BigDecimal resultado = contexto.valorCarteira()
                .multiply(contexto.volatilidade(), MC)
                .multiply(zAprox, MC)
                .multiply(fatorBase, MC)
                .multiply(BigDecimal.ONE.add(contexto.fatorLiquidez(), MC), MC)
                .setScale(2, RoundingMode.HALF_UP);

        return "[PerdaEsperada] Data=%s ~ R$ %s (fator≈%s, liquidez=%s%%)".formatted(
                contexto.dataReferencia(),
                resultado.toPlainString(),
                fatorBase.toPlainString(),
                contexto.fatorLiquidez().multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP)
        );
    }
}
