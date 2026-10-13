package com.techstore.model;

public class LineaPedido {
    private Producto producto;
    private int cantidadSolicitada;
    private double subtotal;

    public LineaPedido(Producto producto, int cantidadSolicitada) {
        this.producto = producto;
        this.cantidadSolicitada = cantidadSolicitada;
        this.subtotal = producto.getPrecio() * cantidadSolicitada;
    }

    public LineaPedido() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
        if (this.producto != null) {
            this.subtotal = this.producto.getPrecio() * cantidadSolicitada;
        }
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double calcularSubtotal() {
        if (producto == null) {
            return 0.0;
        }
        return producto.getPrecio() * cantidadSolicitada;
    }

    @Override
    public String toString() {
        return producto.getNombre() +
                " x" + cantidadSolicitada +
                " | Subtotal: $" + subtotal;
    }

    public void mostrar() {
        System.out.println("Producto: " + producto.getNombre() +
                " | Cantidad: " + cantidadSolicitada +
                " | Precio unitario: $" + producto.getPrecio() +
                " | Subtotal: $" + subtotal);
    }
}