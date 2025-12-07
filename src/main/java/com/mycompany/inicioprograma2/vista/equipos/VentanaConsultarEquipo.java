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

import javax.swing.*;
import java.awt.*;

public class VentanaConsultarEquipo extends JFrame {

    private JTextArea area;
    private JTextField txtId;

    public VentanaConsultarEquipo(ControladorEquipo controlador) {

        setTitle("Consultar Equipos");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // -------------------------
        // PANEL SUPERIOR (ID + botón)
        // -------------------------
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());

        panelSuperior.add(new JLabel("ID:"));
        
        txtId = new JTextField(10);
        panelSuperior.add(txtId);

        JButton btnConsultar = new JButton("Consultar");
        panelSuperior.add(btnConsultar);

        JButton btnCerrar = new JButton("Cerrar");
        panelSuperior.add(btnCerrar);

        add(panelSuperior, BorderLayout.NORTH);

        // -------------------------
        // TEXT AREA
        // -------------------------
        area = new JTextArea();
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);

        // -------------------------
        // ACCIONES DE BOTONES
        // -------------------------

        // Acción Consultar
        btnConsultar.addActionListener(e -> {
            try {
                String Stringid = txtId.getText().trim();

                if (Stringid.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un ID.");
                    return;
                }
                int id = Integer.parseInt(txtId.getText().trim());

                //Limpiar la pantalla antes de la consulta
                area.setText("");

                // Mostrar resultado
                area.setText(controlador.consultarEquipo(id));

                txtId.setText("");

            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
            }
        });

        // Acción Cerrar
        btnCerrar.addActionListener(e -> dispose());
    }
}
