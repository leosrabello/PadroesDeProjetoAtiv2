package com.exemplo.usina;

public final class EstadoOperacaoNormal implements EstadoUsina {
    @Override public String nome() { return "OPERACAO_NORMAL"; }
    @Override public void avaliar(ControleUsina ctx, Leituras l) {
        if (l.temperaturaC() > 300.0) ctx.trocarEstado(new EstadoAlertaAmarelo());
    }
}
