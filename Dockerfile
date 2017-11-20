FROM frolvlad/alpine-oraclejdk8:slim
ENV MONGO_URL mongodb://localhost/spring-microservices-docker-poc
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/spring-microservices-docker-poc-0.0.1-SNAPSHOT.jar /app/spring-microservices-docker-poc.jar
ENTRYPOINT ["java", "-jar", "/app/spring-microservices-docker-poc.jar"]