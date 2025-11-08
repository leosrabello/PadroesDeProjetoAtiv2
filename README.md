# Questão 1 — Strategy
**Decisão de design:** Foi usado o padrão Strategy para permitir trocar os algoritmos de cálculo de risco (VaR, Perda Esperada e Teste de Estresse) em tempo de execução. Cada algoritmo implementa a interface `AlgoritmoRisco`, e o cliente (`CalculadoraRisco`) escolhe qual estratégia usar sem conhecer sua implementação.

**Justificativa:** O padrão Strategy resolve o problema de ter vários algoritmos intercambiáveis, mantendo o código flexível e sem duplicação.

# Questão 2 — Adapter
**Decisão de design:** Foi usado o padrão Adapter para integrar a interface moderna (`ProcessadorTransacoes`) com o sistema bancário legado (`processarTransacao(HashMap)`), que usa outro formato. A classe `AdaptadorProcessadorTransacoes` converte chamadas e respostas entre os dois formatos.

**Justificativa:** O padrão Adapter foi escolhido porque adapta duas interfaces incompatíveis sem precisar alterar o código legado.

# Questão 3 — State

## Decisão de design
Foi utilizado o padrão **State** para modelar os diferentes estados operacionais de uma usina (Desligada, Operação Normal, Alerta Amarelo, Alerta Vermelho, Emergência e Manutenção).  
Cada estado possui sua própria lógica e define quando e para qual próximo estado deve transitar, com base nas leituras de sensores (temperatura, falha de resfriamento e tempo).

O controle principal (`ControleUsina`) atua como o **contexto**, delegando o comportamento para o estado atual e controlando as transições, garantindo que regras e limites sejam respeitados.

## Justificativa
O padrão **State** foi escolhido porque o comportamento da usina **muda conforme o estado interno**, e essas variações exigem **isolamento da lógica de cada estado**.  
Além disso, o padrão evita o uso de estruturas complexas de decisão (`if`/`switch`) e facilita a manutenção, inclusão de novos estados e implementação de regras de transição controladas.

# Questão 4 — Chain of Responsibility

## Decisão de design
Foi utilizado o padrão **Chain of Responsibility** para processar múltiplas validações de um documento fiscal eletrônico (NF-e) de forma encadeada.  
Cada validador executa uma verificação específica (Schema XML, Certificado, Regras Fiscais, Banco de Dados e SEFAZ).  
A cadeia implementa:
- **Validações condicionais** (alguns validadores só executam se os anteriores passarem);  
- **Circuit breaker**, que interrompe a execução após três falhas consecutivas;  
- **Rollback automático** para validadores que modificam o documento (como o banco de dados);  
- **Timeout individual** em cada validador para evitar bloqueios.

## Justificativa
O padrão **Chain of Responsibility** foi escolhido porque permite **encadear e organizar múltiplas validações independentes**, respeitando a ordem e as dependências entre elas.  
Ele garante **flexibilidade e baixo acoplamento** entre os validadores, além de facilitar a manutenção, a adição de novas regras e o controle de fluxo dinâmico exigido pelo sistema.
