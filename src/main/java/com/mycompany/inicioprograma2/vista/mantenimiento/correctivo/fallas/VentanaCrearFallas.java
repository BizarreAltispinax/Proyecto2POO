package com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.fallas;

import com.mycompany.inicioprograma2.controlador.ControladorFalla;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearFallas extends JFrame {
    public VentanaCrearFallas(ControladorFalla controlador, int idEquipo, VentanaListaFallas ventanaPadre) {

        setTitle("Registrar Falla - Equipo " + idEquipo);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JTextField txtID = new JTextField();
        JTextField txtDescripcion = new JTextField();

        add(new JLabel("ID de la falla:"));
        add(txtID);

        add(new JLabel("Descripción:"));
        add(txtDescripcion);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCerrar = new JButton("Cerrar");

        add(btnGuardar);
        add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> {
            try {
                int idFalla = Integer.parseInt(txtID.getText());

                boolean ok = controlador.agregarFalla(
                        idEquipo,
                        idFalla,
                        txtDescripcion.getText()
                );

                if (ok) {
                    controlador.guardar();

                    if (ventanaPadre != null) {
                        ventanaPadre.refrescar();
                    }

                    JOptionPane.showMessageDialog(this, "Falla registrada correctamente");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Datos inválidos o ID ya existente.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La ID debe ser un número entero.");
            }
        });
    }
}