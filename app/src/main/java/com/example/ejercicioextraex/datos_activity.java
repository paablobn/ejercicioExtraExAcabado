package com.example.ejercicioextraex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ejercicioextraex.databinding.ActivityDatosBinding;

public class datos_activity extends AppCompatActivity {

    private ActivityDatosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        binding = ActivityDatosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        Partido partido = (Partido) bundle.getSerializable("partido");

        binding.lbEquipo1.setText(partido.getNombreEquipo1());
        binding.lbEquipo2.setText(partido.getNombreEquipo2());
        binding.lbResultado1.setText(String.valueOf(partido.getGoles1()));
        binding.lbResultado2.setText(String.valueOf(partido.getGoles2()));
        binding.lbResumen.setText(partido.getResumen());
    }
}