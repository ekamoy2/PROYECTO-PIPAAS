package com.example.pipastlahuac;
import java.util.PriorityQueue;
public class GestorAgua {
    private PriorityQueue<SolicitudAgua> colaPrioritaria;

    public GestorAgua() {
        this.colaPrioritaria = new PriorityQueue<>();
    }

    public void agregarSolicitud(String idFolio, String nombreSolicitante, String colonia, int nivelUrgencia, int diasSinAgua) {
        SolicitudAgua nuevaSolicitud = new SolicitudAgua(idFolio, nombreSolicitante, colonia, nivelUrgencia, diasSinAgua);
        colaPrioritaria.add(nuevaSolicitud);
    }

    public SolicitudAgua atenderSiguienteSolicitud() {
        return colaPrioritaria.poll();
    }

    public SolicitudAgua verSiguienteSolicitud() {
        return colaPrioritaria.peek();
    }

    public boolean haySolicitudesPendientes() {
        return !colaPrioritaria.isEmpty();
    }
}
