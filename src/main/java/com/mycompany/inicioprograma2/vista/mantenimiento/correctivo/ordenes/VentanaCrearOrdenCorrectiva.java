package com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorOrdenCorrectiva;
import com.mycompany.inicioprograma2.modelo.Equipos;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de interfaz gráfica para la creación de una nueva orden de mantenimiento correctivo.
 * <p>
 * Esta ventana permite:
 * <ul>
 *     <li>Seleccionar un equipo disponible</li>
 *     <li>Ingresar una descripción del problema</li>
 *     <li>Elegir la prioridad de la orden</li>
 *     <li>Crear la orden mediante el controlador correspondiente</li>
 * </ul>
 * Una vez creada la orden, la ventana se cierra automáticamente.
 */
public class VentanaCrearOrdenCorrectiva extends JFrame {
     /**
     * Constructor que inicializa la interfaz gráfica para crear una orden correctiva.
     *
     * @param ctrlOrden   Controlador encargado de gestionar las órdenes correctivas.
     * @param ctrlEquipos Controlador que contiene los equipos registrados.
     */
    public VentanaCrearOrdenCorrectiva(ControladorOrdenCorrectiva ctrlOrden,
                                       ControladorEquipo ctrlEquipos) {

        setTitle("Crear Orden Correctiva");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));
         // Llenar combo con los equipos disponibles
        JComboBox<String> comboEquipos = new JComboBox<>();
        for (Equipos e : ctrlEquipos.getEquipos()) {
            comboEquipos.addItem(e.getId() + " - " + e.getDescripcion());
        }

        JTextField txtDescripcion = new JTextField();
        JComboBox<String> comboPrioridad = new JComboBox<>(new String[]{
                "ALTA", "MEDIA", "BAJA"
        });

        JButton btnCrear = new JButton("Crear Orden");
        JButton btnCerrar = new JButton("Cerrar");
        // Construcción del formulario
        add(new JLabel("Equipo:"));
        add(comboEquipos);

        add(new JLabel("Descripción del problema:"));
        add(txtDescripcion);

        add(new JLabel("Prioridad:"));
        add(comboPrioridad);

        add(new JLabel());
        add(new JLabel());

        add(btnCrear);
        add(btnCerrar);
        // Cerrar ventana
        btnCerrar.addActionListener(e -> dispose());
        // Crear orden
        btnCrear.addActionListener(e -> {
            try {
                int idEquipo = Integer.parseInt(comboEquipos.getSelectedItem().toString().split(" - ")[0]);

                boolean ok = ctrlOrden.crearOrden(
                        idEquipo,
                        txtDescripcion.getText(),
                        comboPrioridad.getSelectedItem().toString()
                );

                if (ok) {
                    ctrlOrden.guardar();
                    JOptionPane.showMessageDialog(this, "Orden creada exitosamente");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo crear la orden");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}