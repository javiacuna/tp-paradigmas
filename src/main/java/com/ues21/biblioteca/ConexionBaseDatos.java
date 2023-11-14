package com.ues21.biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static ConexionBaseDatos instancia;
    private Connection conexion;

    private ConexionBaseDatos() {
        // Constructor privado para prevenir instanciación directa
    }

    public static ConexionBaseDatos obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConexionBaseDatos();
        }
        return instancia;
    }

    public void conectar() throws SQLException {
        // Configuración de la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/biblioteca";
        String usuario = "root";
        String contrasena = "";

        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión a la base de datos
            this.conexion = DriverManager.getConnection(url, usuario, contrasena);

            // Verificar si la conexión es exitosa
            if (this.conexion != null) {
                System.out.println("Conectado a la base de datos");
                // Puedes guardar la conexión en algún lugar para usarla en otros métodos
                // this.conexion = conexion;
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Desconectado de la base de datos");
            } else {
                System.out.println("La conexión ya está cerrada o es nula");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
