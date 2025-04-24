FROM openjdk:17-jdk-slim-buster
VOLUME /tmp
EXPOSE 8080
ADD ./build/libs/ese-msa-account-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java" , "-jar", "/app.jar"]