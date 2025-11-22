/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inicioprograma2.vista;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Usuario
 */
public class InicioPrograma2 extends JFrame {

    public InicioPrograma2() {
        setTitle("Menú Principal");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1));

        JButton btnEquipos = new JButton("Equipos");
        add(btnEquipos);
        
        JButton btnReportes = new JButton("Reportes");
        add(btnReportes);

        btnEquipos.addActionListener(e -> {
            new VentanaPrincipal().setVisible(true);
            this.dispose();   // ⭐ Opción B
        });
        btnReportes.addActionListener(e -> {
            new Reportes().setVisible(true);
            this.dispose();   // ⭐ Opción B
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InicioPrograma2().setVisible(true));
    }
}
