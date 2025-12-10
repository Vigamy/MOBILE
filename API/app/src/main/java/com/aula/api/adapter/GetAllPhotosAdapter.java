package com.aula.api.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aula.api.GetAllPhotos;
import com.aula.api.R;
import com.aula.api.model.Foto;
import com.bumptech.glide.Glide;

import java.util.List;

public class GetAllPhotosAdapter extends RecyclerView.Adapter<GetAllPhotosAdapter.GetAllPhotosViewHolder> {
    List<Foto> fotos;

    public GetAllPhotosAdapter(List<Foto> fotos) {
        this.fotos = fotos;
    }

    @NonNull
    @Override
    public GetAllPhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        GetAllPhotosViewHolder pvh = new GetAllPhotosViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllPhotosViewHolder holder, int position) {
        holder.albumView.setText(fotos.get(position).getAlbumId());
        holder.tituloView.setText(fotos.get(position).getTitle());

        String url = fotos.get(position).getThumbnailUrl();
        Glide.with(holder.fotoView.getContext()).asBitmap().load(url).into(holder.fotoView);
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class GetAllPhotosViewHolder extends RecyclerView.ViewHolder {
        TextView albumView;
        TextView tituloView;
        ImageView fotoView;

        public GetAllPhotosViewHolder(@NonNull View itemView) {
            super(itemView);

            albumView = itemView.findViewById(R.id.album);
            tituloView = itemView.findViewById(R.id.titulo);
            fotoView = itemView.findViewById(R.id.foto);
        }
    }
}
