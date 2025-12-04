/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.fases;

/**
 *
 * @author Usuario
 */
import com.mycompany.inicioprograma2.controlador.ControladorMatenimientoPreventivo;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalFases extends JFrame {
    private ControladorMatenimientoPreventivo controlador;
    private ControladorTarea controlador2;

    public VentanaPrincipalFases() {
        controlador = new ControladorMatenimientoPreventivo();
        controlador2 = new ControladorTarea();

        setTitle("Fases de Mantenimiento Preventivo");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        JButton btnCrear = new JButton("Crear Fase");
        JButton btnAsignar = new JButton("Asignar Tareas a Fase");
        JButton btnConsultar = new JButton("Consultar Fases");
        JButton btnModificar = new JButton("Modificar Fase");
        JButton btnEliminar = new JButton("Eliminar Fase");

        add(btnCrear);
        add(btnAsignar);
        add(btnConsultar);
        add(btnModificar);
        add(btnEliminar);

        btnCrear.addActionListener(e -> new VentanaCrearFase(controlador).setVisible(true));
        btnAsignar.addActionListener(e -> new VentanaAsignarTareasFase(controlador, controlador2).setVisible(true));
        //btnConsultar.addActionListener(e -> new VentanaListaFases(controlador).setVisible(true));
        //btnModificar.addActionListener(e -> new VentanaModificarFase(controlador).setVisible(true));
        //btnEliminar.addActionListener(e -> new VentanaEliminarFase(controlador).setVisible(true));
    }
}