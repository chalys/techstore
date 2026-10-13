package com.techstore.ui;

import com.techstore.exception.PrecioInvalidoException;
import com.techstore.exception.ProductoNoEncontradoException;
import com.techstore.exception.StockInsuficienteException;
import com.techstore.model.Pedido;
import com.techstore.service.PedidoService;
import com.techstore.util.Consola;

public class PedidoUI {
    private PedidoService pedidoService;

    public PedidoUI(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public void mostrarSubmenu() {
        int opcion = 0;
        do {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1) Crear un pedido");
            System.out.println("2) Listar pedidos");
            System.out.println("3) Volver al menú principal");
            opcion = Consola.leerEntero("Elija una opción: ");

            switch (opcion) {
                case 1 -> crearPedido();
                case 2 -> listarPedidos();
                case 3 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 3);
    }

    private void crearPedido() {
        System.out.println("\n-- CREAR PEDIDO --");

        try {
            Pedido pedido = pedidoService.crearPedido();
            System.out.println("Pedido #" + pedido.getId() + " iniciado.");

            int cantidadProductos = Consola.leerEntero(
                    "¿Cuántos productos desea agregar al pedido? ");

            for (int indice = 0; indice < cantidadProductos; indice++) {
                System.out.println("\nProducto " + (indice + 1) + " de " + cantidadProductos);
                int identificadorProducto = Consola.leerEntero("ID del producto: ");
                int cantidadSolicitada = Consola.leerEntero("Cantidad: ");

                try {
                    pedidoService.agregarLineaAlPedido(
                            pedido, identificadorProducto, cantidadSolicitada);
                    System.out.println("Línea agregada.");
                } catch (StockInsuficienteException error) {
                    System.out.println("No se pudo agregar: " + error.getMessage());
                } catch (ProductoNoEncontradoException error) {
                    System.out.println("No se pudo agregar: " + error.getMessage());
                }
            }

            if (pedido.getListaLineas().isEmpty()) {
                System.out.println("El pedido no tiene líneas. Se cancela.");
                pedidoService.getListaPedidos().remove(pedido);
                return;
            }

            System.out.println("\nResumen del pedido:");
            pedido.mostrar();
            System.out.println("Total: $" + pedido.calcularTotal());

            if (Consola.leerConfirmacion("¿Confirmar el pedido?")) {
                pedidoService.confirmarPedido(pedido);
                System.out.println("Pedido confirmado. Stock descontado.");
            } else {
                System.out.println("Pedido cancelado.");
                pedidoService.getListaPedidos().remove(pedido);
            }

        } catch (PrecioInvalidoException error) {
            System.out.println("Error: " + error.getMessage());
        } catch (StockInsuficienteException error) {
            System.out.println("Error de stock: " + error.getMessage());
        } catch (ProductoNoEncontradoException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private void listarPedidos() {
        System.out.println("\n-- LISTA DE PEDIDOS --");
        pedidoService.listarPedidos();
    }

}