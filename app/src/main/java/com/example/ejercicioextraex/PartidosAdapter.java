package com.example.ejercicioextraex;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PartidosAdapter extends RecyclerView.Adapter<PartidosAdapter.PartidoVH> {

    private List<Partido> objects;
    private int resource;
    private Context context;

    public PartidosAdapter(List<Partido> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public PartidosAdapter.PartidoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View partidoView = LayoutInflater.from(context).inflate(resource, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        partidoView.setLayoutParams(layoutParams);
        return new PartidoVH(partidoView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidoVH holder, int position) {
        Partido partido = objects.get(position);
        holder.lbEquipo1.setText(partido.getNombreEquipo1());
        holder.lbEquipo2.setText(partido.getNombreEquipo2());
        holder.lbResultado1.setText(String.valueOf(partido.getGoles1()));
        holder.lbResultado2.setText(String.valueOf(partido.getGoles2()));

        holder.btnMostrarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarGanador(partido, holder.getAdapterPosition()).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, datos_activity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("partido", partido);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    private AlertDialog mostrarGanador(Partido partido, int adapterPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("RESULTADO PARTIDO");

        if (partido.getGoles1()>partido.getGoles2()){
           builder.setMessage("Ha ganado el "+partido.getNombreEquipo1());}
        else if (partido.getGoles2()>partido.getGoles1()){
            builder.setMessage("Ha ganado el "+partido.getNombreEquipo2());
        }
        else {
            builder.setMessage("Empate");
        }

        return builder.create();
    }


    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PartidoVH extends RecyclerView.ViewHolder {
        TextView lbEquipo1;
        TextView lbEquipo2;
        TextView lbResultado1;
        TextView lbResultado2;
        Button btnMostrarResultado;

        public PartidoVH(@NonNull View itemView) {
            super(itemView);
            lbEquipo1 = itemView.findViewById(R.id.lbEquipo1ViewHolder);
            lbEquipo2 = itemView.findViewById(R.id.lbEquipo2ViewHolder);
            lbResultado1 = itemView.findViewById(R.id.lbResultadoEquipo1);
            lbResultado2 = itemView.findViewById(R.id.lbResultadoEquipo2);
            btnMostrarResultado = itemView.findViewById(R.id.btnMostrarGanador);
        }
    }
}
