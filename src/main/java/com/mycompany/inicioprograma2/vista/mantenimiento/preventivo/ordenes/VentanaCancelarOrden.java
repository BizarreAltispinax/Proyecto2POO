package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorOrdenPreventiva;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.EstadoOrden;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class VentanaCancelarOrden extends JFrame {
    public VentanaCancelarOrden(ControladorOrdenPreventiva controlador) {
        setTitle("Cancelar Orden");
        setSize(400,250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4,2,10,10));

        JComboBox<String> combo = new JComboBox<>();

        for (var o : controlador.getOrdenes()) {
            if (o.getEstado() != EstadoOrden.TERMINADA) {
                combo.addItem(o.getId() + " - Equipo " + o.getIdEquipo());
            }
        }

        JTextField txtFecha = new JTextField(LocalDate.now().toString());
        JTextField txtRazon = new JTextField();

        JButton btnCancelar = new JButton("Cancelar Orden");
        JButton btnCerrar = new JButton("Cerrar");

        add(new JLabel("Orden terminada:"));
        add(combo);

        add(new JLabel("Fecha de cancelación:"));
        add(txtFecha);

        add(new JLabel("Razón:"));
        add(txtRazon);

        add(btnCancelar);
        add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());

        btnCancelar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(combo.getSelectedItem().toString().split(" - ")[0]);
                LocalDate fecha = LocalDate.parse(txtFecha.getText());

                boolean ok = controlador.cancelarOrden(
                        id, fecha, txtRazon.getText()
                );

                if (ok) {
                    controlador.guardar();
                    JOptionPane.showMessageDialog(this, "Orden cancelada");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo cancelar la orden");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error" + ex.getMessage());
            }
        });
    }
}
