package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas;

import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 * Ventana para mostrar la lista de tareas de mantenimiento preventivo.
 * <p>
 * Presenta todas las tareas registradas en una tabla no editable con
 * su ID y descripción.
 * </p>
 * 
 * @author Usuario
 */
public class VentanaListaTareas extends JFrame {
     /**
     * Constructor que inicializa la ventana y muestra las tareas.
     * 
     * @param controlador Controlador de tareas para obtener la lista de tareas
     */
    public VentanaListaTareas(ControladorTarea controlador) {
        setTitle("Lista de Tareas");
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

        add(scroll, BorderLayout.CENTER);
    }
}
