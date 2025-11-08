package com.exemplo.usina;

public final class EstadoDesligada implements EstadoUsina {
    @Override public String nome() { return "DESLIGADA"; }
    @Override public void avaliar(ControleUsina ctx, Leituras l) {}
}
