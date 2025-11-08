package com.exemplo.usina;

public final class EstadoAlertaAmarelo implements EstadoUsina {
    @Override public String nome() { return "ALERTA_AMARELO"; }
    @Override public void entrar(ControleUsina ctx) { ctx.atualizarContadorAcima400(true); }
    @Override public void avaliar(ControleUsina ctx, Leituras l) {
        if (l.temperaturaC() <= 300.0) {
            ctx.zerarContadorAcima400();
            ctx.trocarEstado(new EstadoOperacaoNormal());
            return;
        }
        ctx.atualizarContadorAcima400(l.temperaturaC() > 400.0);
        if (ctx.segundosConsecutivosAcima400(l.epochSegundos()) > 30)
            ctx.trocarEstado(new EstadoAlertaVermelho());
    }
}
