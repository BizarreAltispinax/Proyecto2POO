package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.TipoFrecuencia;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearFase extends JFrame {
    public VentanaCrearFase(int idEquipo, ControladorProgramasPreventivos controlador) {
        setTitle("Crear Fase");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 5, 5));

        JComboBox<TipoFrecuencia> comboFrecuencia = new JComboBox<>(TipoFrecuencia.values());
        JTextField txtMedidor = new JTextField();
        JTextField txtCantCic = new JTextField();
        JTextField txtPartes = new JTextField();
        JTextField txtHerramientas = new JTextField();
        JTextField txtPersonal = new JTextField();
        JTextField txtHorEst = new JTextField();

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCerrar = new JButton("Cerrar");

        add(new JLabel("Tipo de Frecuencia:")); add(comboFrecuencia);
        add(new JLabel("Frecuencia:")); add(txtMedidor);
        add(new JLabel("Cantidad de Ciclos:")); add(txtCantCic);
        add(new JLabel("Partes de la Fase:")); add(txtPartes);
        add(new JLabel("Herramientas:")); add(txtHerramientas);
        add(new JLabel("Personal Asignado:")); add(txtPersonal);
        add(new JLabel("Horas Estimadas:")); add(txtHorEst);
        add(btnGuardar);
        add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> {
            try {
                boolean ok = controlador.crearFase(
                        idEquipo,
                        (TipoFrecuencia) comboFrecuencia.getSelectedItem(),
                        Integer.parseInt(txtMedidor.getText()),
                        Integer.parseInt(txtCantCic.getText()),
                        txtPartes.getText(),
                        txtHerramientas.getText(),
                        txtPersonal.getText(),
                        Float.parseFloat(txtHorEst.getText())
                );

                if (ok) JOptionPane.showMessageDialog(this, "Fase creada");
                else JOptionPane.showMessageDialog(this, "Error al crear la fase");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + ex.getMessage());
            }
        });
    }

}
