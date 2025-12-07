package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorOrdenPreventiva;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.EstadoOrden;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
/**
 * Ventana para iniciar una orden de mantenimiento preventivo.
 * <p>
 * Permite seleccionar una orden pendiente, establecer la fecha de inicio,
 * y actualizar su estado a "en ejecución". Al confirmar, se guardan los cambios
 * mediante el controlador correspondiente.
 * </p>
 * 
 * @author Usuario
 */
public class VentanaIniciarOrden extends JFrame {
    
    /**
     * Constructor que inicializa la ventana para iniciar una orden.
     *
     * @param controlador controlador encargado de gestionar las órdenes preventivas
     */
    public VentanaIniciarOrden(ControladorOrdenPreventiva controlador) {
        setTitle("Iniciar Orden");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JComboBox<String> combo = new JComboBox<>();
        JTextField txtFecha = new JTextField(LocalDate.now().toString());

        for (var o : controlador.getOrdenes()) {
            if (o.getEstado() == EstadoOrden.PENDIENTE) {
                combo.addItem(o.getId() + " - Equipo " + o.getIdEquipo());
            }
        }

        JButton btnIniciar = new JButton("Iniciar");
        JButton btnCerrar = new JButton("Cerrar");

        add(new JLabel("Orden:"));
        add(combo);

        add(new JLabel("Fecha inicio:"));
        add(txtFecha);

        add(btnIniciar);
        add(btnCerrar);
        // Cerrar ventana
        btnCerrar.addActionListener(e -> dispose());

        btnIniciar.addActionListener(e -> {
            try {
                int idOrden = Integer.parseInt(combo.getSelectedItem().toString().split(" - ")[0]);
                LocalDate fecha = LocalDate.parse(txtFecha.getText());

                if (controlador.iniciarOrden(idOrden, fecha)) {
                    controlador.guardar();
                    JOptionPane.showMessageDialog(this, "Orden iniciada.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo iniciar la orden.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
