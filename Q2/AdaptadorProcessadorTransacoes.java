package com.exemplo.banco;

import java.util.HashMap;

/**
 * ADAPTER: expõe a interface moderna e traduz para a interface legada.
 * Também converte a resposta do legado para o formato moderno (bidirecional).
 */
public class AdaptadorProcessadorTransacoes implements ProcessadorTransacoes {

    private final SistemaBancarioLegado legado;
    private final ConfiguracaoLegado configuracao;

    public AdaptadorProcessadorTransacoes(SistemaBancarioLegado legado, ConfiguracaoLegado configuracao){
        this.legado = legado;
        this.configuracao = configuracao;
    }

    // ===== Moderna -> Legado =====
    @Override
    public RespostaAutorizacao autorizar(String cartao, double valor, String moeda) {
        HashMap<String,Object> reqLegado = paraLegado(cartao, valor, moeda);
        HashMap<String,Object> respLegado = legado.processarTransacao(reqLegado);
        return deLegado(respLegado);
    }

    /** Converte a requisição moderna para o mapa exigido pelo legado. */
    public HashMap<String,Object> paraLegado(String cartao, double valor, String moeda){
        var mapa = new HashMap<String,Object>();
        mapa.put("CARTAO", cartao);
        long centavos = Math.round(valor * 100.0);
        mapa.put("VALOR_CENTAVOS", centavos);
        mapa.put("MOEDA_CODIGO", CodigoMoeda.deString(moeda).codigo);
        // Campos obrigatórios do legado (não existem na interface moderna)
        mapa.put("ID_AGENCIA", configuracao.idAgencia());
        mapa.put("CANAL", configuracao.canal());
        return mapa;
    }

    // ===== Legado -> Moderna =====
    /** Converte a resposta do legado para o DTO moderno. */
    public RespostaAutorizacao deLegado(HashMap<String,Object> resposta){
        String status = (String) resposta.getOrDefault("STATUS", "ERRO");
        String codRet = (String) resposta.getOrDefault("COD_RETORNO", "99");
        boolean aprovado = "OK".equalsIgnoreCase(status) && "0".equals(codRet);

        String codAut = (String) resposta.get("COD_AUTORIZACAO");
        long valorCent = ((Number)resposta.getOrDefault("VALOR_CENTAVOS", 0L)).longValue();
        int moedaCodigo = ((Number)resposta.getOrDefault("MOEDA_CODIGO", CodigoMoeda.BRL.codigo)).intValue();
        String moeda = CodigoMoeda.paraString(moedaCodigo);
        String mensagem = (String) resposta.getOrDefault("MENSAGEM", "");

        double valor = valorCent / 100.0;
        return new RespostaAutorizacao(aprovado, codAut, moeda, valor, mensagem);
    }
}
