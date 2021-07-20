# softdesign
- Projeto de desafio tecnico da Softdesign para Sicredi

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

  -> Em resources tem o arquivo "Insomnia" com os endpoints 
