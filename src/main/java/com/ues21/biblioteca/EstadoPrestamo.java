package com.ues21.biblioteca;

public interface EstadoPrestamo {

    void realizarAprobacion(PrestamoContexto prestamoContexto);

    void realizarDevolucion(PrestamoContexto prestamoContexto);

    EstadoPrestamo crearNuevoEstado();
}

