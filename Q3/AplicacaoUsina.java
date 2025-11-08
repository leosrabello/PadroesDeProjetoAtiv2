package com.exemplo.usina;

public class AplicacaoUsina {
    public static void main(String[] args) {
        ControleUsina usina = new ControleUsina();
        System.out.println("Estado inicial: " + usina.estado());
        usina.iniciar();
        System.out.println("Após iniciar: " + usina.estado());

        long t = System.currentTimeMillis() / 1000;
        usina.processar(new Leituras(320, 100, 0.1, false, t));
        System.out.println("Tick1: " + usina.estado());

        usina.processar(new Leituras(410, 100, 0.1, false, t + 10));
        usina.processar(new Leituras(415, 100, 0.1, false, t + 31));
        System.out.println("Tick2: " + usina.estado());

        usina.processar(new Leituras(405, 100, 0.1, true, t + 35));
        System.out.println("Tick3: " + usina.estado());

        usina.resetEmergencia();
        System.out.println("Após reset: " + usina.estado());

        usina.iniciar();
        usina.ativarManutencao();
        System.out.println("Em manutenção: " + usina.estado());
        usina.desativarManutencao();
        System.out.println("Após manutenção: " + usina.estado());
    }
}
