# Dockerfile para HavenClinic Backend

# Etapa 1: Build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar archivos de configuración de Maven
COPY pom.xml .
COPY src ./src

# Construir la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el JAR construido desde la etapa anterior
COPY --from=build /app/target/clinicahaven-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8090

# Variables de entorno por defecto
ENV SPRING_PROFILES_ACTIVE=prod

# Comando de inicio
ENTRYPOINT ["java", "-Dserver.port=$PORT", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
