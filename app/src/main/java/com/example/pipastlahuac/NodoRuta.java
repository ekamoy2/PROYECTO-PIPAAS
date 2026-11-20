package com.example.pipastlahuac;

import java.util.ArrayList;
import java.util.List;

public class NodoRuta {
    private String nombreColonia;
    private List<Arista> adyacencias;

    public NodoRuta(String nombreColonia) {
        this.nombreColonia = nombreColonia;
        this.adyacencias = new ArrayList<>();
    }

    public String getNombreColonia() {
        return nombreColonia;
    }

    public List<Arista> getAdyacencias() {
        return adyacencias;
    }

    public void agregarConexion(NodoRuta destino, int minutos) {
        this.adyacencias.add(new Arista(destino, minutos));
    }
}