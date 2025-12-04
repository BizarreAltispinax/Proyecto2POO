package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.fases;

import com.mycompany.inicioprograma2.controlador.ControladorMatenimientoPreventivo;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import javax.swing.*;
import java.awt.*;

public class VentanaAsignarTareasFase extends JFrame {
    public VentanaAsignarTareasFase(ControladorMatenimientoPreventivo ctrlPrev,
                                    ControladorTarea ctrlTarea) {
        setTitle("Asignar Tareas a Fase");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,2,8,8));

        //Combo de fases
        JComboBox<String> comboFases = new JComboBox<>();

        //Llenar combo con las fases existentes
        for (int i = 0; i < ctrlPrev.getFases().size(); i++) {
            Fase f = ctrlPrev.getFases().get(i);
            comboFases.addItem("Fase " + (i + 1) + " - Frecuencia: " +
                    f.getTipoFrecuencia() + " cada " + f.getMedidorFrecuencia());
        }

        //Combo de tareas
        JComboBox<String> comboTareas = new JComboBox<>();
        for (Tarea t : ctrlTarea.getTareas()) {
            comboTareas.addItem(t.getId() + " - " + t.getDescripcion());
        }

        JButton btnAgregar = new JButton("Agregar Tarea");
        JButton btnCerrar = new JButton("Cerrar");

        // Layout
        add(new JLabel("Seleccione la fase:"));
        add(comboFases);

        add(new JLabel("Seleccione la tarea:"));
        add(comboTareas);

        add(btnAgregar);
        add(btnCerrar);

        // Cerrar ventana
        btnCerrar.addActionListener(e -> dispose());

        // Acción: agregar tarea
        btnAgregar.addActionListener(e -> {
            int indexFase = comboFases.getSelectedIndex();
            int indexTarea = comboTareas.getSelectedIndex();

            if (indexFase == -1 || indexTarea == -1) {
                JOptionPane.showMessageDialog(this,
                        "Debe seleccionar una fase y una tarea.");
                return;
            }

            Tarea tareaSeleccionada = ctrlTarea.getTareas().get(indexTarea);

            boolean ok = ctrlPrev.agregarTareaAFase(indexFase, tareaSeleccionada);

            if (ok) {
                JOptionPane.showMessageDialog(
                        this,
                        "Tarea agregada correctamente a la fase."
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Error al agregar la tarea."
                );
            }
        });
    }
}
