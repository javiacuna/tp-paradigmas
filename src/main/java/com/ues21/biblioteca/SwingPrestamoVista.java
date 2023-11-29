package com.ues21.biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SwingPrestamoVista implements PrestamoVista {

    private JFrame frame;
    private JTextArea estadoPrestamoTextArea;
    private JTextArea estudiantesTextArea;
    private JTextArea librosTextArea;
    private PrestamoController prestamoController;
    private PrestamoModelo prestamoModelo;
    private JTextField idEstudianteTextField;
    private JTextField idLibroTextField;

    public SwingPrestamoVista() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sistema de Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        idEstudianteTextField = new JTextField();
        idLibroTextField = new JTextField();

        formPanel.add(new JLabel("ID Estudiante:"));
        formPanel.add(idEstudianteTextField);
        formPanel.add(new JLabel("ID Libro:"));
        formPanel.add(idLibroTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton solicitarBtn = new JButton("Solicitar Préstamo");
        JButton aprobarBtn = new JButton("Aprobar Préstamo");
        JButton devolverBtn = new JButton("Devolver Préstamo");
        JButton obtenerEstudiantesBtn = new JButton("Obtener Estudiantes");
        JButton obtenerLibrosBtn = new JButton("Obtener Libros");

        solicitarBtn.addActionListener(e -> solicitarPrestamo());
        aprobarBtn.addActionListener(e -> aprobarPrestamo());
        devolverBtn.addActionListener(e -> devolverPrestamo());
        obtenerEstudiantesBtn.addActionListener(e -> obtenerEstudiantes());
        obtenerLibrosBtn.addActionListener(e -> obtenerLibros());

        buttonPanel.add(solicitarBtn);
        buttonPanel.add(aprobarBtn);
        buttonPanel.add(devolverBtn);
        buttonPanel.add(obtenerEstudiantesBtn);
        buttonPanel.add(obtenerLibrosBtn);

        JPanel textAreaPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        estadoPrestamoTextArea = createTextArea(4, 30);
        estudiantesTextArea = createTextArea(4, 30);
        librosTextArea = createTextArea(4, 30);
        textAreaPanel.add(createLabelAndComponent("Estado del Préstamo:", estadoPrestamoTextArea));
        textAreaPanel.add(createLabelAndComponent("Estudiantes:", estudiantesTextArea));
        textAreaPanel.add(createLabelAndComponent("Libros:", librosTextArea));

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(textAreaPanel, BorderLayout.SOUTH);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
    }

    private JTextArea createTextArea(int rows, int columns) {
        JTextArea textArea = new JTextArea(rows, columns);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));  // Borde azul
        textArea.setBackground(new Color(240, 248, 255));  // Fondo azul claro
        return textArea;
    }

    private JPanel createLabelAndComponent(String labelText, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.setBackground(new Color(240, 248, 255));  // Fondo azul claro
        return panel;
    }

    private void solicitarPrestamo() {
        int idEstudiante = Integer.parseInt(idEstudianteTextField.getText());
        int idLibro = Integer.parseInt(idLibroTextField.getText());

        PrestamoModelo nuevoPrestamo = new PrestamoModelo(
                0,
                new EstadoPrestamoSolicitado(),
                null,
                null,
                idEstudiante,
                idLibro
        );

        prestamoModelo = nuevoPrestamo;
        prestamoController.solicitarPrestamo(nuevoPrestamo, idEstudiante, idLibro);
        actualizarEstado();
    }

    private void aprobarPrestamo() {
        prestamoController.aprobarPrestamo(prestamoModelo.getIdPrestamo());
        actualizarEstado();
    }

    private void devolverPrestamo() {
        prestamoController.devolverPrestamo(prestamoModelo.getIdPrestamo());
        actualizarEstado();
    }

    private void obtenerEstudiantes() {
        List<EstudianteModelo> estudiantes = prestamoController.obtenerTodosLosEstudiantes();
        mostrarEstudiantes(estudiantes);
    }

    private void obtenerLibros() {
        List<LibroModelo> libros = prestamoController.obtenerTodosLosLibros();
        mostrarLibros(libros);
    }

    private void actualizarEstado() {
        EstadoPrestamo estado = prestamoController.obtenerEstadoPrestamo(prestamoModelo.getIdPrestamo());
        estadoPrestamoTextArea.setText("Estado actual del préstamo: " + estado.getClass().getSimpleName());
    }

    private void mostrarEstudiantes(List<EstudianteModelo> estudiantes) {
        StringBuilder estudiantesText = new StringBuilder();
        for (EstudianteModelo estudiante : estudiantes) {
            estudiantesText.append(estudiante).append("\n");
        }
        estudiantesTextArea.setText(estudiantesText.toString());
    }

    private void mostrarLibros(List<LibroModelo> libros) {
        StringBuilder librosText = new StringBuilder();
        for (LibroModelo libro : libros) {
            librosText.append(libro).append("\n");
        }
        librosTextArea.setText(librosText.toString());
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
        estadoPrestamoTextArea.setText("Estado actual del préstamo: " + estado.getClass().getSimpleName());
    }

    public void mostrarVentana() {
        EventQueue.invokeLater(() -> frame.setVisible(true));
    }
}
