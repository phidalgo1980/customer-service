# customer-service
# Guía de Uso para la Aplicación Customer Service

Este documento proporciona instrucciones detalladas para construir, ejecutar y utilizar la aplicación **Customer Service**, incluyendo la generación de la imagen Docker y la interacción con los endpoints a través de Swagger UI.

## Construcción de la Aplicación

### 1. Requisitos Previos

- **Java 17** o superior.
- **Maven 3.5+** o **Gradle 7.5+**.
- **Docker** instalado en su sistema.

### 2. Clonar el Repositorio

Clone el repositorio de la aplicación desde GitHub:

```bash
git clone https://github.com/tu_usuario/customer-service.git
cd customer-service
```

### 3. Construir el Proyecto
Utilice Maven para compilar y empaquetar la aplicación:

```bash
./mvnw clean package
```

Este comando generará un archivo JAR en el directorio target/.

## Construcción y Ejecución de la Imagen Docker
### 1. Crear el Archivo Dockerfile
   En el directorio raíz del proyecto, cree un archivo llamado Dockerfile con el siguiente contenido:
```bash
# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/*.jar app.jar

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 2. Construir la Imagen Docker
   Ejecute el siguiente comando para construir la imagen Docker:

```bash
docker build -t customer-service:latest .
```

### 3. Ejecutar el Contenedor Docker
   Inicie un contenedor basado en la imagen creada:

```bash
docker run -p 8080:8080 customer-service:latest
```

La aplicación estará disponible en http://localhost:8080.


## Interacción con los Endpoints a través de Swagger UI
### 1. Acceder a Swagger UI
   Swagger UI está disponible en:

```bash
http://localhost:8080/swagger-ui/index.html
```

### 2. Endpoints Disponibles
   La aplicación expone los siguientes endpoints:

POST /login: Autenticación de usuarios.
GET /customers/metrics: Obtiene métricas sobre los clientes existentes, como el promedio de edad y la desviación estándar de las edades.

### 3. Uso del Endpoint de Autenticación
   Para interactuar con los endpoints protegidos, primero debe autenticarse utilizando el endpoint /login:

En Swagger UI, localice el endpoint POST /login.
Haga clic en "Try it out".
Proporcione las credenciales de usuario. Por defecto:
Usuario: user
Contraseña: password
Haga clic en "Execute".
Si la autenticación es exitosa, recibirá un token JWT.

### 4. Autorización para Endpoints Protegidos
   Para acceder a los endpoints protegidos:

Copie el token JWT recibido tras la autenticación.
En Swagger UI, haga clic en el botón "Authorize" ubicado en la parte superior derecha.
En el campo que aparece, ingrese Bearer seguido del token JWT copiado.
Haga clic en "Authorize".