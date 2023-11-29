package com.ues21.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {
    private Connection conexion;

    public LibroDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<LibroModelo> obtenerTodos() {
        List<LibroModelo> libros = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement("SELECT * FROM libro");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Crear un nuevo EstudianteModelo y establecer sus valores desde el ResultSet
                LibroModelo libro = new LibroModelo();
                libro.setIdLibro(resultSet.getInt("idLibro"));
                libro.setTitulo(resultSet.getString("titulo"));
                libro.setAutor(resultSet.getString("autor"));
                libro.setIsbn(resultSet.getString("isbn"));
                libro.setCantidadDisponible(resultSet.getInt("cantidadDisponible"));

                // Agregar el estudiante a la lista
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja la excepción adecuadamente en tu aplicación
        }

        return libros;
    }
}