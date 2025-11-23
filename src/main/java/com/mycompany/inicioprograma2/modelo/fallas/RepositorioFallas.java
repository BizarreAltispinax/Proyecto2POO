package com.mycompany.inicioprograma2.modelo.fallas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioFallas implements Serializable {
    private List<Falla> fallas;

    public RepositorioFallas() {
        fallas = new ArrayList<>();
    }

    public void agregarFalla(Falla falla) {
        fallas.add(falla);
    }

    public List<Falla> obtenerTodas() {
        return fallas;
    }

    public Falla buscarPorId(int id) {
        for (Falla f : fallas) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public boolean eliminar(int id) {
        return fallas.removeIf(f -> f.getId() == id);
    }

    public boolean modificar(Falla nuevaFalla) {
        for (int i = 0; i < fallas.size(); i++) {
            if (fallas.get(i).getId() == nuevaFalla.getId()) {
                fallas.set(i, nuevaFalla);
                return true;
            }
        }
        return false;
    }
}
