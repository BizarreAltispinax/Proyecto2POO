/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista;

/**
 *
 * @author Usuario
 */
import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private ControladorEquipo controlador;

    public VentanaPrincipal() {
        controlador = new ControladorEquipo();

        setTitle("Gestión de Equipos");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JButton btnCrear = new JButton("Crear Equipo");
        JButton btnConsultar = new JButton("Consultar Equipos");
        JButton btnModificar = new JButton("Modificar Equipo");
        JButton btnEliminar = new JButton("Eliminar Equipo");

        add(btnCrear);
        add(btnConsultar);
        add(btnModificar);
        add(btnEliminar);

        btnCrear.addActionListener(e -> new VentanaCrearEquipo(controlador).setVisible(true));
        btnConsultar.addActionListener(e -> new VentanaConsultarEquipo(controlador).setVisible(true));
        btnModificar.addActionListener(e -> new VentanaModificarEquipo(controlador).setVisible(true));
        btnEliminar.addActionListener(e -> new VentanaEliminarEquipo(controlador).setVisible(true));
    }
}