package com.moonlight.pokerprophet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataUtil {


    public static final ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a"));
    public static final ArrayList<String> suits = new ArrayList<>(Arrays.asList("c", "d", "s", "h"));
    public static Integer adCounter = 5;
    public static ArrayList<String> ranks_c = new ArrayList<>(ranks);
    public static ArrayList<String> ranks_d = new ArrayList<>(ranks);
    public static ArrayList<String> ranks_s = new ArrayList<>(ranks);
    public static ArrayList<String> ranks_h = new ArrayList<>(ranks);
    public static ArrayList<String> ranks_t = new ArrayList<>(), suits_t = new ArrayList<>();
    public static ArrayList<Card> cards_curr;

    //TODO Combo
    static {
        reset();
    }

    public static void reset() {
        cards_curr = new ArrayList<>();
        ranks_c = new ArrayList<>(ranks);
        ranks_h = new ArrayList<>(ranks);
        ranks_s = new ArrayList<>(ranks);
        ranks_d = new ArrayList<>(ranks);
    }

    public static List<Card> getCards(final String suit) {
        List<Card> cards = new ArrayList<>();
        switch (suit) {
            case "s":
                ranks_s.forEach(r -> cards.add(new Card(suit, r)));
                break;
            case "d":
                ranks_d.forEach(r -> cards.add(new Card(suit, r)));
                break;
            case "c":
                ranks_c.forEach(r -> cards.add(new Card(suit, r)));
                break;
            case "h":
                ranks_h.forEach(r -> cards.add(new Card(suit, r)));
                break;
        }
        //ranks.forEach(r -> cards.add(new Card(suit, r)));
        return cards;
    }

}
