package com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorOrdenCorrectiva;
import com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.ordenes.EstadoOrdenCorrectiva;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class VentanaFinalizarOrdenCorrectiva extends JFrame {

    public VentanaFinalizarOrdenCorrectiva(ControladorOrdenCorrectiva ctrlOrden) {

        setTitle("Finalizar Orden Correctiva");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(11, 2, 10, 10));

        JComboBox<String> combo = new JComboBox<>();
        for (var o : ctrlOrden.getOrdenes()) {
            if (o.getEstado() == EstadoOrdenCorrectiva.EN_PROCESO) {
                combo.addItem(o.getId() + " - Equipo " + o.getIdEquipo());
            }
        }

        JTextField txtFecha = new JTextField(LocalDate.now().toString());
        JTextField txtHoras = new JTextField();
        JTextField txtManoObra = new JTextField();
        JTextField txtMateriales = new JTextField();
        JTextField txtRepuestos = new JTextField();
        JTextField txtObs = new JTextField();
        JTextField txtCausa = new JTextField();
        JTextField txtAcciones = new JTextField();

        JButton btnFinalizar = new JButton("Finalizar");
        JButton btnCerrar = new JButton("Cerrar");

        add(new JLabel("Orden en proceso:"));
        add(combo);

        add(new JLabel("Fecha final:"));
        add(txtFecha);

        add(new JLabel("Horas trabajadas:"));
        add(txtHoras);

        add(new JLabel("Costo mano de obra:"));
        add(txtManoObra);

        add(new JLabel("Costo materiales:"));
        add(txtMateriales);

        add(new JLabel("Repuestos utilizados:"));
        add(txtRepuestos);

        add(new JLabel("Observaciones:"));
        add(txtObs);

        add(new JLabel("Causa raíz:"));
        add(txtCausa);

        add(new JLabel("Acciones tomadas:"));
        add(txtAcciones);

        add(btnFinalizar);
        add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());

        btnFinalizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(combo.getSelectedItem().toString().split(" - ")[0]);

                boolean ok = ctrlOrden.finalizarOrden(
                        id,
                        LocalDate.parse(txtFecha.getText()),
                        Float.parseFloat(txtHoras.getText()),
                        Integer.parseInt(txtManoObra.getText()),
                        Integer.parseInt(txtMateriales.getText()),
                        txtRepuestos.getText(),
                        txtObs.getText(),
                        txtCausa.getText(),
                        txtAcciones.getText()
                );

                if (ok) {
                    ctrlOrden.guardar();
                    JOptionPane.showMessageDialog(this, "Orden finalizada");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo finalizar");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}