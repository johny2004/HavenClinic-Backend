<h1 align="center">
  🏥 Clínica Veterinaria Haven
</h1>

<p align="center">
  <img src="https://github.com/QuinteroEP/HavenClinic/blob/main/documentacion/Logo.png" alt="Logo de Haven" width="300"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-9+-orange?style=flat-square&logo=java" alt="Java"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen?style=flat-square&logo=spring" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Maven-3.11-C71A36?style=flat-square&logo=apache-maven" alt="Maven"/>
  <img src="https://img.shields.io/badge/H2-Database-blue?style=flat-square&logo=h2" alt="H2"/>
  <img src="https://img.shields.io/badge/License-MIT-yellow?style=flat-square" alt="License"/>
</p>

<p align="center">
  Sistema de gestión integral para clínicas veterinarias desarrollado con Spring Boot, Thymeleaf y H2 Database.
</p>

---

## 📋 Tabla de Contenidos

- [Acerca del Proyecto](#-acerca-del-proyecto)
- [Características Principales](#-características-principales)
- [Tecnologías](#-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación y Configuración](#-instalación-y-configuración)
- [Uso de la Aplicación](#-uso-de-la-aplicación)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [API Endpoints](#-api-endpoints)
- [Base de Datos H2](#-base-de-datos-h2)
- [Pruebas](#-pruebas)
- [Contribuidores](#-contribuidores)
- [Licencia](#-licencia)

---

## 🎯 Acerca del Proyecto

**Haven Clinic** es un portal web completo para la gestión de una clínica veterinaria ficticia. El sistema permite a los dueños de mascotas registrar a sus animales para tratamiento y mantenerse actualizados sobre su estado de salud. Los veterinarios pueden acceder a información detallada de sus pacientes (raza, edad, razón de consulta, historial médico, tratamientos, etc.), mientras que los administradores tienen acceso total a las estadísticas y datos de la clínica.

### Objetivos del Proyecto

- Facilitar la gestión de pacientes veterinarios y sus historiales clínicos
- Proporcionar una interfaz intuitiva para clientes y veterinarios
- Mantener un registro completo de tratamientos y medicamentos
- Generar dashboards administrativos con estadísticas relevantes
- Implementar un sistema de autenticación y autorización seguro con Spring Security

---

## ✨ Características Principales

### Para Clientes 👥
- ✅ Registro y gestión de perfil de usuario
- 🐾 Registro y actualización de mascotas
- 📋 Visualización del historial médico de mascotas
- 📊 Consulta de tratamientos y medicamentos administrados
- 🔍 Búsqueda de información de mascotas

### Para Veterinarios 
- 📝 Gestión de pacientes asignados
- 💊 Registro de tratamientos y medicamentos
- 📈 Historial completo de consultas
- 🔐 Acceso seguro mediante autenticación
- 📑 Catálogo de medicamentos veterinarios

### Para Administradores 🔧
- 📊 Dashboard con estadísticas de la clínica
- 👥 Gestión de clientes y veterinarios
- 📈 Visualización de métricas clave (total de clientes, mascotas activas, tratamientos, etc.)
- 🗂️ Acceso completo a todos los registros
- 🔒 Control de accesos y permisos

---

## 🛠️ Tecnologías

### Backend
- **Java 9+** - Lenguaje principal
- **Spring Boot 3.3.2** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Spring Security** - Autenticación y autorización
- **H2 Database** - Base de datos embebida
- **Hibernate** - ORM
- **JWT (jjwt 0.11.5)** - Manejo de tokens

### Frontend
- **Thymeleaf** - Motor de plantillas
- **HTML5 & CSS3** - Estructura y estilos
- **JavaScript** - Lógica del cliente
- **Bootstrap/CSS Custom** - Diseño responsivo

### Testing & Quality
- **JUnit** - Pruebas unitarias
- **Selenium 4.11.0** - Pruebas E2E
- **WebDriverManager 5.4.1** - Gestión de drivers
- **Spring Boot Test** - Testing de integración

### Otros
- **Lombok** - Reducción de código boilerplate
- **MapStruct 1.5.5** - Mapeo de DTOs
- **SpringDoc OpenAPI 2.1.0** - Documentación de API
- **Maven** - Gestión de dependencias

---

## 📦 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- **Java JDK 9 o superior** - [Descargar](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** (opcional, el proyecto incluye Maven Wrapper)
- **Git** - [Descargar](https://git-scm.com/downloads)
- **Un navegador web moderno** (Chrome, Firefox, Edge)

---

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/QuinteroEP/HavenClinic.git
cd HavenClinic
```

### 2. Compilar el Proyecto

**Windows:**
```bash
.\mvnw.cmd clean install
```

**Linux/Mac:**
```bash
./mvnw clean install
```

### 3. Ejecutar la Aplicación

**Opción 1 - Con Maven Wrapper (Recomendado):**
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

**Opción 2 - Con Java:**
```bash
java -jar target\clinicahaven-0.0.1-SNAPSHOT.jar
```

### 4. Acceder a la Aplicación

Una vez iniciada la aplicación, abre tu navegador en:

- **Aplicación Principal:** http://localhost:4200/
- **Consola H2:** http://localhost:8090/h2
- **Documentación API (Swagger):** http://localhost:8090/swagger-ui.html

---

## 💻 Uso de la Aplicación

### Acceso según Roles

#### 👤 Cliente
1. Registrarse desde la página principal
2. Iniciar sesión con tus credenciales
3. Registrar mascotas desde el menú principal
4. Consultar información y tratamientos de tus mascotas

#### 👨‍⚕️ Veterinario
1. Iniciar sesión con credenciales de veterinario
2. Visualizar lista de pacientes asignados
3. Registrar tratamientos y medicamentos
4. Actualizar información de mascotas

#### 🔐 Administrador
1. Acceder con credenciales de administrador
2. Visualizar dashboard con estadísticas
3. Gestionar clientes y veterinarios
4. Consultar reportes generales

### Usuarios de Prueba

```
# Cliente
Usuario: a@c.m
Contraseña: abc

# Veterinario
Usuario: 	qwe@m.c
Contraseña: abc

# Administrador
Usuario: 	user
Contraseña: contra
```

## 📁 Estructura del Proyecto

```
HavenClinic/
├── src/
│   ├── main/
│   │   ├── java/puj/web/clinicahaven/
│   │   │   ├── controller/          # Controladores REST y MVC
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── entity/              # Entidades JPA
│   │   │   ├── repository/          # Repositorios de datos
│   │   │   ├── service/             # Lógica de negocio
│   │   │   ├── security/            # Configuración de seguridad
│   │   │   └── errorHandling/       # Manejo de excepciones
│   │   └── resources/
│   │       ├── application.properties  # Configuración
│   │       ├── static/              # CSS, JS, Imágenes
│   │       └── templates/           # Vistas Thymeleaf
│   └── test/                        # Pruebas unitarias y E2E
├── target/                          # Archivos compilados
├── pom.xml                          # Configuración Maven
└── README.md
```

---

## 🔌 API Endpoints

### Clientes
- `GET /clientes` - Listar todos los clientes
- `GET /clientes/{id}` - Obtener cliente por ID
- `POST /clientes` - Crear nuevo cliente
- `PUT /clientes/{id}` - Actualizar cliente
- `DELETE /clientes/{id}` - Eliminar cliente

### Mascotas
- `GET /mascotas` - Listar todas las mascotas
- `GET /mascotas/{id}` - Obtener mascota por ID
- `POST /mascotas` - Registrar nueva mascota
- `PUT /mascotas/{id}` - Actualizar mascota
- `DELETE /mascotas/{id}` - Eliminar mascota

### Veterinarios
- `GET /veterinarios` - Listar veterinarios
- `GET /veterinarios/{id}` - Obtener veterinario por ID
- `POST /veterinarios` - Crear veterinario
- `PUT /veterinarios/{id}` - Actualizar veterinario

### Tratamientos
- `GET /tratamientos` - Listar tratamientos
- `POST /tratamientos` - Registrar tratamiento
- `GET /tratamientos/mascota/{id}` - Tratamientos de una mascota

### Dashboard
- `GET /dashboard` - Obtener estadísticas generales

*(Para más detalles, consulta la documentación Swagger en `/swagger-ui.html`)*

---

## 🗄️ Base de Datos H2

### Acceder a la Consola H2

1. Inicia la aplicación
2. Abre http://localhost:8090/h2 en tu navegador
3. Configura la conexión:
   - **JDBC URL:** `jdbc:h2:file:./mydatabase`
   - **User Name:** `sa`
   - **Password:** *(dejar vacío)*
4. Click en **Connect**

### Configuración

El proyecto utiliza H2 como base de datos embebida en modo archivo. La configuración se encuentra en `application.properties`:

```properties
spring.datasource.url=jdbc:h2:file:./mydatabase
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2
spring.jpa.hibernate.ddl-auto=create-drop
```

### Tablas Principales

- `CLIENTE` - Información de clientes
- `MASCOTA` - Datos de mascotas
- `VETERINARIO` - Veterinarios de la clínica
- `TRATAMIENTO` - Registro de tratamientos
- `DROGA` - Catálogo de medicamentos
- `USER_ENTITY` - Usuarios del sistema
- `ADMIN` - Administradores

---

## 🧪 Pruebas

### Ejecutar Todas las Pruebas

```bash
.\mvnw.cmd test
```

### Ejecutar sin Pruebas

```bash
.\mvnw.cmd clean install -DskipTests
```

### Tipos de Pruebas

- **Unitarias:** Pruebas de servicios y repositorios
- **Integración:** Pruebas de controladores
- **E2E:** Pruebas con Selenium para flujos completos

---

## 👥 Contribuidores

<table>
  <tr>
    <td align="center">
      <b>Jonathan Jurado</b><br/>
      Desarrollador Full Stack
    </td>
    <td align="center">
      <b>Lucas Rivera</b><br/>
      Desarrollador Full Stack
    </td>
    <td align="center">
      <b>Pablo Quintero</b><br/>
      Desarrollador Full Stack
    </td>
  </tr>
</table>

### Institución

**Pontificia Universidad Javeriana** - Bogotá, Colombia  
**Facultad:** Ingeniería de Sistemas  
**Materia:** Desarrollo Web 2024  
**Profesor:** Juan Angarita Torres

---

## 📄 Licencia

Este proyecto fue desarrollado con fines académicos como parte del curso de Desarrollo Web de la Pontificia Universidad Javeriana.

---

## 🙏 Agradecimientos

- A la Universidad Javeriana por el apoyo académico
- Al profesor Juan Angarita Torres por la guía durante el desarrollo
- A la comunidad de Spring Boot por la excelente documentación

---

<p align="center">
  Hecho con ❤️ por el equipo de Haven Clinic
</p>

<p align="center">
  <sub>© 2024 - Proyecto Académico - Pontificia Universidad Javeriana</sub>
</p>
