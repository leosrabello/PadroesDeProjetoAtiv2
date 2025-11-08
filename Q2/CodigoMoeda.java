package com.exemplo.banco;

import java.util.Locale;

/** Mapeamento exigido pelo legado: USD=1, EUR=2, BRL=3. */
public enum CodigoMoeda {
    USD(1), EUR(2), BRL(3);

    public final int codigo;
    CodigoMoeda(int codigo){ this.codigo = codigo; }

    public static CodigoMoeda deString(String s){
        if(s == null) throw new IllegalArgumentException("Moeda nula");
        return valueOf(s.trim().toUpperCase(Locale.ROOT));
    }

    public static String paraString(int codigo){
        for (CodigoMoeda m: values()){
            if (m.codigo == codigo) return m.name();
        }
        throw new IllegalArgumentException("Código de moeda inválido: " + codigo);
    }
}
