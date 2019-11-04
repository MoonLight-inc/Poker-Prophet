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

    public static String prophet() {
        Card card1, card2, card3, card4, card5, card6, card7;
        switch (cards_curr.size()) {
            case 2:
                card1 = cards_curr.get(0);
                card2 = cards_curr.get(1);
                if (pair(card1, card2) && (top10(card1)))
                    return "Perfect start";
                if (top10(card1) && (top10(card2)))
                    return "Good start";
                if (norm2(card1, card2))
                    return "Maybe...";
                return "No, no, no...";
            case 5:

                break;
            case 6:

                break;
            case 7:
                card1 = cards_curr.get(0);
                card2 = cards_curr.get(1);
                card3 = cards_curr.get(2);
                card4 = cards_curr.get(3);
                card5 = cards_curr.get(4);
                card6 = cards_curr.get(5);
                card7 = cards_curr.get(6);

                break;
            default:
                System.out.println("ERRRRRRRROOOOORRRRRR ====== " + cards_curr.size());
        }
        return "GAME OVER";
    }


    private static boolean pair(Card card1, Card card2) {
        return card1.getRank().equals(card2.getRank());
    }

    private static boolean top10(Card card) {
        return ranks.indexOf(card.getRank()) > 10;
    }

    private static boolean norm2(Card card1, Card card2) {
        return (card1.getSuit().equals(card2.getSuit())) ||
                (ranks.indexOf(card1.getRank()) - ranks.indexOf(card2.getRank()) < 5) ||
                ((card1.getRank().equals("a")) && (ranks.indexOf(card2) < 4));
    }
}
