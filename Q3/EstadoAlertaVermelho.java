package com.exemplo.usina;

public final class EstadoAlertaVermelho implements EstadoUsina {
    @Override public String nome() { return "ALERTA_VERMELHO"; }
    @Override public void avaliar(ControleUsina ctx, Leituras l) {
        if (l.falhaResfriamento()) {
            ctx.trocarEstado(new EstadoEmergencia());
            return;
        }
        if (!l.falhaResfriamento() && l.temperaturaC() <= 400.0) {
            if (ctx.contarAlivioAbaixo400(l.epochSegundos()) >= 30)
                ctx.trocarEstado(new EstadoAlertaAmarelo());
        } else ctx.resetarJanelaAlivio();
    }
    @Override public void entrar(ControleUsina ctx) { ctx.iniciarJanelaAlivio(); }
}
