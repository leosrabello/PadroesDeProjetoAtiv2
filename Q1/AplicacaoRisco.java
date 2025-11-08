package com.exemplo.risco;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AplicacaoRisco {
    public static void main(String[] args) {
        ContextoRisco contexto = new ContextoRisco(
                bd("10000000"), bd("0.12"), bd("0.99"), bd("0.05"), LocalDate.now()
        );

        FabricaAlgoritmosRisco fabrica = new FabricaPadraoAlgoritmosRisco();
        CalculadoraRisco calculadora = new CalculadoraRisco(fabrica.criar("VaR"), contexto);

        System.out.println(calculadora.calcular());
        calculadora.definirEstrategia(fabrica.criar("Perda"));
        System.out.println(calculadora.calcular());

        ContextoRisco estressado = new ContextoRisco(
                bd("10000000"), bd("0.20"), bd("0.99"), bd("0.10"), LocalDate.now()
        );
        calculadora.atualizarContexto(estressado);
        calculadora.definirEstrategia(fabrica.criar("Estresse"));
        System.out.println(calculadora.calcular());
    }

    private static BigDecimal bd(String s) {
        return new BigDecimal(s);
    }
}
