package com.moonlight.pokerprophet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataUtil {


    public static final ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a"));
    public static final ArrayList<String> suits = new ArrayList<>(Arrays.asList("c", "d", "s", "h"));


    public static ArrayList<String> ranks_c = ranks;
    public static ArrayList<String> ranks_d = ranks;
    public static ArrayList<String> ranks_s = ranks;
    public static ArrayList<String> ranks_h = ranks;
    public static ArrayList<String> ranks_t = new ArrayList<>(), suits_t = new ArrayList<>(), cards_curr;

    //TODO Combo
    static {
        reset();
    }

    public static void reset() {
        cards_curr = new ArrayList<>();
        ranks_c = ranks;
        ranks_h = ranks;
        ranks_s = ranks;
        ranks_d = ranks;
        for (String s : suits)
            for (String r : ranks) {
                cards_curr.add(r + s);
            }
    }

    public static List<Card> getCards(final String suit) {
        List<Card> cards = new ArrayList<>();
        ranks.forEach(r -> cards.add(new Card(suit, r)));
        return cards;
    }
}
