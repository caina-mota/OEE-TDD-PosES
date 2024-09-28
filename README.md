# Projeto TDD para disciplina de Desenvolvimento Ágil

- Cainã dos Santos Mota
- Diego Francio
- João Jeefer da Silva Félix
- Renata Barros da Silva


## Projeto desenvolvido para cobrir principalmente as seguintes histórias:

### História: Cálculo do OEE em Processos de Manufatura
SENDO um gerente de produção em uma fábrica
POSSO calcular a eficiência geral do equipamento (OEE) com base em três fatores: Disponibilidade, Performance e Qualidade
POIS ASSIM consigo monitorar e otimizar a produtividade dos equipamentos de manufatura e identificar áreas que precisam de melhoria.



Cálculo do OEE introduzido na manufatura para monitorar a eficiência.

### Cenários

#### Disponibilidade:

Cenário 1: Cálculo de Disponibilidade com Tempos Planejado e de Setup Válidos
DADO QUE o tempo planejado é 3600 segundos
E o tempo de setup é 600 segundos
QUANDO solicito calcular a disponibilidade
ENTÃO o sistema calcula que a disponibilidade é 83,33%
E o tempo de produção é 3000 segundos

Cenário 2: Erro no Cálculo de Disponibilidade ao Configurar Tempo de Produção Incorreto
DADO QUE o tempo planejado é 3600 segundos
E o tempo de setup é 600 segundos
E a disponibilidade foi criada corretamente
QUANDO configuro o tempo de produção como 3200 segundos
ENTÃO o sistema lança uma exceção de métrica de disponibilidade

Cenário 3: Não Permitir Criação de Objeto Disponibilidade com Valores Negativos ou Inválidos
DADO QUE o tempo de setup é negativo
QUANDO tento criar uma nova disponibilidade
ENTÃO o sistema lança uma exceção de argumento inválido

DADO QUE o tempo planejado é negativo
QUANDO tento criar uma nova disponibilidade
ENTÃO o sistema lança uma exceção de argumento inválido

DADO QUE o tempo de setup é maior que o tempo planejado
QUANDO tento criar uma nova disponibilidade
ENTÃO o sistema lança uma exceção de argumento inválido

Cenário 4: Lançar Exceção Quando o Tempo Planejado for Zero
DADO QUE o tempo planejado é zero
E o tempo de setup é zero
QUANDO tento criar uma nova disponibilidade
ENTÃO o sistema lança uma exceção de argumento inválido

#### Performance:

Cenário 5: Cálculo de Performance com Tempos Teóricos e de Parada Válidos
DADO QUE o tempo teórico é de 3600 segundos
E o tempo de parada é de 600 segundos
QUANDO solicito calcular a performance
ENTÃO o sistema calcula que a performance é 83,33%
E o tempo real de produção é 3000 segundos

Cenário 6: Erro no Cálculo de Performance ao Configurar Tempo de Produção Incorreto
DADO QUE o tempo teórico é de 3600 segundos
E o tempo de parada é de 600 segundos
E a performance foi criada corretamente
QUANDO configuro o tempo real de produção como 3200 segundos
ENTÃO o sistema lança uma exceção de métrica de performance

Cenário 7: Não Permitir Criação de Objeto Performance com Valores Negativos ou Inválidos
DADO QUE o tempo de parada é negativo
QUANDO tento criar uma nova performance
ENTÃO o sistema lança uma exceção de argumento inválido

DADO QUE o tempo teórico é negativo
QUANDO tento criar uma nova performance
ENTÃO o sistema lança uma exceção de argumento inválido

DADO QUE o tempo de parada é maior que o tempo teórico
QUANDO tento criar uma nova performance
ENTÃO o sistema lança uma exceção de argumento inválido

Cenário 8: Lançar Exceção Quando o Tempo Teórico for Zero
DADO QUE o tempo teórico é zero
E o tempo de parada é zero
QUANDO tento criar uma nova performance
ENTÃO o sistema lança uma exceção de argumento inválido

#### Qualidade:

Cenário 9: Cálculo de Qualidade com Peças Produzidas e Rejeitadas Válidas
DADO QUE o número de peças produzidas é 1000
E o número de peças rejeitadas é 300
QUANDO solicito calcular a qualidade
ENTÃO o sistema calcula que a qualidade é 70%
E o número de peças boas é 700

Cenário 10: Lançar Exceção ao Configurar Peças Boas de Forma Incorreta
DADO QUE o número de peças produzidas é 1000
E o número de peças rejeitadas é 300
QUANDO configuro o número de peças boas como 300 (diferente de 1000 - 300)
ENTÃO o sistema lança uma exceção de métrica de qualidade

Cenário 11: Não Permitir Criação de Objeto Quality com Entradas Negativas ou Inválidas
DADO QUE o número de peças rejeitadas é negativo
QUANDO tento criar um novo objeto de qualidade
ENTÃO o sistema lança uma exceção de argumento inválido

DADO QUE o número de peças produzidas é negativo
QUANDO tento criar um novo objeto de qualidade
ENTÃO o sistema lança uma exceção de argumento inválido

DADO QUE o número de peças rejeitadas é maior que o número de peças produzidas
QUANDO tento criar um novo objeto de qualidade
ENTÃO o sistema lança uma exceção de argumento inválido

Cenário 12: Lançar Exceção Quando a Quantidade de Peças Produzidas for zero
DADO QUE o peças produzidas é zero
E a quantidade de rejeitos é zero
QUANDO tento criar uma nova qualidade
ENTÃO o sistema lança uma exceção de argumento inválido

#### OEE:

Cenário 13: Cálculo Correto do OEE
DADO QUE a disponibilidade é 0.8
E a qualidade é 0.9
E a performance é 0.875
QUANDO calculo o OEE
ENTÃO o OEE é 0.63
E o percentual de OEE é 63.0%

Cenário 14: Lançar Exceção ao Passar Parâmetro Nulo no Construtor
DADO QUE o objeto de qualidade é nulo
QUANDO crio um objeto Oee
ENTÃO o sistema lança uma exceção de argumento inválido

DADO QUE o objeto de performance é nulo
QUANDO crio um objeto Oee
ENTÃO o sistema lança uma exceção de argumento inválido

DADO QUE o objeto de disponibilidade é nulo
QUANDO crio um objeto Oee
ENTÃO o sistema lança uma exceção de argumento inválido

Cenário 15: Cálculo de OEE com Valores Zero
DADO QUE a disponibilidade é 0.0
E a qualidade é 0.0
E a performance é 0.0
QUANDO calculo o OEE
ENTÃO o OEE calculado é 0.0

Cenário 16: Recalcular OEE Após Reatribuição de Disponibilidade
DADO QUE o OEE foi calculado com disponibilidade inicial de 0.8
E a disponibilidade foi alterada para 0.888
QUANDO recalculo o OEE
ENTÃO o novo valor de OEE é recalculado corretamente com a nova disponibilidade





