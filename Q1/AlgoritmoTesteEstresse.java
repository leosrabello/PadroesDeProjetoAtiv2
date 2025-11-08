package com.exemplo.risco;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public final class AlgoritmoTesteEstresse implements AlgoritmoRisco {
    private static final MathContext MC = new MathContext(10, RoundingMode.HALF_UP);

    @Override public String nome() { return "TesteEstresse"; }

    @Override
    public String calcular(ContextoRisco contexto) {
        BigDecimal volatilidadeEstressada = contexto.volatilidade().multiply(new BigDecimal("1.30"), MC);
        BigDecimal perda = contexto.valorCarteira()
                .multiply(volatilidadeEstressada, MC)
                .multiply(BigDecimal.ONE.add(contexto.fatorLiquidez(), MC), MC)
                .setScale(2, RoundingMode.HALF_UP);

        return "[Estresse] Data=%s perda estimada ~ R$ %s (σ*30%%, liquidez aplicada)".formatted(
                contexto.dataReferencia(),
                perda.toPlainString()
        );
    }
}
