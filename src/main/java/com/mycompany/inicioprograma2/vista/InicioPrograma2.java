/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inicioprograma2.vista;
import com.mycompany.inicioprograma2.vista.equipos.ConsultaArbol;
import com.mycompany.inicioprograma2.vista.equipos.VentanaPrincipalEquipos;
import com.mycompany.inicioprograma2.vista.equipos.GraficoEstado;
import com.mycompany.inicioprograma2.vista.equipos.GraficoTipos;
import com.mycompany.inicioprograma2.vista.mantenimiento.VentanaPrincipalMantenimiento;
import com.mycompany.inicioprograma2.controlador.*;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del sistema. Presenta el menú general desde el cual se
 * accede a las secciones de Equipos, Reportes, Programas de Mantenimiento
 * Preventivo, consultas en árbol y gráficos. Gestiona también el cierre
 * ordenado de la aplicación.
 *
 * Esta clase utiliza el patrón MVC, recibiendo instancias de los controladores
 * para ser enviadas a las ventanas hijas según corresponda.
 *
 * @author Usuario
 */
public class InicioPrograma2 extends JFrame {
     /**
     * Constructor principal que inicializa la ventana del menú y configura
     * los botones para navegar hacia las distintas vistas del sistema.
     *
     * @param ctlEquipos controlador para la gestión de equipos
     * @param ctlFallas controlador para la gestión de fallas
     * @param ctlMP controlador para la gestión de mantenimiento preventivo
     * @param ctlOP controlador para las órdenes preventivas
     * @param ctlOC controlador para las órdenes correctivas
     * @param ctlPP controlador para los programas preventivos
     * @param ctlTarea controlador para las tareas
     */
    public InicioPrograma2(ControladorEquipo ctlEquipos, ControladorFalla ctlFallas,ControladorMatenimientoPreventivo ctlMP,ControladorOrdenPreventiva ctlOP,ControladorOrdenCorrectiva ctlOC,ControladorProgramasPreventivos ctlPP,ControladorTarea ctlTarea) {
        setTitle("Menú Principal");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        /**
         * Implementacion de los botones
         */
        JButton btnEquipos = new JButton("Equipos");
        add(btnEquipos);
        
        JButton btnReportes = new JButton("Reportes");
        add(btnReportes);

        JButton btnMantePreventivo = new JButton("Programas de Mantenimiento");
        add(btnMantePreventivo);
        
        JButton btnConsultarArbol = new JButton("Consulta Estilo Árbol");
        add(btnConsultarArbol);
        
        JButton btnGraficos1 = new JButton("Gráfico por Tipo de Equipo");
        add(btnGraficos1);
        
        JButton btnGraficos2 = new JButton("Gráfico por Estado de Equipo");
        add(btnGraficos2);

        JButton btnSalir = new JButton("Salir");
        add(btnSalir);

        /**
         * Funcionalidades de los botones
         */
        //Boton de equipos
        btnEquipos.addActionListener(e -> {
            new VentanaPrincipalEquipos(ctlEquipos,this).setVisible(true);
            this.setVisible(false);//Opcion A
        });

        //Boton de reportes
        btnReportes.addActionListener(e -> {
            new Reportes(ctlEquipos,this).setVisible(true);
            this.setVisible(false);  //Opcion B
        });

        //Boton de mantenimiento
        btnMantePreventivo.addActionListener(e -> {
            new VentanaPrincipalMantenimiento(ctlEquipos,ctlFallas,ctlMP,ctlOP,ctlOC,ctlPP,ctlTarea,this).setVisible(true);
            this.setVisible(false);  //Opcion C
        });

        //Boton para ver grafico por tipos
        btnGraficos1.addActionListener(e -> {
            new GraficoTipos(ctlEquipos,this).setVisible(true);
            this.setVisible(false);  //Opcion D
        });

        //Boton para ver el grafico de estado
        btnGraficos2.addActionListener(e -> {
            new GraficoEstado(ctlEquipos,this).setVisible(true);
            this.setVisible(false);  //Opcion E
        });

        //Boton de consulta tipo arbol
        btnConsultarArbol.addActionListener(e -> {
            new ConsultaArbol(ctlEquipos,this).setVisible(true);
            this.setVisible(false);  //Opcion F
        });

        //Boton para cerrar el programa
        btnSalir.addActionListener(e -> dispose());
    }

    /**
     *
     * @param args
     * Metodo Main
     * Ejecuta los controladores y guarda los datos en archivos .dat
     *
     */
    public static void main(String[] args) {
        ControladorEquipo ctlEquipos = new ControladorEquipo();
        ControladorFalla ctlPersonas = new ControladorFalla();
        ControladorMatenimientoPreventivo ctlUsuarios = new ControladorMatenimientoPreventivo();
        ControladorOrdenPreventiva ctlProductos = new ControladorOrdenPreventiva();
        ControladorOrdenCorrectiva ctlCorrecciones = new ControladorOrdenCorrectiva();
        ControladorProgramasPreventivos ctlPedidos = new ControladorProgramasPreventivos(ctlEquipos);
        ControladorTarea ctlPedido = new ControladorTarea();
        SwingUtilities.invokeLater(() -> new InicioPrograma2(ctlEquipos,ctlPersonas,ctlUsuarios,ctlProductos,ctlCorrecciones,ctlPedidos,ctlPedido).setVisible(true));
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
