package com.mycompany.inicioprograma2.vista.mantenimiento;

import com.mycompany.inicioprograma2.controlador.*;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;
import com.mycompany.inicioprograma2.vista.mantenimiento.correctivo.VentanaPrincipalManteCorrectivo;
import com.mycompany.inicioprograma2.vista.mantenimiento.fallas.VentanaPrincipalFallas;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.VentanaPrincipalMantePreventivo;

import javax.swing.*;
import java.awt.*;
/**
 * Ventana principal del módulo de mantenimiento.
 *
 * <p>Desde esta ventana el usuario puede acceder a:
 * <ul>
 *     <li>Programa de mantenimiento preventivo</li>
 *     <li>Programa de mantenimiento correctivo</li>
 *     <li>Registro de fallas</li>
 * </ul>
 *
 * <p>Esta clase únicamente controla la navegación entre ventanas del módulo 
 * de mantenimiento; no contiene lógica de negocio.
 *
 * <p>También incluye un botón para regresar al menú principal del sistema.
 *
 * @author Usuario
 */
public class VentanaPrincipalMantenimiento extends JFrame {
    
    /**
     * Constructor que inicializa el menú principal de mantenimiento.
     *
     * @param ctlEquipos controlador de equipos
     * @param ctlFallas controlador de fallas
     * @param ctlMP controlador de mantenimiento preventivo
     * @param ctlOP controlador de órdenes preventivas
     * @param ctlOC controlador de órdenes correctivas
     * @param ctlPP controlador de programas preventivos
     * @param ctlTarea controlador de tareas de mantenimiento
     * @param prin ventana principal del sistema
     */
    public VentanaPrincipalMantenimiento(ControladorEquipo ctlEquipos, ControladorFalla ctlFallas, ControladorMatenimientoPreventivo ctlMP, ControladorOrdenPreventiva ctlOP, ControladorOrdenCorrectiva ctlOC, ControladorProgramasPreventivos ctlPP, ControladorTarea ctlTarea, InicioPrograma2 prin) {
        setTitle("Mantenimiento");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));
        //Botones
        JButton btnPreventivo = new JButton("Programa de Mantenimiento Preventivo");
        add(btnPreventivo);

        JButton btnCorrectivo = new JButton("Programa de Mantenimiento Correctivo");
        add(btnCorrectivo);

        JButton btnFallas = new JButton("Registro de Fallas");
        add(btnFallas);

        JButton btnSalir = new JButton("Salir");
        add(btnSalir);
        //Acciones de los botones
        btnPreventivo.addActionListener(e -> {
            new VentanaPrincipalMantePreventivo(ctlEquipos, ctlFallas, ctlMP, ctlOP, ctlPP, ctlTarea, prin).setVisible(true);
            this.dispose();   //Opcion A
        });
        btnCorrectivo.addActionListener(e -> {
            new VentanaPrincipalManteCorrectivo(ctlOC, ctlEquipos, ctlFallas, prin).setVisible(true);
            this.dispose();   //Opcion B
        });
        btnFallas.addActionListener(e -> {
            new VentanaPrincipalFallas(ctlEquipos, ctlFallas).setVisible(true);
        });
        btnSalir.addActionListener(e -> {
            this.dispose();   //Opcion D
            prin.setVisible(true);
        });
    }
}
