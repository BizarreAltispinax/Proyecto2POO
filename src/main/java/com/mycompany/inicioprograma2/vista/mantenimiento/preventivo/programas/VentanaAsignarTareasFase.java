package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorMatenimientoPreventivo;
import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import javax.swing.*;
import java.awt.*;

public class VentanaAsignarTareasFase extends JFrame {
    public VentanaAsignarTareasFase(int idEquipo, ControladorProgramasPreventivos controladorProg, ControladorTarea controladorTarea, VentanaListaFases ventanaPadre) {
        setTitle("Asignar Tareas a Fase");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4,2,10,10));

        //Combo de fases
        JComboBox<String> comboFases = new JComboBox<>();
        int index = 0;
        for (Fase f : controladorProg.getFases(idEquipo)) {
            comboFases.addItem("Fase " + index + " - " +
                    f.getTipoFrecuencia() + " (" + f.getTareas().size() + " tareas)");
        }

        //Combo de tareas
        JComboBox<String> comboTareas = new JComboBox<>();
        for (Tarea t : controladorTarea.getTareas()) {
            comboTareas.addItem(t.getId() + " - " + t.getDescripcion());
        }

        JButton btnAsignar = new JButton("Asignar Tarea");
        JButton btnCerrar = new JButton("Cerrar");

        add(new JLabel("Seleccione Fase:"));
        add(new JLabel("Seleccione Tarea:"));

        add(comboFases);
        add(comboTareas);
        add(btnAsignar);
        add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());

        btnAsignar.addActionListener(e -> {
            int indiceFase = comboFases.getSelectedIndex();
            int indiceTarea = comboTareas.getSelectedIndex();

            if (indiceFase == -1 || indiceTarea == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione fase y tarea");
                return;
            }

            Tarea tarea = controladorTarea.getTareas().get(indiceTarea);

            controladorProg.agregarTareaAFase(idEquipo, indiceFase, tarea);

            if (ventanaPadre != null)
                ventanaPadre.cargarFases();

            JOptionPane.showMessageDialog(this, "Tarea asignada corrcetamente");
        });
    }
}
