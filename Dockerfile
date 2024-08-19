#docker build -t interview/interview-app .
#docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-docker .

FROM openjdk:22-jdk
ARG JAR_FILE="/build/libs/*.jar"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
