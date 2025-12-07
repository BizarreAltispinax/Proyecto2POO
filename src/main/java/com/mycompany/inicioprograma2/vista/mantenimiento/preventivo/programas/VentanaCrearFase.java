package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.TipoFrecuencia;

import javax.swing.*;
import java.awt.*;
/**
 * Ventana para crear una nueva fase dentro del programa de mantenimiento preventivo
 * de un equipo específico.
 * <p>
 * Permite al usuario seleccionar el tipo de frecuencia, ingresar datos de ciclos,
 * partes, herramientas, personal asignado y horas estimadas.
 * </p>
 * 
 * @author Usuario
 */
public class VentanaCrearFase extends JFrame {
    
    /**
     * Constructor de la ventana para crear una fase.
     *
     * @param idEquipo    ID del equipo al que se le asignará la fase
     * @param controlador Controlador de programas preventivos
     */
    public VentanaCrearFase(int idEquipo, ControladorProgramasPreventivos controlador) {
        setTitle("Crear Fase");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 5, 5));
        // Componentes de entrada de datos
        JComboBox<TipoFrecuencia> comboFrecuencia = new JComboBox<>(TipoFrecuencia.values());
        JTextField txtMedidor = new JTextField();
        JTextField txtCantCic = new JTextField();
        JTextField txtPartes = new JTextField();
        JTextField txtHerramientas = new JTextField();
        JTextField txtPersonal = new JTextField();
        JTextField txtHorEst = new JTextField();
        // Botones de acción
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCerrar = new JButton("Cerrar");
        // Añadir etiquetas y campos al layout
        add(new JLabel("Tipo de Frecuencia:")); add(comboFrecuencia);
        add(new JLabel("Frecuencia:")); add(txtMedidor);
        add(new JLabel("Cantidad de Ciclos:")); add(txtCantCic);
        add(new JLabel("Partes de la Fase:")); add(txtPartes);
        add(new JLabel("Herramientas:")); add(txtHerramientas);
        add(new JLabel("Personal Asignado:")); add(txtPersonal);
        add(new JLabel("Horas Estimadas:")); add(txtHorEst);
        add(btnGuardar);
        add(btnCerrar);
        // Cerrar ventana al presionar "Cerrar"
        btnCerrar.addActionListener(e -> dispose());
        // Guardar nueva fase al presionar "Guardar"
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
