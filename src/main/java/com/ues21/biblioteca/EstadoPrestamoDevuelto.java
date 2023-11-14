package com.ues21.biblioteca;

public class EstadoPrestamoDevuelto implements EstadoPrestamo {

    @Override
    public void realizarAprobacion(PrestamoContexto prestamoContexto) {
        // Lógica relacionada con la aprobación desde el estado devuelto
        System.out.println("No se puede aprobar un préstamo que ya ha sido devuelto.");
    }

    @Override
    public void realizarDevolucion(PrestamoContexto prestamoContexto) {
        // Lógica relacionada con la devolución desde el estado devuelto
        System.out.println("El préstamo ya ha sido devuelto anteriormente.");
    }

    @Override
    public EstadoPrestamo crearNuevoEstado() {
        return new EstadoPrestamoDevuelto();
    }
}
