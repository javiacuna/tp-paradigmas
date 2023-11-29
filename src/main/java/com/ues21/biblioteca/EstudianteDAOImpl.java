package com.ues21.biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOImpl implements EstudianteDAO {
    private Connection conexion;

    public EstudianteDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<EstudianteModelo> obtenerTodos() {
        List<EstudianteModelo> estudiantes = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement("SELECT * FROM estudiante");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Crear un nuevo EstudianteModelo y establecer sus valores desde el ResultSet
                EstudianteModelo estudiante = new EstudianteModelo();
                estudiante.setIdEstudiante(resultSet.getInt("idEstudiante"));
                estudiante.setNombre(resultSet.getString("nombre"));
                estudiante.setApellido(resultSet.getString("apellido"));
                estudiante.setCarnet(resultSet.getString("carnet"));

                // Agregar el estudiante a la lista
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja la excepción adecuadamente en tu aplicación
        }

        return estudiantes;
    }
}
