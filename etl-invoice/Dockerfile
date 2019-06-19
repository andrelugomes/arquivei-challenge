FROM openjdk:11.0.3

MAINTAINER andrelugomes@gmail.com

ADD build/libs/etl-invoice-*.jar /etl.jar

ENTRYPOINT exec java -jar etl.jar
