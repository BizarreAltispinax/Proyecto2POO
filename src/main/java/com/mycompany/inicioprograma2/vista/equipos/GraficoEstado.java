/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.equipos;
import com.mycompany.inicioprograma2.controlador.ControladorEquipo;

import com.mycompany.inicioprograma2.modelo.Equipos.EstadoEquipo;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 * Ventana que genera y muestra un gráfico de barras con la cantidad de equipos
 * según su estado actual. Utiliza JFreeChart para construir el gráfico.
 *
 * <p>
 * Esta vista obtiene los datos desde {@link ControladorEquipo} y permite volver
 * a la ventana principal {@link InicioPrograma2}.
 * </p>
 *
 * @author Usuario
 */
public class GraficoEstado extends JFrame{
     /**
     * Constructor que genera la ventana del gráfico basada en los datos
     * del controlador de equipos.
     *
     * @param controlador Controlador de equipos del cual se obtienen los estados y cantidades.
     * @param prin Ventana principal a la cual se regresa al presionar "Salir".
     */
    public GraficoEstado(ControladorEquipo controlador,InicioPrograma2 prin) {

       
 // ------ 2. Crear dataset ------
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<EstadoEquipo, Integer> entry : controlador.contarPorEstado().entrySet()) {
            dataset.addValue(entry.getValue(), "Cantidad", entry.getKey().toString());
        }

        // ------ 3. Crear gráfico ------
        JFreeChart chart = ChartFactory.createBarChart(
                "Equipos por Estado",
                "Estado",
                "Cantidad",
                dataset
        );

        // Panel del gráfico
        ChartPanel chartPanel = new ChartPanel(chart);

        // ------ 4. Botón Salir ------
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> {
            this.dispose();
            prin.setVisible(true);
                });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnSalir);

        // Layout
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        // Configuración de ventana
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
