package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo;

import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.fases.VentanaPrincipalFases;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas.VentanaPrincipalTareas;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalMantePreventivo extends JFrame {
    public VentanaPrincipalMantePreventivo() {
        setTitle("Programa de Mantenimiento Preventivo");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JButton btnFases = new JButton("Fases");
        add(btnFases);

        JButton btnTareas = new JButton("Tareas");
        add(btnTareas);

        JButton btnOrdenes = new JButton("Órdenes");
        add(btnOrdenes);

        btnFases.addActionListener(e -> {
            new VentanaPrincipalFases().setVisible(true);
            this.dispose();   //Opcion A
        });
        btnTareas.addActionListener(e -> {
            new VentanaPrincipalTareas().setVisible(true);
            this.dispose();   //Opcion B
        });
//        btnOrdenes.addActionListener(e -> {
//            new VentanaPrincipalOrdenes().setVisible(true);
//            this.dispose();   //Opcion C
//        });
    }
}
