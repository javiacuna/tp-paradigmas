package com.ues21.biblioteca;

public interface PrestamoDAO {
    void guardarPrestamo(PrestamoModelo prestamo, int idEstudiante, int idLibro);
    PrestamoModelo obtenerPrestamo(int idPrestamo);
    void actualizarEstadoPrestamo(int idPrestamo, EstadoPrestamo nuevoEstado);
}
