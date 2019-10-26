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
        img.imageView.setTag(cardId.subSequence(4, cardId.length()));
        System.out.println(cardId.subSequence(4, cardId.length()));

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
                    String tag = imageView.getTag().toString();
                    switch (cards.get(0).getSuit()) {
                        case "c":
                            DataUtil.ranks_c.remove(tag.substring(0, tag.length() - 1));
                            break;
                        case "h":
                            DataUtil.ranks_h.remove(tag.substring(0, tag.length() - 1));
                            break;
                        case "s":
                            DataUtil.ranks_s.remove(tag.substring(0, tag.length() - 1));
                            break;
                        case "d":
                            DataUtil.ranks_d.remove(tag.substring(0, tag.length() - 1));
                            break;
                    }
                    //((Activity)context).onBackPressed();
                    alertDialog.dismiss();
                }
            });
        }
    }
}
