package com.moonlight.pokerprophet;

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

    public String getSuit() {
        return suit;
    }
}
