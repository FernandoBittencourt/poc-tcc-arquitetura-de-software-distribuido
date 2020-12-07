# poc-tcc-arquitetura-de-software-distribuido
Prova de conceito desenvolvida para validar o projeto arquitetural proposto no TCC de Arquitetura de software Distribuido.

##Requisitos para execução

* Java
* Docker
* AWS CLI

##Execução
A seguir seguem os passos para execução do sistema no ambiente local

###Preparação do ambiente

* Será necessario executar o docker-compose na raiz do projeto que contém o localstack para simular a execução na AWS.
> docker-compose up
* Será necessario executar os comandos abaixo para que o AWS esteja apontando corretamente para o localstack.
> aws configure
  AWS Access Key ID [None]: ACCESSKEYAWSUSER
  AWS Secret Access Key [None]: sEcreTKey
  Default region name [None]: us-west-2
  Default output format [None]: json
* Será necessario criar o bucket no ambiente do localstack
> aws --endpoint-url=http://localhost:4566 s3api create-bucket --bucket relatorios-bucket --region us-west-2
> aws --endpoint-url=http://localhost:4566 s3api put-bucket-acl --bucket relatorios-bucket --acl public-read
* Verifique se o bucket foi criado corretamente
> aws --endpoint-url=http://localhost:4566 s3api list-buckets


