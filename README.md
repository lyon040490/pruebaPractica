# Microservicio de Pagos

Este microservicio permite **crear pagos, consultar su estatus y actualizarlo**, además de publicar eventos a RabbitMQ cuando cambia el estatus.  

Tecnologías principales:

- Java 17 + Spring Boot  
- MongoDB  
- RabbitMQ  
- Docker / Docker Compose  
- OpenAPI / Swagger UI  

---

## 📦 Requisitos

- Docker Compose v2  
- Maven (si quieres compilar localmente)  

---

## 🚀 Levantar el servicio con Docker

En la raíz del proyecto:

```bash
docker compose up --build
