package com.example.pipastlahuac;

public class Arista {
    private NodoRuta destino;
    private int minutos; // Peso o costo de traslado entre nodos

    public Arista(NodoRuta destino, int minutos) {
        this.destino = destino;
        this.minutos = minutos;
    }

    public NodoRuta getDestino() {
        return destino;
    }

    public int getMinutos() {
        return minutos;
    }
}
