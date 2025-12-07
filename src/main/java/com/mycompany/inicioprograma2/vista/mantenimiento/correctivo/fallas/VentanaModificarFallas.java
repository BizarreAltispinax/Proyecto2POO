package com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.fallas;

import com.mycompany.inicioprograma2.controlador.ControladorFalla;
import com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.fallas.Falla;

import javax.swing.*;
import java.awt.*;

public class VentanaModificarFallas extends JFrame {

    public VentanaModificarFallas(ControladorFalla controlador, int idEquipo, int idFalla, VentanaListaFallas ventanaPadre) {

        setTitle("Modificar Falla " + idFalla + " (Equipo " + idEquipo + ")");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

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

        btnCerrar.addActionListener(e -> dispose());

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