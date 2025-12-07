package com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.ordenes;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorFalla;
import com.mycompany.inicioprograma2.controlador.ControladorOrdenPreventiva;
import com.mycompany.inicioprograma2.controlador.ControladorProgramasPreventivos;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalOrdenesPreventivas extends JFrame {
    public VentanaPrincipalOrdenesPreventivas(ControladorOrdenPreventiva ctrlOrden, ControladorProgramasPreventivos ctrlProgramas, ControladorEquipo ctrlEquipo, ControladorFalla ctrlFalla) {
        setTitle("Gestión de Órdenes de Mantenimiento Preventivo");
        setSize(400, 400);
        setLayout(new GridLayout(6, 1));
        setLocationRelativeTo(null);

        JButton btnCrear = new JButton("Crear Orden");
        JButton btnIniciar = new JButton("Iniciar Orden");
        JButton btnFinalizar = new JButton("Finalizar Orden");
        JButton btnCancelar = new JButton("Cancelar Orden");
        JButton btnVer = new JButton("Ver Órdenes");
        JButton btnCerrar = new JButton("Salir");

        add(btnCrear);
        add(btnIniciar);
        add(btnFinalizar);
        add(btnCancelar);
        add(btnVer);
        add(btnCerrar);

        btnCrear.addActionListener(e ->
                new VentanaCrearOrdenPreventiva(ctrlOrden, ctrlProgramas, ctrlEquipo).setVisible(true)
        );

        btnIniciar.addActionListener(e ->
                new VentanaIniciarOrden(ctrlOrden).setVisible(true)
        );

        btnFinalizar.addActionListener(e ->
                new VentanaFinalizarOrden(ctrlOrden, ctrlFalla).setVisible(true)
        );

        btnCancelar.addActionListener(e ->
                new VentanaCancelarOrden(ctrlOrden).setVisible(true)
        );

        btnVer.addActionListener(e ->
                new VentanaListaOrdenes(ctrlOrden).setVisible(true)
        );

        btnCerrar.addActionListener(e -> dispose());
    }
}
