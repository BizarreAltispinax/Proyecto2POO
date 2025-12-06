package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;

import javax.swing.*;
import java.awt.*;

public class VentanaGestionPrograma extends JFrame {
    private final ControladorProgramasPreventivos controlador;
    private final int idEquipo;
    private final ControladorTarea controladorTarea;

    public VentanaGestionPrograma(ControladorProgramasPreventivos controlador, int idEquipo) {
        this.controlador = controlador;
        this.idEquipo = idEquipo;
        this.controladorTarea = new ControladorTarea();

        setTitle("Gestión del Programa Preventivo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,1,10,10));

        JButton btnCrearFase = new JButton("Crear Nueva Fase");
        JButton btnListarFases = new JButton("Ver / Modificar / Eliminar Fases");
        JButton btnAsignarTareas = new JButton("Asignar Tareas a Fase");
        JButton btnCerrar = new JButton("Cerrar");

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
