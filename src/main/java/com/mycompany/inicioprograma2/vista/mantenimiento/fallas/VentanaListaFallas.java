package com.mycompany.inicioprograma2.vista.mantenimiento.fallas;

import com.mycompany.inicioprograma2.controlador.ControladorFalla;
import com.mycompany.inicioprograma2.modelo.mantenimiento.fallas.Falla;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaFallas extends JFrame {

    private final ControladorFalla controlador;
    private final int idEquipo;   // ← NUEVO
    private final DefaultTableModel modelo;
    private final JTable tabla;

    public VentanaListaFallas(ControladorFalla controlador, int idEquipo) {
        this.controlador = controlador;
        this.idEquipo = idEquipo;

        setTitle("Fallas del Equipo " + idEquipo);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(
                new Object[]{"ID Falla", "Descripción"}, 0
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        cargarTabla();

        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnCerrar.addActionListener(e -> dispose());
        btnEliminar.addActionListener(e -> eliminarSeleccionada());
        btnModificar.addActionListener(e -> modificarSeleccionada());
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        for (Falla f : controlador.getFallasEquipo(idEquipo)) {
            modelo.addRow(new Object[]{
                    f.getId(),
                    f.getDescripcion()
            });
        }
    }

    private void eliminarSeleccionada() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una falla.");
            return;
        }

        int idFalla = (int) modelo.getValueAt(fila, 0);

        controlador.eliminarFalla(idEquipo, idFalla);
        controlador.guardar();
        cargarTabla();

        JOptionPane.showMessageDialog(this, "Falla eliminada.");
    }

    private void modificarSeleccionada() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una falla.");
            return;
        }

        int idFalla = (int) modelo.getValueAt(fila, 0);

        new VentanaModificarFallas(controlador, idEquipo, idFalla, this).setVisible(true);
    }

    public void refrescar() {
        cargarTabla();
    }
}