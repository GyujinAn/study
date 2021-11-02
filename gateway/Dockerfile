FROM openjdk:8-jdk-alpine
LABEL maintainer="gyujinan <agj 017@gmail.com>"
RUN mkdir -p /app
ADD ./target/gateway-0.0.1-SNAPSHOT.jar /app
WORKDIR /app
EXPOSE 18082
ENTRYPOINT ["java","-jar","gateway-0.0.1-SNAPSHOT.jar"]
