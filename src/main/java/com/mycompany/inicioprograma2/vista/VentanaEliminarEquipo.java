/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista;

/**
 *
 * @author Usuario
 */
import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.modelo.Equipos;

import javax.swing.*;
import java.awt.*;

public class VentanaEliminarEquipo extends JFrame {

    public VentanaEliminarEquipo(ControladorEquipo controlador) {
        setTitle("Eliminar Equipo");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JComboBox<Equipos> combo = new JComboBox<>();
        controlador.getEquipos().forEach(combo::addItem);

        JButton btnEliminar = new JButton("Eliminar");

        add(combo, BorderLayout.CENTER);
        add(btnEliminar, BorderLayout.SOUTH);

        btnEliminar.addActionListener(e -> {
            Equipos eq = (Equipos) combo.getSelectedItem();
            if (eq != null) {
                controlador.eliminarEquipo(eq.getId());
                JOptionPane.showMessageDialog(this, "Equipo eliminado.");
                dispose();
            }
        });
    }
}

