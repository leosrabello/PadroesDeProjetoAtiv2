package com.exemplo.risco;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class FabricaPadraoAlgoritmosRisco implements FabricaAlgoritmosRisco {
    private final Map<String, Supplier<AlgoritmoRisco>> registro = new HashMap<>();

    public FabricaPadraoAlgoritmosRisco() {
        registro.put("VaR", AlgoritmoVaR::new);
        registro.put("Perda", AlgoritmoPerdaEsperada::new);
        registro.put("Estresse", AlgoritmoTesteEstresse::new);
    }

    @Override
    public AlgoritmoRisco criar(String chave) {
        Supplier<AlgoritmoRisco> fornecedor = registro.get(chave);
        if (fornecedor == null) throw new IllegalArgumentException("Algoritmo '" + chave + "' não registrado.");
        return fornecedor.get();
    }

    public void registrar(String chave, Supplier<AlgoritmoRisco> fornecedor) {
        registro.put(chave, fornecedor);
    }
}
