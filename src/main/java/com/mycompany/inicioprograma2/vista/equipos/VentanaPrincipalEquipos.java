/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.equipos;

/**
 *
 * @author Usuario
 */
import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;
import javax.swing.*;
import java.awt.*;
/**
 * Ventana principal del módulo de gestión de equipos.
 *
 * Esta ventana funciona como menú principal para acceder a las operaciones
 * CRUD: crear, consultar, modificar y eliminar equipos.
 *
 * Cada botón abre una ventana independiente correspondiente a cada función.
 * 
 * También permite volver a la ventana principal del sistema (InicioPrograma2).
 * 
 * No contiene lógica de negocio; únicamente controla la navegación entre ventanas.
 * 
 * @author Usuario
 */
public class VentanaPrincipalEquipos extends JFrame {

    private ControladorEquipo controlador;
     /**
     * Constructor que genera el menú principal de gestión de equipos.
     *
     * @param ctlEquipos instancia del controlador de equipos.
     * @param prin ventana principal del sistema a la que se regresa al cerrar.
     */
    public VentanaPrincipalEquipos(ControladorEquipo ctlEquipos, InicioPrograma2 prin) {


        setTitle("Gestión de Equipos");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));
        // Botones principales
        JButton btnCrear = new JButton("Crear Equipo");
        JButton btnConsultar = new JButton("Consultar Equipos");
        JButton btnModificar = new JButton("Modificar Equipo");
        JButton btnEliminar = new JButton("Eliminar Equipo");
        JButton btnSalir = new JButton("Salir");
        // Se agregan en orden al layout
        add(btnCrear);
        add(btnConsultar);
        add(btnModificar);
        add(btnEliminar);
        add(btnSalir);
        //Acciones de los botones
        btnCrear.addActionListener(e -> new VentanaCrearEquipo(ctlEquipos).setVisible(true));
        btnConsultar.addActionListener(e -> new VentanaConsultarEquipo(ctlEquipos).setVisible(true));
        btnModificar.addActionListener(e -> new VentanaModificarEquipo(ctlEquipos).setVisible(true));
        btnEliminar.addActionListener(e -> new VentanaEliminarEquipo(ctlEquipos).setVisible(true));
        btnSalir.addActionListener(e -> {
            this.dispose();
            prin.setVisible(true);
                });
    }
}