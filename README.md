# Questão 1 — Strategy
**Decisão de design:** Foi usado o padrão Strategy para permitir trocar os algoritmos de cálculo de risco (VaR, Perda Esperada e Teste de Estresse) em tempo de execução. Cada algoritmo implementa a interface `AlgoritmoRisco`, e o cliente (`CalculadoraRisco`) escolhe qual estratégia usar sem conhecer sua implementação.

**Justificativa:** O padrão Strategy resolve o problema de ter vários algoritmos intercambiáveis, mantendo o código flexível e sem duplicação.

# Questão 2 — Adapter
**Decisão de design:** Foi usado o padrão Adapter para integrar a interface moderna (`ProcessadorTransacoes`) com o sistema bancário legado (`processarTransacao(HashMap)`), que usa outro formato. A classe `AdaptadorProcessadorTransacoes` converte chamadas e respostas entre os dois formatos.

**Justificativa:** O padrão Adapter foi escolhido porque adapta duas interfaces incompatíveis sem precisar alterar o código legado.
