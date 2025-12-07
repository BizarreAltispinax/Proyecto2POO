/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicioprograma2.modelo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa un equipo computacional con jerarquía.
 */
public class Equipos implements Serializable {

    private int id;
    private String descripcion;
    private String tipo;
    private String ubicacion;
    private String fabricante;
    private String serie;
    private LocalDate fechaAdquisicion;
    private LocalDate fechaPuestaServicio;
    private int mesesVidaUtil;
    private EstadoEquipo estado;
    private double costoInicial;
    private String especificaciones;
    private String garantia;
    private Integer idPadre;
    private int equipoPadre;   // Nuevo campo

    public enum EstadoEquipo {
        FUNCIONANDO,
        EN_MANTENIMIENTO_PREVENTIVO,
        EN_MANTENIMIENTO_CORRECTIVO,
        FUERA_DE_SERVICIO,
        DESECHADO
    }

    public Equipos(int id, String descripcion, String tipo, String ubicacion, String fabricante,
                  String serie, LocalDate fechaAdquisicion, LocalDate fechaPuestaServicio,
                  int mesesVidaUtil, EstadoEquipo estado, double costoInicial,
                  String especificaciones, String garantia, int equipoPadre) {

        this.id = id;

        this.descripcion = descripcion;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.fabricante = fabricante;
        this.serie = serie;
        this.fechaAdquisicion = fechaAdquisicion;
        this.fechaPuestaServicio = fechaPuestaServicio;
        this.mesesVidaUtil = mesesVidaUtil;
        this.estado = estado;
        this.costoInicial = costoInicial;
        this.especificaciones = especificaciones;
        this.garantia = garantia;
        this.equipoPadre = equipoPadre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public LocalDate getFechaPuestaServicio() {
        return fechaPuestaServicio;
    }

    public void setFechaPuestaServicio(LocalDate fechaPuestaServicio) {
        this.fechaPuestaServicio = fechaPuestaServicio;
    }

    public int getMesesVidaUtil() {
        return mesesVidaUtil;
    }

    public void setMesesVidaUtil(int mesesVidaUtil) {
        this.mesesVidaUtil = mesesVidaUtil;
    }

    public EstadoEquipo getEstado() {
        return estado;
    }
    public String getEstadoS() {
        return estado.toString();
    }
    public void setEstado(EstadoEquipo estado) {
        this.estado = estado;
    }

    public double getCostoInicial() {
        return costoInicial;
    }

    public void setCostoInicial(double costoInicial) {
        this.costoInicial = costoInicial;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public int getEquipoPadre() {
        return equipoPadre;
    }
    public Integer getEquipoPadreInteger() {
        return idPadre=equipoPadre;
    }


    public void setEquipoPadre(int equipoPadre) {
        this.equipoPadre = equipoPadre;
    }

//    @Override
//    public String toString() {
//    return "ID: " + id + "\n" +
//           "Descripción: " + descripcion + "\n" +
//           "Tipo: " + tipo + "\n" +
//           "Ubicación: " + ubicacion + "\n" +
//           "Fabricante: " + fabricante + "\n" +
//           "Serie: " + serie + "\n" +
//           "Fecha de adquisición: " + fechaAdquisicion + "\n" +
//           "Fecha de puesta en servicio: " + fechaPuestaServicio + "\n" +
//           "Meses de vida útil: " + mesesVidaUtil + "\n" +
//           "Estado: " + estado + "\n" +
//           "Costo inicial: " + costoInicial + "\n" +
//           "Especificaciones: " + especificaciones + "\n" +
//           "Garantía: " + garantia + "\n" +
//           "Equipo padre: " + equipoPadre;
//
//    }

    @Override
    public String toString() {
        return "Equipo " + id +
                "   Fabricante: " + fabricante +
                "   Tipo: " + tipo +
                "   Serie: " + serie;
    }
}