/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.vista.equipos;

import com.mycompany.inicioprograma2.controlador.ControladorEquipo;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;
import javax.swing.tree.*;
import java.awt.BorderLayout;
import com.mycompany.inicioprograma2.modelo.Equipos;
import com.mycompany.inicioprograma2.vista.InicioPrograma2;

/**
 * Ventana que permite consultar la estructura jerárquica de equipos
 * en forma de árbol (subequipos y equipos padre). Además, permite 
 * buscar un equipo por su ID y generar dinámicamente el árbol a partir 
 * de dicho equipo como raíz.
 *
 * Esta herramienta facilita la visualización de dependencias entre 
 * equipos y muestra en un panel lateral los detalles del equipo 
 * seleccionado dentro del árbol.
 *
 * Funcionalidades principales:
 * <ul>
 *   <li>Búsqueda de equipos por ID</li>
 *   <li>Construcción de árbol dinámico según el equipo seleccionado</li>
 *   <li>Visualización de los detalles de cada equipo</li>
 *   <li>Listado de subequipos pertenecientes al nodo elegido</li>
 *   <li>Regreso a la ventana principal del sistema</li>
 * </ul>
 *
 * Esta clase sigue el esquema MVC utilizando el controlador de equipos
 * para extraer la información y construir la jerarquía correspondiente.
 *
 * @author Usuario
 */
public class ConsultaArbol extends JFrame{
        /**
     * Crea la ventana de consulta con árbol de equipos. Recibe el 
     * controlador para gestionar la información y la ventana principal
     * para volver a ella al cerrar esta vista.
     *
     * @param controlador controlador encargado de gestionar los equipos
     * @param prin ventana principal para regresar al menú inicial
     */
    public ConsultaArbol(ControladorEquipo controlador, InicioPrograma2 prin){

        // Campo y botón de búsqueda
        JTextField txtBusqueda = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar equipo");
        JButton btnSalir = new JButton("Salir");
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.add(new JLabel("ID de equipo:"));
        panelBusqueda.add(txtBusqueda);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnSalir);

        // Árbol inicial (vacío)
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Seleccione un equipo");
        JTree tree = new JTree(root);

        // Panel de detalles
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));

        JLabel lblID = new JLabel("ID:");
        JLabel lblDesc = new JLabel("Descripcion:");
        JLabel lblTipo = new JLabel("Tipo:");
        JLabel lblUbi = new JLabel("Ubicacion:");
        JLabel lblFab = new JLabel("Fabricante:");
        JLabel lblSerie = new JLabel("Serie:");
        JLabel lblFechaA = new JLabel("Fecha de Adquisicion:");
        JLabel lblFechaS = new JLabel("Fecha puesta en Servivio:");
        JLabel lblMeses = new JLabel("Meses de vida util:");
        JLabel lblEstado = new JLabel("Estado:");
        JLabel lblCosto = new JLabel("Costo Inicial:");
        JLabel lblEspecificaciones = new JLabel("Especificaciones:");
        JLabel lblGarantia = new JLabel("Garantia:");
        
        JLabel lblPadre = new JLabel("ID Padre:");
        JLabel lblSubequiposTitulo = new JLabel("Compuesto por:");
        JTextArea txtSubequipos = new JTextArea(8, 20);

        txtSubequipos.setEditable(false);
        txtSubequipos.setLineWrap(true);

        panelDetalles.add(lblID);
        panelDetalles.add(lblDesc);
        panelDetalles.add(lblTipo);
        panelDetalles.add(lblUbi);
        panelDetalles.add(lblFab);
        panelDetalles.add(lblSerie);
        panelDetalles.add(lblFechaA);
        panelDetalles.add(lblFechaS);
        panelDetalles.add(lblMeses);
        panelDetalles.add(lblEstado);
        panelDetalles.add(lblCosto);
        panelDetalles.add(lblEspecificaciones);
        panelDetalles.add(lblGarantia);
        panelDetalles.add(lblPadre);
        panelDetalles.add(new JLabel(" "));
        panelDetalles.add(lblSubequiposTitulo);
        panelDetalles.add(new JScrollPane(txtSubequipos));

        // Evento al seleccionar un nodo
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode nodo =
                (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            if (nodo == null) return;
            Object obj = nodo.getUserObject();

            if (!(obj instanceof Equipos)) return;
            Equipos eq = (Equipos) obj;

            lblID.setText("ID: " + eq.getId());
            lblDesc.setText("Descripcion: " + eq.getDescripcion());
            lblTipo.setText("Tipo: " + eq.getTipo());
            lblUbi.setText("Ubicacion: " + eq.getUbicacion());
            lblFab.setText("Fabricante: " + eq.getFabricante());
            lblSerie.setText("Serie: " + eq.getSerie());
            lblFechaA.setText("Fecha de Adquisicion: " + eq.getFechaAdquisicion());
            lblFechaS.setText("Fecha puesta en Servivio: " + (eq.getFechaPuestaServicio()== null ? "Ninguno" : eq.getFechaPuestaServicio()));
            lblMeses.setText("Meses de vida util: " + eq.getMesesVidaUtil());
            lblEstado.setText("Estado: " + eq.getEstado());
            lblCosto.setText("Costo: " + eq.getCostoInicial());
            lblEspecificaciones.setText("Especificaciones: " + (eq.getEspecificaciones().equals("") ? "Ninguno" : eq.getEspecificaciones()));
            lblGarantia.setText("Garantia: " + (eq.getGarantia().equals("")  ? "Ninguno" : eq.getGarantia()));
            
            
            lblPadre.setText("ID Padre: " + (eq.getEquipoPadreInteger() == null ? "Ninguno" : eq.getEquipoPadreInteger()));
            
            // Mostrar subequipos
            StringBuilder sb = new StringBuilder();
            Enumeration<TreeNode> hijos = nodo.children();

            while (hijos.hasMoreElements()) {
                DefaultMutableTreeNode h = (DefaultMutableTreeNode) hijos.nextElement();
                Equipos eh = (Equipos) h.getUserObject();
                sb.append(" - " + eh.getDescripcion() + "\n");
            }

            txtSubequipos.setText(sb.length() == 0 ? "No tiene subequipos." : sb.toString());
        });

        // Evento: buscar equipo por ID
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtBusqueda.getText());

                // Buscar equipo raíz
                Equipos raiz = null;
                for (Equipos eq : controlador.getEquipos()) {
                    if (eq.getId() == id) {
                        raiz = eq;
                        break;
                    }
                }

                if (raiz == null) {
                    JOptionPane.showMessageDialog(null, "No existe un equipo con ID " + id);
                    return;
                }
                
                // Crear nuevo árbol con ese equipo como raíz
                DefaultMutableTreeNode nuevoRoot = controlador.construirSubArbol(raiz, controlador.getEquipos());

                tree.setModel(new DefaultTreeModel(nuevoRoot));
                for (int i = 0; i < tree.getRowCount(); i++) tree.expandRow(i);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número.");
            }
        });
        btnSalir.addActionListener(e -> {
            this.dispose();
            prin.setVisible(true);
                });

        // Split pane
        JSplitPane split = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(tree),
            panelDetalles
        );

        split.setDividerLocation(250);
        this.setTitle("Árbol de Equipos con Búsqueda");
        this.setLayout(new BorderLayout());
        this.add(panelBusqueda, BorderLayout.NORTH);
        this.add(split, BorderLayout.CENTER);

        this.setSize(750, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
