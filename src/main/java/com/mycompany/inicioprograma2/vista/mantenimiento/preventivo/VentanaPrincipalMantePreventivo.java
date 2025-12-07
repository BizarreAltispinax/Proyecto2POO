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
/**
 * Ventana principal del módulo de Mantenimiento Preventivo.
 * Presenta las opciones para gestionar programas, tareas y órdenes
 * de mantenimiento preventivo, además de regresar al menú principal.
 *
 * <p>Esta ventana funciona como panel principal de navegación
 * para todas las funciones relacionadas al mantenimiento preventivo.</p>
 */
public class VentanaPrincipalMantePreventivo extends JFrame {
     /**
     * Constructor de la ventana principal del módulo de mantenimiento preventivo.
     *
     * @param ctrlEquipos Controlador de equipos.
     * @param ctlFallas Controlador de fallas.
     * @param ctlMP Controlador general de mantenimiento preventivo.
     * @param ctlOP Controlador de órdenes preventivas.
     * @param ctlPP Controlador de programas preventivos.
     * @param ctrlTareas Controlador de tareas de mantenimiento preventivo.
     * @param prin Ventana principal del sistema.
     */
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
        // Abrir gestión de programas preventivos
        btnFases.addActionListener(e -> {
            new VentanaPrincipalProgramas(ctrlEquipos, ctrlTareas, ctlPP,prin).setVisible(true);
        }); //Opcion A
        // Abrir gestión de tareas preventivas
        btnTareas.addActionListener(e -> {
            new VentanaPrincipalTareas(ctrlTareas,prin).setVisible(true);
        }); //Opcion B
        // Abrir gestión de órdenes preventivas
        btnOrdenes.addActionListener(e -> {
            new VentanaPrincipalOrdenesPreventivas(ctlOP, ctlPP, ctrlEquipos, ctlFallas).setVisible(true);
        }); //Opcion C
        // Volver al menú principal
        btnSalir.addActionListener(e -> {
            this.dispose();  
            prin.setVisible(true);
        }); //Opcion D
    }
}
