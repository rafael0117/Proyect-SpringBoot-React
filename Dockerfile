# Usa una imagen de Maven con OpenJDK 17
FROM maven:3.9.4-eclipse-temurin-17 as builder

WORKDIR /app

# Copia todo el proyecto
COPY . .

# Compila y empaqueta la app
RUN mvn clean package -DskipTests

# Usa una imagen ligera de Java para ejecutar
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia el JAR desde la etapa de compilación
COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto que usa Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
