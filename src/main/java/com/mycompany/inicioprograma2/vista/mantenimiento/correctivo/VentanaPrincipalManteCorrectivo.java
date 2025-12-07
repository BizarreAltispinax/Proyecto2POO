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
/**
 * Ventana principal del módulo de Mantenimiento Correctivo.
 * <p>
 * Desde esta ventana el usuario puede:
 * <ul>
 *     <li>Crear órdenes correctivas</li>
 *     <li>Iniciar órdenes</li>
 *     <li>Finalizar órdenes</li>
 *     <li>Cancelar órdenes</li>
 *     <li>Listar órdenes registradas</li>
 * </ul>
 * También permite regresar a la ventana principal del sistema.
 *
 * Esta clase actúa como menú central para todas las operaciones
 * relacionadas con mantenimiento correctivo.
 */
public class VentanaPrincipalManteCorrectivo extends JFrame {
     /**
     * Constructor que inicializa la ventana principal del módulo
     * de mantenimiento correctivo.
     *
     * @param ctrlOrden Controlador para las órdenes correctivas.
     * @param ctrlEquipos Controlador de equipos del sistema.
     * @param ctrlFallas Controlador para las fallas registradas.
     * @param prin Ventana principal de la aplicación para regresar al menú principal.
     */
    public VentanaPrincipalManteCorrectivo(ControladorOrdenCorrectiva ctrlOrden, ControladorEquipo ctrlEquipos, ControladorFalla ctrlFallas, InicioPrograma2 prin) {

        setTitle("Mantenimiento Correctivo");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));
        // Botones principales
        JButton btnCrear = new JButton("Crear Orden Correctiva");
        JButton btnIniciar = new JButton("Iniciar Orden");
        JButton btnFinalizar = new JButton("Finalizar Orden");
        JButton btnCancelar = new JButton("Cancelar Orden");
        JButton btnListar = new JButton("Listar Órdenes");
        JButton btnSalir = new JButton("Salir");
        // Agregar botones al layout
        add(btnCrear);
        add(btnIniciar);
        add(btnFinalizar);
        add(btnCancelar);
        add(btnListar);
        add(btnSalir);
        
        
        //Acciones de los botones
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