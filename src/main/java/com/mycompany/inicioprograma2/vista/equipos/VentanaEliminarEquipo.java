/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.equipos;

/**
 *
 * @author Usuario
 */
import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.modelo.Equipos;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana que permite al usuario eliminar un equipo existente.
 * Muestra un JComboBox con todos los equipos registrados y un botón
 * para confirmar su eliminación. Una vez eliminado, la ventana se cierra.
 * 
 * Recibe una instancia de ControladorEquipo para acceder a la lista de equipos
 * y realizar la operación de eliminación.
 * 
 * @author Usuario
 */
public class VentanaEliminarEquipo extends JFrame {
     /**
     * Constructor que construye la ventana de eliminación de equipos.
     * Llena un JComboBox con los equipos administrados por el controlador.
     *
     * @param controlador controlador que provee acceso a los equipos y métodos de eliminación
     */
    public VentanaEliminarEquipo(ControladorEquipo controlador) {
        setTitle("Eliminar Equipo");
        setSize(300, 200);
        setLocationRelativeTo(null);
        // ComboBox donde se listan los equipos disponibles
        JComboBox<Equipos> combo = new JComboBox<>();
        controlador.getEquipos().forEach(combo::addItem);

        JButton btnEliminar = new JButton("Eliminar");

        add(combo, BorderLayout.CENTER);
        add(btnEliminar, BorderLayout.SOUTH);
        // Acción del botón Eliminar
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

