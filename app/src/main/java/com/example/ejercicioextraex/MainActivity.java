package com.example.ejercicioextraex;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.ejercicioextraex.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Partido> partidoList;

    private PartidosAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private ActivityResultLauncher<Intent> crearPartidoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        partidoList = new ArrayList<>();
        inicializarLaunchers();

        adapter = new PartidosAdapter(partidoList, R.layout.card_partidos, this);
        layoutManager = new GridLayoutManager(this, 1);

        binding.contentMain.Contenedor.setAdapter(adapter);
        binding.contentMain.Contenedor.setLayoutManager(layoutManager);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, crear_partidos.class);
                crearPartidoLauncher.launch(intent);
            }
        });

    }

    private void inicializarLaunchers() {
        crearPartidoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                Partido partido = (Partido) result.getData().getExtras().getSerializable("partido");
                                partidoList.add(0, partido);
                                adapter.notifyItemInserted(0);
                            }
                        }
                    }
                }
        );
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("partido",partidoList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<Partido> datos = (ArrayList<Partido>) savedInstanceState.getSerializable("producto");
        partidoList.addAll(datos);
        adapter.notifyItemRangeInserted(0,partidoList.size());
    }
}