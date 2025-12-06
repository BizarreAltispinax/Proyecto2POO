package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaFases extends JFrame {
    private final int idEquipo;
    private final ControladorProgramasPreventivos controlador;

    private final DefaultTableModel modelo;
    private final JTable tabla;

    public VentanaListaFases(int idEquipo, ControladorProgramasPreventivos controlador) {
        this.idEquipo = idEquipo;
        this.controlador = controlador;

        setTitle("Eliminar Fase");
        setSize(800, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Tabla no editable
        modelo = new DefaultTableModel(
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

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        cargarFases();

        //Botones inferiores
        JPanel panelBotones = new JPanel();
        JButton btnEliminar = new JButton("Eliminar Fase");
        JButton btnModificar = new JButton("Modificar Fase");
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        //Cerrar
        btnCerrar.addActionListener(e -> dispose());

        //Eliminar Fase
        btnEliminar.addActionListener(e -> eliminarFase());

        //Modificar Fase
        btnModificar.addActionListener(e -> abrirModificarFase());
    }

    public void cargarFases() {
        modelo.setRowCount(0);
        List<Fase> fases = controlador.getFases(idEquipo);

        for (Fase f : fases) {
            modelo.addRow(new Object[] {
                    f.getTipoFrecuencia(),
                    f.getMedidorFrecuencia(),
                    f.getCantidadCiclos(),
                    f.getHorasEstimadas(),
                    f.getTareas()
            });
        }
    }

    private void eliminarFase() {
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

        boolean ok = controlador.eliminarFase(idEquipo, fila);

        if (ok) {
            cargarFases();
            JOptionPane.showMessageDialog(this, "Fase eliminada correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la fase");
        }
    }

    private void abrirModificarFase() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fase");
            return;
        }

        new VentanaModificarFase(idEquipo, fila, controlador, this).setVisible(true);
    }
}
