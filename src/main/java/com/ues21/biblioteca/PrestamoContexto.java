package com.ues21.biblioteca;

public abstract class PrestamoContexto {
    private EstadoPrestamo estado;

    public PrestamoContexto() {
        this.estado = new EstadoPrestamoSolicitado();
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public void aprobarPrestamo(int idPrestamo, PrestamoController prestamoController){
        prestamoController.aprobarPrestamo(idPrestamo);
    }

    public abstract void devolverPrestamo(int idPrestamo, PrestamoController prestamoController);
}
