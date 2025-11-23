package com.mycompany.inicioprograma2.modelo.tareas;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTareas implements Serializable {
    public static final String DOCUMENTO = "tareas.dat";

    private List<Tarea> listaTareas;

    public RepositorioTareas() {
        listaTareas = new ArrayList<>();
        cargarDesdeArchivo();
    }

    public void agregarTarea(String desecripcion) {
        Tarea nueva = new Tarea(desecripcion);
        listaTareas.add(nueva);
        guardarEnArchivo();
    }

    public Tarea buscarTarea(int id) {
        return listaTareas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Tarea> getTodasLasTareas() {
        return listaTareas;
    }

    public boolean modificarTarea(int id, String nuevaDescripcion) {
        Tarea t = buscarTarea(id);
        if (t != null) {
            t.setDescripcion(nuevaDescripcion);
            guardarEnArchivo();
            return true;
        }

        return false;
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DOCUMENTO))) {
            out.writeObject(listaTareas);
        } catch (Exception e) {
            System.out.println("Error guardando tareas: " + e.getMessage());
        }
    }

    @SuppressWarnings("sin revisar")
    private void cargarDesdeArchivo() {
        File archivo = new File(DOCUMENTO);
        if(!archivo.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            listaTareas = (List<Tarea>) in.readObject();

            int maxId = listaTareas.stream()
                    .mapToInt(Tarea::getId)
                    .max()
                    .orElse(0);

            Tarea temp = new Tarea(maxId, "");
            listaTareas.remove(temp);
        } catch (Exception e) {
            System.out.println("Error cargando tareas: " + e.getMessage());
        }
    }
}
