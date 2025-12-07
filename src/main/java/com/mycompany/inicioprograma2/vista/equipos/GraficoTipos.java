/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.equipos;
import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;
/**
 *
 * @author Usuario
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraficoTipos extends JFrame{
    public GraficoTipos(ControladorEquipo controlador,InicioPrograma2 prin) {

       

        // 2. Crear dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : controlador.conteoTipos().entrySet()) {
            dataset.addValue(entry.getValue(), "Cantidad", entry.getKey());
        }

        // 3. Crear el gráfico
        JFreeChart chart = ChartFactory.createBarChart(
                "Equipos por tipo",
                "Tipo",
                "Cantidad",
                dataset
        );

        // 4. Panel del gráfico
        ChartPanel chartPanel = new ChartPanel(chart);

        // 5. Crear botón “Salir”
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> {
            this.dispose();
            prin.setVisible(true);
                });

        // 6. Panel inferior para el botón
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnSalir);

        // 7. Añadir todo a la ventana con BorderLayout
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        // 8. Configuración general
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
