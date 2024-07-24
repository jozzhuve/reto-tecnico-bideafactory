FROM openjdk:17-jdk-slim-buster

COPY **/*.jar /opt/app.jar

ENTRYPOINT ["java","-jar","/opt/app.jar"]
