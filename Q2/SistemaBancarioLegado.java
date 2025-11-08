package com.exemplo.banco;

import java.util.HashMap;

/**
 * Simulação do sistema bancário legado.
 * Assinatura incompatível: usa HashMap<String,Object>.
 */
public class SistemaBancarioLegado {

    @SuppressWarnings("unchecked")
    public HashMap<String,Object> processarTransacao(HashMap<String,Object> parametros){
        // validações do legado (campos obrigatórios)
        if (!parametros.containsKey("CARTAO")
            || !parametros.containsKey("VALOR_CENTAVOS")
            || !parametros.containsKey("MOEDA_CODIGO")
            || !parametros.containsKey("ID_AGENCIA")
            || !parametros.containsKey("CANAL")){
            var erro = new HashMap<String,Object>();
            erro.put("STATUS", "ERRO");
            erro.put("COD_RETORNO", "99");
            erro.put("MENSAGEM", "Parâmetros obrigatórios ausentes.");
            return erro;
        }

        // regra dummy de aprovação: aprova até 100000 centavos (R$ 1.000,00)
        long valorCentavos = ((Number)parametros.get("VALOR_CENTAVOS")).longValue();
        boolean aprovado = valorCentavos <= 100_000L;

        var resp = new HashMap<String,Object>();
        resp.put("STATUS", aprovado ? "OK" : "NEGADO");
        resp.put("COD_RETORNO", aprovado ? "0" : "51"); // "0" = aprovado
        resp.put("COD_AUTORIZACAO", aprovado ? ("AUT-" + System.currentTimeMillis()) : null);
        resp.put("MOEDA_CODIGO", parametros.get("MOEDA_CODIGO"));
        resp.put("VALOR_CENTAVOS", valorCentavos);
        resp.put("MENSAGEM", aprovado ? "Autorizada no legado." : "Negada no legado.");
        return resp;
    }
}
