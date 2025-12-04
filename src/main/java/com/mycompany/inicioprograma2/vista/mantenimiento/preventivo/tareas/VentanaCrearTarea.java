package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas;

import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.TipoFrecuencia;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearTarea extends JFrame {
    public VentanaCrearTarea(ControladorTarea controlador) {
        setTitle("Crear Tarea");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2, 6, 6));

        JTextField txtDescripcion = new JTextField();

        add(new JLabel("Descripción de la Tarea:")); add(txtDescripcion);

        JButton btnGuardar = new JButton("Guardar");
        add(btnGuardar);

        JButton btnCerrar = new JButton("Cerrar");
        add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> {

            try {
                String descripcion = txtDescripcion.getText();

                boolean ok = controlador.agregarTarea(
                        descripcion
                );

                if (ok)
                    JOptionPane.showMessageDialog(this, "Tarea agregada correctamente.");
                else
                    JOptionPane.showMessageDialog(this, "Error. Agregar descripción.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
