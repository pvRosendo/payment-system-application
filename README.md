# Payment System Application

## Desafio Back-end PicPay


![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)




Este projeto é uma API de sistema de pagamentos construída, principalmente, usando **Java, Java Spring e PostgreSQL**.

A API foi desenvolvida com o objetivo de aplicar os estudos e demonstrar como resolver o [Desafio Back-end PicPay](https://github.com/PicPay/picpay-desafio-backend). Após atender a todos os requisitos fui melhorando e aplicando novas funcionalidades (como transação planejada através de Scheduleds functions) para aprimorar e visualizar os conhecimentos que obtive através dos estudos.


## Table of Contents

- [Instalação](#instalação)
- [Uso](#uso)
- [Endpoints da API](#endpoints-da-api)

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/pvRosendo/payment-system-application.git
```

2. Instale as dependências e faça o build do projeto com Maven

```
$ ./mvnw clean package
```
3. Crie uma configuração com as variáveis de ambiente de tempo de execução usadas no `application.properties`

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```
4. Execute a aplicação:

```
$ java -jar /transferSystem/target/transferSystem-0.0.1-SNAPSHOT.jar
```

## Uso

1. Start a aplicação com Maven;
2. A API vai estar acessível no host pré-determinado;

## Endpoints da API
A API possui endpoints para usuários, transferências e transferências programadas.

### Users

| rota               | descrição                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /internalSystem/users</kbd>      |  Retorna todos os usuários (issue: sem paginação)
| <kbd>GET /internalSystem/users/{id}</kbd>      |  Retorna um único usuário
| <kbd>POST /internalSystem/users</kbd>     |  Cria um novo usuário
| <kbd>PUT /internalSystem/users/{id}</kbd>      |   Atualiza informações de um usuário
| <kbd>PUT /internalSystem/users/deposits/{id}</kbd> |   Deposita um valor específico para um usuário
| <kbd>DELETE /internalSystem/users/{id}</kbd>   |  Deleta um usuário

### Transactions

| rota               | descrição                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /internalSystem/transactions</kbd>      |  Retorna todas as transações (issue: sem paginação)
| <kbd>GET /internalSystem/transactions/{id}</kbd>      |  Retorna detalhes de uma transação
| <kbd>POST /internalSystem/transactions</kbd>     |  Cria uma nova transação


### Scheduled transfers

| rota               | descrição                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /internalSystem/scheduledTransactions</kbd>      |  Retorna todas as transações planejadas (issue: sem paginação)
| <kbd>GET /internalSystem/scheduledTransactions/{time}</kbd>      |  Retorna detalhes de uma transação planejada pelo horário dela
| <kbd>POST /internalSystem/scheduledTransactions</kbd>     |  Cria uma nova transação planejada
