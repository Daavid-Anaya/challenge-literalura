<h1 align="center"> Challenge Literalura - Oracle Next Education (ONE)</h1>

<p align="center">
  <img src="https://img.shields.io/badge/STATUS-FINALIZADO-blue">
</p>

Con este desafío propuesto en la Formación de Java y Spring Boot, creando una aplicación con conexión a una base de datos relacional. Se busca poner en práctica conceptos avanzados de Java y Spring, como el consumo de APIs externas, Java Persistence API, colecciones, streams, la persistencia de datos, entre otros.

---
## 💡 Sobre el Desafío 💡


---
## ✨ Características
- 📖 Buscar libro por título en la API Gutendex y registrarlo en la base de datos.
- 📚 Listar los libros registrados en la base de datos.
- 👨‍🎨 Listar los autores registrados en la base de datos.
- 📆 Listar los autores vivos apartir de un año determinado.
- 🖋 Listar los libros por idioma.

---
## 🖥 Tecnologías utilizadas
<div align="center">
  
  |    Tecnología   |                        Descripción                      |                              Icon                               |
  | :-------------: | :-----------------------------------------------------: | :-------------------------------------------------------------: |
  |      Java       |                 Lenguaje de programación                |   <img src="https://skillicons.dev/icons?i=java" width="48">    |
  |       Git       |             Sistema de control de versiones             |    <img src="https://skillicons.dev/icons?i=git" width="48">    |
  |      Maven      |           gestión y construcción de proyecto            |   <img src="https://skillicons.dev/icons?i=maven" width="48">   |
  |   Spring Boot   |                        Framework                        |   <img src="https://skillicons.dev/icons?i=spring" width="48">  |
  |    PostgreSQL   |         sistema almacenar y gestionar datos (DB)        | <img src="https://skillicons.dev/icons?i=postgres" width="48">  |
  | Spring Data JPA |  persistencia de datos y mapeo objeto-relacional (ORM)  |           <img src="" width="48" alt="Spring Data JPA">         |
  |  Gutendex API   | API para obtener la información de los libros y autores |            <img src="" width="48" alt="Gutendex API">           |
   
</div>

---
## 📂 Estructura del Proyecto

```text
├── src/main/java/com/david
│   ├── controller          # Lógica de control y coordinación
│   │   ├── App.java
│   │   └── Coordinador.java
│   │
│   ├── main                # Punto de entrada de la aplicación
│   │   └── Main.java
│   │
│   ├── model               # Lógica de negocio y definición de datos
│   │   ├── ConsultarIntercambio.java
│   │   └── Moneda.java
│   │
│   └── view                # Interfaz gráfica de usuario
│       └── VentanaPrincipal.java
│
├── .env                    # Variables de entorno (tokens, config)
├── pom.xml                 # Dependencias de Maven
└── README.md               # Documentación del proyecto
```

### Descripcion de Paquetes
- **com.david.literalura.model:** Contiene las clases que modelan los datos del dominio.
  - Incluye Entidades JPA (Libro, Autor) para el mapeo con la base de datos.
  - Incluye clases Record o DTOs (DatosLibro, DatosGutendex) utilizadas para mapear la respuesta JSON proveniente de la API externa.
- **com.david.literalura.repository:** Capa de persistencia de datos.
  - Contiene interfaces que extienden de JpaRepository (Spring Data JPA) para realizar operaciones CRUD y consultas personalizadas a la base de datos para Autores y Libros.
- **com.david.literalura.service:** Contiene la lógica de negocio y servicios de utilidad.
  - ConsumoAPI: Encargada de realizar las peticiones HTTP a la API externa (Gutendex).
  - ConvierteDatos: Implementación para transformar los datos crudos (JSON) en objetos Java.
  - Menu: Gestiona la interacción con el usuario a través de la consola.
  - LibroService: Orquesta el flujo de datos entre la API, la base de datos y el menú.
- **LiteraluraApplication.java:** Punto de entrada de la aplicación Spring Boot. Implementa CommandLineRunner para ejecutar el menú interactivo al iniciar el programa.

---

---
## 🖥 Screenshots
<table align="center">
  <tr>
    <td align="center"><img src="./images/buscar_libro.jpg" alt="Buscar libro" width="200"><br><sub><b>Buscar libro</b></sub></td>
    <td align="center"><img src="./images/libros_registrados.jpg" alt="Libros registrados" width="200"><br><sub><b>Libros registrados</b></sub></td>
  </tr>
  <tr>
    <td align="center"><img src="./images/autores_registrados.jpg" alt="Autores registrados" width="200"><br><sub><b>Autores registrados</b></sub></td>
    <td align="center"><img src="./images/autores_vivos_por_año.jpg" alt="Autores vivos por año" width="200"><br><sub><b>Autores vivos por año</b></sub></td>
  </tr>
   <tr>
    <td align="center"><img src="./images/libros_por_idioma.jpg" alt="Libros por idioma" width="200"><br><sub><b>Libros por idioma</b></sub></td>
    <td align="center"><img src="" alt="" width="200"><br><sub><b></b></sub></td>
  </tr>
</table>

---
## ✅ Prerrequisitos
Antes de empezar, asegúrate de tener instalados:
- Java JDK 21+
- Maven (se recomienda utilizar el wrapper incluido: `./mvnw` para Linux/Mac o `mvnw.cmd` en Windows)
- Un gestor de base de datos (PostgreSQL)

---
## ⚙ Ejecución


---
## 📚 Documentación de la API
