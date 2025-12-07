package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.Persistencia;
import com.mycompany.inicioprograma2.modelo.mantenimiento.fallas.Falla;

import java.util.ArrayList;
/**
 * Controlador encargado de gestionar las fallas asociadas a los equipos.
 * Permite agregar, modificar, eliminar y consultar fallas.
 *
 * Las fallas se almacenan en un archivo binario mediante la clase {@link Persistencia}.
 * Cada falla está asociada a un ID de equipo y posee un ID propio.
 */
public class ControladorFalla {

    private final ArrayList<Falla> fallas;
        /**
     * Constructor: carga la lista de fallas desde el archivo de persistencia.
     * Si el archivo no existe o está vacío, la lista cargada será una lista vacía.
     */
    public ControladorFalla() {
        fallas = Persistencia.cargar("data/fallas.dat");
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
        Persistencia.guardar("data/fallas.dat", fallas);
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