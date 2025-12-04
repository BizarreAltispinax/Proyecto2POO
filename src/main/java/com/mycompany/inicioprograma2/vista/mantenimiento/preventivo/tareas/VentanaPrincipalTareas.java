/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas;

/**
 *
 * @author Usuario
 */
import com.mycompany.inicioprograma2.controlador.ControladorMatenimientoPreventivo;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.fases.VentanaCrearFase;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalTareas extends JFrame {
    private ControladorTarea controlador;

    public VentanaPrincipalTareas() {
        controlador = new ControladorTarea();

        setTitle("Fases de Mantenimiento Preventivo");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JButton btnCrear = new JButton("Crear Tarea");
        JButton btnLista = new JButton("Lista de Tareas");
        JButton btnModificar = new JButton("Modificar Tarea");
        JButton btnEliminar = new JButton("Eliminar Tarea");

        add(btnCrear);
        add(btnLista);
        add(btnModificar);
        add(btnEliminar);

        btnCrear.addActionListener(e -> new VentanaCrearTarea(controlador).setVisible(true));
        btnLista.addActionListener(e -> new VentanaListaTareas(controlador).setVisible(true));
        btnModificar.addActionListener(e -> new VentanaModificarTarea(controlador).setVisible(true));
        btnEliminar.addActionListener(e -> new VentanaEliminarTarea(controlador).setVisible(true));
    }
}