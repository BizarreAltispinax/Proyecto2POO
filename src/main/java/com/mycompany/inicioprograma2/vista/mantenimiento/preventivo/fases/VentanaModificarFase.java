package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.fases;

import com.mycompany.inicioprograma2.controlador.ControladorMatenimientoPreventivo;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.TipoFrecuencia;

import javax.swing.*;
import java.awt.*;

public class VentanaModificarFase extends JFrame {
    public VentanaModificarFase(ControladorMatenimientoPreventivo controlador, int indice) {
        Fase fase = controlador.getFases().get(indice);

        setTitle("Modificar Fase");
        setSize(500, 600);
        setLayout(new GridLayout(9, 2, 8, 8));
        setLocationRelativeTo(null);

        JComboBox<TipoFrecuencia> comboFrecuencia = new JComboBox<>(TipoFrecuencia.values());
        comboFrecuencia.setSelectedItem(fase.getTipoFrecuencia());

        JTextField txtMedFrec = new JTextField(String.valueOf(fase.getMedidorFrecuencia()));
        JTextField txtCantCic = new JTextField(String.valueOf(fase.getCantidadCiclos()));
        JTextField txtPrtes = new JTextField(String.valueOf(fase.getPartes()));
        JTextField txtHerramientas = new JTextField(String.valueOf(fase.getHerramientas()));
        JTextField txtPersonal = new JTextField(String.valueOf(fase.getPersonal()));
        JTextField txtHorasEst = new JTextField(String.valueOf(fase.getHorasEstimadas()));
    }
}
