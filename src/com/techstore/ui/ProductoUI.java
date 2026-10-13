package com.techstore.ui;

import com.techstore.exception.PrecioInvalidoException;
import com.techstore.exception.ProductoNoEncontradoException;
import com.techstore.model.Producto;
import com.techstore.service.ProductoService;
import com.techstore.util.Consola;

public class ProductoUI {
    private ProductoService productoService;

    public ProductoUI(ProductoService productoService) {
        this.productoService = productoService;
    }

    public void mostrarSubmenu() {
        int opcion = 0;
        do {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar / Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Volver al menú principal");
            opcion = Consola.leerEntero("Elija una opción: ");

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> buscarYActualizar();
                case 4 -> eliminarProducto();
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void agregarProducto() {
        System.out.println("\n-- AGREGAR PRODUCTO --");
        String nombre = Consola.leerTexto("Nombre: ");
        double precio = Consola.leerDecimal("Precio: ");
        int cantidadStock = Consola.leerEntero("Stock: ");

        try {
            Producto creado = productoService.agregarProducto(nombre, precio, cantidadStock);
            System.out.println("Producto agregado con ID " + creado.getId() + ".");
        } catch (PrecioInvalidoException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private void listarProductos() {
        System.out.println("\n-- LISTA DE PRODUCTOS --");
        productoService.listarProductos();
    }

    private void buscarYActualizar() {
        System.out.println("\n-- BUSCAR / ACTUALIZAR --");
        int identificador = Consola.leerEntero("Ingrese ID del producto: ");

        try {
            Producto producto = productoService.buscarPorIdentificador(identificador);
            System.out.println("Producto encontrado:");
            producto.mostrar();

            if (!Consola.leerConfirmacion("¿Desea actualizar precio o stock?")) {
                return;
            }

            System.out.println("1) Actualizar precio");
            System.out.println("2) Actualizar stock");
            int opcion = Consola.leerEntero("Elija una opción: ");

            switch (opcion) {
                case 1 -> {
                    double nuevoPrecio = Consola.leerDecimal("Nuevo precio: ");
                    productoService.actualizarPrecio(identificador, nuevoPrecio);
                    System.out.println("Precio actualizado.");
                }
                case 2 -> {
                    int nuevoStock = Consola.leerEntero("Nuevo stock: ");
                    productoService.actualizarStock(identificador, nuevoStock);
                    System.out.println("Stock actualizado.");
                }
                default -> System.out.println("Opción inválida.");
            }
        } catch (ProductoNoEncontradoException error) {
            System.out.println("Error: " + error.getMessage());
        } catch (PrecioInvalidoException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private void eliminarProducto() {
        System.out.println("\n-- ELIMINAR PRODUCTO --");
        int identificador = Consola.leerEntero("Ingrese ID del producto a eliminar: ");

        try {
            Producto producto = productoService.buscarPorIdentificador(identificador);
            System.out.println("Producto a eliminar:");
            producto.mostrar();

            if (Consola.leerConfirmacion("¿Confirma la eliminación?")) {
                productoService.eliminarProducto(identificador);
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (ProductoNoEncontradoException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}