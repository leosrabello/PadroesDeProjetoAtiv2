package com.exemplo.risco;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContextoRisco(
        BigDecimal valorCarteira,
        BigDecimal volatilidade,
        BigDecimal nivelConfianca,
        BigDecimal fatorLiquidez,
        LocalDate dataReferencia
) {
    public ContextoRisco {
        if (valorCarteira == null || volatilidade == null || nivelConfianca == null
                || fatorLiquidez == null || dataReferencia == null) {
            throw new IllegalArgumentException("Nenhum campo do contexto pode ser nulo.");
        }
        if (valorCarteira.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("O valor da carteira não pode ser negativo.");
        if (nivelConfianca.compareTo(BigDecimal.ZERO) <= 0 || nivelConfianca.compareTo(BigDecimal.ONE) >= 0)
            throw new IllegalArgumentException("O nível de confiança deve estar entre 0 e 1 (ex.: 0.95).");
    }
}
