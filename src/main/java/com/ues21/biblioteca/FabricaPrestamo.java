package com.ues21.biblioteca;

public class FabricaPrestamo implements FabricaObjetos {

    @Override
    public PrestamoContexto crearInstancia() {
        return new PrestamoContexto() {
            @Override
            public void aprobarPrestamo(int idPrestamo, PrestamoController prestamoController) {

            }

            @Override
            public void devolverPrestamo(int idPrestamo, PrestamoController prestamoController) {

            }
        };
    }
}
