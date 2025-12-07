package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 * Ventana para consultar y visualizar el programa de mantenimiento preventivo
 * de un equipo específico.
 * <p>
 * Muestra en un área de texto todas las fases del programa asignadas al equipo.
 * Permite únicamente la visualización y cierre de la ventana.
 * </p>
 * 
 * @author Usuario
 */
public class VentanaConsultaPrograma extends JFrame {
    private final int idEquipo;
    private final ControladorProgramasPreventivos controlador;

    private final JTextArea area;
     /**
     * Constructor de la ventana de consulta de programa.
     *
     * @param idEquipo    ID del equipo a consultar
     * @param controlador Controlador de programas preventivos
     */
    public VentanaConsultaPrograma(int idEquipo, ControladorProgramasPreventivos controlador) {
        this.idEquipo = idEquipo;
        this.controlador = controlador;

        setTitle("Ver Programa de Equipo");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Área de texto para mostrar las fases
        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));

        add(new JScrollPane(area), BorderLayout.CENTER);

        cargarFases();
        // Panel inferior con botón de cierre
        JPanel panelBotones = new JPanel();
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        btnCerrar.addActionListener(e -> dispose());
    }
     /**
     * Carga y muestra las fases del programa del equipo en el área de texto.
     * Si no existen fases, se muestra un mensaje indicándolo.
     */
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
