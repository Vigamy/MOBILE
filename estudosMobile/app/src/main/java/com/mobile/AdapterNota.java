package com.mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.List;

public class AdapterNota extends RecyclerView.Adapter<AdapterNota.MeuViewHolder> {
    private List<Nota> listaNota;
    public AdapterNota(List<Nota> arg) {
        this.listaNota = arg;
    }
    DatabaseSalaF databaseSalaF = new DatabaseSalaF();

    @NonNull
    @Override
    public AdapterNota.MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Carrega o template de visualização XML
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_lista, parent, false);

        // Chama o ViewHolder
        return new MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNota.MeuViewHolder holder, int position) {
        // Carregar os dados do obj
        holder.titulo.setText(listaNota.get(position).getTitulo());
        holder.descricao.setText(listaNota.get(position).getDescricao());

        // Se necessário fazer
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Posição " + position, Toast.LENGTH_SHORT).show();
        });

//        if(position == 2) {
//            holder.titulo.setText(holder.titulo.getText() + "(Esse é o terceiro)");
//        }

        if(position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.pares_cor));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
            holder.titulo.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
            holder.descricao.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }

        holder.itemView.setOnLongClickListener(v -> {
            databaseSalaF.remover(listaNota.get(holder.getAdapterPosition()), v.getContext());
            return true;
        });

}

    @Override
    public int getItemCount() {
        return listaNota.size();
    }

    // InnerClass pra carregar os elementos XML vs JAVA
    public class MeuViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descricao;
        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            descricao = itemView.findViewById(R.id.descricao);
        }

    }

}
