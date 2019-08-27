FROM openjdk:8-jre-alpine
EXPOSE 8080
COPY target/my-app.jar /
ENTRYPOINT ["java", "-jar", "/my-app.jar"]
