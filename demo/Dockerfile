FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/redsocial-0.0.1.jar
COPY ${JAR_FILE} redsocial.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","redsocial.jar"]
