package com.ues21.biblioteca;

public class PrestamoVista {
    public void mostrarEstadoPrestamo(EstadoPrestamo estado) {
        // Lógica para mostrar el estado del préstamo al usuario
        System.out.println("Mostrando el estado del préstamo: " + estado.getClass().getSimpleName());
    }
}
