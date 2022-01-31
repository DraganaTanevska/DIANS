FROM openjdk:17
EXPOSE 9005
ADD target/parking-docker.jar parking-docker.jar
ENTRYPOINT ["java", "-jar", "/parking-docker.jar"]