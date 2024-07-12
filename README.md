### Auth-Server
Esse repositório tem como objetivo expor dois endpoints, onde: </br></br>
**v1/jwt**: Endpoint método GET que retorna um token JWT assinado com uma chave privada previamente carregadada no arquivo de configurações da aplicação.</br>
**v1/assets**: Endpoint método GET que retorna uma lista de criptomoedas, onde é necessário passar no header o parâmetro Authorization com o token recuperado no endpoint anterior com o prefixo "Bearer ".  </br>
O token JWT é validado com uma chave pública, também previamente carregada no arquivo de configuração.</br></br>
Para recuperar as informações de criptomoedas é ativado um CircuitBreaker caso a API AssetsCap falhar. Em caso de fallback, é realizado uma chamada para a API CoinLore para buscar os dados de uma fonte alternativa.</br>
```failureRateThreshold(50):``` Ativa o disjuntor se 50% das requisições falharem.</br>
```waitDurationInOpenState(Duration.ofSeconds(30)):```Mantém o disjuntor aberto por 30 segundos antes de tentar novamente.</br>
```permittedNumberOfCallsInHalfOpenState(3):``` Permite 3 chamadas enquanto o disjuntor estiver no estado "semiaberto" para testar se a API se recuperou.</br>
