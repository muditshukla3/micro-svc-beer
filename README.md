# micro-svc-beer

## JMS Config
This application depends on JMS. Run the following command to run vromero/activemq-artemis as docker container with defaults

`docker run --name activemq -it --rm -p 8161:8161 -p 61616:61616 vromero/activemq-artemis`

## MySQL Config
Run mysql in docker container using following command. Execute SQL statements mentioned in mysql_init.sql after bash into mysql container.

`docker run --name mysql-container -p 33306:3306 -v /Users/muditshukla/dockerdata/mysqldata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:latest`

## Inventory Service
This service depends on inventory service(another micro service). Make sure it's also running in another container.