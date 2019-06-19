FROM zenika/kotlin:1.2.71-jdk8-alpine

MAINTAINER andrelugomes@gmail.com

ADD build/libs/api-*.jar /api.jar

ENTRYPOINT exec java -jar api.jar

