package com.ues21.biblioteca;

import java.util.List;

public class PrestamoController {
    private PrestamoDAO prestamoDAO;
    private PrestamoContexto prestamoContexto;
    private PrestamoVista prestamoVista;
    private EstudianteDAO estudianteDAO;
    private LibroDAO libroDAO;

    public PrestamoController(PrestamoDAO prestamoDAO, FabricaObjetos fabricaObjetos, PrestamoVista prestamoVista,
                              EstudianteDAO estudianteDAO, LibroDAO libroDAO) {
        this.prestamoDAO = prestamoDAO;
        this.prestamoContexto = fabricaObjetos.crearInstancia();
        this.prestamoVista = prestamoVista;
        this.estudianteDAO = estudianteDAO;
        this.libroDAO = libroDAO;
    }

    public void solicitarPrestamo(PrestamoModelo nuevoPrestamo, int idEstudiante, int idLibro) {
        if (estudianteExiste(idEstudiante) && libroDisponible(idLibro)) {
            nuevoPrestamo.setIdEstudiante(idEstudiante);
            nuevoPrestamo.setIdLibro(idLibro);
            prestamoDAO.guardarPrestamo(nuevoPrestamo, idEstudiante, idLibro);
            System.out.println("Préstamo solicitado correctamente.");
        } else {
            System.out.println("No se pudo solicitar el préstamo. Verifica la disponibilidad del libro y la existencia del estudiante.");
        }
    }

    public void aprobarPrestamo(int idPrestamo) {
        prestamoDAO.actualizarEstadoPrestamo(idPrestamo, new EstadoPrestamoAprobado());
    }

    public void devolverPrestamo(int idPrestamo) {
        prestamoDAO.actualizarEstadoPrestamo(idPrestamo, new EstadoPrestamoDevuelto());
    }

    public EstadoPrestamo obtenerEstadoPrestamo(int idPrestamo) {
        PrestamoModelo prestamo = prestamoDAO.obtenerPrestamo(idPrestamo);
        if (prestamo != null) {
            return prestamo.getEstado();
        } else {
            System.out.println("No se pudo obtener el estado del préstamo. Verifica el ID del préstamo.");
            return null;
        }
    }

    public List<EstudianteModelo> obtenerTodosLosEstudiantes() {
        return estudianteDAO.obtenerTodos(); // Delega la operación al EstudianteDAO
    }

    public List<LibroModelo> obtenerTodosLosLibros() {
        return libroDAO.obtenerTodos(); // Delega la operación al LibroDAO
    }

    public void setEstado(EstadoPrestamo estado) {
        prestamoContexto.setEstado(estado);
    }

    private boolean estudianteExiste(int idEstudiante) {
        // Lógica para verificar si el estudiante existe en la base de datos
        return true;
    }

    private boolean libroDisponible(int idLibro) {
        // Lógica para verificar si el libro está disponible en la base de datos
        return true;
    }
}
