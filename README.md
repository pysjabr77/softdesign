# softdesign

  - Projeto de desafio tecnico da Softdesign para Sicredi

# Instruções para executar a aplicação

  - Foi utilizado o Intellij IDE 2020.3
  - Ter postgres instalado/configurado com o banco "softdesign" criado;
    Obs.: Foi utilizado uma instalação docker conformedescrita a baixo.
  - Fazer a configuração url, usuario e senha no arquivo "application-development.properties" ou "application-production.properties" 
    dependendo do profile escolhido no arquivo "application.properties";
    Obs.: Para o ambiente de desenvolvimento o hibernate está configurado para criar e drop a cada inicialização.
  - Ter RabbitMQ instalado/configurado com a fila "softdesign_teste" e "softdesign_teste2";
    Obs.: Foi utilizado uma instalação docker conforme descrito a baixo.
    
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

# Software para fazer as chamadas para as implementações da api foi o Insomnia

  - Em resources tem o arquivo "Insomnia" com os endpoints 

# Fila RabbitMQ basica

  -> sudo mkdir -p /docker/rabbitmq/data

  -> VERSAO=3-management
     export VERSAO
     docker pull rabbitmq:$VERSAO
	
  -> docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 --restart=always --hostname rabbitmq-master -v /home/pedro/Desenvolvimento/docker/rabbitmq/data:/var/lib/rabbitmq rabbitmq:$VERSAO

  -> http://localhost:15672/ (usuario e senha padrão "guest")
