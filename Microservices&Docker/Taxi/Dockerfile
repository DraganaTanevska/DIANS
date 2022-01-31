FROM openjdk:17
EXPOSE 9003
ADD target/taxi-docker.jar taxi-docker.jar
ENTRYPOINT ["java", "-jar", "/taxi-docker.jar"]