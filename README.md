# customer-service
# Guía de Uso para la Aplicación Customer Service

Este documento proporciona instrucciones detalladas para construir, ejecutar y utilizar la aplicación **Customer Service**, incluyendo la generación de la imagen Docker y la interacción con los endpoints a través de Swagger UI.

## Construcción de la Aplicación

### 1. Requisitos Previos

- **Java 17** o superior.
- **Maven 3.8.7**.
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

**`POST /auth/login`**: Autenticación de usuarios.

**`POST /customers`**: Genera un nuevo cliente con nombre, apellido, edad y fecha de nacimiento.

**`GET /customers`**: Obtiene la lista de clientes existentes, con los datos con los que se crea mas alguno que se calcula.

**`GET /customers/metrics`**: Obtiene métricas sobre los clientes existentes, como el promedio de edad y la desviación estándar de las edades.

### 3. Uso del Endpoint de Autenticación
   Para interactuar con los endpoints protegidos, primero debe autenticarse utilizando el endpoint /login:

En Swagger UI, localice el endpoint **`POST /login`**.
Haga clic en **`"Try it out"`**.
Proporcione las credenciales de usuario. Por defecto:
```bash
Usuario: user
Contraseña: password
```
Haga clic en **`"Execute"`**.
Si la autenticación es exitosa, recibirá un token JWT.

### 4. Autorización para Endpoints Protegidos
   Para acceder a los endpoints protegidos:

Copie el token JWT recibido tras la autenticación.
En Swagger UI, haga clic en el botón **`"Authorize"`** ubicado en la parte superior derecha.
En el campo que aparece, ingrese **`Bearer`** seguido del token JWT copiado.
Haga clic en **`"Authorize"`**.

## Consideraciones Generales

### 1. Uso de Lombok y Patrón Builder

Se integró la biblioteca Lombok para reducir el código boilerplate en las clases de datos. En particular, se utilizó la anotación @Builder para implementar el patrón de diseño Builder, facilitando la creación y configuración de objetos de manera más legible y concisa.

### 2. Implementación del Patrón Factory

Se adoptó el patrón de diseño Factory para centralizar la creación de instancias de las clases Customer y CustomerDTOResponse. Esto permite encapsular la lógica de instanciación y proporciona flexibilidad para futuras modificaciones en el proceso de creación de objetos.

### 3. Desarrollo de Pruebas Unitarias e Integración

Se desarrollaron dos tipos de pruebas para asegurar la calidad y funcionalidad del código:

- **Pruebas de Servicio**: Pruebas unitarias que validan la lógica de negocio en los servicios, asegurando que cada método funcione según lo esperado.

- **Pruebas de Integración**: Pruebas que verifican la correcta interacción entre los diferentes componentes de la aplicación, incluyendo la comunicación con la base de datos y la respuesta de los endpoints REST.

### 4. Internacionalización (Multiidioma)

Se implementó la internacionalización para soportar múltiples idiomas, específicamente inglés y español. Esto se logró mediante la creación de archivos de mensajes (messages.properties para inglés y messages_es.properties para español), permitiendo que la aplicación muestre mensajes y validaciones en el idioma correspondiente según la configuración regional del usuario.

### 5. Manejo Centralizado de Excepciones

Se estableció un manejo centralizado de excepciones para gestionar de manera uniforme los errores que puedan ocurrir durante la ejecución de la aplicación. Esto se implementó mediante un controlador global de excepciones que captura y maneja diferentes tipos de errores, proporcionando respuestas coherentes y códigos de estado HTTP adecuados.

### 6. Uso de Base de Datos H2 con Datos Precargados

Para facilitar el desarrollo y las pruebas, se optó por utilizar la base de datos en memoria H2. Se configuraron scripts de inicialización (schema.sql y data.sql) que crean las tablas necesarias y precargan datos de ejemplo. Esto permite probar los endpoints de la aplicación sin necesidad de configurar una base de datos externa.

Para una comprensión más profunda sobre algunos de estos temas, puedes consultar los siguientes recursos:

### 7. Trazabilidad

Para garantizar un seguimiento preciso de cada solicitud que maneja la aplicación, se implementó un mecanismo de trazabilidad mediante un request_id único. Este enfoque permite identificar y rastrear de manera efectiva cada interacción con los endpoints del servicio.

Los pasos clave de esta implementación son:

- **Filtro de Asignación de request_id**: Se creó un filtro que intercepta todas las solicitudes entrantes. Este filtro verifica si la solicitud contiene un encabezado X-Request-Id. Si no está presente, genera un UUID único que actúa como request_id. Este identificador se añade al contexto de diagnóstico mapeado (MDC) para su uso en los registros.

- **Inclusión del request_id en los Logs**: Se configuró el sistema de registro (Logback) para que cada entrada de log incluya el request_id correspondiente. Esto se logró modificando el patrón de registro en el archivo de configuración logback-spring.xml, asegurando que el request_id esté presente en todos los registros generados durante el ciclo de vida de una solicitud.

- **Acceso al request_id en los Controladores**: Dentro de los controladores, es posible acceder al request_id actual a través del MDC. Esto permite que, además de los registros, el request_id se incluya en las respuestas HTTP o se utilice en la lógica de negocio según sea necesario.

Esta implementación mejora significativamente la capacidad de monitoreo y depuración de la aplicación, facilitando la correlación de eventos y la identificación rápida de problemas relacionados con solicitudes específicas.

Ejemplo:
```bash
2025-02-17 21:00:32 [4fc01e20-fb60-41a7-a7f7-1725dc0361f2] INFO  c.c.c.controller.CustomerController - Getting customer metrics2025-02-17 21:00:32 [4fc01e20-fb60-41a7-a7f7-1725dc0361f2] INFO  c.c.c.controller.CustomerController - Getting customer metrics
```
