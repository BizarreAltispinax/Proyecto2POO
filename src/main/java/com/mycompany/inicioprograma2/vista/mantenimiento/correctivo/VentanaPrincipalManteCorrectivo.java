package com.mycompany.inicioprograma2.vista.mantenimiento.correctivo;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.controlador.ControladorOrdenCorrectiva;
import com.mycompany.inicioprograma2.controlador.ControladorFalla;
import com.mycompany.inicioprograma2.modelo.Equipos;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;
import com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.ordenes.*;
import com.mycompany.inicioprograma2.vista.mantenimiento.fallas.VentanaCrearFallas;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalManteCorrectivo extends JFrame {
    public VentanaPrincipalManteCorrectivo(ControladorOrdenCorrectiva ctrlOrden, ControladorEquipo ctrlEquipos, ControladorFalla ctrlFallas, InicioPrograma2 prin) {

        setTitle("Mantenimiento Correctivo");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JButton btnCrear = new JButton("Crear Orden Correctiva");
        JButton btnIniciar = new JButton("Iniciar Orden");
        JButton btnFinalizar = new JButton("Finalizar Orden");
        JButton btnCancelar = new JButton("Cancelar Orden");
        JButton btnListar = new JButton("Listar Órdenes");
        JButton btnSalir = new JButton("Salir");

        add(btnCrear);
        add(btnIniciar);
        add(btnFinalizar);
        add(btnCancelar);
        add(btnListar);
        add(btnSalir);

        btnSalir.addActionListener(e -> {
            this.dispose();
            prin.setVisible(true);
        }); //Opcion D

        btnCrear.addActionListener(e ->
                new VentanaCrearOrdenCorrectiva(ctrlOrden, ctrlEquipos).setVisible(true)
        );

        btnIniciar.addActionListener(e ->
                new VentanaIniciarOrdenCorrectiva(ctrlOrden).setVisible(true)
        );

        btnFinalizar.addActionListener(e ->
                new VentanaFinalizarOrdenCorrectiva(ctrlOrden).setVisible(true)
        );

        btnCancelar.addActionListener(e ->
                new VentanaCancelarOrdenCorrectiva(ctrlOrden).setVisible(true)
        );

        btnListar.addActionListener(e ->
                new VentanaListaOrdenesCorrectivas(ctrlOrden).setVisible(true)
        );
    }
}