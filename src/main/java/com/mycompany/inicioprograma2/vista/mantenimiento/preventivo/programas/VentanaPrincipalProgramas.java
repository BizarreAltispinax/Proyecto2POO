/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.programas;

/**
 *
 * @author Usuario
 */

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.modelo.Equipos;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalProgramas extends JFrame {
    private final ControladorProgramasPreventivos ctrlProgramas;
    private final ControladorEquipo ctrlEquipos;
    private final ControladorTarea ctrlTareas;

    private JComboBox<Equipos> comboEquipos;
    private JButton btnCrearFase, btnAsignar, btnModificar, btnConsultar, btnSalir;

    public VentanaPrincipalProgramas(ControladorEquipo ctrlEquipos, ControladorTarea ctrlTareas, ControladorProgramasPreventivos ctrlProgramas,InicioPrograma2 prin) {
        this.ctrlEquipos = ctrlEquipos;
        this.ctrlTareas = ctrlTareas;
        this.ctrlProgramas = ctrlProgramas;

        setTitle("Programas de Mantenimiento Preventivo");
        setSize(850, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        comboEquipos = new JComboBox<>();
        for (Equipos eq : ctrlEquipos.getEquipos()) {
            comboEquipos.addItem(eq);
        }

        add(new JLabel("Seleccione un equipo:"));
        add(comboEquipos);

        //Botones
        JButton btnCrearPrograma = new JButton("Crear o Activar Programa del Equipo");
        btnCrearFase = new JButton("Crear Fase");
        btnAsignar = new JButton("Asignar Tareas a Fase");
        btnModificar = new JButton("Modificar Fases");
        btnConsultar = new JButton("Ver Programa");
        btnSalir = new JButton("Salir");
        
        btnCrearFase.setEnabled(false);
        btnAsignar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnConsultar.setEnabled(false);
        btnSalir.setEnabled(true);
        add(btnCrearPrograma);
        add(btnCrearFase);
        add(btnAsignar);
        add(btnModificar);
        add(btnConsultar);
        add(btnSalir);
        //Eventos
        btnCrearPrograma.addActionListener(e -> activarPrograma());
        btnCrearFase.addActionListener(e -> abrirCrearFase());
        btnAsignar.addActionListener(e -> abrirAsignarTareas());
        btnModificar.addActionListener(e -> abrirModificarFases());
        btnConsultar.addActionListener(e -> abrirCosultarPrograma());
        btnSalir.addActionListener(e -> {
            
            this.dispose();  

        });
        
    }

    private void activarPrograma() {
        Equipos seleccionado = (Equipos) comboEquipos.getSelectedItem();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "No hay equipos disponibles.");
            return;
        }

        ctrlProgramas.crearProgramaParaEquipo(seleccionado.getId());

        // Habilitar botones
        btnCrearFase.setEnabled(true);
        btnAsignar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnConsultar.setEnabled(true);

        JOptionPane.showMessageDialog(this,
                "Programa del equipo habilitado: " + seleccionado.getDescripcion());
    }

    private void abrirCrearFase() {
        Equipos equipo = (Equipos) comboEquipos.getSelectedItem();
        new VentanaCrearFase(equipo.getId(), ctrlProgramas).setVisible(true);
    }

    private void abrirAsignarTareas() {
        Equipos equipo = (Equipos) comboEquipos.getSelectedItem();
        new VentanaAsignarTareasFase(equipo.getId(), ctrlProgramas, ctrlTareas, null).setVisible(true);
    }

    private void abrirModificarFases() {
        Equipos equipo = (Equipos) comboEquipos.getSelectedItem();
        new VentanaListaFases(equipo.getId(), ctrlProgramas).setVisible(true);
    }

    private void abrirCosultarPrograma() {
        Equipos equipo = (Equipos) comboEquipos.getSelectedItem();
        new VentanaConsultaPrograma(equipo.getId(), ctrlProgramas).setVisible(true);
    }
}