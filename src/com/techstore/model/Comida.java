package com.techstore.model;

public class Comida extends Producto {
    private String fechaVencimiento;
    private double pesoGramos;
    private boolean esPerecedera;

    public Comida(String nombre, double precio, int stock, String fechaVencimiento, double pesoGramos,
            boolean esPerecedera) {
        super(nombre, precio, stock);
        this.fechaVencimiento = fechaVencimiento;
        this.pesoGramos = pesoGramos;
        this.esPerecedera = esPerecedera;
    }

    public Comida() {
        super();
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public boolean isEsPerecedera() {
        return esPerecedera;
    }

    public void setEsPerecedera(boolean esPerecedera) {
        this.esPerecedera = esPerecedera;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Peso: " + pesoGramos + "g" +
                " | Perecedera: " + (esPerecedera ? "Sí" : "No") +
                " | Vence: " + fechaVencimiento;
    }

    @Override
    public void mostrar() {
        System.out.println("ID: " + getId() +
                " | " + getNombre() +
                " | $" + getPrecio() +
                " | Stock: " + getStock() +
                " | Peso: " + pesoGramos + "g" +
                " | Perecedera: " + (esPerecedera ? "Sí" : "No") +
                " | Vence: " + fechaVencimiento);
    }

}
