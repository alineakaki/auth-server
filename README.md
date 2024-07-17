## Auth-Server

### Introdução


Esse repositório tem como objetivo expor dois endpoints, onde: </br></br>
**v1/jwt**: Endpoint método POST que retorna um token JWT. </br>
Parâmetros de entrada: clientId e chave privada em formato String enviados no Body.</br>
**v1/assets**: Endpoint método GET que retorna uma lista de criptomoedas, onde é necessário passar no header o parâmetro Authorization com o token recuperado no endpoint anterior com o prefixo "Bearer ".  </br>
O token JWT é validado com uma chave pública, previamente carregada no arquivo de configuração.</br></br>
Para recuperar as informações de criptomoedas é ativado um CircuitBreaker caso a API AssetsCap falhar. Em caso de fallback, é realizado uma chamada para a API CoinLore para buscar os dados de uma fonte alternativa.</br>
```failureRateThreshold=50``` Ativa o disjuntor se 50% das requisições falharem.</br>
```waitDurationInOpenState=30s```Mantém o disjuntor aberto por 30 segundos antes de tentar chamar a API novamente.</br>
```permittedNumberOfCallsInHalfOpenState=3``` Permite 3 chamadas enquanto o disjuntor estiver no estado "semiaberto" para testar se a API se recuperou.</br>

### Pré-requisitos

* Java JDK 17
* Maven 3.9.*
* IntelliJ IDEA Community
* OpenSSL

### Instalação local

Para rodar o projeto localmente é necessário gerar um par de chaves para realizar a assinatura do token, bem como sua validação. 
1. Abra o terminal na pasta que deseja criar a chave privada e execute o comando abaixo informando como parâmetro o nome da chave, algoritmo RSA e o tamanho em bits da chave a ser criada:</br>
```openssl genpkey -out private-key.pem -algorithm RSA -pkeyopt rsa_keygen_bits:2048``` </br>
2. A saída será o arquivo criado com a nomenclatura private-key.pem definida no item acima. </br>
3. Para gerar a chave pública, abra o terminal na pasta que foi criada a chave privada e execute o comando abaixo para gerar a chave pública informando o nome da chave que deseja criar e a nomenclatura da chave
   privada criada no item anterior. </br>
   ```openssl rsa -in private-key.pem -pubout -out public-key.pem```

#### Variáveis de ambiente necessárias

| Variável                                         | Tipo | Descrição                          | 
|:-------------------------------------------------| :--- |:-----------------------------------|
| `PUBLIC_KEY`                           | `string` | chave pública gerada no item acima |
