package com.exemplo.usina;

public final class EstadoEmergencia implements EstadoUsina {
    @Override public String nome() { return "EMERGENCIA"; }
    @Override public void avaliar(ControleUsina ctx, Leituras l) {}
}
