# ETL Invoices

+ java 11
+ MySQL


## MySQL

Dependes On

```bash
docker run --name mysql_invoices -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=invoices -p 3306:3306 -d mysql
```

## Build ETL

```bash
cd etl-invoice

./gradlew clean build
```

```bash
docker build -t andrelugomes/etl-invoice .
```

## Release ETL

```bash
docker login --username=andrelugomes 
 
docker push andrelugomes/etl-invoice:latest
```

## Run ETL standalone

```bash
docker run -it --net host andrelugomes/etl-invoice:latest
```




