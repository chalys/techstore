package com.techstore;

import com.techstore.service.PedidoService;
import com.techstore.service.ProductoService;
import com.techstore.ui.Menu;

public class Main {
    public static void main(String[] args) {
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);
        Menu menu = new Menu(productoService, pedidoService);
        menu.iniciar();
    }
}