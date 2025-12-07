package com.mycompany.inicioprograma2.vista.mantenimiento;

import com.mycompany.inicioprograma2.controlador.*;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;
import com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.VentanaPrincipalManteCorrectivo;
import com.mycompany.inicioprograma2.vista.mantenimiento.fallas.VentanaPrincipalFallas;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.VentanaPrincipalMantePreventivo;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalMantenimiento extends JFrame {
    public VentanaPrincipalMantenimiento(ControladorEquipo ctlEquipos, ControladorFalla ctlFallas, ControladorMatenimientoPreventivo ctlMP, ControladorOrdenPreventiva ctlOP, ControladorOrdenCorrectiva ctlOC, ControladorProgramasPreventivos ctlPP, ControladorTarea ctlTarea, InicioPrograma2 prin) {
        setTitle("Mantenimiento");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JButton btnPreventivo = new JButton("Programa de Mantenimiento Preventivo");
        add(btnPreventivo);

        JButton btnCorrectivo = new JButton("Programa de Mantenimiento Correctivo");
        add(btnCorrectivo);

        JButton btnFallas = new JButton("Registro de Fallas");
        add(btnFallas);

        JButton btnSalir = new JButton("Salir");
        add(btnSalir);
        
        btnPreventivo.addActionListener(e -> {
            new VentanaPrincipalMantePreventivo(ctlEquipos, ctlFallas, ctlMP, ctlOP, ctlPP, ctlTarea, prin).setVisible(true);
            this.dispose();   //Opcion A
        });
        btnCorrectivo.addActionListener(e -> {
            new VentanaPrincipalManteCorrectivo(ctlOC, ctlEquipos, ctlFallas, prin).setVisible(true);
            this.dispose();   //Opcion B
        });
        btnFallas.addActionListener(e -> {
            new VentanaPrincipalFallas(ctlEquipos, ctlFallas).setVisible(true);
        });
        btnSalir.addActionListener(e -> {
            this.dispose();   //Opcion D
            prin.setVisible(true);
        });
    }
}
