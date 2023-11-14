package com.ues21.biblioteca;

import java.util.Date;

public class PrestamoModelo {
    private int idPrestamo;
    private EstadoPrestamo estado;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private int idEstudiante;  // Agregado para representar el estudiante asociado al préstamo
    private int idLibro;       // Agregado para representar el libro asociado al préstamo

    public PrestamoModelo(int idPrestamo, EstadoPrestamo estado, Date fechaPrestamo, Date fechaDevolucion, int idEstudiante, int idLibro) {
        this.idPrestamo = idPrestamo;
        this.estado = estado;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.idEstudiante = idEstudiante;
        this.idLibro = idLibro;
    }


    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }
}
