package com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorOrdenCorrectiva;
import com.mycompany.inicioprograma2.modelo.Equipos;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearOrdenCorrectiva extends JFrame {

    public VentanaCrearOrdenCorrectiva(ControladorOrdenCorrectiva ctrlOrden,
                                       ControladorEquipo ctrlEquipos) {

        setTitle("Crear Orden Correctiva");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        JComboBox<String> comboEquipos = new JComboBox<>();
        for (Equipos e : ctrlEquipos.getEquipos()) {
            comboEquipos.addItem(e.getId() + " - " + e.getDescripcion());
        }

        JTextField txtDescripcion = new JTextField();
        JComboBox<String> comboPrioridad = new JComboBox<>(new String[]{
                "ALTA", "MEDIA", "BAJA"
        });

        JButton btnCrear = new JButton("Crear Orden");
        JButton btnCerrar = new JButton("Cerrar");

        add(new JLabel("Equipo:"));
        add(comboEquipos);

        add(new JLabel("Descripción del problema:"));
        add(txtDescripcion);

        add(new JLabel("Prioridad:"));
        add(comboPrioridad);

        add(new JLabel());
        add(new JLabel());

        add(btnCrear);
        add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());

        btnCrear.addActionListener(e -> {
            try {
                int idEquipo = Integer.parseInt(comboEquipos.getSelectedItem().toString().split(" - ")[0]);

                boolean ok = ctrlOrden.crearOrden(
                        idEquipo,
                        txtDescripcion.getText(),
                        comboPrioridad.getSelectedItem().toString()
                );

                if (ok) {
                    ctrlOrden.guardar();
                    JOptionPane.showMessageDialog(this, "Orden creada exitosamente");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo crear la orden");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}