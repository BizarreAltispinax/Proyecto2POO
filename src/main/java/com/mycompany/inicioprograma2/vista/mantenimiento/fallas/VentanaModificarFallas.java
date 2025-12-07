package com.mycompany.inicioprograma2.vista.mantenimiento.fallas;

import com.mycompany.inicioprograma2.controlador.ControladorFalla;
import com.mycompany.inicioprograma2.modelo.mantenimiento.fallas.Falla;

import javax.swing.*;
import java.awt.*;
/**
 * Ventana para modificar una falla existente asociada a un equipo.
 * Permite editar la descripción de la falla y guardar los cambios.
 */
public class VentanaModificarFallas extends JFrame {
     /**
     * Constructor que crea la ventana para modificar una falla específica.
     *
     * @param controlador   controlador encargado de gestionar las fallas
     * @param idEquipo      ID del equipo al que pertenece la falla
     * @param idFalla       ID de la falla que se desea modificar
     * @param ventanaPadre  ventana de la cual se abrió esta, usada para refrescar la tabla
     */
    public VentanaModificarFallas(ControladorFalla controlador, int idEquipo, int idFalla, VentanaListaFallas ventanaPadre) {

        setTitle("Modificar Falla " + idFalla + " (Equipo " + idEquipo + ")");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));
        // Se busca la falla en el controlador
        Falla f = controlador.buscarFalla(idEquipo, idFalla);
        if (f == null) {
            JOptionPane.showMessageDialog(this, "La falla no existe en este equipo.");
            dispose();
            return;
        }

        JTextField txtDescripcion = new JTextField(f.getDescripcion());

        add(new JLabel("Descripción:"));
        add(txtDescripcion);

        JButton btnGuardar = new JButton("Guardar Cambios");
        JButton btnCerrar = new JButton("Cerrar");

        add(btnGuardar);
        add(btnCerrar);
        // Cerrar ventana
        btnCerrar.addActionListener(e -> dispose());
        // Guardar cambios realizados
        btnGuardar.addActionListener(e -> {

            boolean ok = controlador.modificarFalla(
                    idEquipo,
                    idFalla,
                    txtDescripcion.getText()
            );

            if (ok) {
                controlador.guardar();
                ventanaPadre.refrescar();
                JOptionPane.showMessageDialog(this, "Falla modificada correctamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar la falla.");
            }
        });
    }
}