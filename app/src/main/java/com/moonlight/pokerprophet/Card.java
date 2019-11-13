package com.moonlight.pokerprophet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Card {


    private String rank;
    private String suit;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((this.rank.equals(((Card) obj).getRank())) && (this.suit.equals(((Card) obj).getSuit())));
    }

    @NonNull
    @Override
    public String toString() {
        return rank + suit;
    }

    public String getSuit() {
        return suit;
    }
}
