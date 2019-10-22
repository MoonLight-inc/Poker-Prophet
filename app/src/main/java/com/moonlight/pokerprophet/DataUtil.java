package com.moonlight.pokerprophet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataUtil {


    public static ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a"));
    public static ArrayList<String> suits = new ArrayList<>(Arrays.asList("c", "d", "s", "h"));


    public static List<Card> getCards(final String suit) {
        List<Card> cards = new ArrayList<>();
        ranks.forEach(r -> cards.add(new Card(suit, r)));
        return cards;
    }
}
