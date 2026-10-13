package com.techstore.ui;

import com.techstore.service.PedidoService;
import com.techstore.service.ProductoService;
import com.techstore.util.Consola;

public class Menu {
    private ProductoService productoService;
    private PedidoService pedidoService;
    private ProductoUI productoUI;
    private PedidoUI pedidoUI;

    public Menu(ProductoService productoService, PedidoService pedidoService) {
        this.productoService = productoService;
        this.pedidoService = pedidoService;
        this.productoUI = new ProductoUI(productoService);
        this.pedidoUI = new PedidoUI(pedidoService);
    }

    public void iniciar() {
        int opcion = 0;
        do {
            System.out.println(
                    "\n=================================== SISTEMA DE GESTIÓN - TECHSTORE ==================================");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear un pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            opcion = Consola.leerEntero("Elija una opción: ");

            switch (opcion) {
                case 1, 2, 3, 4 -> productoUI.mostrarSubmenu();
                case 5, 6 -> pedidoUI.mostrarSubmenu();
                case 7 -> System.out.println("Saliendo del sistema. ¡Hasta luego!");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

}