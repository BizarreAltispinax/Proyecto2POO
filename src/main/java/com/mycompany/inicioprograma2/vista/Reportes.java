/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista;

/**
 * Ventana de reportes generales del sistema. Permite acceder a los reportes
 * relacionados con los equipos y regresar a la ventana principal del programa.
 *
 * Esta clase recibe el controlador de equipos para enviarlo a la ventana
 * específica de ReportesEquipos, preservando el patrón MVC.
 *
 * @author Usuario
 */
import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.vista.equipos.ReportesEquipos;

import javax.swing.*;
import java.awt.*;

public class Reportes extends JFrame {

    private ControladorEquipo controlador;
    
    /**
     * Crea la ventana de reportes generales, permitiendo navegar hacia los
     * reportes de equipos o volver a la ventana principal.
     *
     * @param ctlEquipos controlador encargado de gestionar los equipos
     * @param prin referencia a la ventana principal para volver a ella
     */
    public Reportes(ControladorEquipo ctlEquipos, InicioPrograma2 prin) {
     
        // Configuración de la ventana
        setTitle("Gestión de Equipos");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JButton btnEquipos = new JButton("Equipos");
        JButton btnSalir = new JButton("Salir");
        // Se agregan los botones a la ventana
        add(btnEquipos);
        add(btnSalir);
        // Abre la ventana de reportes específicos de equipos
        btnEquipos.addActionListener(e -> new ReportesEquipos(ctlEquipos).setVisible(true));
        // Cierra esta ventana y muestra la ventana principal nuevamente
        btnSalir.addActionListener(e -> {
        this.dispose();
        prin.setVisible(true);
        });
    }
}
