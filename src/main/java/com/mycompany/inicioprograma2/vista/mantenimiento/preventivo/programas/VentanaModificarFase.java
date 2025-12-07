package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.TipoFrecuencia;

import javax.swing.*;
import java.awt.*;

public class VentanaModificarFase extends JFrame {
    public VentanaModificarFase(int idEquipo, int indiceFase, ControladorProgramasPreventivos controlador, VentanaListaFases ventanaPadre) {
        setTitle("Modificar Fase");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2, 8, 8));

        //Obtiene la fase
        Fase fase = controlador.getFases(idEquipo).get(indiceFase);

        //Campos GUI con datos actuales
        JComboBox<TipoFrecuencia> comboFrecuencia = new JComboBox<>(TipoFrecuencia.values());
        comboFrecuencia.setSelectedItem(fase.getTipoFrecuencia());

        JTextField txtMedFrec = new JTextField(String.valueOf(fase.getMedidorFrecuencia()));
        JTextField txtCantCic = new JTextField(String.valueOf(fase.getCantidadCiclos()));
        JTextField txtPartes = new JTextField(String.valueOf(fase.getPartes()));
        JTextField txtHerramientas = new JTextField(String.valueOf(fase.getHerramientas()));
        JTextField txtPersonal = new JTextField(String.valueOf(fase.getPersonal()));
        JTextField txtHorasEst = new JTextField(String.valueOf(fase.getHorasEstimadas()));

        //UI
        add(new JLabel("Tipo de Frecuencia:"));
        add(new JLabel("Medidor de Frecuencia:"));
        add(new JLabel("Cantidada de Ciclos:"));
        add(new JLabel("Partes:"));
        add(new JLabel("Herramientas:"));
        add(new JLabel("Personal:"));
        add(new JLabel("Horas Estimadas:"));

        add(comboFrecuencia);
        add(txtMedFrec);
        add(txtCantCic);
        add(txtPartes);
        add(txtHerramientas);
        add(txtPersonal);
        add(txtHorasEst);

        JButton btnGuardar = new JButton("Guardar Cambios");
        JButton btnCerrar = new JButton("Cerrar");
        add(btnGuardar);
        add(btnCerrar);


        btnCerrar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> {
            try {
                TipoFrecuencia nuevaFrecuencia = (TipoFrecuencia) comboFrecuencia.getSelectedItem();
                int nuevaMedidaFrecuencia = Integer.parseInt(txtMedFrec.getText());
                int nuevosCiclos = Integer.parseInt(txtCantCic.getText());
                String nuevasPartes = txtPartes.getText();
                String nuevasHerramientas = txtHerramientas.getText();
                String nuevoPersonal = txtPersonal.getText();
                float nuevasHorasEstimadas = Float.parseFloat(txtHorasEst.getText());

                controlador.modificarFase(
                        idEquipo, indiceFase, nuevaFrecuencia, nuevaMedidaFrecuencia,
                        nuevosCiclos, nuevasPartes, nuevasHerramientas, nuevoPersonal,
                        nuevasHorasEstimadas
                );

                ventanaPadre.cargarFases();
                JOptionPane.showMessageDialog(this, "Fase modificada exitosamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
