package com.ues21.biblioteca;

import java.sql.*;
import java.util.Calendar;

public class PrestamoDAOImpl implements PrestamoDAO {
    private Connection conexion;

    public PrestamoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardarPrestamo(PrestamoModelo prestamo, int idEstudiante, int idLibro) {
        String sql = "INSERT INTO Prestamo (idEstudiante, idLibro, estado, fechaPrestamo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, idEstudiante);
            pstmt.setInt(2, idLibro);
            pstmt.setString(3, prestamo.getEstado().getClass().getSimpleName());

            // Establecer la fecha del préstamo antes de guardarlo en la base de datos
            prestamo.setFechaPrestamo(new Date(System.currentTimeMillis()));  // Puedes ajustar esto según tus necesidades

            pstmt.setDate(4, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Préstamo guardado correctamente.");

                // Recuperar las claves generadas automáticamente
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);
                        prestamo.setIdPrestamo(idGenerado);
                    } else {
                        System.out.println("No se pudo obtener la clave generada para el préstamo.");
                    }
                }
            } else {
                System.out.println("No se pudo guardar el préstamo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public PrestamoModelo obtenerPrestamo(int idPrestamo) {
        String sql = "SELECT * FROM Prestamo WHERE idPrestamo = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idPrestamo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nombreClaseEstado = rs.getString("estado");

                    EstadoPrestamo estado;
                    switch (nombreClaseEstado) {
                        case "EstadoPrestamoSolicitado":
                            estado = new EstadoPrestamoSolicitado();
                            break;
                        case "EstadoPrestamoAprobado":
                            estado = new EstadoPrestamoAprobado();
                            break;
                        case "EstadoPrestamoDevuelto":
                            estado = new EstadoPrestamoDevuelto();
                            break;
                        default:
                            throw new IllegalArgumentException("Clase de estado desconocida: " + nombreClaseEstado);
                    }

                    PrestamoModelo prestamo = new PrestamoModelo(
                            rs.getInt("idPrestamo"),
                            estado,
                            rs.getDate("fechaPrestamo"),
                            rs.getDate("fechaDevolucion"),
                            rs.getInt("idEstudiante"),
                            rs.getInt("idLibro")
                    );

                    return prestamo;
                } else {
                    System.out.println("No se encontró el préstamo con ID " + idPrestamo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizarEstadoPrestamo(int idPrestamo, EstadoPrestamo nuevoEstado) {
        String sql = "UPDATE Prestamo SET estado = ?, fechaDevolucion = ? WHERE idPrestamo = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nuevoEstado.getClass().getSimpleName());
            Date date = new Date(System.currentTimeMillis());
            pstmt.setDate(2, new java.sql.Date(date.getTime()));
            pstmt.setInt(3, idPrestamo);

            pstmt.executeUpdate();
            System.out.println("Estado del préstamo actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Otros métodos relacionados con la gestión de préstamos
}

