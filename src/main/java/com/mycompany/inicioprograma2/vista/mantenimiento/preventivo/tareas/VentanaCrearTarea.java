package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas;

import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.fases.TipoFrecuencia;

import javax.swing.*;
import java.awt.*;
/**
 * Ventana para crear una nueva tarea de mantenimiento preventivo.
 * <p>
 * Permite al usuario ingresar la descripción de una tarea y guardarla
 * mediante el controlador correspondiente.
 * </p>
 * 
 * @author Usuario
 */
public class VentanaCrearTarea extends JFrame {
    
    /**
     * Constructor que inicializa la ventana y sus componentes.
     * 
     * @param controlador Controlador de tareas que gestiona la creación de nuevas tareas
     */
    public VentanaCrearTarea(ControladorTarea controlador) {
        setTitle("Crear Tarea");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2, 6, 6));

        JTextField txtDescripcion = new JTextField();

        add(new JLabel("Descripción de la Tarea:")); add(txtDescripcion);

        JButton btnGuardar = new JButton("Guardar");
        add(btnGuardar);

        JButton btnCerrar = new JButton("Cerrar");
        add(btnCerrar);
        // Acción para cerrar la ventana
        btnCerrar.addActionListener(e -> dispose());
        // Acción para guardar la tarea usando el controlador
        btnGuardar.addActionListener(e -> {

            try {
                String descripcion = txtDescripcion.getText();

                boolean ok = controlador.agregarTarea(
                        descripcion
                );

                if (ok)
                    JOptionPane.showMessageDialog(this, "Tarea agregada correctamente.");
                else
                    JOptionPane.showMessageDialog(this, "Error. Agregar descripción.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
