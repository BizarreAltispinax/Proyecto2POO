package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.modelo.Equipos;

import javax.swing.*;
import java.awt.*;
/**
 * Ventana para seleccionar un equipo y crear o gestionar su programa
 * de mantenimiento preventivo.
 * <p>
 * Permite elegir un equipo de la lista de equipos registrados, crear
 * un programa preventivo para dicho equipo o abrir la ventana de gestión
 * de programa si ya existe.
 * </p>
 * 
 * @author Usuario
 */
public class VentanaSeleccionEquipoPrograma extends JFrame {
    private final ControladorEquipo ctrlEquipos;
    private final ControladorProgramasPreventivos ctrlProgramas;
    /**
     * Constructor que inicializa la ventana con los controles necesarios.
     *
     * @param ctrlEquipos   Controlador de equipos
     * @param ctrlProgramas Controlador de programas preventivos
     */
    public VentanaSeleccionEquipoPrograma(ControladorEquipo ctrlEquipos, ControladorProgramasPreventivos ctrlProgramas) {
        this.ctrlEquipos = ctrlEquipos;
        this.ctrlProgramas = ctrlProgramas;

        setTitle("Programas de Mantenimiento Preventivo");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));
        // ComboBox con todos los equipos disponibles
        JComboBox<Equipos> comboEquipos = new JComboBox<>();
        for (Equipos e : ctrlEquipos.getEquipos()) comboEquipos.addItem(e);

        JButton btnCrearPrograma = new JButton("Crear Programa para este Equipo");
        JButton btnGestionarPrograma = new JButton("Gestionar Programa");

        add(new JLabel("Seleccione un equipo:", SwingConstants.CENTER));
        add(comboEquipos);
        add(btnCrearPrograma);
        add(btnGestionarPrograma);

        //Crear Programa
        btnCrearPrograma.addActionListener(e -> {
            Equipos sel = (Equipos) comboEquipos.getSelectedItem();

            if (sel == null) {
                JOptionPane.showMessageDialog(this, "No hay equipos registrados");
                return;
            }

            boolean ok = ctrlProgramas.crearProgramaParaEquipo(sel.getId());

            if (ok)
                JOptionPane.showMessageDialog(this, "Programa creado correctamente");
            else
                JOptionPane.showMessageDialog(this, "Este equipo ya tiene un programa creado");
        });

        //Gestionar programas
        btnGestionarPrograma.addActionListener(e -> {
            Equipos sel = (Equipos) comboEquipos.getSelectedItem();

            if (sel == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un equipo");
                return;
            }

            if (ctrlProgramas.getPrograma(sel.getId()) == null) {
                JOptionPane.showMessageDialog(this,
                        "Este equipo no tiene un programa\nCree un programa primero");
                return;
            }

            new VentanaGestionPrograma(ctrlProgramas, sel.getId()).setVisible(true);
        });
    }
}
