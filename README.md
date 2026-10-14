# 🛒 TechStore - Sistema de Gestión
Aplicación de consola desarrollada en Java para registrar, listar y gestionar productos, además de crear pedidos con múltiples productos y descontar el stock automáticamente.

>    🎓 Preentrega — Ejercicio Práctico Obligatorio

## 🛠️ Tecnologías
- Java JDK 25+
- Visual Studio Code
- Extension Pack for Java

## 📁 Estructura del proyecto
<pre> 
src/
└── com/
        └── techstore/
        ├── Main.java
        ├── exception/  # Excepciones personalizadas
        ├── model/      # Producto, Bebida, Comida, Pedido, LineaPedido
        ├── service/    # ProductoService, PedidoService
        ├── ui/         # Menu, ProductoUI, PedidoUI
        └── util/       # Validador, Consola
</pre>

## 🚀 Cómo ejecutar
### 1. Clonar el repositorio
``` bash
git clone https://github.com/chalys/techstore.git
```
### 2. Ingresar al directorio del proyecto
``` bash
cd techstore
```
### 3. Abrir el proyecto en Visual Studio Code
``` bash
code .
``` 
### 4. Ejecutar la aplicación
Abrir el archivo Main.java y presionar el botón ▶ Run de VS Code.

## 🖥️ Menú principal
| SISTEMA DE GESTIÓN - TECHSTORE|
|--------------------------|
| 1) Agregar producto |
| 2) Listar productos |
| 3) Buscar/Actualizar producto |
| 4) Eliminar producto |
| 5) Crear pedido |
| 6) Lista pedidos |
| 7) Salir |

## 🧱 Conceptos aplicados
### 🏗️ Programación Orientada a Objetos (POO)
* Encapsulamiento
* Herencia
* Polimorfismo

### 📦 Colecciones
* ArrayList de productos
* ArrayList de líneas de pedido
* ArrayList de pedidos

### ⚠️ Excepciones
* NumberFormatException
* StockInsuficienteException
* ProductoNoEncontradoException
* PrecioInvalidoException

### 🗂️ Organización por paquetes
* model: clases del modelo de datos.
* service: lógica de negocio.
* ui: interfaz de usuario por consola.
* util: utilidades y validaciones.
* exception: excepciones personalizadas.

## 👨‍💻 Autor
Carlos Arando
* Curso: Back-End Java
* Proyecto: TechStore - Sistema de Gestión