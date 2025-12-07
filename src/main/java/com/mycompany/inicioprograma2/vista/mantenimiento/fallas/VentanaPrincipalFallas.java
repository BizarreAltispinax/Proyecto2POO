package com.mycompany.inicioprograma2.vista.mantenimiento.fallas;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorFalla;
import com.mycompany.inicioprograma2.modelo.Equipos;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalFallas extends JFrame {

    public VentanaPrincipalFallas(ControladorEquipo ctrlEquipos, ControladorFalla controladorFalla) {

        setTitle("Gestión de Fallas");
        setSize(800, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Selector de equipo
        JComboBox<Equipos> comboEquipos = new JComboBox<>();

        for (Equipos eq : ctrlEquipos.getEquipos()) {
            comboEquipos.addItem(eq);
        }

        JButton btnCrear = new JButton("Registrar Nueva Falla");
        JButton btnListar = new JButton("Ver / Modificar / Eliminar Fallas");
        JButton btnCerrar = new JButton("Cerrar");

        add(new JLabel("Seleccione un equipo:", SwingConstants.CENTER));
        add(comboEquipos);
        add(btnCrear);
        add(btnListar);
        add(btnCerrar);

        // Crear falla
        btnCrear.addActionListener(e -> {
            Equipos sel = (Equipos) comboEquipos.getSelectedItem();
            if (sel == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un equipo.");
                return;
            }

            new VentanaCrearFallas(controladorFalla, sel.getId(), null).setVisible(true);
        });

        // Listar fallas
        btnListar.addActionListener(e -> {
            Equipos sel = (Equipos) comboEquipos.getSelectedItem();
            if (sel == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un equipo.");
                return;
            }

            new VentanaListaFallas(controladorFalla, sel.getId()).setVisible(true);
        });

        btnCerrar.addActionListener(e -> dispose());
    }
}