package com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorOrdenCorrectiva;
import com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.ordenes.EstadoOrdenCorrectiva;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class VentanaCancelarOrdenCorrectiva extends JFrame {

    public VentanaCancelarOrdenCorrectiva(ControladorOrdenCorrectiva ctrlOrden) {

        setTitle("Cancelar Orden Correctiva");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JComboBox<String> combo = new JComboBox<>();
        for (var o : ctrlOrden.getOrdenes()) {
            if (o.getEstado() != EstadoOrdenCorrectiva.FINALIZADA) {
                combo.addItem(o.getId() + " - Equipo " + o.getIdEquipo());
            }
        }

        JTextField txtFecha = new JTextField(LocalDate.now().toString());
        JTextField txtRazon = new JTextField();

        JButton btnCancelar = new JButton("Cancelar Orden");
        JButton btnCerrar = new JButton("Cerrar");

        add(new JLabel("Orden:"));
        add(combo);

        add(new JLabel("Fecha cancelación:"));
        add(txtFecha);

        add(new JLabel("Razón:"));
        add(txtRazon);

        add(btnCancelar);
        add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());

        btnCancelar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(combo.getSelectedItem().toString().split(" - ")[0]);

                boolean ok = ctrlOrden.cancelarOrden(
                        id,
                        LocalDate.parse(txtFecha.getText()),
                        txtRazon.getText()
                );

                if (ok) {
                    ctrlOrden.guardar();
                    JOptionPane.showMessageDialog(this, "Orden cancelada");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo cancelar");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}