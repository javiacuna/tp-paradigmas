package com.ues21.biblioteca;

public class EstadoPrestamoSolicitado implements EstadoPrestamo {

    @Override
    public void realizarAprobacion(PrestamoContexto prestamoContexto) {
        prestamoContexto.setEstado(crearNuevoEstado());
        System.out.println("Préstamo aprobado correctamente.");
    }

    @Override
    public void realizarDevolucion(PrestamoContexto prestamoContexto) {
        // Lógica relacionada con la devolución desde el estado solicitado
        System.out.println("Devolución no permitida. El préstamo está en estado solicitado.");
    }

    @Override
    public EstadoPrestamo crearNuevoEstado() {
        return new EstadoPrestamoAprobado();
    }
}
