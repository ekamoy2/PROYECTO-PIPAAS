package com.example.pipastlahuac;

public class SolicitudAgua implements Comparable<SolicitudAgua> {
    private String idFolio;
    private String nombreSolicitante;
    private String colonia;
    private int nivelUrgencia; // 1: Máxima, 2: Media, 3: Baja
    private int diasSinAgua;

    public SolicitudAgua(String idFolio, String nombreSolicitante, String colonia, int nivelUrgencia, int diasSinAgua) {
        this.idFolio = idFolio;
        this.nombreSolicitante = nombreSolicitante;
        this.colonia = colonia;
        this.nivelUrgencia = nivelUrgencia;
        this.diasSinAgua = diasSinAgua;
    }

    public String getIdFolio() { return idFolio; }
    public String getNombreSolicitante() { return nombreSolicitante; }
    public String getColonia() { return colonia; }
    public int getNivelUrgencia() { return nivelUrgencia; }
    public int getDiasSinAgua() { return diasSinAgua; }

    @Override
    public int compareTo(SolicitudAgua otra) {
        if (this.nivelUrgencia != otra.nivelUrgencia) {
            return Integer.compare(this.nivelUrgencia, otra.nivelUrgencia);
        }
        return Integer.compare(otra.diasSinAgua, this.diasSinAgua);
    }

    @Override
    public String toString() {
        return "Folio: " + idFolio + " | Colonia: " + colonia +
                " | Urgencia: " + nivelUrgencia + " | Días sin agua: " + diasSinAgua;
    }
}