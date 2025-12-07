package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorOrdenPreventiva;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.OrdenTrabajoPreventivo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 * Ventana que muestra la lista de órdenes de mantenimiento preventivo.
 * <p>
 * Presenta todas las órdenes existentes en una tabla no editable, mostrando su
 * ID, equipo, fase, estado y fecha programada. Permite cerrar la ventana.
 * </p>
 * 
 * @author Usuario
 */
public class VentanaListaOrdenes extends JFrame {
    
    /**
     * Constructor que inicializa la ventana y carga la tabla con las órdenes.
     *
     * @param control Controlador encargado de gestionar las órdenes preventivas
     */
    public VentanaListaOrdenes(ControladorOrdenPreventiva control) {
        setTitle("Lista de Órdenes de Matenimiento Preventivo");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Modelo de tabla con columnas fijas y no editables
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{
                        "ID",
                        "Equipo",
                        "Fase",
                        "Estado",
                        "Programada"
                }, 0
        ) {
            @Override public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        JTable tabla = new JTable(modelo);

        for (OrdenTrabajoPreventivo o : control.getOrdenes()) {
            modelo.addRow(new Object[]{
                    o.getId(),
                    o.getIdEquipo(),
                    o.getIndiceFase(),
                    o.getEstado(),
                    o.getFechaAgendada()
            });
        }

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        // Botón para cerrar la ventana
        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(e -> dispose());
        add(cerrar, BorderLayout.SOUTH);
    }
}
