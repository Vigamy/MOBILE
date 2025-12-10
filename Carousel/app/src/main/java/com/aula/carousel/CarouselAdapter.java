package com.aula.carousel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class CarouselAdapter extends PagerAdapter {
    private Context context;

    public CarouselAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        // Retorna o número de itens no carrossel
        return 5;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Cria uma view para o item na posição atual
        View view = LayoutInflater.from(context).inflate(R.layout.item_carousel, container, false);
        // Popula a view com os dados do item
        populateView(view, position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove a view do container
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    private void populateView(View view, int position) {
        // Popula a view com os dados do item na posição atual
        TextView textView = view.findViewById(R.id.textView);
        textView.setText("Item " + position);
    }
}