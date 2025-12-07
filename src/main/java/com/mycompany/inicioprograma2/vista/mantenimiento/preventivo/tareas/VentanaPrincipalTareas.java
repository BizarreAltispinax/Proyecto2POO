/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas;


import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de gestión de tareas de mantenimiento preventivo.
 * <p>
 * Permite al usuario crear, listar, modificar o eliminar tareas mediante botones.
 * Se comunica con el {@link ControladorTarea} para realizar las operaciones.
 * </p>
 * 
 * Autor: Usuario
 */
public class VentanaPrincipalTareas extends JFrame {
    private ControladorTarea controlador;
     /**
     * Constructor que inicializa la ventana principal de tareas.
     *
     * @param controlador Controlador de tareas para realizar las operaciones
     * @param prin        Ventana principal del programa (no usada aquí, solo referencia)
     */
    public VentanaPrincipalTareas(ControladorTarea controlador,InicioPrograma2 prin ) {


        setTitle("Fases de Mantenimiento Preventivo");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2));
        // ============================
        // Botones de la ventana
        // ============================
        JButton btnCrear = new JButton("Crear Tarea");
        JButton btnLista = new JButton("Lista de Tareas");
        JButton btnModificar = new JButton("Modificar Tarea");
        JButton btnEliminar = new JButton("Eliminar Tarea");
        JButton btnSalir = new JButton("Salir");
        
        // Agregar botones a la ventana
        add(btnCrear);
        add(btnLista);
        add(btnModificar);
        add(btnEliminar);
        add(btnSalir);
        
        // ============================
        // Eventos de los botones
        // ============================
        btnCrear.addActionListener(e -> new VentanaCrearTarea(controlador).setVisible(true));
        btnLista.addActionListener(e -> new VentanaListaTareas(controlador).setVisible(true));
        btnModificar.addActionListener(e -> new VentanaModificarTarea(controlador).setVisible(true));
        btnEliminar.addActionListener(e -> new VentanaEliminarTarea(controlador).setVisible(true));
        btnSalir.addActionListener(e -> {
            
            this.dispose();  

        });
    }
}