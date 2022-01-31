FROM openjdk:17
EXPOSE 9005
ADD target/rating-docker.jar rating-docker.jar
ENTRYPOINT ["java", "-jar", "/rating-docker.jar"]