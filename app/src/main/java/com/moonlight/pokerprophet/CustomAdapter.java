package com.moonlight.pokerprophet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final List<Card> cards_const;
    List<Card> cards;
    Context context;
    MaterialCardView clicked;
    AlertDialog alertDialog;
    Resources res;


    public CustomAdapter(List<Card> cards, AlertDialog alertDialog, MaterialCardView clicked) {
        this.cards_const = cards;
        this.context = alertDialog.getContext();
        this.clicked = clicked;
        this.alertDialog = alertDialog;
        this.cards = new ArrayList<>(this.cards_const);
        this.res = context.getResources();
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
        //Log.wtf("qqqa",context.getOpPackageName());
        ImageView img = ((ImageHolder) holder).getImageView();
        Card card = cards.get(position % cards.size());
        //String cardId = "img_" + card.getRank() + card.getSuit()+".webp";
        Glide.with(context)
                .asDrawable()
                .load(res.getIdentifier("img_" + card.getRank() + card.getSuit(), "drawable", "com.moonlight.pokerprophet"))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .thumbnail(0.5f)
                //.load(R.drawable.img_2d)
                .into(img);
        //img.setImageResource(context.getResources().getIdentifier(cardId, "drawable", context.getPackageName()));
        img.setTag(card);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
        //return cards.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public ImageHolder(View v) {
            super(v);

            imageView = v.findViewById(R.id.imageHolder);

            imageView.setOnClickListener(view -> {
                Glide.with(view)
                        .load(imageView.getDrawable())
                        .into((ImageView) clicked.findViewById(R.id.imageView));
                //((ImageView) clicked.findViewById(R.id.imageView)).setImageDrawable(imageView.getDrawable());
                String tag = imageView.getTag().toString();
                clicked.setTag(tag);
                switch (cards.get(0).getSuit()) {
                    case "c":
                        DataUtil.ranks_c.remove(tag.substring(0, tag.length() - 1));
                        DataUtil.cards_curr.add(new Card("c", tag.substring(0, tag.length() - 1)));
                        break;
                    case "h":
                        DataUtil.ranks_h.remove(tag.substring(0, tag.length() - 1));
                        DataUtil.cards_curr.add(new Card("h", tag.substring(0, tag.length() - 1)));
                        break;
                    case "s":
                        DataUtil.ranks_s.remove(tag.substring(0, tag.length() - 1));
                        DataUtil.cards_curr.add(new Card("s", tag.substring(0, tag.length() - 1)));
                        break;
                    case "d":
                        DataUtil.ranks_d.remove(tag.substring(0, tag.length() - 1));
                        DataUtil.cards_curr.add(new Card("d", tag.substring(0, tag.length() - 1)));
                        break;
                }
                clicked.setClickable(false);
                alertDialog.dismiss();
            });
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
