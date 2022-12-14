FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/spring-petclinic-2.7.3.jar  /usr/share
ENTRYPOINT ["java","-jar","/usr/share/spring-petclinic-2.7.3.jar"]