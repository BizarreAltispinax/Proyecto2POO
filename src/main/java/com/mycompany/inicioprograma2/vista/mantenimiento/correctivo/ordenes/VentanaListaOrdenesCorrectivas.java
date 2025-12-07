package com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorOrdenCorrectiva;

import javax.swing.*;
import java.awt.*;

public class VentanaListaOrdenesCorrectivas extends JFrame {

    public VentanaListaOrdenesCorrectivas(ControladorOrdenCorrectiva ctrlOrden) {

        setTitle("Lista de Órdenes Correctivas");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        DefaultListModel<String> modelo = new DefaultListModel<>();

        for (var o : ctrlOrden.getOrdenes()) {
            modelo.addElement("Orden " + o.getId() + " | Equipo " + o.getIdEquipo()
                    + " | Estado: " + o.getEstado());
        }

        JList<String> lista = new JList<>(modelo);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(lista), BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        add(btnCerrar, BorderLayout.SOUTH);

        btnCerrar.addActionListener(e -> dispose());
    }
}