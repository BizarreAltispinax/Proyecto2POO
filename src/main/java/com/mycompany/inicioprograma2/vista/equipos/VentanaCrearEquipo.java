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
import java.time.LocalDate;
/**
 * Ventana para crear un nuevo equipo dentro del sistema. Permite ingresar
 * información general, datos técnicos, fechas, estado y opcionalmente
 * seleccionar un equipo padre al cual pertenecen sus componentes.
 *
 * <p>Los datos ingresados son enviados al {@link ControladorEquipo}
 * para su validación y posterior registro.</p>
 */
public class VentanaCrearEquipo extends JFrame {
     /**
     * Constructor principal que arma visualmente el formulario de creación
     * de equipos y define las acciones de los botones.
     *
     * @param controlador controlador encargado de gestionar operaciones de equipos.
     */
    public VentanaCrearEquipo(ControladorEquipo controlador) {

        setTitle("Crear Equipo");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(15, 2, 5, 5));

        JTextField txtDesc = new JTextField();
        
        // Combo con tipos cargados desde el controlador
        JComboBox<String> comboTipo = new JComboBox<>();
        controlador.cargarOpcionesEn(comboTipo);
        
        
        JTextField txtUbic = new JTextField();
        JTextField txtFab = new JTextField();
        JTextField txtSerie = new JTextField();
        JTextField txtFechaAdq = new JTextField("2025-01-01");
        JTextField txtFechaServ = new JTextField("");
        JTextField txtMeses = new JTextField();
        JTextField txtCosto = new JTextField();
        JTextField txtSpecs = new JTextField();
        JTextField txtGarantia = new JTextField();

        JComboBox<Equipos.EstadoEquipo> comboEstado =
                new JComboBox<>(Equipos.EstadoEquipo.values());

        JCheckBox chkTienePadre = new JCheckBox("¿Este equipo pertenece a otro?");
        JComboBox<Equipos> comboPadres = new JComboBox<>();
        comboPadres.setEnabled(false);

        chkTienePadre.addActionListener(e -> {
            comboPadres.setEnabled(chkTienePadre.isSelected());
        });
        // Cargar equipos existentes para seleccionar como padre
        controlador.getEquipos().forEach(comboPadres::addItem);

        add(new JLabel("Descripción:")); add(txtDesc);
        add(new JLabel("Tipo:")); add(comboTipo);
        add(new JLabel("Ubicación:")); add(txtUbic);
        add(new JLabel("Fabricante:")); add(txtFab);
        add(new JLabel("Serie:")); add(txtSerie);
        add(new JLabel("Fecha adquisición (AAAA-MM-DD):")); add(txtFechaAdq);
        add(new JLabel("Fecha puesta servicio (opcional):")); add(txtFechaServ);
        add(new JLabel("Meses vida útil:")); add(txtMeses);
        add(new JLabel("Costo inicial:")); add(txtCosto);
        add(new JLabel("Especificaciones (opcional):")); add(txtSpecs);
        add(new JLabel("Garantía (opcional):")); add(txtGarantia);
        add(new JLabel("Estado:")); add(comboEstado);

        add(chkTienePadre);
        add(comboPadres);

        JButton btnGuardar = new JButton("Guardar");
        add(btnGuardar);

        JButton btnCerrar = new JButton("Cerrar");
        add(btnCerrar);
        // Cerrar ventana
        btnCerrar.addActionListener(e -> dispose());
        // Acción para guardar equipo
        btnGuardar.addActionListener(e -> {

            try {
                String desc = txtDesc.getText();
                String tipo = (String) comboTipo.getSelectedItem();
                String ubic = txtUbic.getText();
                String fab = txtFab.getText();
                String serie = txtSerie.getText();
                LocalDate fechaAdq = LocalDate.parse(txtFechaAdq.getText());
                LocalDate fechaServ = txtFechaServ.getText().isEmpty() ?
                        null : LocalDate.parse(txtFechaServ.getText());
                int meses = Integer.parseInt(txtMeses.getText());
                double costo = Double.parseDouble(txtCosto.getText());
                String specs = txtSpecs.getText();
                String garantia = txtGarantia.getText();
                Equipos.EstadoEquipo estado = (Equipos.EstadoEquipo) comboEstado.getSelectedItem();
                
                
                
                
                
                Equipos padre = chkTienePadre.isSelected() ?
                        (Equipos) comboPadres.getSelectedItem() : null;
                int idPadre;
                if (padre!=null){
                    idPadre=padre.getId();
                }else{
                    idPadre=0;
                }
                
                boolean ok = controlador.agregarEquipo(
                        desc, tipo, ubic, fab, serie, fechaAdq,
                        fechaServ, meses, estado, costo, specs, garantia, idPadre
                );

                if (ok)
                    JOptionPane.showMessageDialog(this, "Equipo agregado correctamente.");
                else
                    JOptionPane.showMessageDialog(this, "Error en los datos ingresados.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
