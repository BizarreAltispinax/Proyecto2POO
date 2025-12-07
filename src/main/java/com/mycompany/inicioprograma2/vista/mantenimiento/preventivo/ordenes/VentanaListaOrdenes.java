package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorOrdenPreventiva;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.OrdenTrabajoPreventivo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaOrdenes extends JFrame {
    public VentanaListaOrdenes(ControladorOrdenPreventiva control) {
        setTitle("Lista de Órdenes de Matenimiento Preventivo");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

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

        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(e -> dispose());
        add(cerrar, BorderLayout.SOUTH);
    }
}
