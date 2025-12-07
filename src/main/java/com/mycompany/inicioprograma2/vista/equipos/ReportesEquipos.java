/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.equipos;


import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import javax.swing.*;
import java.awt.*;
/**
 * Ventana que permite generar diferentes tipos de reportes relacionados
 * con equipos registrados en el sistema. Ofrece opciones para generar un
 * reporte de un equipo sin componentes, con componentes o un reporte completo
 * de todos los equipos.
 *
 * <p>
 * La clase interactúa directamente con el {@link ControladorEquipo} para
 * solicitar los reportes correspondientes.
 * </p>
 */
public class ReportesEquipos extends JFrame {

    private ControladorEquipo controlador;
     /**
     * Constructor que inicializa la ventana de reportes de equipos y configura
     * los botones para generar los distintos reportes disponibles.
     *
     * @param controlador Controlador desde el cual se ejecutarán los reportes.
     */
    public ReportesEquipos(ControladorEquipo controlador) {
        
        // Configuración básica de la ventana
        setTitle("Gestión de Equipos");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));
        JTextField txtId = new JTextField();
        JButton btnEquipsSin = new JButton("Equipo (Sin componentes)");
        JButton btnEquipsCon = new JButton("Equipo (Con sus componentes)");
        JButton btnTodos = new JButton("Todos (Con sus componentes)");

        add(new JLabel("Id del Equipo:"));add(txtId);
        add(btnEquipsSin);

        add(btnEquipsCon);

        add(btnTodos);

        // Acción para reporte sin componentes
        btnEquipsSin.addActionListener(e -> {
            try{
                int id = Integer.parseInt(txtId.getText());
                controlador.reporteEquipoSin(id);
            }catch (NumberFormatException w){
                JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
            }
        });
        btnEquipsCon.addActionListener(e -> {
            try{
                int id = Integer.parseInt(txtId.getText());
                controlador.reporteEquipoCon(id);
            }catch (NumberFormatException w){
                JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
            }
        });
        btnTodos.addActionListener(e -> { 
            controlador.reporteEquiposTodos();});
        
    }
}
