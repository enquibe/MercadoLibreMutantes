# Mercado Libre Mutantes

Este proyecto es una API REST que permite verificar si un ADN es mutante o humano. Para ello, expone las siguientes rutas:

* `POST /mutant` - Analiza un ADN.

* `GET /stats` - Estadísticas de verificaciones de ADN.

Desde el navegador se puede acceder a la [interfaz de Swagger](https://springhelloworld.azurewebsites.net/swagger-ui/):
![Swagger](https://user-images.githubusercontent.com/15891071/117591868-b4719a80-b10c-11eb-872f-fc245f1d4f60.png)

También se provee una [colección de Postman](/MercadoLibreMutantsAPI.postman_collection.json), con requests y entornos precargados para facilitar las pruebas: 
![Postman](https://user-images.githubusercontent.com/15891071/117593033-28ae3d00-b111-11eb-9dca-a0d427baed9d.png)

## Cómo ejecutarlo

### Base de datos
Requiere una instancia de MongoDB. Para esto, puede: 
* [Instalarlo localmente](http://www.mongodb.org/downloads) 
* [Correrlo en Docker](https://hub.docker.com/_/mongo/)
* [Usar MongoDB Atlas](https://www.mongodb.com/cloud)

No es necesario definir el esquema. Se creará automáticamente al ejecutar la aplicación.

### API
Mutants es una aplicación desarrollada con [Spring Boot](https://spring.io/projects/spring-boot) y [Maven](https://maven.apache.org/).

Descargue el código:

```
git clone https://github.com/enquibe/MercadoLibreMutantes
cd MercadoLibreMutantes/mutants
```

Puede generar el jar y correrlo desde CLI: 

```
./mvnw clean package
java -jar target/*.jar
```

O ejecutarlo directamente desde Maven:

```
./mvnw spring-boot:run
```

En ambos casos, la aplicación estará disponible en http://localhost:8080. 

Si entra en conflicto con otro servicio, puede cambiar el puerto modificando la propiedad *server.port*

## Modificar el código
Requiere:
* Java 11.
* IDE.

## Despliegue

Se puede usar cualquier plataforma que provea un entorno con Java 11 y Java SE (o JBoss/Tomcat si se empaqueta como war).

Ejemplo en Azure, con grupo de recursos que incluye suscripción a MongoDB Atlas: 

![image](https://user-images.githubusercontent.com/15891071/117599490-48009680-b120-11eb-8c56-92e5cf5d6ea9.png)