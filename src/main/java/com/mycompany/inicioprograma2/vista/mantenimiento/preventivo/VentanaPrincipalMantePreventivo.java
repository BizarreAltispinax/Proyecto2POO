package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorFalla;
import com.mycompany.inicioprograma2.controlador.ControladorMatenimientoPreventivo;
import com.mycompany.inicioprograma2.controlador.ControladorOrdenPreventiva;
import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.ordenes.VentanaPrincipalOrdenesPreventivas;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas.VentanaPrincipalProgramas;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas.VentanaPrincipalTareas;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalMantePreventivo extends JFrame {
    public VentanaPrincipalMantePreventivo(ControladorEquipo ctrlEquipos, ControladorFalla ctlFallas, ControladorMatenimientoPreventivo ctlMP, ControladorOrdenPreventiva ctlOP, ControladorProgramasPreventivos ctlPP, ControladorTarea ctrlTareas, InicioPrograma2 prin) {
        setTitle("Programa de Mantenimiento Preventivo");
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2));

        JButton btnFases = new JButton("Programas");
        add(btnFases);

        JButton btnTareas = new JButton("Tareas");
        add(btnTareas);

        JButton btnOrdenes = new JButton("Órdenes");
        add(btnOrdenes);
        
        JButton btnSalir = new JButton("Salir");
        add(btnSalir);

        btnFases.addActionListener(e -> {
            new VentanaPrincipalProgramas(ctrlEquipos, ctrlTareas, ctlPP,prin).setVisible(true);
        }); //Opcion A

        btnTareas.addActionListener(e -> {
            new VentanaPrincipalTareas(ctrlTareas,prin).setVisible(true);
        }); //Opcion B

        btnOrdenes.addActionListener(e -> {
            new VentanaPrincipalOrdenesPreventivas(ctlOP, ctlPP, ctrlEquipos, ctlFallas).setVisible(true);
        }); //Opcion C

        btnSalir.addActionListener(e -> {
            this.dispose();  
            prin.setVisible(true);
        }); //Opcion D
    }
}
