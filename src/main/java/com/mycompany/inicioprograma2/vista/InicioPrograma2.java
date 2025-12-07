/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inicioprograma2.vista;
import com.mycompany.inicioprograma2.vista.equipos.VentanaPrincipalEquipos;
import com.mycompany.inicioprograma2.vista.equipos.ConultaArbol;
import com.mycompany.inicioprograma2.vista.equipos.GraficoEstado;
import com.mycompany.inicioprograma2.vista.equipos.GraficoTipos;
import com.mycompany.inicioprograma2.vista.mantenimiento.VentanaPrincipalMantenimiento;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.*;
import com.mycompany.inicioprograma2.controlador.*;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Usuario
 */
public class InicioPrograma2 extends JFrame {

    public InicioPrograma2(ControladorEquipo ctlEquipos,ControladorFalla ctlFallas,ControladorMatenimientoPreventivo ctlMP,ControladorOrdenPreventiva ctlOP,ControladorProgramasPreventivos ctlPP,ControladorTarea ctlTarea) {
        setTitle("Menú Principal");
        setSize(700, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));

        JButton btnEquipos = new JButton("Equipos");
        add(btnEquipos);
        
        JButton btnReportes = new JButton("Reportes");
        add(btnReportes);

        JButton btnMantePreventivo = new JButton("Programas de Mantenimiento");
        add(btnMantePreventivo);
        
        JButton btnConsultarArbol = new JButton("Consultar en estilo Arbol");
        add(btnConsultarArbol);
        
        JButton btnGraficos1 = new JButton("Graficos1");
        add(btnGraficos1);
        
        JButton btnGraficos2 = new JButton("Graficos2");
        add(btnGraficos2);

        btnEquipos.addActionListener(e -> {
            new VentanaPrincipalEquipos(ctlEquipos,this).setVisible(true);
            this.setVisible(false);//Opcion A
        });
        btnReportes.addActionListener(e -> {
            new Reportes(ctlEquipos,this).setVisible(true);
            this.setVisible(false);  //Opcion B
        });
        btnMantePreventivo.addActionListener(e -> {
            new VentanaPrincipalMantenimiento(ctlEquipos,ctlFallas,ctlMP,ctlOP,ctlPP,ctlTarea,this).setVisible(true);
            this.setVisible(false);  //Opcion C
        });
        
        btnGraficos1.addActionListener(e -> {
            new GraficoTipos(ctlEquipos,this).setVisible(true);
            this.setVisible(false);  //Opcion C
        });
        
        btnGraficos2.addActionListener(e -> {
            new GraficoEstado(ctlEquipos,this).setVisible(true);
            this.setVisible(false);  //Opcion C
        });
        
        btnConsultarArbol.addActionListener(e -> {
            new ConultaArbol(ctlEquipos,this).setVisible(true);
            this.setVisible(false);  //Opcion C
        });
    }

    public static void main(String[] args) {
        ControladorEquipo ctlEquipos = new ControladorEquipo();
        ControladorFalla ctlPersonas = new ControladorFalla();
        ControladorMatenimientoPreventivo ctlUsuarios = new ControladorMatenimientoPreventivo();
        ControladorOrdenPreventiva ctlProductos = new ControladorOrdenPreventiva();
        ControladorProgramasPreventivos ctlPedidos = new ControladorProgramasPreventivos(ctlEquipos);
        ControladorTarea ctlPedido = new ControladorTarea();
        SwingUtilities.invokeLater(() -> new InicioPrograma2(ctlEquipos,ctlPersonas,ctlUsuarios,ctlProductos,ctlPedidos,ctlPedido).setVisible(true));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ctlEquipos.guardar();
            ctlPersonas.guardar();
            ctlUsuarios.guardar();
            ctlProductos.guardar();
            ctlPedidos.guardar();
            ctlPedido.guardar();
        }));
    }
}
