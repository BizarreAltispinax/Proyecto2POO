/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.controlador;

/**
 *
 * @author Usuario
 */
import com.mycompany.inicioprograma2.modelo.Equipos;
import com.mycompany.inicioprograma2.modelo.Equipos.EstadoEquipo;
import com.mycompany.inicioprograma2.modelo.Persistencia;
import java.util.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class ControladorEquipo {
    private String rutaArchivo;
    private final ArrayList<Equipos> equipos;

    public ControladorEquipo() {
        equipos = Persistencia.cargar("equipos.dat");
 
        this.rutaArchivo = "datos.txt";
    }

    public ArrayList<Equipos> getEquipos() {
        return equipos;
    }
    public void guardar() {
        Persistencia.guardar("equipos.dat", equipos);
    }

    public Equipos buscarPorId(int id) {
        for (Equipos eq : equipos) {
            if (eq.getId() == id)
                return eq;
        }
        return null;
    }

    public boolean agregarEquipo(String descripcion, String tipo, String ubicacion, String fabricante,
                                 String serie, LocalDate fechaAdquisicion, LocalDate fechaPuestaServicio,
                                 int mesesVidaUtil, Equipos.EstadoEquipo estado, double costoInicial,
                                 String especificaciones, String garantia, int equipoPadre) {

        if (mesesVidaUtil <= 0 || costoInicial <= 0) return false;
        if (fechaPuestaServicio != null && fechaPuestaServicio.isBefore(fechaAdquisicion)) return false;

        Equipos nuevo = new Equipos(descripcion, tipo, ubicacion, fabricante, serie, fechaAdquisicion,
                fechaPuestaServicio, mesesVidaUtil, estado, costoInicial, especificaciones, garantia, equipoPadre);

        equipos.add(nuevo);
        return true;
    }
    public DefaultMutableTreeNode construirSubArbol(Equipos raiz, List<Equipos> equipos) {
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(raiz);

        for (Equipos e : equipos) {
            if (e.getEquipoPadreInteger() != null && e.getEquipoPadreInteger() == raiz.getId()) {
                nodo.add(construirSubArbol(e, equipos));
            }
        }

        return nodo;
    }
    public Map<String, Integer>ConteoTipos(){
        Map<String, Integer> conteo = new HashMap<>();
        for (Equipos e : equipos) {
            conteo.put(e.getTipo(), conteo.getOrDefault(e.getTipo(), 0) + 1);
        }
        return conteo;
    }
    public Map<EstadoEquipo, Integer> contarPorEstado() {
        Map<EstadoEquipo, Integer> conteo = new EnumMap<>(EstadoEquipo.class);

        // Inicializa
        for (EstadoEquipo e : EstadoEquipo.values()) {
            conteo.put(e, 0);
        }

        // Cuenta
        for (Equipos eq : equipos) {
            conteo.put(eq.getEstado(), conteo.get(eq.getEstado()) + 1);
        }

        return conteo;
    }  
        
    
    public boolean modificarEquipo(
        int id,
        String descripcion,
        String tipo,
        String ubicacion,
        String fabricante,
        String serie,
        String fechaAdq,
        String fechaServ,
        String vidaUtilStr,
        String costoStr,
        String especificaciones,
        String garantia,
        Equipos.EstadoEquipo estado,
        Integer idPadre
    ) {

        //===============================
        // 1. Buscar equipo a modificar
        //===============================
        System.out.println("Hola");
        Equipos equipo = buscarPorId(id);
        if (equipo == null) return false;
        System.out.println("Hola2");
        try {
            System.out.println("Hola3");
            //===============================
            // Validaciones básicas
            //===============================
            if (descripcion == null || descripcion.isBlank())
                throw new IllegalArgumentException("La descripción no puede estar vacía.");

            if (tipo == null || tipo.isBlank())
                throw new IllegalArgumentException("El tipo no puede estar vacío.");

            if (ubicacion == null || ubicacion.isBlank())
                throw new IllegalArgumentException("La ubicación no puede estar vacía.");

            if (fabricante == null || fabricante.isBlank())
                throw new IllegalArgumentException("El fabricante no puede estar vacío.");

            if (serie == null || serie.isBlank())
                throw new IllegalArgumentException("La serie no puede estar vacía.");

            //===============================
            // Conversión de valores numéricos
            //===============================
            int vidaUtil = Integer.parseInt(vidaUtilStr);
            if (vidaUtil <= 0)
                throw new IllegalArgumentException("La vida útil debe ser > 0.");

            double costo = Double.parseDouble(costoStr);
            if (costo <= 0)
                throw new IllegalArgumentException("El costo inicial debe ser > 0.");

            //===============================
            // Conversión de fechas
            //===============================
            LocalDate fAdq = null;
            LocalDate fServ = null;

            if (!fechaAdq.isBlank())
                fAdq = LocalDate.parse(fechaAdq);

            if (!fechaServ.isBlank())
                fServ = LocalDate.parse(fechaServ);

            if (fAdq != null && fServ != null && fServ.isBefore(fAdq))
                throw new IllegalArgumentException("La fecha de puesta en servicio no puede ser menor que la de adquisición.");

            //===============================
            // Padre opcional
            //===============================
            Equipos padre = null;
            if (idPadre != 0) {
                padre = buscarPorId(idPadre);

                if (padre == null)
                    throw new IllegalArgumentException("El equipo padre no existe.");

                // 🔥 Evitar ciclos
                if (creaCiclo(equipo, padre))
                    throw new IllegalArgumentException("Asignar este padre provocaría un ciclo en la jerarquía.");
            }

            //===============================
            // Aplicar cambios al equipo
            //===============================
            equipo.setDescripcion(descripcion);
            equipo.setTipo(tipo);
            equipo.setUbicacion(ubicacion);
            equipo.setFabricante(fabricante);
            equipo.setSerie(serie);
            equipo.setFechaAdquisicion(fAdq);
            equipo.setFechaPuestaServicio(fServ);
            equipo.setMesesVidaUtil(vidaUtil);
            equipo.setCostoInicial(costo);
            equipo.setEspecificaciones(especificaciones);
            equipo.setGarantia(garantia);
            equipo.setEstado(estado);

            //===============================
            // Actualizar padre
            //===============================
            if (idPadre == 0){
                equipo.setEquipoPadre(0);
            }else{
                equipo.setEquipoPadre(padre.getId());
            }
            

            return true; // éxito

        } catch (Exception e) {
            // Podés imprimir si querés:
            // e.printStackTrace();
            System.out.println("Hola5");
            e.printStackTrace();
            return false;
            
        }
    }
    
    
    
    public String ConsultarEquipo(int id) {
        for (Equipos e : equipos){
            if (e.getId()==id){
                return e.toString();
            }
        }
        return "No se encontro";
    }
    
    public Equipos EquiposPadreObj(int id){
        for (Equipos e :equipos){
            if(e.getId()==id){
                return e;
            }
        }
        return null;
    }
    

    public boolean eliminarEquipo(int id) {
        return equipos.removeIf(e -> e.getId() == id);
    }
    
    
    
    
    
    
    private boolean creaCiclo(Equipos hijo, Equipos posiblePadre) {
        System.out.println("Hola4");
        Equipos actual = posiblePadre;
        while (actual != null) {
            if (actual.getId() == hijo.getId()) {
                return true; // ciclo detectado
            }
            actual = buscarPorId(actual.getEquipoPadre());
        }
        return false;
    }
    
    public List<String> cargarOpciones() {
        List<String> lista = new ArrayList<>();

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)), "UTF-8");

            String[] opciones = contenido.split(";");

            for (String op : opciones) {
                op = op.trim();
                if (!op.isEmpty()) {
                    lista.add(op);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public void cargarOpcionesEn(JComboBox<String> combo) {
        combo.removeAllItems();
        for (String op : cargarOpciones()) {
            combo.addItem(op);
        }
    }
    
    public void ReporteEquipoSin(int id) {
        StringBuilder sb = new StringBuilder();
        for (Equipos e : equipos){
            if (e.getId()==id){
                sb.append(e.toString()); 
            }
        }
        generarPDF("Reporte_Equipo.pdf", sb.toString());
        
    }
    
    
    public void ReporteEquipoCon(int id) {
        StringBuilder sb = new StringBuilder();
        for (Equipos e : equipos){
            if (e.getId()==id){
                sb.append(e.toString()); 
                imprimirRecursivo(e, sb, 0);
                
                
                
            }
        }
        generarPDF("Reporte_Equipo.pdf", sb.toString());
        
    }
    
        public void ReporteEquiposTodos() {
        StringBuilder sb = new StringBuilder();
        for (Equipos e : equipos){
            sb.append(e.toString()); 
            sb.append("\n Componentes de los que esta conformado: ");
            sb.append("\n");
            imprimirRecursivo(e, sb, 0);
        }
        generarPDF("Reporte_Equipo.pdf", sb.toString());
        
    }
    

   private void generarPDF(String nombreArchivo, String contenido) {
    Document document = new Document();

    try {
        PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
        document.open();

        // Agregar contenido del StringBuilder como párrafo
        document.add(new Paragraph(contenido));

        document.close();
        System.out.println("PDF generado: " + nombreArchivo);

    } catch (DocumentException | IOException e) {
        e.printStackTrace();
    }
}
    
    
    private void imprimirRecursivo(Equipos padre, StringBuilder sb, int nivel) {

    for (Equipos eq : equipos) {
        
        if (EquiposPadreObj(eq.getEquipoPadre()) != null && EquiposPadreObj(eq.getEquipoPadre()).equals(padre)) {

            // Sangría según nivel
            sb.append("   ".repeat(nivel));

            // Agregar equipo
            sb.append("- ").append(eq.getId()).append(" | ").append(eq.getDescripcion()).append("\n");

            // Llamada recursiva aumentando el nivel
            imprimirRecursivo(eq, sb, nivel + 1);
        }
    }
}
    
    
    
    
}
