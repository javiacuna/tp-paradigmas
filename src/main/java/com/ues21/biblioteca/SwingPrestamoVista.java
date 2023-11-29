package com.ues21.biblioteca;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingPrestamoVista implements PrestamoVista {

    private JFrame frame;
    private JTextArea estadoTextArea;
    private PrestamoController prestamoController;
    private PrestamoModelo prestamoModelo;
    private JTextField idEstudianteTextField;
    private JTextField idLibroTextField;

    public SwingPrestamoVista() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sistema de Biblioteca");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Utilizar GridLayout con 4 filas y 2 columnas
        frame.setLayout(new GridLayout(5, 2));

        // Crear JTextField para el ID del estudiante
        idEstudianteTextField = new JTextField();
        frame.add(new JLabel("ID Estudiante:"));  // Etiqueta explicativa
        frame.add(idEstudianteTextField);

        // Crear JTextField para el ID del libro
        idLibroTextField = new JTextField();
        frame.add(new JLabel("ID Libro:"));  // Etiqueta explicativa
        frame.add(idLibroTextField);

        JButton solicitarBtn = new JButton("Solicitar Préstamo");
        solicitarBtn.addActionListener(e -> {
            // Obtener los valores ingresados por el usuario
            int idEstudiante = Integer.parseInt(idEstudianteTextField.getText());
            int idLibro = Integer.parseInt(idLibroTextField.getText());

            // Crear una nueva instancia de PrestamoModelo con los valores ingresados
            PrestamoModelo nuevoPrestamo = new PrestamoModelo(
                    0,
                    new EstadoPrestamoSolicitado(),
                    null,
                    null,
                    idEstudiante,
                    idLibro
            );

            // Asignar el nuevoPrestamo a prestamoModelo antes de llamar a solicitarPrestamo
            prestamoModelo = nuevoPrestamo;
            // Llamar al método solicitarPrestamo del PrestamoController
            prestamoController.solicitarPrestamo(nuevoPrestamo, idEstudiante, idLibro);
            // Actualizar el estado en el JTextArea
            actualizarEstado();
        });
        frame.add(solicitarBtn);

        JButton aprobarBtn = new JButton("Aprobar Préstamo");
        aprobarBtn.addActionListener(e -> {
            // Llamar al método aprobarPrestamo del PrestamoController
            prestamoController.aprobarPrestamo(prestamoModelo.getIdPrestamo());
            // Actualizar el estado en el JTextArea
            actualizarEstado();
        });
        frame.add(aprobarBtn);

        JButton devolverBtn = new JButton("Devolver Préstamo");
        devolverBtn.addActionListener(e -> {
            // Llamar al método devolverPrestamo del PrestamoController
            prestamoController.devolverPrestamo(prestamoModelo.getIdPrestamo());
            // Actualizar el estado en el JTextArea
            actualizarEstado();
        });
        frame.add(devolverBtn);

        JButton obtenerEstudiantesBtn = new JButton("Obtener Estudiantes");
        obtenerEstudiantesBtn.addActionListener(e -> {
            // Llamar al método obtenerTodosLosEstudiantes del PrestamoController
            List<EstudianteModelo> estudiantes = prestamoController.obtenerTodosLosEstudiantes();
            // Mostrar los estudiantes en el JTextArea
            mostrarEstudiantes(estudiantes);
        });
        frame.add(obtenerEstudiantesBtn);

        JButton obtenerLibrosBtn = new JButton("Obtener Libros");
        obtenerLibrosBtn.addActionListener(e -> {
            // Llamar al método obtenerTodosLosLibros del PrestamoController
            List<LibroModelo> libros = prestamoController.obtenerTodosLosLibros();
            // Mostrar los estudiantes en el JTextArea
            mostrarLibros(libros);
        });
        frame.add(obtenerLibrosBtn);

        estadoTextArea = new JTextArea();
        frame.add(estadoTextArea);

        frame.pack();  // Ajustar el tamaño del frame automáticamente
    }

    private void actualizarEstado() {
        // Obtener el estado actualizado del préstamo
        EstadoPrestamo estado = prestamoController.obtenerEstadoPrestamo(prestamoModelo.getIdPrestamo());
        // Actualizar el JTextArea con el nuevo estado
        estadoTextArea.setText("Estado actual del préstamo: " + estado.getClass().getSimpleName());
    }

    private void mostrarEstudiantes(List<EstudianteModelo> estudiantes) {
        // Mostrar los estudiantes en el JTextArea
        StringBuilder estudiantesText = new StringBuilder("Estudiantes:\n");
        for (EstudianteModelo estudiante : estudiantes) {
            estudiantesText.append(estudiante.toString()).append("\n");
        }
        estadoTextArea.setText(estudiantesText.toString());
    }

    private void mostrarLibros(List<LibroModelo> libros) {
        // Mostrar los estudiantes en el JTextArea
        StringBuilder librosText = new StringBuilder("Libros:\n");
        for (LibroModelo libro : libros) {
            librosText.append(libro.toString()).append("\n");
        }
        estadoTextArea.setText(librosText.toString());
    }

    public void setPrestamoController(PrestamoController prestamoController) {
        this.prestamoController = prestamoController;
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }

    @Override
    public void actualizarEstadoPrestamo(EstadoPrestamo estado) {
        estadoTextArea.setText("Estado actual del préstamo: " + estado.getClass().getSimpleName());
    }

    public void mostrarVentana() {
        EventQueue.invokeLater(() -> {
            try {
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
