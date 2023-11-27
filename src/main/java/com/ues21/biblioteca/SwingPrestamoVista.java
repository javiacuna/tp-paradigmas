package com.ues21.biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingPrestamoVista implements PrestamoVista {

    private JFrame frame;
    private JTextArea estadoTextArea;
    private PrestamoController prestamoController;
    private PrestamoModelo prestamoModelo;

    public SwingPrestamoVista() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sistema de Biblioteca");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        estadoTextArea = new JTextArea();
        frame.getContentPane().add(estadoTextArea, BorderLayout.CENTER);

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

        JButton solicitarBtn = new JButton("Solicitar Préstamo");
        solicitarBtn.addActionListener(e -> {
            // Asignar el nuevoPrestamo a prestamoModelo antes de llamar a solicitarPrestamo
            prestamoModelo = nuevoPrestamo;
            // Llamar al método solicitarPrestamo del PrestamoController
            prestamoController.solicitarPrestamo(nuevoPrestamo, idEstudiante, idLibro);
            // Actualizar el estado en el JTextArea
            actualizarEstado();
            //EstadoPrestamo estado = prestamoController.obtenerEstadoPrestamo(nuevoPrestamo.getIdPrestamo());
            //System.out.println("Estado actual del préstamo: " + estado.getClass().getSimpleName());
        });
        frame.getContentPane().add(solicitarBtn, BorderLayout.NORTH);

        JButton aprobarBtn = new JButton("Aprobar Préstamo");
        aprobarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Llamar al método aprobarPrestamo del PrestamoController
                prestamoController.aprobarPrestamo(prestamoModelo.getIdPrestamo());
                // Actualizar el estado en el JTextArea
                actualizarEstado();
            }
        });
        frame.getContentPane().add(aprobarBtn, BorderLayout.WEST);

        JButton devolverBtn = new JButton("Devolver Préstamo");
        devolverBtn.addActionListener(e -> {
            // Llamar al método devolverPrestamo del PrestamoController
            prestamoController.devolverPrestamo(prestamoModelo.getIdPrestamo());
            // Actualizar el estado en el JTextArea
            actualizarEstado();
        });
        frame.getContentPane().add(devolverBtn, BorderLayout.EAST);
    }

    private void actualizarEstado() {
        // Obtener el estado actualizado del préstamo
        EstadoPrestamo estado = prestamoController.obtenerEstadoPrestamo(prestamoModelo.getIdPrestamo());
        // Actualizar el JTextArea con el nuevo estado
        estadoTextArea.setText("Estado actual del préstamo: " + estado.getClass().getSimpleName());
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
