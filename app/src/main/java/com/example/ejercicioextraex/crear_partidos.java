package com.example.ejercicioextraex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ejercicioextraex.databinding.ActivityCrearPartidosBinding;

import java.util.ArrayList;

public class crear_partidos extends AppCompatActivity {

    private ActivityCrearPartidosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_partidos);

        binding = ActivityCrearPartidosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(binding.sbEquipo1.getSelectedItemPosition() == 0) &&
                        !(binding.sbEquipo2.getSelectedItemPosition() == 0) &&
                        !binding.txtGolesEquipo1.getText().toString().isEmpty() &&
                        !binding.txtGolesEquipo2.getText().toString().isEmpty() &&
                        !binding.txtResumenPartido.getText().toString().isEmpty()) {
                    funcion();
                } else {
                    Toast.makeText(crear_partidos.this, "RELLENA LOS DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void funcion() {
        if ((binding.sbEquipo1.getSelectedItem() == binding.sbEquipo2.getSelectedItem())) {
            Toast.makeText(crear_partidos.this, "NO SE PUEDEN ENFRENTAR", Toast.LENGTH_SHORT).show();

        } else {
            Partido partido = new Partido(binding.sbEquipo1.getSelectedItem().toString(),
                    binding.sbEquipo2.getSelectedItem().toString(),
                    Integer.parseInt(binding.txtGolesEquipo1.getText().toString()),
                    Integer.parseInt(binding.txtGolesEquipo2.getText().toString()),
                    binding.txtResumenPartido.getText().toString());
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("partido", partido);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}