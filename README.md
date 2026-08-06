# API REST - Gestión de Docentes

API REST desarrollada con **Spring Boot 4.1.0**, **Spring Data JPA** y **MySQL** para la gestión de un CRUD completo de la entidad Docente, como parte de la Primera Evaluación del curso Desarrollo de Aplicaciones Web I.

## Datos del curso

| Campo | Valor |
|---|---|
| Curso | 4694 - Desarrollo de Aplicaciones Web I |
| Profesor | Berrio Huamani Miguel Angel |
| Semestre | 2026-Julio |
| Ciclo | Quinto |
| Sección | T5C0 |
| Grupo | 01 |
| Alumno | Leonardo Dorregaray |
| Fecha | 05/08/2026 |

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA (Hibernate)
- MySQL 8
- Lombok
- Bean Validation (Jakarta Validation)
- Springdoc OpenAPI (Swagger UI)
- Maven

## Estructura del proyecto

src/main/java/com/cibertec/DAWI_T2/T2_Leonardo_Dorregaray
├── controllers → Controladores REST
├── entities → Entidades JPA
├── exceptions → Manejo global de excepciones
├── models → DTOs (Request / Response)
├── repositories → Repositorios JpaRepository
└── services
├── DocenteService.java → Interfaz del servicio
└── implementation
└── DocenteServiceImpl.java → Implementación (inyección por constructor)

## Configuración y ejecución

### 1. Requisitos previos
- JDK 21+
- MySQL 8 corriendo localmente
- Maven (o el wrapper `mvnw` incluido)

### 2. Configurar la base de datos

La aplicación crea la base de datos automáticamente si no existe. Opcionalmente, puedes crearla manualmente:

```sql
CREATE DATABASE IF NOT EXISTS db_docentes;
```

### 3. Configurar credenciales

Editar `src/main/resources/application.properties` según tu entorno local:

```properties
spring.datasource.username=root
spring.datasource.password=mysql
```

### 4. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

O directamente desde IntelliJ, ejecutando la clase `T2LeonardoDorregarayApplication`.

La aplicación levanta en: `http://localhost:8080/api/v1`

### 5. Documentación Swagger

Una vez levantada la app, la documentación interactiva está disponible en:

http://localhost:8080/api/v1/swagger-ui.html

El contrato OpenAPI usado como referencia se encuentra en [`docs/contrato-swagger.yaml`](./docs/contrato-swagger.yaml).

## Endpoints disponibles

| Método | Endpoint | Descripción | Código éxito |
|---|---|---|---|
| GET | `/docentes` | Listar todos los docentes | 200 |
| GET | `/docentes/{id}` | Obtener un docente por ID | 200 / 404 |
| GET | `/docentes/especialidad/{especialidad}` | Buscar docentes por especialidad (Query Method) | 200 |
| GET | `/docentes/apellidos/{apellidos}` | Buscar docentes por apellidos (Query Method) | 200 |
| POST | `/docentes` | Registrar un nuevo docente | 201 / 400 |
| PUT | `/docentes/{id}` | Actualizar un docente existente | 200 / 404 |
| DELETE | `/docentes/{id}` | Eliminar un docente | 204 / 404 |

## Entidad Docente

| Campo | Tipo |
|---|---|
| id | Long (autogenerado) |
| nombres | String |
| apellidos | String |
| correo | String |
| telefono | String |
| especialidad | String |
| fechaIngreso | LocalDate |

## Validaciones (Bean Validation)

- `nombres`: obligatorio, entre 2 y 100 caracteres
- `apellidos`: obligatorio, entre 2 y 100 caracteres
- `correo`: obligatorio, formato de email válido
- `especialidad`: obligatorio

## Buenas prácticas aplicadas

- Inyección de dependencias por **constructor** (sin field injection, sin `@Autowired`)
- Uso de `@Builder` (Lombok) en clases con más de 3 atributos
- Separación de capas: entity / repository / service / controller / model
- Manejo centralizado de excepciones con `@RestControllerAdvice`
- Búsquedas implementadas con **Query Methods** de Spring Data JPA

## Autor

Leonardo Fabricio Dorregaray Guevara - Cibertec
