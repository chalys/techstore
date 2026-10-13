package com.techstore.util;

public class Validador {

    public static boolean esTextoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean esPrecioValido(double precio) {
        return precio > 0;
    }

    public static boolean esStockValido(int stock) {
        return stock >= 0;
    }

    public static boolean esCantidadValida(int cantidad) {
        return cantidad > 0;
    }
}