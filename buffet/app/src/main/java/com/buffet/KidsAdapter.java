package com.buffet;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class KidsAdapter extends RecyclerView.Adapter<KidsAdapter.KidViewHolder> {

    List<Crianca> listaCrianca;
    DatabaseBuffet databaseBuffet = new DatabaseBuffet();
    public KidsAdapter(List<Crianca> arg) {
        this.listaCrianca = arg;
    }

    // Inflar o Layout
    @NonNull
    @Override
    public KidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_crianca_cardview, parent, false);

        return new KidViewHolder(view);

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull KidViewHolder holder, int position) {
        holder.nome.setText(listaCrianca.get(position).getNome());
        holder.responsavel.setText(listaCrianca.get(position).getNome());
        if (holder.rank != null){
            holder.rank.setRating(listaCrianca.get(position).getRank());
        }
        if (holder.rank != null){
            holder.telefone.setText(listaCrianca.get(position).getNome());
        }

        holder.rank.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Dê uma nota para a festa");

            TextView nome = new TextView(v.getContext());
            nome.setText(holder.nome.getText());
            nome.setTextSize(24);

            RatingBar ratingBar = new RatingBar(v.getContext());
            ratingBar.setNumStars(5);
            ratingBar.setMax(5);
            ratingBar.setRating(listaCrianca.get(position).getRank());
            ratingBar.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout layout = new LinearLayout(v.getContext());
            layout.setGravity(Gravity.CENTER);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(nome);
            layout.addView(ratingBar);

            builder.setView(layout);

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {


                    listaCrianca.get(position).setRank(rating);
                }
            });

        });

        // config menu
        @SuppressLint("RestrictedApi")
        MenuBuilder menu = new MenuBuilder(holder.itemView.getContext());
        MenuInflater inflater = new MenuInflater(holder.itemView.getContext());
        inflater.inflate(R.menu.item_menu, menu);

        // botão menu
        holder.itemMenu.setOnClickListener(v -> {
            // Abrir o Menu
            @SuppressLint("RestrictedApi")
            MenuPopupHelper optionsMenu = new MenuPopupHelper(holder.itemView.getContext(), menu, v);
            optionsMenu.setForceShowIcon(true);
            optionsMenu.show();

            menu.setCallback(new MenuBuilder.Callback() {
                @Override
                public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                    if (item.getItemId() == R.id.menu_ligar) {
                        // Ligar
                        Intent intentLigar = new Intent(Intent.ACTION_DIAL);
                        intentLigar.setData(Uri.parse("tel:" + listaCrianca.get(position).getTelefone()));
                        v.getContext().startActivity(intentLigar);
                        return true;
                    } else if (item.getItemId() == R.id.menu_sms) {
                        // SMS
                        Intent intentSMS = new Intent(v.getContext().toString());
                        PendingIntent pi = PendingIntent.getActivity(v.getContext(), 0, intentSMS, PendingIntent.FLAG_IMMUTABLE);
                        // Gerenciar o SMS
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(listaCrianca.get(position).getTelefone(), null,
                                "Sua criança esta de castigo, venha buscá-la!", pi, null);
                        return true;
                    } else if (item.getItemId() == R.id.menu_excluir) {
                        // Excluir

                    } else if (item.getItemId() == R.id.menu_alterar) {
                        // Alterar
                        Intent intent = new Intent(v.getContext(), Formulario.class);
                        intent.putExtra("crianca", (Serializable) listaCrianca.get(position));
                    }
                     return true;
                }

                @Override
                public void onMenuModeChange(@NonNull MenuBuilder menu) {

                }
            });

        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), Formulario.class);
            v.getContext().startActivity(intent);
        });



    }

    @Override
    public int getItemCount() {
        return listaCrianca.size();
    }

    public class KidViewHolder extends RecyclerView.ViewHolder {

        private ImageView profile_pic;
        private TextView nome;
        private TextView responsavel;
        private TextView telefone;
        private RatingBar rank;
        private ImageButton itemMenu;
        public KidViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_pic = itemView.findViewById(R.id.profile_pic);
            nome = itemView.findViewById(R.id.nome);
            responsavel = itemView.findViewById(R.id.responsavel);
            telefone = itemView.findViewById(R.id.telefone);
            rank = itemView.findViewById(R.id.ratingBar);
            itemMenu = itemView.findViewById(R.id.menu);

        }
    }
}