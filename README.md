# softdesign

  - Projeto de desafio tecnico da Softdesign para Sicredi

# Instruções para executar a aplicação

  - Foi utilizado o Intellij IDE 2020.3
  - Ter postgres instalado/configurado com o banco "softdesign" criado;
    Obs.: Foi utilizado uma instalação docker conformedescrita a baixo.
  - Fazer a configuração url, usuario e senha no arquivo "application-development.properties" ou "application-production.properties" 
    dependendo do profile escolhido no arquivo "application.properties";
    Obs.: 
        Para o ambiente de desenvolvimento o hibernate está configurado para criar e drop a cada inicialização, e caso seja
    configurado o ambiente de produção depois do banco criado, rodar o script "inicial.sql" que esta em resources/sql para criar
    as tabelas e as sequencias;
        O ambiente de desenvolvimento roda na porta 8081 e produção na 8080;
  - Ter RabbitMQ instalado/configurado com a fila "softdesign_teste" e "softdesign_teste2";
    Obs.: Foi utilizado uma instalação docker conforme descrito a baixo.
  - Ter o maven instalado/configurado e rodar os comandos na pasta do projeto baixado no git:
    a) "mvn clean install"
    b) "mvn spring-boot:run"
  - Para as chamadas da api foi utilizado o Insomnia, e em resources tem o arquivo "Insomnia" com os endpoints.
    Obs.: Ficar atento ao ambiente que está usando, por causa das portas, conforme descrito mais acima.
  
# Alguns detalhes da implementação

  - Foi utilizado o versionamento da api rest por path;
  - Ficou estruturado inicialmente conforme a baixo, a partir do pacote base "br.com.pedroyodasaito.softdesign":
    a) api.v1 -> a versão 1 da api, e detro dele os controllers e dto;
    b) config -> pacote de classes de configuração;
    c) entidade -> as entidades de persistencia;
    d) execption -> classes de exceção;
    e) integração -> a classes de integração, como o validador de cpf e um pacote com o dto;
    f) mensageria -> pacote que contem as classes de integração com o RabbitMQ;
    g) repository -> interfaces de persistencia;
    h) service -> interfaces e implementações que vão prover os serviços para os controllers;
  
# Banco de Dados

  - Foi utilizado a uma imagem postgres para docker "postgres:latest" (Obs.: Usei a instalação que já tinha configurado)
    
    Postgres
  
      -> https://imasters.com.br/banco-de-dados/postgresql-docker-executando-uma-instancia-e-o-pgadmin-4-partir-de-containers
  
      -> docker pull postgres
    
      -> docker network create --driver bridge postgres-network 
  
      -> docker run --name postgres --network=postgres-network -e "POSTGRES_PASSWORD=123456" -p 5432:5432 -v /home/pedro/Desenvolvimento/softdesign/banco:/var/lib/postgresql/data -d postgres
  
      PGADMIN
  
      -> docker pull dpage/pgadmin4
  
      -> docker run --name pgadmin --network=postgres-network -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=pedro.yoda@gmail.com" -e "PGADMIN_DEFAULT_PASSWORD=123456" -d dpage/pgadmin4
  
      -> Acessando a URL http://localhost:15432 aparecerá a tela para login no pgAdmin 4

# Fila RabbitMQ basica

  -> sudo mkdir -p /docker/rabbitmq/data

  -> VERSAO=3-management
     export VERSAO
     docker pull rabbitmq:$VERSAO
	
  -> docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 --restart=always --hostname rabbitmq-master -v /home/pedro/Desenvolvimento/docker/rabbitmq/data:/var/lib/rabbitmq rabbitmq:$VERSAO

  -> http://localhost:15672/ (usuario e senha padrão "guest")
