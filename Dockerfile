FROM openjdk:17

ARG JAR_FILE=build/libs/docker-hello-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} docker-hello.jar

ENTRYPOINT ["java","-jar","/docker-hello.jar"]

EXPOSE 8080
