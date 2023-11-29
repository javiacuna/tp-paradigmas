package com.ues21.biblioteca;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        ConexionBaseDatos conexionBaseDatos = ConexionBaseDatos.obtenerInstancia();
        try {
            conexionBaseDatos.conectar();

            PrestamoDAOImpl prestamoDAO = new PrestamoDAOImpl(conexionBaseDatos.getConexion());
            EstudianteDAOImpl estudianteDAO = new EstudianteDAOImpl(conexionBaseDatos.getConexion());
            LibroDAOImpl libroDAO = new LibroDAOImpl(conexionBaseDatos.getConexion());
            FabricaObjetos fabricaPrestamo = new FabricaPrestamo();

            // Crear una instancia de PrestamoVista (puedes usar la implementación SwingPrestamoVista)
            SwingPrestamoVista swingPrestamoVista = new SwingPrestamoVista();

            PrestamoController prestamoController = new PrestamoController(prestamoDAO, fabricaPrestamo, swingPrestamoVista, estudianteDAO, libroDAO);

            // Establecer el PrestamoController en SwingPrestamoVista
            swingPrestamoVista.setPrestamoController(prestamoController);

            // Mostrar la ventana
            swingPrestamoVista.mostrarVentana();

            // Resto del código...
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
