FROM eclipse-temurin:17.0.12_7-jre-jammy
WORKDIR /opt
COPY target/*.jar /opt/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]