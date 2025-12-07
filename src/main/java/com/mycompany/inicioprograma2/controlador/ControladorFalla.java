package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.Persistencia;
import com.mycompany.inicioprograma2.modelo.mantenimiento.fallas.Falla;

import java.util.ArrayList;

public class ControladorFalla {

    private final ArrayList<Falla> fallas;

    public ControladorFalla() {
        fallas = Persistencia.cargar("fallas.dat");
    }

    public ArrayList<Falla> getFallasEquipo(int idEquipo) {
        ArrayList<Falla> lista = new ArrayList<>();
        for (Falla f : fallas) {
            if (f.getIdEquipo() == idEquipo) lista.add(f);
        }
        return lista;
    }

    public Falla buscarFalla(int idEquipo, int idFalla) {
        for (Falla f : fallas) {
            if (f.getIdEquipo() == idEquipo && f.getId() == idFalla)
                return f;
        }
        return null;
    }

    public void guardar() {
        Persistencia.guardar("fallas.dat", fallas);
    }

    public boolean agregarFalla(int idEquipo, int idFalla, String descripcion) {

        // validar ID única por equipo
        if (buscarFalla(idEquipo, idFalla) != null) return false;

        if (descripcion == null || descripcion.isBlank()) return false;

        fallas.add(new Falla(idEquipo, idFalla, descripcion));
        return true;
    }

    public boolean modificarFalla(int idEquipo, int idFalla, String nuevaDescripcion) {

        Falla f = buscarFalla(idEquipo, idFalla);
        if (f == null) return false;

        if (nuevaDescripcion == null || nuevaDescripcion.isBlank())
            return false;

        f.setDescripcion(nuevaDescripcion);
        return true;
    }

    public boolean eliminarFalla(int idEquipo, int idFalla) {
        return fallas.removeIf(f ->
                f.getIdEquipo() == idEquipo && f.getId() == idFalla
        );
    }
}