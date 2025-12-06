package com.mycompany.inicioprograma2.vista.mantenimiento;

import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.VentanaPrincipalMantePreventivo;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalMantenimiento extends JFrame {
    public VentanaPrincipalMantenimiento() {
        setTitle("Mantenimiento");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));

        JButton btnPreventivo = new JButton("Programa de Mantenimiento Preventivo");
        add(btnPreventivo);

        JButton btnCorrectivo = new JButton("Programa de Mantenimiento Correctivo");
        add(btnCorrectivo);

        btnPreventivo.addActionListener(e -> {
            new VentanaPrincipalMantePreventivo().setVisible(true);
            this.dispose();   //Opcion A
        });
//        btnCorrectivo.addActionListener(e -> {
//            new VentanaPrincipalManteCorrectivo().setVisible(true);
//            this.dispose();   //Opcion B
//        });
    }
}
