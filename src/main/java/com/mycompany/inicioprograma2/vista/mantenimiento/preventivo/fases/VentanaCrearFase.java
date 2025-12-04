package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.fases;

import com.mycompany.inicioprograma2.controlador.ControladorMatenimientoPreventivo;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.TipoFrecuencia;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearFase extends JFrame {
    public VentanaCrearFase(ControladorMatenimientoPreventivo controlador) {
        setTitle("Crear Fase del Programa");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2, 8, 8));

        JComboBox<TipoFrecuencia> comboFrecuencia = new JComboBox<>(TipoFrecuencia.values());
        JTextField txtMedFrec = new JTextField();
        JTextField txtCantCic = new JTextField();
        JTextField txtPartes = new JTextField();
        JTextField txtHerramientas = new JTextField();
        JTextField txtPersonal = new JTextField();
        JTextField txtHorEst = new JTextField();

        add(new JLabel("Tipo de Frecuencia:")); add(comboFrecuencia);
        add(new JLabel("Frecuencia:")); add(txtMedFrec);
        add(new JLabel("Cantidad de Ciclos:")); add(txtCantCic);
        add(new JLabel("Partes de la Fase:")); add(txtPartes);
        add(new JLabel("Herramientas:")); add(txtHerramientas);
        add(new JLabel("Personal Asignado:")); add(txtPersonal);
        add(new JLabel("Horas Estimadas:")); add(txtHorEst);


        JButton btnGuardar = new JButton("Guardar");
        add(btnGuardar);

        JButton btnCerrar = new JButton("Cerrar");
        add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> {

            try {
                TipoFrecuencia tipoFrecuencia = (TipoFrecuencia) comboFrecuencia.getSelectedItem();
                int medidor = Integer.parseInt(txtMedFrec.getText());
                int cantCiclos = Integer.parseInt(txtCantCic.getText());
                //Lista de Tareas
                String partes = txtPartes.getText();
                String herramientas = txtHerramientas.getText();
                String personal = txtPersonal.getText();
                float horasEstimadas = Float.parseFloat(txtHorEst.getText());

                boolean ok = controlador.crearFase(
                        tipoFrecuencia, medidor, cantCiclos, partes, herramientas, personal, horasEstimadas
                );

                if (ok)
                    JOptionPane.showMessageDialog(this, "Fase agregada correctamente.");
                else
                    JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }

}
