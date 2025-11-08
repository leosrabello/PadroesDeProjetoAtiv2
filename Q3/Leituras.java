package com.exemplo.usina;

public record Leituras(
        double temperaturaC,
        double pressaoBar,
        double radiacaoSievert,
        boolean falhaResfriamento,
        long epochSegundos
) {}
