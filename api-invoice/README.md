# Invoices API

+ kotlin 1.2.71
+ MySQL

## MySQL

Depends On
```bash
docker run --name mysql_invoices -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=invoices -p 3306:3306 -d mysql
```

## Build API

```bash
cd api-invoice

./gradlew clean build
```

```bash
docker build -t andrelugomes/api-invoice .
```

## Release API

```bash
docker login --username=andrelugomes 
 
docker push andrelugomes/api-invoice:latest
```
## Run API standalone

```bash
docker run -d --name api-invoice --net host andrelugomes/api-invoice:latest
```

 http://localhost:8081/swagger-ui.html




