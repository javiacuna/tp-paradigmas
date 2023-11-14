package com.ues21.biblioteca;

public class EstadoPrestamoAprobado implements EstadoPrestamo {

    @Override
    public void realizarAprobacion(PrestamoContexto prestamoContexto) {
        // Lógica relacionada con la aprobación desde el estado aprobado
        System.out.println("El préstamo ya ha sido aprobado.");
    }

    @Override
    public void realizarDevolucion(PrestamoContexto prestamoContexto) {
        prestamoContexto.setEstado(new EstadoPrestamoDevuelto());
        // Lógica relacionada con la devolución desde el estado aprobado
        System.out.println("Préstamo devuelto correctamente.");
    }

    @Override
    public EstadoPrestamo crearNuevoEstado() {
        return new EstadoPrestamoDevuelto();
    }
}
