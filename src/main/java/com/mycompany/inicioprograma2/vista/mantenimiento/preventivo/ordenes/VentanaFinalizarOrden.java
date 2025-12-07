package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorFalla;
import com.mycompany.inicioprograma2.controlador.ControladorOrdenPreventiva;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.EstadoOrden;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.HistorialFallas;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.OrdenTrabajoPreventivo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
/**
 * Ventana para finalizar una orden de trabajo preventiva.
 * <p>
 * Permite seleccionar una orden en ejecución, registrar la fecha de finalización,
 * horas trabajadas, costos, observaciones y fallas detectadas durante su ejecución.
 * Utiliza una ventana modal para agregar fallas detectadas. Al finalizar, actualiza
 * el estado de la orden y guarda los cambios mediante el controlador.
 * </p>
 *
 * @author Usuario
 */
public class VentanaFinalizarOrden extends JFrame {
    
    /**
     * Constructor que inicializa la ventana para finalizar una orden preventiva.
     *
     * @param ctrlOrden  controlador encargado de gestionar las órdenes preventivas
     * @param ctrlFallas controlador encargado de gestionar las fallas
     */
    public VentanaFinalizarOrden(ControladorOrdenPreventiva ctrlOrden, ControladorFalla ctrlFallas) {
        setTitle("Finalizar Orden");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8,2,10,10));

        JComboBox<String> combo = new JComboBox<>();

        for (var o : ctrlOrden.getOrdenes()) {
            if (o.getEstado() == EstadoOrden.EN_EJECUCION) {
                combo.addItem(o.getId() + " - Equipo " + o.getIdEquipo());
            }
        }

        JTextField txtFecha = new JTextField(LocalDate.now().toString());
        JTextField txtHoras = new JTextField();
        JTextField txtManoObra = new JTextField();
        JTextField txtMateriales = new JTextField();
        JTextField txtObs = new JTextField();

        JButton btnFallas = new JButton("Agregar Fallas Detectadas");
        JButton btnFinalizar = new JButton("Finalizar Orden");
        JButton btnCerrar = new JButton("Cerrar");

        java.util.List<HistorialFallas> fallasTemporal = new java.util.ArrayList<>();
        //Se añaden las partes visuales
        add(new JLabel("Orden en ejecución"));
        add(combo);

        add(new JLabel("Fecha final:"));
        add(txtFecha);

        add(new JLabel("Horas trabajadas:"));
        add(txtHoras);

        add(new JLabel("Costo mano de obra:"));
        add(txtManoObra);

        add(new JLabel("Costo materiales:"));
        add(txtMateriales);

        add(new JLabel("Observaciones:"));
        add(txtObs);

        add(btnFallas);
        add(btnFinalizar);

        add(btnCerrar);
        // Cierra ventana
        btnCerrar.addActionListener(e -> dispose());

        btnFallas.addActionListener(e -> {
            try {
                int idOrden = Integer.parseInt(combo.getSelectedItem().toString().split(" - ")[0]);
                OrdenTrabajoPreventivo o = ctrlOrden.buscarPorId(idOrden);

                //Ventana modal
                VentanaAgregarFallasDetectadas v =
                        new VentanaAgregarFallasDetectadas(this, ctrlFallas, o.getIdEquipo());

                v.setVisible(true);

                fallasTemporal.addAll(v.getResultado());

                JOptionPane.showMessageDialog(this,
                        "Fallas agregadas: " + fallasTemporal.size()
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error cargando fallas");
            }
        });

        btnFinalizar.addActionListener(e -> {
            try {
                int idOrden = Integer.parseInt(combo.getSelectedItem().toString().split(" - ")[0]);

                boolean ok = ctrlOrden.finalizarOrden(
                        idOrden,
                        LocalDate.parse(txtFecha.getText()),
                        Float.parseFloat(txtHoras.getText()),
                        Integer.parseInt(txtManoObra.getText()),
                        Integer.parseInt(txtMateriales.getText()),
                        txtObs.getText()
                );

                if (!ok) {
                    JOptionPane.showMessageDialog(this, "No se pudo finalizar la orden");
                    return;
                }

                //Agregar las fallas detectadas
                for (HistorialFallas h : fallasTemporal) {
                    ctrlOrden.agregarFalla(idOrden, h);
                }

                ctrlOrden.guardar();
                JOptionPane.showMessageDialog(this, "Orden finalizada existosamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
