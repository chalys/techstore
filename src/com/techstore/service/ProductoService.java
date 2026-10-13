package com.techstore.service;

import java.util.ArrayList;
import java.util.List;

import com.techstore.exception.PrecioInvalidoException;
import com.techstore.exception.ProductoNoEncontradoException;
import com.techstore.model.Producto;

public class ProductoService {
  private List<Producto> listaProductos;
    private static int contadorIdentificadores = 1;

    public ProductoService() {
        this.listaProductos = new ArrayList<>();
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto agregarProducto(String nombre, double precio, int cantidadStock)
            throws PrecioInvalidoException {

        if (precio <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero.");
        }
        if (cantidadStock < 0) {
            throw new PrecioInvalidoException("El stock no puede ser negativo.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new PrecioInvalidoException("El nombre del producto no puede estar vacío.");
        }

        Producto nuevoProducto = new Producto(nombre, precio, cantidadStock);
        nuevoProducto.setId(contadorIdentificadores);
        contadorIdentificadores++;

        listaProductos.add(nuevoProducto);
        return nuevoProducto;
    }

    public void agregarProducto(Producto producto) throws PrecioInvalidoException {
        if (producto == null) {
            throw new PrecioInvalidoException("El producto no puede ser nulo.");
        }
        if (producto.getPrecio() <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero.");
        }
        if (producto.getStock() < 0) {
            throw new PrecioInvalidoException("El stock no puede ser negativo.");
        }

        producto.setId(contadorIdentificadores);
        contadorIdentificadores++;
        listaProductos.add(producto);
    }

    public void listarProductos() {
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (Producto producto : listaProductos) {
            producto.mostrar();
        }
    }

    public Producto buscarPorIdentificador(int identificador)
            throws ProductoNoEncontradoException {

        for (Producto producto : listaProductos) {
            if (producto.getId() == identificador) {
                return producto;
            }
        }
        throw new ProductoNoEncontradoException(
                "No se encontró un producto con ID " + identificador + ".");
    }

    public Producto buscarPorNombre(String nombre)
            throws ProductoNoEncontradoException {

        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        throw new ProductoNoEncontradoException(
                "No se encontró un producto con nombre '" + nombre + "'.");
    }

    public void actualizarPrecio(int identificador, double nuevoPrecio)
            throws ProductoNoEncontradoException, PrecioInvalidoException {

        if (nuevoPrecio <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero.");
        }
        Producto producto = buscarPorIdentificador(identificador);
        producto.setPrecio(nuevoPrecio);
    }

    public void actualizarStock(int identificador, int nuevoStock)
            throws ProductoNoEncontradoException, PrecioInvalidoException {

        if (nuevoStock < 0) {
            throw new PrecioInvalidoException("El stock no puede ser negativo.");
        }
        Producto producto = buscarPorIdentificador(identificador);
        producto.setStock(nuevoStock);
    }

    public void eliminarProducto(int identificador)
            throws ProductoNoEncontradoException {

        Producto producto = buscarPorIdentificador(identificador);
        listaProductos.remove(producto);
    }

    public boolean hayStockDisponible(int identificador, int cantidadSolicitada)
            throws ProductoNoEncontradoException {

        Producto producto = buscarPorIdentificador(identificador);
        return producto.getStock() >= cantidadSolicitada;
    }

    public void descontarStock(int identificador, int cantidadSolicitada)
            throws ProductoNoEncontradoException, PrecioInvalidoException {

        Producto producto = buscarPorIdentificador(identificador);
        int stockActual = producto.getStock();
        int nuevoStock = stockActual - cantidadSolicitada;

        if (nuevoStock < 0) {
            throw new PrecioInvalidoException("El stock no puede quedar negativo.");
        }
        producto.setStock(nuevoStock);
    }

    

}