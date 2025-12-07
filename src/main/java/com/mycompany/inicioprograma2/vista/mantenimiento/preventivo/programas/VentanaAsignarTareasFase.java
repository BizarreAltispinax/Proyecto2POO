package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorMatenimientoPreventivo;
import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import javax.swing.*;
import java.awt.*;
/**
 * Ventana para asignar tareas a una fase de un programa de mantenimiento preventivo.
 * <p>
 * Permite seleccionar una fase y una tarea, y asignar la tarea a la fase correspondiente.
 * Después de asignar, actualiza la ventana padre que lista las fases.
 * </p>
 * 
 * @author Usuario
 */
public class VentanaAsignarTareasFase extends JFrame {
     /**
     * Constructor de la ventana para asignar tareas a una fase.
     *
     * @param idEquipo        ID del equipo al que pertenece la fase
     * @param controladorProg Controlador de programas preventivos
     * @param controladorTarea Controlador de tareas
     * @param ventanaPadre    Ventana que lista las fases (para refrescar después de asignar)
     */
    public VentanaAsignarTareasFase(int idEquipo, ControladorProgramasPreventivos controladorProg, ControladorTarea controladorTarea, VentanaListaFases ventanaPadre) {
        setTitle("Asignar Tareas a Fase");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4,2,10,10));

        //Combo de fases
        JComboBox<String> comboFases = new JComboBox<>();
        int index = 1;
        for (Fase f : controladorProg.getFases(idEquipo)) {
            comboFases.addItem("Fase " + index + " - " +
                    f.getTipoFrecuencia() + " (" + f.getTareas().size() + " tareas)");
        }

        //Combo de tareas
        JComboBox<String> comboTareas = new JComboBox<>();
        for (Tarea t : controladorTarea.getTareas()) {
            comboTareas.addItem(t.getId() + " - " + t.getDescripcion());
        }
        // Botones
        JButton btnAsignar = new JButton("Asignar Tarea");
        JButton btnCerrar = new JButton("Cerrar");

        add(new JLabel("Seleccione Fase:"));
        add(new JLabel("Seleccione Tarea:"));

        add(comboFases);
        add(comboTareas);
        add(btnAsignar);
        add(btnCerrar);
        // Acción de cerrar ventana
        btnCerrar.addActionListener(e -> dispose());
        // Acción de asignar tarea a la fase seleccionada
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
