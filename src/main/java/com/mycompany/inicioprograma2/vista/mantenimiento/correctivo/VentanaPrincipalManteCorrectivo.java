package com.mycompany.inicioprograma2.vista.mantenimiento.correctivo;

import com.mycompany.inicioprograma2.controlador.*;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;
import com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.fallas.VentanaListaFallas;
import com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.fallas.VentanaPrincipalFallas;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas.VentanaPrincipalProgramas;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas.VentanaPrincipalTareas;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalManteCorrectivo extends JFrame {
    public VentanaPrincipalManteCorrectivo(ControladorEquipo ctrlEquipos, ControladorFalla ctlFallas, ControladorMatenimientoPreventivo ctlMP, ControladorOrdenPreventiva ctlOP, ControladorProgramasPreventivos ctlPP, ControladorTarea ctrlTareas, InicioPrograma2 prin) {
        setTitle("Programa de Mantenimiento Preventivo");
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2));

        JButton btnFallas = new JButton("Fallas");
        add(btnFallas);

        JButton btnOrdenes = new JButton("Órdenes");
        add(btnOrdenes);

        JButton btnSalir = new JButton("Salir");
        add(btnSalir);

        btnFallas.addActionListener(e -> {
            new VentanaPrincipalFallas(ctrlEquipos).setVisible(true);
        });
//        btnOrdenes.addActionListener(e -> {
//            new VentanaPrincipalOrdenes().setVisible(true);
//            this.dispose();   //Opcion C
//        });
        btnSalir.addActionListener(e -> {
            
            this.dispose();  
            prin.setVisible(true);
        });
    }
}
