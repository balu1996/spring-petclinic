FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/spring-petclinic-2.7.3.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]