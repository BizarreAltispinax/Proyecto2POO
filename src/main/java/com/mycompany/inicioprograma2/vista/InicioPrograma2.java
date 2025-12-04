/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inicioprograma2.vista;
import com.mycompany.inicioprograma2.vista.equipos.VentanaPrincipalEquipos;
import com.mycompany.inicioprograma2.vista.mantenimiento.VentanaPrincipalMantenimiento;
import com.mycompany.inicioprograma2.vista.mantenimiento.preventivo.*;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Usuario
 */
public class InicioPrograma2 extends JFrame {

    public InicioPrograma2() {
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

        btnEquipos.addActionListener(e -> {
            new VentanaPrincipalEquipos().setVisible(true);
            this.dispose();   //Opcion A
        });
        btnReportes.addActionListener(e -> {
            new Reportes().setVisible(true);
            this.dispose();   //Opcion B
        });
        btnMantePreventivo.addActionListener(e -> {
            new VentanaPrincipalMantenimiento().setVisible(true);
            this.dispose();   //Opcion C
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InicioPrograma2().setVisible(true));
    }
}
