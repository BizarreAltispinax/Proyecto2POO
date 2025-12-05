package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.fases;

import com.mycompany.inicioprograma2.controlador.ControladorMatenimientoPreventivo;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaFases extends JFrame {
    public VentanaListaFases(ControladorMatenimientoPreventivo controlador) {
        setTitle("Eliminar Fase");
        setSize(800, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Tabla no editable
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{
                        "Tipo de Frecuencia",
                        "Medidor de Frecuencia",
                        "Cantidad de Ciclos",
                        "Horas Estimadas",
                        "Tareas"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        //Ancho de columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(60);

        //Llenar datos
        for (Fase f : controlador.getFases()) {
            modelo.addRow(new Object[]{
                    f.getTipoFrecuencia(),
                    f.getMedidorFrecuencia(),
                    f.getCantidadCiclos(),
                    f.getHorasEstimadas(),
                    f.getTareas()
            });
        }

        //Botones
        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar Fase");
        JButton btnModificar = new JButton("Modificar Fase");
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnCerrar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnCerrar.addActionListener(e -> dispose());

        //Eliminar Fase
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una fase");
                return;
            }

            int confirmar = JOptionPane.showConfirmDialog(
                    this, "¿Eliminar la fase seleccionada?",
                    "Confirmar", JOptionPane.YES_NO_OPTION
            );

            if (confirmar != JOptionPane.YES_OPTION)
                return;

            boolean ok = controlador.eliminarFase(fila);

            if (ok) {
                modelo.removeRow(fila);
                JOptionPane.showMessageDialog(this, "Fase eliminada");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la fase seleccionada");
            }
        });

        //Modificar fase
        btnModificar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una fase");
                return;
            }

            //Abrir vetnana de modificacion de fase
            new VentanaModificarFase(controlador, fila).setVisible(true);
        });
    }
}
