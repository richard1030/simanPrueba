# simanPrueba
Proyecto Java modulo de ventas


# Sistema de Ventas - Spring Boot + React + Docker

Este proyecto es un sistema desarrollado con:

- **Backend:** Spring Boot + PostgreSQL
- **Frontend:** React con PrimeReact y React Router
- **Base de datos:** PostgreSQL
- **Contenerización:** Docker + Docker Compose

Incluye gestión de productos, reporte clientes y ventas con autenticación por roles y estadísticas.

---

## 📁 Estructura del proyecto

SISTEMA_VENTAS/
├── backend/ # Proyecto Spring Boot (fuentes, sin /target)
├── frontend/ # Proyecto React (fuentes, sin /build)
├── docker-compose.yml

---

## ✅ Requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- Git (para clonar el repositorio)
- Java minima version 17
- Apache Maven (https://maven.apache.org/download.cgi)

---

## 🚀 Instrucciones para compilar y ejecutar

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/tu-repo.git
cd SISTEMA_VENTAS

2. Compilar manualmente (opcional si no usás docker-compose --build)
Backend (Spring Boot)

Compilar manualmente (opcional si no usás docker-compose --build)

cd backend
mvn clean install -DskipTests
cd ..

Frontend (React)

cd frontend
npm install
npm install react-router-dom
npm run build
cd ..

Si usás PrimeReact, asegurate también de tener:

npm install primereact primeicons primeflex

3. Ejecutar el proyecto con Docker

docker-compose up --build

Esto hará lo siguiente:

    Construye el backend con Maven

    Compila el frontend con npm run build

    Levanta contenedores de backend, frontend y base de datos PostgreSQL

Acceso a la aplicación

| Componente  | URL                                                          |
| ----------- | ------------------------------------------------------------ |
| Frontend    | [http://localhost:3000](http://localhost:3000)               |
| Backend API | [http://localhost:8080/api](http://localhost:8080/api)       |
| PostgreSQL  | localhost:5432 (usuario: `postgres`, contraseña: `rramos`) |


Comandos útiles

    Detener contenedores y eliminar volúmenes (incluye la base de datos):

docker-compose down --volumes

    Detener sin eliminar volúmenes:

docker-compose down

    evantar sin recompilar (si ya fue compilado antes):

docker-compose up

Variables de entorno

Por defecto, los valores están definidos en el docker-compose.yml, pero si deseas usar un archivo .env, podrías incluir:

SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/sistemaventas
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres

Notas

    El frontend se comunica con el backend en producción usando la URL interna http://backend:8080.

    Los artefactos compilados (/target, /build) no están versionados.

    Por el momento los script de base de datos de prueba cuenta con menos de 10 registros



