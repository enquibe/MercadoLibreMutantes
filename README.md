# Mercado Libre Mutantes

Este proyecto es una API REST que permite verificar si un ADN es mutante o humano. Para ello, expone las rutas:

* `POST /mutant` - Analiza un ADN.

* `GET /stats` - Estadísticas de verificaciones de ADN.

## Cómo ejecutarlo

### Base de datos
Requiere una instancia de MongoDB. Para esto, puede: 
* [Instalar localmente](http://www.mongodb.org/downloads) 
* [Correr en Docker](https://hub.docker.com/_/mongo/)
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
Si entra en conflicto con otro servicio, puede cambiar el puerto modificando la propiedad server.port

## Modificar el código
Requiere:
* Java 11.
* IDE.