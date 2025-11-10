FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=preprod
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]