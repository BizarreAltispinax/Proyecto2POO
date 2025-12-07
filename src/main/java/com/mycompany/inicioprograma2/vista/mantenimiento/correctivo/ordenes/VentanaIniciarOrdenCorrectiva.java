package com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorOrdenCorrectiva;
import com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.ordenes.EstadoOrdenCorrectiva;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class VentanaIniciarOrdenCorrectiva extends JFrame {

    public VentanaIniciarOrdenCorrectiva(ControladorOrdenCorrectiva ctrlOrden) {

        setTitle("Iniciar Orden Correctiva");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JComboBox<String> combo = new JComboBox<>();
        for (var o : ctrlOrden.getOrdenes()) {
            if (o.getEstado() == EstadoOrdenCorrectiva.ABIERTA) {
                combo.addItem(o.getId() + " - Equipo " + o.getIdEquipo());
            }
        }

        JTextField txtFecha = new JTextField(LocalDate.now().toString());

        JButton btnIniciar = new JButton("Iniciar");
        JButton btnCerrar = new JButton("Cerrar");

        add(new JLabel("Orden abierta:"));
        add(combo);

        add(new JLabel("Fecha de inicio:"));
        add(txtFecha);

        add(btnIniciar);
        add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());

        btnIniciar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(combo.getSelectedItem().toString().split(" - ")[0]);

                boolean ok = ctrlOrden.iniciarOrden(id,
                        LocalDate.parse(txtFecha.getText()));

                if (ok) {
                    ctrlOrden.guardar();
                    JOptionPane.showMessageDialog(this, "Orden iniciada");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo iniciar");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}