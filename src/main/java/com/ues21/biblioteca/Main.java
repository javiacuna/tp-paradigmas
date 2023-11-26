package com.ues21.biblioteca;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        ConexionBaseDatos conexionBaseDatos = ConexionBaseDatos.obtenerInstancia();
        try {
            conexionBaseDatos.conectar();
            // Crear la instancia de PrestamoDAOImpl pasando la conexión
            PrestamoDAOImpl prestamoDAO = new PrestamoDAOImpl(conexionBaseDatos.getConexion());

            // Crear una instancia de FabricaPrestamo (o cualquier otra implementación de FabricaObjetos)
            FabricaObjetos fabricaPrestamo = new FabricaPrestamo();

            // Crear un controlador de préstamos con PrestamoDAO y FabricaObjetos
            PrestamoController prestamoController = new PrestamoController(prestamoDAO, fabricaPrestamo);

            // Solicitar un préstamo
            int idEstudiante = 1;  // Ajusta el ID del estudiante
            int idLibro = 1;       // Ajusta el ID del libro

            PrestamoModelo nuevoPrestamo = new PrestamoModelo(
                    0,                              // idPrestamo (se autogenerará en la base de datos)
                    new EstadoPrestamoSolicitado(), // Estado inicial
                    null,                           // fechaPrestamo (se asignará automáticamente en la base de datos)
                    null,                           // fechaDevolucion (inicialmente nula)
                    idEstudiante,                   // idEstudiante
                    idLibro                         // idLibro
            );

            prestamoController.solicitarPrestamo(nuevoPrestamo, idEstudiante, idLibro);

            // Obtener el estado del préstamo
            EstadoPrestamo estado = prestamoController.obtenerEstadoPrestamo(nuevoPrestamo.getIdPrestamo());
            System.out.println("Estado actual del préstamo: " + estado.getClass().getSimpleName());

            // Aprobar el préstamo
            prestamoController.aprobarPrestamo(nuevoPrestamo.getIdPrestamo());

            // Obtener y mostrar el nuevo estado del préstamo
            estado = prestamoController.obtenerEstadoPrestamo(nuevoPrestamo.getIdPrestamo());
            System.out.println("Nuevo estado del préstamo: " + estado.getClass().getSimpleName());

            // Devolver el préstamo
            prestamoController.devolverPrestamo(nuevoPrestamo.getIdPrestamo());

            // Obtener y mostrar el estado final del préstamo
            estado = prestamoController.obtenerEstadoPrestamo(nuevoPrestamo.getIdPrestamo());
            if (estado != null) {
                System.out.println("Estado final del préstamo: " + estado.getClass().getSimpleName());
            } else {
                System.out.println("No se pudo obtener el estado final del préstamo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBaseDatos.desconectar();
        }
    }
}
