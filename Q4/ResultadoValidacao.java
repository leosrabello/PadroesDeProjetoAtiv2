package com.exemplo.nfe;

public record ResultadoValidacao(boolean sucesso, String mensagem) {
    public static ResultadoValidacao ok(String msg){ return new ResultadoValidacao(true, msg); }
    public static ResultadoValidacao falha(String msg){ return new ResultadoValidacao(false, msg); }
}
