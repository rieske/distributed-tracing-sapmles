FROM openjdk:11-jdk-slim

ADD build/libs/*.jar /opt/service/service.jar

ENTRYPOINT java -jar /opt/service/service.jar
