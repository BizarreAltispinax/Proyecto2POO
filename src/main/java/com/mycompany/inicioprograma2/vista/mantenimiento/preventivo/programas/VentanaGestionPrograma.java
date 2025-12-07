package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal para la gestión del programa preventivo de un equipo específico.
 * <p>
 * Permite al usuario crear nuevas fases, listar/modificar/eliminar fases existentes,
 * y asignar tareas a cada fase del programa.
 * </p>
 * 
 * @author Usuario
 */
public class VentanaGestionPrograma extends JFrame {
    private final ControladorProgramasPreventivos controlador;
    private final int idEquipo;
    private final ControladorTarea controladorTarea;
     /**
     * Constructor de la ventana de gestión del programa preventivo.
     *
     * @param controlador Controlador de programas preventivos
     * @param idEquipo    ID del equipo cuya gestión de programa se realizará
     */
    public VentanaGestionPrograma(ControladorProgramasPreventivos controlador, int idEquipo) {
        this.controlador = controlador;
        this.idEquipo = idEquipo;
        this.controladorTarea = new ControladorTarea();

        setTitle("Gestión del Programa Preventivo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,1,10,10));
        // Botones de la interfaz
        JButton btnCrearFase = new JButton("Crear Nueva Fase");
        JButton btnListarFases = new JButton("Ver / Modificar / Eliminar Fases");
        JButton btnAsignarTareas = new JButton("Asignar Tareas a Fase");
        JButton btnCerrar = new JButton("Cerrar");
        // Añadir componentes al layout
        add(new JLabel("Programa del Equipo #" + idEquipo, SwingConstants.CENTER));
        add(btnCrearFase);
        add(btnListarFases);
        add(btnAsignarTareas);
        add(btnCerrar);

        //Botones
        btnCrearFase.addActionListener(e ->
                new VentanaCrearFase(idEquipo, controlador).setVisible(true));

        btnListarFases.addActionListener(e ->
                new VentanaListaFases(idEquipo, controlador).setVisible(true));

        btnAsignarTareas.addActionListener(e ->
                new VentanaAsignarTareasFase(idEquipo, controlador, controladorTarea, null).setVisible(true));

        btnCerrar.addActionListener(e -> dispose());
    }
}
