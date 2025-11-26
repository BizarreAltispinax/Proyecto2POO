package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.fallas.Falla;
import javax.swing.*;
import java.util.ArrayList;

public class ControladorFalla {
    private final ArrayList<Falla> fallas;

    public ControladorFalla() {
        fallas = new ArrayList<>();
    }

    public ArrayList<Falla> getFallas() {
        return fallas;
    }

    public Falla buscarPorId(int id) {
        for (Falla f : fallas) {
            if (f.getId() == id) return f;
        }
        return null;
    }

    public boolean agregarFalla(String descripcion, String tipo, String criticidad) {
        if (descripcion == null || descripcion.isBlank()) return false;
        if (tipo == null || tipo.isBlank()) return false;
        if (criticidad == null || criticidad.isBlank()) return false;

        Falla nueva = new Falla(descripcion, tipo, criticidad);
        fallas.add(nueva);

        return true;
    }

    public boolean modificarFalla(int id, String descripcion, String tipo, String criticidad) {
        Falla f = buscarPorId(id);
        if (f == null) return false;

        try {
            if (descripcion == null || descripcion.isBlank())
                throw new IllegalArgumentException("La descripcion no puede estar vacia");

            if (tipo == null || tipo.isBlank())
                throw new IllegalArgumentException("El tipo no puede estar vacio");

            if (criticidad == null || criticidad.isBlank())
                throw new IllegalArgumentException("Debe asignarle la criticidad");

            f.setDescripcion(descripcion);
            f.setTipo(tipo);
            f.setCriticidad(criticidad);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean eliminarFalla(int id) {
        return fallas.removeIf(f -> f.getId() == id);
    }

    public void cargarFallasEn(JComboBox<String> combo) {
        combo.removeAllItems();
        for (Falla f : fallas) {
            combo.addItem(f.getId() + " - " + f.getDescripcion());
        }
    }
}
