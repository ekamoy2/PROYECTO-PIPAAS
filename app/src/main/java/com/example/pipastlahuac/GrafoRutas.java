package com.example.pipastlahuac;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafoRutas {
    private final Map<String, NodoRuta> nodos = new HashMap<>();

    public NodoRuta obtenerOCrearNodo(String nombreColonia) {
        String clave = nombreColonia.trim().toLowerCase();
        if (!nodos.containsKey(clave)) {
            nodos.put(clave, new NodoRuta(nombreColonia.trim()));
        }
        return nodos.get(clave);
    }

    public void conectarBidireccional(String origen, String destino, int minutos) {
        NodoRuta nodoOrigen = obtenerOCrearNodo(origen);
        NodoRuta nodoDestino = obtenerOCrearNodo(destino);
        nodoOrigen.agregarConexion(nodoDestino, minutos);
        nodoDestino.agregarConexion(nodoOrigen, minutos);
    }

    public void cargarRutaBaseTlahuac() {
        conectarBidireccional("Garza Tláhuac", "Zapotitlán", 12);
        conectarBidireccional("Zapotitlán", "Tlaltenco", 10);
        conectarBidireccional("Garza Tláhuac", "San Francisco Tlaltenco", 15);
        conectarBidireccional("San Francisco Tlaltenco", "Mixquic", 18);
        conectarBidireccional("Zapotitlán", "Del Mar", 14);
    }

    public String calcularRutaSimple(String origen, String destino) {
        NodoRuta inicio = nodos.get(origen.trim().toLowerCase());
        NodoRuta meta = nodos.get(destino.trim().toLowerCase());

        if (inicio == null || meta == null) {
            return "Ruta no registrada: verifica el origen o destino.";
        }
        if (inicio == meta) {
            return "Ya estás en " + inicio.getNombreColonia() + " (0 min).";
        }

        List<String> visitados = new ArrayList<>();
        List<String> camino = new ArrayList<>();
        int[] totalMinutos = new int[]{0};

        boolean encontrada = buscarCamino(inicio, meta, visitados, camino, 0, totalMinutos);
        if (!encontrada) {
            return "Sin conexión directa registrada entre " + inicio.getNombreColonia() + " y " + meta.getNombreColonia() + ".";
        }
        return String.join(" -> ", camino) + " (" + totalMinutos[0] + " min)";
    }

    private boolean buscarCamino(NodoRuta actual, NodoRuta meta, List<String> visitados,
                                 List<String> camino, int acumulado, int[] totalMinutos) {
        String claveActual = actual.getNombreColonia().toLowerCase();
        visitados.add(claveActual);
        camino.add(actual.getNombreColonia());

        if (actual == meta) {
            totalMinutos[0] = acumulado;
            return true;
        }

        for (Arista arista : actual.getAdyacencias()) {
            NodoRuta siguiente = arista.getDestino();
            String claveSiguiente = siguiente.getNombreColonia().toLowerCase();
            if (!visitados.contains(claveSiguiente)) {
                if (buscarCamino(siguiente, meta, visitados, camino, acumulado + arista.getMinutos(), totalMinutos)) {
                    return true;
                }
            }
        }

        camino.remove(camino.size() - 1);
        return false;
    }
}
