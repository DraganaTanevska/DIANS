FROM openjdk:17
EXPOSE 9002
ADD target/place-docker.jar place-docker.jar
ENTRYPOINT ["java", "-jar", "/place-docker.jar"]