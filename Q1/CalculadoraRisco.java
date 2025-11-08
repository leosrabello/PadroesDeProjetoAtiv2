package com.exemplo.risco;

public final class CalculadoraRisco {
    private AlgoritmoRisco estrategia;
    private ContextoRisco contexto;

    public CalculadoraRisco(AlgoritmoRisco estrategiaInicial, ContextoRisco contexto) {
        if (estrategiaInicial == null || contexto == null)
            throw new IllegalArgumentException("Parâmetros obrigatórios.");
        this.estrategia = estrategiaInicial;
        this.contexto = contexto;
    }

    public void definirEstrategia(AlgoritmoRisco estrategia) {
        if (estrategia == null) throw new IllegalArgumentException("Estratégia nula.");
        this.estrategia = estrategia;
    }

    public void atualizarContexto(ContextoRisco contexto) {
        if (contexto == null) throw new IllegalArgumentException("Contexto nulo.");
        this.contexto = contexto;
    }

    public String calcular() {
        return estrategia.calcular(contexto);
    }
}
