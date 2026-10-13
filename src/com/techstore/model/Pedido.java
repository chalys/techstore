package com.techstore.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private String fecha;
    private List<LineaPedido> listaLineas;
    private double total;

    public Pedido(String fecha) {
        this.fecha = fecha;
        this.listaLineas = new ArrayList<>();
        this.total = 0.0;
    }

    public Pedido() {
        this.listaLineas = new ArrayList<>();
        this.total = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<LineaPedido> getListaLineas() {
        return listaLineas;
    }

    public void setListaLineas(List<LineaPedido> listaLineas) {
        this.listaLineas = listaLineas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void agregarLinea(LineaPedido linea) {
        this.listaLineas.add(linea);
    }

    public double calcularTotal() {
        double sumaTotal = 0.0;
        for (LineaPedido linea : listaLineas) {
            sumaTotal += linea.calcularSubtotal();
        }
        this.total = sumaTotal;
        return sumaTotal;
    }

    @Override
    public String toString() {
        StringBuilder detalle = new StringBuilder();
        detalle.append("Pedido #").append(id)
                .append(" | Fecha: ").append(fecha)
                .append(" | Total: $").append(total)
                .append("\n");

        for (LineaPedido linea : listaLineas) {
            detalle.append("   → ").append(linea.toString()).append("\n");
        }
        return detalle.toString();
    }

    public void mostrar() {
        System.out.println("Pedido #" + id +
                " | Fecha: " + fecha +
                " | Total: $" + total);
        for (LineaPedido linea : listaLineas) {
            System.out.print("   → ");
            linea.mostrar();
        }
    }
}