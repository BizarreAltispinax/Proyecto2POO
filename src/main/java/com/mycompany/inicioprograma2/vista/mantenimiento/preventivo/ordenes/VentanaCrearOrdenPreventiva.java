package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorOrdenPreventiva;
import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;
import com.mycompany.inicioprograma2.modelo.Equipos;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.Fase;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
/**
 * Ventana para la creación de una nueva orden de mantenimiento preventivo.
 * <p>
 * Permite seleccionar un equipo, escoger una de sus fases definidas en su
 * programa preventivo y asignar una fecha. Tras validar la información,
 * solicita al controlador la creación y persistencia de la orden.
 * </p>
 */
public class VentanaCrearOrdenPreventiva extends JFrame {
     /**
     * Constructor que inicializa la interfaz para crear una orden preventiva.
     *
     * @param ctrlOrden     controlador encargado de gestionar las órdenes
     * @param ctrlProgramas controlador que administra los programas preventivos y sus fases
     * @param ctrlEquipos   controlador para obtener la lista de equipos existentes
     */
    public VentanaCrearOrdenPreventiva(ControladorOrdenPreventiva ctrlOrden, ControladorProgramasPreventivos ctrlProgramas, ControladorEquipo ctrlEquipos) {
        setTitle("Crear Orden Preventiva");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        JComboBox<String> comboEquipos = new JComboBox<>();
        JComboBox<String> comboFases = new JComboBox<>();

        JTextField txtFecha = new JTextField(LocalDate.now().toString());

        JButton btnCrear = new JButton("Crear");
        JButton btnCerrar = new JButton("Cerrar");

        //Cargar equipos
        for (Equipos eq : ctrlEquipos.getEquipos()) {
            comboEquipos.addItem(eq.getId() + " - " + eq.getDescripcion());
        }
        // ------------------------------------------------------------
        // Cuando se seleccione un equipo, cargar las fases de su programa
        // ------------------------------------------------------------
        comboEquipos.addActionListener(e -> {
            comboFases.removeAllItems();

            if (comboEquipos.getSelectedIndex() == -1) return;

            int idEquipo = Integer.parseInt(comboEquipos.getSelectedItem().toString().split(" - ")[0]);

            var programa = ctrlProgramas.getPrograma(idEquipo);
            if (programa == null) {
                comboFases.addItem("Este equipo no tiene programa preventivo");
                return;
            }

            int index = 1;
            for (Fase f : programa.getFases()) {
                comboFases.addItem(Fase.formatearFase(index, f).substring(2));
                index++;
            }
        });
        // ------------------------------------------------------------
        // Armado de la interfaz
        // ------------------------------------------------------------
        add(new JLabel("Equipo:"));
        add(comboEquipos);

        add(new JLabel("Fase:"));
        add(comboFases);

        add(new JLabel("Fecha programada (AAAA-MM-DD):"));
        add(txtFecha);

        add(btnCrear);
        add(btnCerrar);
        // Cerrar ventana
        btnCerrar.addActionListener(e -> dispose());
        // ------------------------------------------------------------
        // Crear orden preventiva
        // ------------------------------------------------------------
        btnCrear.addActionListener(e -> {
            try {
                int idEquipo = Integer.parseInt(comboEquipos.getSelectedItem().toString().split(" - ")[0]);
                int indiceFase = comboFases.getSelectedIndex();
                LocalDate fecha = LocalDate.parse(txtFecha.getText());

                boolean ok = ctrlOrden.crearOrden(idEquipo, indiceFase, fecha);

                if (ok) {
                    ctrlOrden.guardar();
                    JOptionPane.showMessageDialog(this, "Orden creada correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Datos inválidos o fase no válida.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
            }
        });
    }
}
