package com.exemplo.usina;

public interface EstadoUsina {
    String nome();
    default void entrar(ControleUsina ctx) {}
    default void sair(ControleUsina ctx) {}
    void avaliar(ControleUsina ctx, Leituras l);
}
