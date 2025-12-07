package com.mycompany.inicioprograma2.controlador;

import com.mycompany.inicioprograma2.modelo.Persistencia;
import com.mycompany.inicioprograma2.modelo.mantenimiento.correctivo.ordenes.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Controlador encargado de gestionar las órdenes de trabajo correctivo.
 * Permite crear, buscar, iniciar, finalizar y cancelar órdenes, además de
 * cargar y guardar la persistencia de datos.
 */     
public class ControladorOrdenCorrectiva {

    private final ArrayList<OrdenTrabajoCorrectivo> ordenes;
     /**
     * Constructor que carga las órdenes previamente guardadas.
     * Si el archivo no existe o está vacío, se obtiene null.
     */
    public ControladorOrdenCorrectiva() {
        ordenes = Persistencia.cargar("data/ordenesCorrectivas.dat");
    }

    public List<OrdenTrabajoCorrectivo> getOrdenes() {
        return ordenes;
    }

    public void guardar() {
        Persistencia.guardar("data/ordenesCorrectivas.dat", ordenes);
    }
     /**
     * Crea una nueva orden de trabajo correctivo y la agrega a la lista.
     *
     * @param idEquipo     ID del equipo asociado.
     * @param descripcion  descripción del problema.
     * @param prioridad    prioridad de la orden.
     * @return true siempre que se cree correctamente.
     */
    public boolean crearOrden(int idEquipo, String descripcion, String prioridad) {
        OrdenTrabajoCorrectivo o =
                new OrdenTrabajoCorrectivo(idEquipo, descripcion, prioridad);

        ordenes.add(o);
        return true;
    }

    public OrdenTrabajoCorrectivo buscarPorId(int id) {
        for (OrdenTrabajoCorrectivo o : ordenes)
            if (o.getId() == id)
                return o;

        return null;
    }

    public boolean iniciarOrden(int id, LocalDate fechaInicio) {
        OrdenTrabajoCorrectivo o = buscarPorId(id);
        if (o == null) return false;
        if (o.getEstado() != EstadoOrdenCorrectiva.ABIERTA) return false;

        o.iniciar(fechaInicio);
        return true;
    }
    //Finaliza la orden
    public boolean finalizarOrden(int id, LocalDate fechaFin,
                                  float horas, int manoObra, int materiales,
                                  String repuestos, String obs,
                                  String causaRaiz, String acciones) {

        OrdenTrabajoCorrectivo o = buscarPorId(id);
        //Validaciones
        if (o == null) return false;
        if (o.getEstado() != EstadoOrdenCorrectiva.EN_PROCESO) return false;

        o.finalizar(fechaFin, horas, manoObra, materiales,
                repuestos, obs, causaRaiz, acciones);

        return true;
    }
    //Cancela la orden
    public boolean cancelarOrden(int id, LocalDate fecha, String razon) {
        OrdenTrabajoCorrectivo o = buscarPorId(id);
        if (o == null) return false;
        if (o.getEstado() == EstadoOrdenCorrectiva.FINALIZADA) return false;

        o.cancelar(fecha, razon);
        return true;
    }
}