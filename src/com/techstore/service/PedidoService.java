package com.techstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.techstore.exception.PrecioInvalidoException;
import com.techstore.exception.ProductoNoEncontradoException;
import com.techstore.exception.StockInsuficienteException;
import com.techstore.model.LineaPedido;
import com.techstore.model.Pedido;
import com.techstore.model.Producto;

public class PedidoService {

        private List<Pedido> listaPedidos;
    private ProductoService productoService;
    private static int contadorIdentificadores = 1;

    public PedidoService(ProductoService productoService) {
        this.productoService = productoService;
        this.listaPedidos = new ArrayList<>();
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public ProductoService getProductoService() {
        return productoService;
    }

    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public Pedido crearPedido()
            throws ProductoNoEncontradoException, StockInsuficienteException,
                   PrecioInvalidoException {

        Pedido nuevoPedido = new Pedido(LocalDate.now().toString());
        nuevoPedido.setId(contadorIdentificadores);
        contadorIdentificadores++;

        listaPedidos.add(nuevoPedido);
        return nuevoPedido;
    }

    public void agregarLineaAlPedido(Pedido pedido, int identificadorProducto,
                                     int cantidadSolicitada)
            throws ProductoNoEncontradoException, StockInsuficienteException,
                   PrecioInvalidoException {

        if (pedido == null) {
            throw new PrecioInvalidoException("El pedido no puede ser nulo.");
        }
        if (cantidadSolicitada <= 0) {
            throw new PrecioInvalidoException("La cantidad solicitada debe ser mayor a cero.");
        }

        Producto producto = productoService.buscarPorIdentificador(identificadorProducto);

        int cantidadEnPedido = cantidadYaSolicitada(pedido, identificadorProducto);
        int cantidadTotal = cantidadEnPedido + cantidadSolicitada;

        if (producto.getStock() < cantidadTotal) {
            throw new StockInsuficienteException(
                    "Stock insuficiente para '" + producto.getNombre() +
                    "'. Disponible: " + producto.getStock() +
                    ", solicitado: " + cantidadTotal + ".");
        }

        LineaPedido linea = new LineaPedido(producto, cantidadSolicitada);
        pedido.agregarLinea(linea);
        pedido.calcularTotal();
    }

    private int cantidadYaSolicitada(Pedido pedido, int identificadorProducto) {
        int total = 0;
        for (LineaPedido linea : pedido.getListaLineas()) {
            if (linea.getProducto().getId() == identificadorProducto) {
                total += linea.getCantidadSolicitada();
            }
        }
        return total;
    }

    public void confirmarPedido(Pedido pedido)
            throws ProductoNoEncontradoException, StockInsuficienteException,
                   PrecioInvalidoException {

        if (pedido == null) {
            throw new PrecioInvalidoException("El pedido no puede ser nulo.");
        }
        if (pedido.getListaLineas().isEmpty()) {
            throw new PrecioInvalidoException("El pedido no tiene productos asociados.");
        }

        for (LineaPedido linea : pedido.getListaLineas()) {
            Producto producto = linea.getProducto();
            if (producto.getStock() < linea.getCantidadSolicitada()) {
                throw new StockInsuficienteException(
                        "Stock insuficiente para '" + producto.getNombre() +
                        "'. Disponible: " + producto.getStock() +
                        ", solicitado: " + linea.getCantidadSolicitada() + ".");
            }
        }

        for (LineaPedido linea : pedido.getListaLineas()) {
            productoService.descontarStock(
                    linea.getProducto().getId(),
                    linea.getCantidadSolicitada());
        }

        pedido.calcularTotal();
    }

    public void listarPedidos() {
        if (listaPedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        for (Pedido pedido : listaPedidos) {
            pedido.mostrar();
            System.out.println("----------------------------------------");
        }
    }

    public Pedido buscarPedidoPorIdentificador(int identificador)
            throws ProductoNoEncontradoException {

        for (Pedido pedido : listaPedidos) {
            if (pedido.getId() == identificador) {
                return pedido;
            }
        }
        throw new ProductoNoEncontradoException(
                "No se encontró un pedido con ID " + identificador + ".");
    }
}