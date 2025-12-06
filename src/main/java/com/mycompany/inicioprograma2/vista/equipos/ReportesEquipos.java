/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.equipos;

/**
 *
 * @author Usuario
 */
import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import javax.swing.*;
import java.awt.*;

public class ReportesEquipos extends JFrame {

    private ControladorEquipo controlador;

    public ReportesEquipos(ControladorEquipo controlador) {
        

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


        btnEquipsSin.addActionListener(e -> {
            try{
                int id = Integer.parseInt(txtId.getText());
                controlador.ReporteEquipoSin(id);
            }catch (NumberFormatException w){
                JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
            }
        });
        btnEquipsCon.addActionListener(e -> {
            try{
                int id = Integer.parseInt(txtId.getText());
                controlador.ReporteEquipoCon(id);
            }catch (NumberFormatException w){
                JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");
            }
        });
        btnTodos.addActionListener(e -> { 
            controlador.ReporteEquiposTodos();});
        
    }
}
