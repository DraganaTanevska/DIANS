FROM openjdk:17
EXPOSE 8761
ADD target/service-registry-docker.jar service-registry-docker.jar
ENTRYPOINT ["java", "-jar", "/service-registry-docker.jar"]