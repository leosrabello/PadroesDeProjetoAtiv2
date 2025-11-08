package com.exemplo.usina;

public final class ControleUsina {
    private EstadoUsina estadoAtual = new EstadoDesligada();
    private EstadoUsina estadoAntesManutencao = null;
    private long inicioAcima400 = -1;
    private long inicioAbaixoOuIgual400 = -1;

    public String estado() { return estadoAtual.nome(); }

    public void processar(Leituras l) {
        if (estadoAtual instanceof EstadoManutencao) {
            estadoAtual.avaliar(this, l);
            return;
        }
        estadoAtual.avaliar(this, l);
    }

    public void iniciar() {
        if (estadoAtual instanceof EstadoDesligada)
            trocarEstado(new EstadoOperacaoNormal());
    }

    public void resetEmergencia() {
        if (estadoAtual instanceof EstadoEmergencia)
            trocarEstado(new EstadoDesligada());
    }

    public void ativarManutencao() {
        if (!(estadoAtual instanceof EstadoManutencao)) {
            estadoAntesManutencao = estadoAtual;
            trocarEstado(new EstadoManutencao());
        }
    }

    public void desativarManutencao() {
        if (estadoAtual instanceof EstadoManutencao && estadoAntesManutencao != null) {
            trocarEstado(estadoAntesManutencao);
            estadoAntesManutencao = null;
        }
    }

    void trocarEstado(EstadoUsina proximo) {
        if (proximo == null || proximo.getClass().equals(estadoAtual.getClass())) return;
        estadoAtual.sair(this);
        estadoAtual = proximo;
        if (!(estadoAtual instanceof EstadoAlertaVermelho)) resetarJanelaAlivio();
        estadoAtual.entrar(this);
    }

    public void atualizarContadorAcima400(boolean acima400) {
        if (acima400) {
            if (inicioAcima400 < 0) inicioAcima400 = System.currentTimeMillis() / 1000;
        } else inicioAcima400 = -1;
    }

    public long segundosConsecutivosAcima400(long agora) {
        return (inicioAcima400 < 0) ? 0 : (agora - inicioAcima400);
    }

    public void zerarContadorAcima400() { inicioAcima400 = -1; }

    public void iniciarJanelaAlivio() { inicioAbaixoOuIgual400 = -1; }
    public void resetarJanelaAlivio() { inicioAbaixoOuIgual400 = -1; }
    public long contarAlivioAbaixo400(long agora) {
        if (inicioAbaixoOuIgual400 < 0) inicioAbaixoOuIgual400 = agora;
        return agora - inicioAbaixoOuIgual400;
    }
}
