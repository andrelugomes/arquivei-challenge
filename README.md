# Challenge

## Requirements

```text
$ docker version

Client:
 Version:  18.06.0-ce

$ docker-compose version
docker-compose version 1.14.0
```

## Running

```bash
$ docker-compose up
```

Be sure that ETL runned without errors. See the logs...

```
etl_1 | 2019-06-21 INFO 1 --- [main] b.c.arquivei.etl.invoice.domain.etl.ETL  : Starting ETL.

...

etl_1 | 2019-06-21 INFO 1 --- [main] b.c.arquivei.etl.invoice.domain.etl.ETL  : Finished ETL..

```

## Access API to get invoices

+ http://localhost:8081/swagger-ui.html

### Explanation

There is an ETL to extract invoices from Arquivei API, transforming and parsing to a Java Object to discovery Value and Access Key from invoices then store into a MySQL data base.

API invoice running on 8081 and it is capable to read Invoice table and expose data to http client. The API can search all invoices paging by custom page number and custon size (/invoices?page=0&size=10), or search by an especific access key (/invoices/{access_key}) 

I choose using the same data base for both projects because I think that ETL would be a scheduled job to search for new invoices periodically.

Thinking about scale and volume, after each ETL process, data maybe indexed using some hash table in-memory database like Redis, and the API would search cached data instead  MySQL. 

## Links
+ https://www.base64decode.org/
+ https://www.freeformatter.com/xml-formatter.html
+ https://docs.arquivei.com.br/?urls.primaryName=Arquivei%20API#/

+ https://www.stitchdata.com/etldatabase/etl-extract/
+ https://docs.oracle.com/cd/B19306_01/server.102/b14223/ettover.htm

+ https://cloud.docker.com/repository/docker/andrelugomes/etl-invoice
+ https://cloud.docker.com/repository/docker/andrelugomes/api-invoice
