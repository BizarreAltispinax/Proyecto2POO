package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.tareas;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorTarea;
import com.mycompany.inicioprograma2.modelo.Equipos;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.tareas.Tarea;

import javax.swing.*;
import java.awt.*;
/**
 * Ventana para modificar una tarea existente de mantenimiento preventivo.
 * <p>
 * Permite al usuario validar el ID de la tarea a modificar, ingresar
 * una nueva descripción y guardar los cambios a través del controlador.
 * </p>
 * 
 * Autor: Usuario
 */
public class VentanaModificarTarea extends JFrame{
    private ControladorTarea controlador;

    private JTextField txtIdBusqueda;
    private JTextField txtDescripcion;

    private JButton btnBuscar;
    private JButton btnGuardar;
    private JButton btnCancelar;

    private int idTareaModificar = -1;

    /**
     * Constructor que inicializa la ventana y sus componentes.
     * 
     * @param controlador Controlador de tareas para acceder a la lista de tareas y modificarlas
     */
    public VentanaModificarTarea(ControladorTarea controlador) {
        this.controlador = controlador;

        setTitle("Modificar Tarea");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponentes();
        }

    private void initComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 6, 6));

        //============================
        // ID de la tarea a modificar
        //============================
        panel.add(new JLabel("ID de la tarea a modificar:"));
        txtIdBusqueda = new JTextField();
        panel.add(txtIdBusqueda);

        btnBuscar = new JButton("Validar ID");
        panel.add(btnBuscar);
        panel.add(new JLabel("")); // espacio vacío

        //============================
        // Campo de la tarea (vacío)
        //============================
        panel.add(new JLabel("Nueva Descripción:"));
        txtDescripcion = new JTextField();
        panel.add(txtDescripcion);

        //============================
        // Botones
        //============================
        btnGuardar = new JButton("Guardar Cambios");
        btnCancelar = new JButton("Cancelar");

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        add(panel);

        // Acciones
        btnBuscar.addActionListener(e -> validarID());
        btnGuardar.addActionListener(e -> guardarCambios());
        btnCancelar.addActionListener(e -> dispose());
    }


        //============================
        // Validación del ID
        //============================
        private void validarID() {
            try {
                int id = Integer.parseInt(txtIdBusqueda.getText());
                Tarea t = controlador.buscarPorId(id);

                if (t == null) {
                    JOptionPane.showMessageDialog(this, "Tarea inexistente.");
                    idTareaModificar = -1;
                    return;
                }

                idTareaModificar = id;
                JOptionPane.showMessageDialog(this,
                        "ID válido, ingrese la nueva descripción.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        }

        //============================
        // Guardar todos los cambios
        //============================
        private void guardarCambios() {
            if (idTareaModificar == -1) {
                JOptionPane.showMessageDialog(this, "Primero valide un ID.");
                return;
            }

            try {
                String descripcion = txtDescripcion.getText();

                boolean ok = controlador.modificarTarea(
                        idTareaModificar,
                        descripcion
                );

                if (ok) {
                    JOptionPane.showMessageDialog(this, "Cambios guardados correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error: no se pudo modificar la tarea.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
    }
}
