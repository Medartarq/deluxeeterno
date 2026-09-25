# Deluxe Eterno — Spring Boot

Aplicación web full-stack para el proyecto académico Deluxe Eterno. Esta carpeta contiene la evolución del prototipo estático hacia una aplicación renderizada en servidor.

## Stack

- Java 26
- Spring Boot 4.1.1
- Maven
- Spring Web MVC
- Thymeleaf
- Spring Data JPA/Hibernate
- MySQL
- Spring Security con autenticación por sesión y BCrypt
- Bootstrap 5 y Bootstrap Icons mediante CDN

## Estructura

```text
src/main/java/com/deluxeterno/
├── config/       Seguridad e inicialización
├── domain/       Entidades JPA y enums
├── dto/          Formularios validados
├── repository/   Repositorios Spring Data
├── service/      Lógica de catálogo y consultas
└── web/          Controladores públicos y administrativos

src/main/resources/
├── templates/    Vistas Thymeleaf
├── static/       CSS, JavaScript y assets
└── application.yml
```

## Requisitos

- JDK 26
- Maven 3.6.3 o superior
- MySQL 8 o superior para ejecutar la aplicación

El proyecto de pruebas usa H2 en memoria mediante el perfil `test`; no requiere MySQL para ejecutar `mvn test`.

## Configuración local

Crear la base de datos MySQL `deluxe_eterno` y configurar las variables antes de iniciar:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/deluxe_eterno?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="tu_password"
$env:ADMIN_USERNAME="admin"
$env:ADMIN_PASSWORD="una_password_local"
mvn spring-boot:run
```

La contraseña administrativa no se guarda en el repositorio. El usuario inicial solo se crea si `ADMIN_PASSWORD` está definida y la tabla de usuarios está vacía.

## Comandos

```powershell
mvn test
mvn clean verify
mvn spring-boot:run
```

La aplicación queda disponible en `http://localhost:8080`.

## Rutas iniciales

### Público

- `/`
- `/nosotros`
- `/productos`
- `/productos/{id}`
- `/servicios`
- `/galeria`
- `/contacto`
- `POST /contactos`

### Panel protegido

- `/login`
- `/admin`
- `/admin/products`
- `/admin/categories`
- `/admin/inquiries`

## Alcance actual

- Catálogo dinámico de categorías y productos.
- Detalle de producto.
- Consultas públicas validadas y persistidas.
- Panel administrativo protegido para productos, categorías y estados de consulta.
- Contenido público basado en información comercial confirmada; los registros de muestra quedan inactivos hasta ser reemplazados por offerings reales.

Las siguientes etapas del curso pueden agregar servicios, galería, canales de contacto administrables, API REST y JWT, sin modificar el prototipo APF1 preservado en `../deluxe_eterno_avance/`.
