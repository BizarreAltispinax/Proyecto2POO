package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorFalla;
import com.mycompany.inicioprograma2.modelo.mantenimiento.fallas.Falla;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.HistorialFallas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Ventana en forma de diálogo para seleccionar fallas detectadas en un equipo
 * y registrar las causas y acciones tomadas. Se utiliza en la creación o modificación
 * de órdenes preventivas.
 *
 * <p>Esta ventana permite seleccionar múltiples fallas de la lista de fallas de un equipo
 * y asociarlas a un historial.</p>
 */
public class VentanaAgregarFallasDetectadas extends JDialog {
    private final List<HistorialFallas> resultado = new ArrayList<>();
    
    /**
     * Obtiene las fallas seleccionadas junto a sus causas y acciones.
     *
     * @return lista de objetos {@link HistorialFallas}
     */
    public List<HistorialFallas> getResultado() {
        return resultado;
    }
     /**
     * Constructor del diálogo de selección de fallas detectadas.
     *
     * @param parent ventana padre que abrió el diálogo
     * @param ctrlFallas controlador de fallas para obtener y buscar fallas del equipo
     * @param idEquipo identificador del equipo al que pertenecen las fallas
     */
    public VentanaAgregarFallasDetectadas(Frame parent, ControladorFalla ctrlFallas, int idEquipo) {
        super(parent, "Fallas Detectadas", true);
        setSize(500,400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        System.out.println("Fallas del equipo " + idEquipo + ":");
        for (Falla f : ctrlFallas.getFallasEquipo(idEquipo)) {
            System.out.println(" - " + f.getId() + ": " + f.getDescripcion());
        }

        //Obtener las fallas de un equipo por su ID
        List<Falla> fallasEquipo = ctrlFallas.getFallasEquipo(idEquipo);

        DefaultListModel<String> modelo = new DefaultListModel<>();

        for (Falla f : fallasEquipo) {
            modelo.addElement(f.getId() + " - " + f.getDescripcion());
        }
        // Lista con selección múltiple
        JList<String> lista = new JList<>(modelo);
        lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel abajo = new JPanel(new GridLayout(3,2, 10, 10));

        JTextField txtCausas = new JTextField();
        JTextField txtAcciones = new JTextField();

        abajo.add(new JLabel("Causas:"));
        abajo.add(txtCausas);

        abajo.add(new JLabel("Acciones tomadas:"));
        abajo.add(txtAcciones);

        JButton btnAceptar = new JButton("Agregar");
        abajo.add(btnAceptar);

        JButton btnCerrar = new JButton("Cerrar");
        abajo.add(btnCerrar);

        add(abajo, BorderLayout.SOUTH);
        // Cerrar ventana sin guardar
        btnCerrar.addActionListener(e -> dispose());
        // Guardar selección
        btnAceptar.addActionListener(e -> {
            for (String sel : lista.getSelectedValuesList()) {
                int idFalla = Integer.parseInt(sel.split(" - ")[0]);
                Falla f = ctrlFallas.buscarFalla(idEquipo, idFalla);

                if (f != null) {
                    resultado.add(new HistorialFallas(
                            idFalla,
                            txtCausas.getText(),
                            txtAcciones.getText()
                    ));
                }
            }

            dispose();
        });
    }
}
