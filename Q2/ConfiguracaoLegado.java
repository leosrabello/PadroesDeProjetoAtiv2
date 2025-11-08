package com.exemplo.banco;

/**
 * Campos obrigatórios exigidos pelo legado que não existem na interface moderna.
 * Ex.: ID_AGENCIA e CANAL.
 */
public record ConfiguracaoLegado(
        String idAgencia,
        String canal
){
    public ConfiguracaoLegado{
        if(idAgencia == null || idAgencia.isBlank())
            throw new IllegalArgumentException("idAgencia é obrigatório para o legado.");
        if(canal == null || canal.isBlank())
            throw new IllegalArgumentException("canal é obrigatório para o legado.");
    }
}
