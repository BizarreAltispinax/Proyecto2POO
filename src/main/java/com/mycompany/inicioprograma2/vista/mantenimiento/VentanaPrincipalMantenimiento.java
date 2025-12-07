package com.mycompany.inicioprograma2.vista.mantenimiento;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorFalla;
import com.mycompany.inicioprograma2.controlador.ControladorMatenimientoPreventivo;
import com.mycompany.inicioprograma2.controlador.ControladorOrdenPreventiva;
import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;
import com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.VentanaPrincipalManteCorrectivo;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.VentanaPrincipalMantePreventivo;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalMantenimiento extends JFrame {
    public VentanaPrincipalMantenimiento(ControladorEquipo ctlEquipos,ControladorFalla ctlFallas,ControladorMatenimientoPreventivo ctlMP,ControladorOrdenPreventiva ctlOP,ControladorProgramasPreventivos ctlPP,ControladorTarea ctlTarea,InicioPrograma2 prin) {
        setTitle("Mantenimiento");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JButton btnPreventivo = new JButton("Programa de Mantenimiento Preventivo");
        add(btnPreventivo);

        JButton btnCorrectivo = new JButton("Programa de Mantenimiento Correctivo");
        add(btnCorrectivo);
        
        JButton btnSalir = new JButton("Salir");
        add(btnSalir);
        
        btnPreventivo.addActionListener(e -> {
            new VentanaPrincipalMantePreventivo(ctlEquipos, ctlFallas, ctlMP, ctlOP, ctlPP, ctlTarea, prin).setVisible(true);
            this.dispose();   //Opcion A
        });
        btnCorrectivo.addActionListener(e -> {
            new VentanaPrincipalManteCorrectivo(ctlEquipos, ctlFallas, ctlMP, ctlOP, ctlPP, ctlTarea, prin).setVisible(true);
            this.dispose();   //Opcion B
        });
        btnSalir.addActionListener(e -> {
            
            this.dispose();   //Opcion A
            prin.setVisible(true);
        });
    }
}
