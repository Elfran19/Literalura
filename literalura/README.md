# LiterAlura 📚

Proyecto desarrollado en Java con Spring Boot para consultar libros utilizando la API de Gutendex y almacenarlos en una base de datos PostgreSQL.

Este proyecto fue desarrollado como parte del Challenge LiterAlura del programa Oracle Next Education (ONE) en colaboración con Alura Latam.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- API Gutendex
- IntelliJ IDEA

---

## 📖 Funcionalidades

El sistema permite realizar las siguientes acciones desde un menú en consola:

1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos en un determinado año
5. Listar libros por idioma

Los datos de los libros se obtienen desde la API pública:

https://gutendex.com/

---

## 🗄 Base de datos

El proyecto utiliza **PostgreSQL** para almacenar:

- Libros
- Autores

Relación:

```
Autor 1 ---- * Libro
```

---

## ⚙️ Configuración del proyecto

1. Clonar el repositorio:

```
git clone https://github.com/TUUSUARIO/literalura.git
```

2. Crear la base de datos en PostgreSQL:

```
CREATE DATABASE literalura;
```

3. Configurar el archivo:

```
application.properties
```

Ejemplo:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4. Ejecutar el proyecto desde IntelliJ.

---

## ▶️ Ejecución

Al iniciar el programa aparecerá el siguiente menú:

```
===== LiterAlura =====

1 - Buscar libro por titulo
2 - Listar libros
3 - Listar autores
4 - Autores vivos en determinado año
5 - Libros por idioma

0 - Salir
```

---

## 🌐 API utilizada

Gutendex API

https://gutendex.com/

Esta API proporciona información de libros del proyecto Gutenberg.

---

## 👨‍💻 Autor

Proyecto desarrollado para el Challenge **LiterAlura** del programa:

Oracle Next Education (ONE) + Alura Latam