package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaConsultaPrograma extends JFrame {
    private final int idEquipo;
    private final ControladorProgramasPreventivos controlador;

    private final JTextArea area;

    public VentanaConsultaPrograma(int idEquipo, ControladorProgramasPreventivos controlador) {
        this.idEquipo = idEquipo;
        this.controlador = controlador;

        setTitle("Ver Programa de Equipo");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));

        add(new JScrollPane(area), BorderLayout.CENTER);

        cargarFases();

        JPanel panelBotones = new JPanel();
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        btnCerrar.addActionListener(e -> dispose());
    }

    private void cargarFases() {
        area.setText("");

        List<Fase> fases = controlador.getFases(idEquipo);

        if (fases == null || fases.isEmpty()) {
            area.setText("Este equipo no tiene fases registradas");
            return;
        }

        int numero = 1;
        for (Fase f : fases) {
            area.append(Fase.formatearFase(numero, f) + "\n");
            numero++;
        }
    }
}
