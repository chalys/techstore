package com.techstore.model;

public class Bebida extends Producto {
    private double volumenLitros;
    private boolean esAlcoholica;
    private String fechaVencimiento;

    public Bebida(String nombre, double precio, int stock, double volumenLitros, boolean esAlcoholica,
            String fechaVencimiento) {
        super(nombre, precio, stock);
        this.volumenLitros = volumenLitros;
        this.esAlcoholica = esAlcoholica;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Bebida() {
        super();
    }

    public double getVolumenLitros() {
        return volumenLitros;
    }

    public void setVolumenLitros(double volumenLitros) {
        this.volumenLitros = volumenLitros;
    }

    public boolean isEsAlcoholica() {
        return esAlcoholica;
    }

    public void setEsAlcoholica(boolean esAlcoholica) {
        this.esAlcoholica = esAlcoholica;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Volumen: " + volumenLitros + "L" +
                " | Alcoholica: " + (esAlcoholica ? "Sí" : "No") +
                " | Vence: " + fechaVencimiento;

    }

    @Override
    public void mostrar() {
        System.out.println("ID: " + getId() +
                " | " + getNombre() +
                " | $" + getPrecio() +
                " | Stock: " + getStock() +
                " | Volumen: " + volumenLitros + "L" +
                " | Alcoholica: " + (esAlcoholica ? "Sí" : "No") +
                " | Vence: " + fechaVencimiento);
    }
}