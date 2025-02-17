# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Establecer el autor de la imagen
LABEL maintainer="phidalgo@gmail.com"

# Añadir un argumento para el nombre del archivo JAR
ARG JAR_FILE=target/*.jar

# Copiar el archivo JAR de la aplicación al contenedor
COPY ${JAR_FILE} app.jar

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
