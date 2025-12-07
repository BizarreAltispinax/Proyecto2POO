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
import com.mycompany.inicioprograma2.modelo.Equipos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ventana destinada a modificar los datos de un equipo existente.
 * 
 * El usuario primero ingresa un ID y valida si existe. 
 * Una vez validado, se habilita la edición manual de todos los campos,
 * los cuales deben completarse nuevamente, ya que no se cargan automáticamente.
 * 
 * Al guardar, se envía toda la información al ControladorEquipo, que realiza
 * la actualización real en la estructura de datos.
 * 
 * Esta ventana incluye datos básicos, técnicos, de estado, costos, y relaciones con equipos padre.
 * 
 * @author Usuario
 */
public class VentanaModificarEquipo extends JFrame {

    private ControladorEquipo controlador;

    private JTextField txtIdBusqueda;
    private JTextField txtDescripcion;
    private JComboBox<String> comboTipo;
    private JTextField txtUbicacion;
    private JTextField txtFabricante;
    private JTextField txtSerie;
    private JTextField txtFechaAdq;
    private JTextField txtFechaServicio;
    private JTextField txtVidaUtil;
    private JTextField txtCosto;
    private JTextArea txtEspecificaciones;
    private JTextArea txtGarantia;

    private JComboBox<Equipos> comboPadre;
    private JComboBox<Equipos.EstadoEquipo> comboEstado;

    private JButton btnBuscar;
    private JButton btnGuardar;
    private JButton btnCancelar;

    private int idEquipoModificar = -1;
     /**
     * Constructor principal que inicializa la ventana.
     *
     * @param controlador instancia del controlador de equipos.
     */
    public VentanaModificarEquipo(ControladorEquipo controlador) {
        this.controlador = controlador;

        setTitle("Modificar Equipo");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(17, 2, 5, 5));

        //============================
        // ID del equipo a modificar
        //============================
        panel.add(new JLabel("ID del equipo a modificar:"));
        txtIdBusqueda = new JTextField();
        panel.add(txtIdBusqueda);

        btnBuscar = new JButton("Validar ID");
        panel.add(btnBuscar);
        panel.add(new JLabel("")); // espacio vacío

        //============================
        // Campos del equipo (vacíos)
        //============================
        panel.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panel.add(txtDescripcion);
        
        
        panel.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>();
        controlador.cargarOpcionesEn(comboTipo);
        panel.add(comboTipo);
        
       
        panel.add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        panel.add(txtUbicacion);

        panel.add(new JLabel("Fabricante:"));
        txtFabricante = new JTextField();
        panel.add(txtFabricante);

        panel.add(new JLabel("Serie:"));
        txtSerie = new JTextField();
        panel.add(txtSerie);

        panel.add(new JLabel("Fecha adquisición (AAAA-MM-DD):"));
        txtFechaAdq = new JTextField();
        panel.add(txtFechaAdq);

        panel.add(new JLabel("Fecha puesta en servicio:"));
        txtFechaServicio = new JTextField();
        panel.add(txtFechaServicio);

        panel.add(new JLabel("Meses vida útil:"));
        txtVidaUtil = new JTextField();
        panel.add(txtVidaUtil);

        panel.add(new JLabel("Estado:"));
        comboEstado = new JComboBox<>(Equipos.EstadoEquipo.values());
        panel.add(comboEstado);

        panel.add(new JLabel("Costo inicial:"));
        txtCosto = new JTextField();
        panel.add(txtCosto);

        //============================
        // Combo equipo padre
        //============================
        panel.add(new JLabel("Equipo Padre (opcional):"));
        comboPadre = new JComboBox<>();

        comboPadre.addItem(null); // "sin padre"
        ArrayList<Equipos> lista = controlador.getEquipos();
        for (Equipos e : lista) {
            comboPadre.addItem(e);
        }

        panel.add(comboPadre);

        //============================
        // Áreas de texto
        //============================
        panel.add(new JLabel("Especificaciones técnicas:"));
        txtEspecificaciones = new JTextArea(3, 20);
        panel.add(new JScrollPane(txtEspecificaciones));

        panel.add(new JLabel("Información garantía:"));
        txtGarantia = new JTextArea(3, 20);
        panel.add(new JScrollPane(txtGarantia));

        //============================
        // Botones
        //============================
        btnGuardar = new JButton("Guardar Cambios");
        btnCancelar = new JButton("Cancelar");

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        add(panel);

        // Acciones
        btnBuscar.addActionListener(e -> validarID());
        btnGuardar.addActionListener(e -> guardarCambios());
        btnCancelar.addActionListener(e -> dispose());
    }

    //============================
    // Validación del ID
    //============================
    private void validarID() {
        try {
            int id = Integer.parseInt(txtIdBusqueda.getText());
            Equipos eq = controlador.buscarPorId(id);

            if (eq == null) {
                JOptionPane.showMessageDialog(this, "No existe un equipo con ese ID.");
                idEquipoModificar = -1;
                return;
            }

            idEquipoModificar = id;
            JOptionPane.showMessageDialog(this,
                    "ID válido. Ahora ingrese TODOS los datos nuevos.\n"
                    + "(Los campos no se llenan automáticamente)");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    //============================
    // Guardar todos los cambios
    //============================
    private void guardarCambios() {
        if (idEquipoModificar == -1) {
            JOptionPane.showMessageDialog(this, "Primero valide un ID.");
            return;
        }

        try {
            String descripcion = txtDescripcion.getText();
            String tipo = (String) comboTipo.getSelectedItem();
            String ubicacion = txtUbicacion.getText();
            String fabricante = txtFabricante.getText();
            String serie = txtSerie.getText();
            String fechaAdq = txtFechaAdq.getText();
            String fechaServ = txtFechaServicio.getText();
            String vida = txtVidaUtil.getText();
            String costo = txtCosto.getText();
            String especificaciones = txtEspecificaciones.getText();
            String garantia = txtGarantia.getText();

            Equipos.EstadoEquipo estado = (Equipos.EstadoEquipo) comboEstado.getSelectedItem();
            Equipos padre = (Equipos) comboPadre.getSelectedItem();

            boolean ok = controlador.modificarEquipo(
                    idEquipoModificar,
                    descripcion,
                    tipo,
                    ubicacion,
                    fabricante,
                    serie,
                    fechaAdq,
                    fechaServ,
                    vida,
                    costo,
                    especificaciones,
                    garantia,
                    estado,
                    (padre == null ? 0 : padre.getId())
            );

            if (ok) {
                JOptionPane.showMessageDialog(this, "Cambios guardados correctamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error: no se pudo modificar el equipo.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }
}
