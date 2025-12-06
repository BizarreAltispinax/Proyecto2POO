package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas.VentanaPrincipalProgramas;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas.VentanaPrincipalTareas;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalMantePreventivo extends JFrame {
    public VentanaPrincipalMantePreventivo() {
        setTitle("Programa de Mantenimiento Preventivo");
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JButton btnFases = new JButton("Programas");
        add(btnFases);

        JButton btnTareas = new JButton("Tareas");
        add(btnTareas);

        JButton btnOrdenes = new JButton("Órdenes");
        add(btnOrdenes);

        btnFases.addActionListener(e -> {
            ControladorEquipo ctrlEquipos = new ControladorEquipo();
            ControladorTarea ctrlTareas = new ControladorTarea();
            ControladorProgramasPreventivos ctrlProgramas = new ControladorProgramasPreventivos(ctrlEquipos);

            new VentanaPrincipalProgramas(ctrlEquipos, ctrlTareas, ctrlProgramas).setVisible(true);
        });
        btnTareas.addActionListener(e -> {
            new VentanaPrincipalTareas().setVisible(true);
        });
//        btnOrdenes.addActionListener(e -> {
//            new VentanaPrincipalOrdenes().setVisible(true);
//            this.dispose();   //Opcion C
//        });
    }
}
