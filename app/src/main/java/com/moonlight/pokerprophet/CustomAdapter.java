package com.moonlight.pokerprophet;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Card> cards;
    Context context;
    MaterialCardView clicked;
    AlertDialog alertDialog;

    public CustomAdapter(List<Card> cards, AlertDialog alertDialog, MaterialCardView clicked) {
        this.cards = cards;
        this.context = alertDialog.getContext();
        this.clicked = clicked;
        this.alertDialog = alertDialog;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        vh = new ImageHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageHolder img = (ImageHolder) holder;
        String cardId = "img_" + cards.get(position % cards.size()).getRank() + cards.get(position % cards.size()).getSuit();
        img.imageView.setImageResource(context.getResources().getIdentifier(cardId, "drawable", context.getPackageName()));

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
        //return cards.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ImageHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageHolder);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ImageView) clicked.findViewById(R.id.imageView)).setImageDrawable(imageView.getDrawable());
                    //((Activity)context).onBackPressed();
                    alertDialog.dismiss();
                }
            });
        }
    }
}
