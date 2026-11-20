package com.example.pipastlahuac;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GestorAgua gestorAgua;
    private GrafoRutas grafoRutas;
    private int contadorFolio = 1;

    private EditText etSolicitante, etColonia, etUrgencia, etDias;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestorAgua = new GestorAgua();
        grafoRutas = new GrafoRutas();
        grafoRutas.cargarRutaBaseTlahuac();

        etSolicitante = findViewById(R.id.etSolicitante);
        etColonia = findViewById(R.id.etColonia);
        etUrgencia = findViewById(R.id.etUrgencia);
        etDias = findViewById(R.id.etDias);
        tvResultado = findViewById(R.id.tvResultado);

        Button btnAgregar = findViewById(R.id.btnAgregar);
        Button btnAtender = findViewById(R.id.btnAtender);

        btnAgregar.setOnClickListener(v -> registrarSolicitud());
        btnAtender.setOnClickListener(v -> atenderSiguiente());
    }

    private void registrarSolicitud() {
        String solicitante = etSolicitante.getText().toString().trim();
        String colonia = etColonia.getText().toString().trim();
        String urgenciaStr = etUrgencia.getText().toString().trim();
        String diasStr = etDias.getText().toString().trim();

        if (solicitante.isEmpty() || colonia.isEmpty() || urgenciaStr.isEmpty() || diasStr.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int urgencia = Integer.parseInt(urgenciaStr);
        int dias = Integer.parseInt(diasStr);
        String folio = "TLH-" + String.format("%03d", contadorFolio++);

        gestorAgua.agregarSolicitud(folio, solicitante, colonia, urgencia, dias);

        tvResultado.setText("Registrado -> " + folio + " | Colonia: " + colonia +
                " | Urgencia: " + urgencia + " | Días: " + dias);

        etSolicitante.setText("");
        etColonia.setText("");
        etUrgencia.setText("");
        etDias.setText("");
    }

    private void atenderSiguiente() {
        if (!gestorAgua.haySolicitudesPendientes()) {
            tvResultado.setText("No hay solicitudes pendientes en la cola.");
            return;
        }

        SolicitudAgua siguiente = gestorAgua.atenderSiguienteSolicitud();
        String ruta = grafoRutas.calcularRutaSimple("Garza Tláhuac", siguiente.getColonia());

        tvResultado.setText("ATENDIENDO:\n" + siguiente.toString() +
                "\nSolicitante: " + siguiente.getNombreSolicitante() +
                "\nRuta desde Garza Tláhuac:\n" + ruta);
    }
}