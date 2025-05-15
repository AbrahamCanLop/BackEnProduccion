# Etapa de compilación
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos solo el pom.xml y descargamos dependencias (mejor uso de caché)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copiamos el resto del código
COPY src ./src

# Compilamos el proyecto
RUN mvn clean package -DskipTests

# Etapa final: imagen ligera para ejecución
FROM eclipse-temurin:21-jdk-alpine

# Variables de entorno seguras
ENV TZ=UTC \
    LANG=en_US.UTF-8 \
    JAVA_OPTS=""

WORKDIR /app

# Copiamos el jar desde la imagen de build
COPY --from=build /app/target/back-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=5s --start-period=120s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
# Comando de inicio, con opción a pasar flags adicionales desde el entorno
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]