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
import com.mycompany.inicioprograma2.vista.equipos.ReportesEquipos;

import javax.swing.*;
import java.awt.*;

public class Reportes extends JFrame {

    private ControladorEquipo controlador;

    public Reportes(ControladorEquipo ctlEquipos, InicioPrograma2 prin) {
     

        setTitle("Gestión de Equipos");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JButton btnEquipos = new JButton("Equipos");
        JButton btnSalir = new JButton("Salir");

        add(btnEquipos);
        add(btnSalir);

        btnEquipos.addActionListener(e -> new ReportesEquipos(ctlEquipos).setVisible(true));
        btnSalir.addActionListener(e -> {
        this.dispose();
        prin.setVisible(true);
        });
    }
}
