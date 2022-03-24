FROM adoptopenjdk/openjdk11:alpine
MAINTAINER janpodrouzek.cz
ENV APP_HOME=/usr/app
ENV SPRING_PROFILES_ACTIVE=docker
WORKDIR $APP_HOME
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
