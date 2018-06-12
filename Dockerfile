FROM openjdk:8-jre-alpine
MAINTAINER alireza.online
COPY ./target/Practice-1-1.0-SNAPSHOT.jar /myApplication/
COPY ./target/libs/ /myApplication/libs/
EXPOSE 8080
CMD ["java", "-jar", "./myApplication/Practice-1-1.0-SNAPSHOT.jar"]