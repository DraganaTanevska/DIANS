FROM openjdk:17
EXPOSE 9005
ADD target/cloud-docker.jar cloud-docker.jar
ENTRYPOINT ["java", "-jar", "/cloud-docker.jar"]