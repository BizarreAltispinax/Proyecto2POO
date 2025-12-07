package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.Persistencia;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.EstadoOrden;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.HistorialFallas;
import com.mycompany.inicioprograma2.modelo.mantenimiento.preventivo.ordenes.OrdenTrabajoPreventivo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorOrdenPreventiva {
    ControladorEquipo controladorEquipo = new ControladorEquipo();
    private final ArrayList<OrdenTrabajoPreventivo> ordenes;

    public ControladorOrdenPreventiva() {
        ordenes = Persistencia.cargar("data/ordenesPreventivas.dat");
        actualizarContador();
    }

    private void actualizarContador() {
        int max = 0;
        for (OrdenTrabajoPreventivo o : ordenes) {
            if (o.getId() > max) max = o.getId();
        }
        OrdenTrabajoPreventivo.setContador(max + 1);
    }

    public ArrayList<OrdenTrabajoPreventivo> getOrdenes() {
        return ordenes;
    }

    public OrdenTrabajoPreventivo buscarPorId(int id) {
        for (OrdenTrabajoPreventivo o : ordenes) {
            if (o.getId() == id) return o;
        }

        return null;
    }
    public void guardar() {
        Persistencia.guardar("data/ordenesPreventivas.dat", ordenes);
    }

    public boolean crearOrden(int equipoId, int faseIndex, LocalDate fechaProgramada) {
        faseIndex = faseIndex+1;
        if (equipoId <= 0) return false;
        if (faseIndex < 0) return false;
        if (fechaProgramada == null) return false;
        if (controladorEquipo.buscarPorId(equipoId) == null)
            return false;

        OrdenTrabajoPreventivo o = new OrdenTrabajoPreventivo(equipoId, faseIndex, fechaProgramada);
        ordenes.add(o);

        return true;
    }

    public boolean iniciarOrden(int id, LocalDate fechaInicio) {
        OrdenTrabajoPreventivo o = buscarPorId(id);

        if (o == null) return false;
        if (o.getEstado() != EstadoOrden.PENDIENTE) return false;

        o.empezarOrden(fechaInicio);

        return true;
    }

    public boolean finalizarOrden(int id, LocalDate fechaFinalizacion, float horas, int manoObra, int materiales, String obs) {
        OrdenTrabajoPreventivo o = buscarPorId(id);

        if (o == null) return false;
        if (o.getEstado() != EstadoOrden.EN_EJECUCION) return false;

        o.finalizarOrden(fechaFinalizacion, horas, manoObra, materiales, obs);
        return true;
    }

    public boolean cancelarOrden(int id, LocalDate fechaCancelacion, String razon) {
        OrdenTrabajoPreventivo o = buscarPorId(id);

        if (o == null) return false;
        if (o.getEstado() == EstadoOrden.TERMINADA) return false;
        if (o.getEstado() == EstadoOrden.CANCELADA) return false;

        o.cancelarOrden(fechaCancelacion, razon);

        return true;
    }

    public boolean agregarFalla(int idOrden, HistorialFallas historial) {
        OrdenTrabajoPreventivo o = buscarPorId(idOrden);

        if(o == null) return false;

        o.agregarFalla(historial);

        return true;
    }
}
