package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas;

import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;
import com.mycompany.inicioprograma2.vista.equipos.VentanaEliminarEquipo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaEliminarTarea extends JFrame {
    public VentanaEliminarTarea(ControladorTarea controlador) {
        setTitle("Eliminar Tarea");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID de tarea", "Descripción de la tarea"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //Bloquea edición de todas las celdas
            }
        };

        //Llenar la tabla con las tareas
        for (Tarea t : controlador.getTareas()) {
            modelo.addRow(new Object[]{
                    t.getId(),
                    t.getDescripcion()
            });
        }

        JTable tabla = new JTable(modelo);
        // Ajustes de ancho
        tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(500);
        JScrollPane scroll = new JScrollPane(tabla);

        // Botón eliminar
        JButton btnEliminar = new JButton("Eliminar tarea seleccionada");

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una tarea.");
                return;
            }

            int id = (int) modelo.getValueAt(fila, 0);

            if (controlador.eliminarTarea(id)) {
                modelo.removeRow(fila);
                JOptionPane.showMessageDialog(this, "Tarea eliminada.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar.");
            }
        });

        add(scroll, BorderLayout.CENTER);
        add(btnEliminar, BorderLayout.SOUTH);
    }
}
