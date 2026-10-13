package com.techstore.util;

import java.util.Scanner;

public class Consola {
    private static final Scanner scanner = new Scanner(System.in);

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException error) {
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }
    }

    public static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException error) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }

    public static boolean leerConfirmacion(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (S/N): ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("S")) {
                return true;
            }
            if (entrada.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Error: responda 'S' o 'N'.");
        }
    }

    public static void pausar() {
        System.out.print("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }
}