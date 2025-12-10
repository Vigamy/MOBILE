package com.aula.praticamobile;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Colaborador> listaColaboradores;
    DatabaseHelper databaseHelper = new DatabaseHelper();
    public Adapter(List<Colaborador> arg){ this.listaColaboradores = arg; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        holder.numCracha.setText("Num: " + listaColaboradores.get(position).getNumCracha());
        holder.atendimentoInicio.setText("Data Inicial: " + dtf.format(listaColaboradores.get(position).getDataInicio()));
        if(listaColaboradores.get(position).getDataFim() == null){
            holder.atendimentoFim.setText("Data Final: ainda não finalizado");
        } else {
            if (listaColaboradores.get(position).getDataFim().equals(listaColaboradores.get(position).getDataInicio())) {
                holder.atendimentoFim.setText("Data Final: ainda não finalizado");
            } else {
                holder.atendimentoFim.setText("Data Final: " + dtf.format(listaColaboradores.get(position).getDataFim()));
            }
        }

        holder.itemView.setOnClickListener(v -> {
            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.cardview_confirmacao);
            dialog.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
            dialog.setCancelable(false);

            Button btnConfirm = dialog.findViewById(R.id.btnConfirmarRegistro);
            Button btnCancelar = dialog.findViewById(R.id.btnCancelarRegistro);

            btnConfirm.setOnClickListener(v1 -> {

                databaseHelper.alterarData(listaColaboradores.get(holder.getAdapterPosition()));

                dialog.dismiss();

            });

            btnCancelar.setOnClickListener(v1 -> dialog.dismiss());

            dialog.show();

        });

        holder.itemView.setOnLongClickListener(v -> {

            databaseHelper.deletar(listaColaboradores.get(holder.getAdapterPosition()));
            Toast.makeText(v.getContext(), "Deletando...", Toast.LENGTH_SHORT).show();
            return true;

        });

    }

    @Override
    public int getItemCount() { return listaColaboradores.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numCracha, atendimentoFim, atendimentoInicio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numCracha = itemView.findViewById(R.id.numCracha);
            atendimentoFim = itemView.findViewById(R.id.atendimentoFim);
            atendimentoInicio = itemView.findViewById(R.id.atendimentoInicio);
        }
    }
}
