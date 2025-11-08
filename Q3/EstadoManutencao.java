package com.exemplo.usina;

public final class EstadoManutencao implements EstadoUsina {
    @Override public String nome() { return "MANUTENCAO"; }
    @Override public void avaliar(ControleUsina ctx, Leituras l) {}
}
