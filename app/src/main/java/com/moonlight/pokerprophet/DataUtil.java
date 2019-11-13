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
        ArrayList<Card> arr = null;
        switch (cards_curr.size()) {
            case 2:
                card1 = cards_curr.get(0);
                card2 = cards_curr.get(1);
                if ((pair(card1, card2) && (top10(card1))) || ((top10(card1) && top10(card2)) && norm2(card1, card2)))
                    return "Perfect start";
                if (top10(card1) && (top10(card2)))
                    return "Good start";
                if (norm2(card1, card2))
                    return "Maybe...";
                return "No, no, no...";
            case 5:
                return check(cards_curr);
            case 6:

                for (Card card : cards_curr) {
                    arr = new ArrayList<>(cards_curr);
                    arr.remove(card);

                    System.out.println(check(arr));
                }
                return check(arr);
            case 7:
                for (Card c1 : cards_curr) {
                    for (Card c2 : cards_curr)
                        if (!c1.equals(c2)) {
                            arr = new ArrayList<>(cards_curr);
                            arr.remove(c1);
                            arr.remove(c2);
                            //check(arr);
                            System.out.println(check(arr));
                        }
                    return check(arr);


                }
            default:
                return "GAME OVER";
        }
    }

    private static boolean checkFlash(ArrayList<Card> arr) {
        for (int i = 1; i < arr.size() - 1; i++)
            if (!arr.get(0).getSuit().equals(arr.get(i).getSuit()))
                return false;
        return true;
    }

    private static boolean checkStr(ArrayList<Card> arr) {
        ArrayList<String> ranks_curr = new ArrayList<>();
        arr.forEach(card -> ranks_curr.add(card.getRank()));
        for (int i = 1; i < ranks.size() - 5; i++)
            if (ranks_curr.containsAll(ranks.subList(i, i + 4)))
                return true;
        return false;
    }

    private static boolean pair(Card card1, Card card2) {
        return card1.getRank().equals(card2.getRank());
    }

    private static boolean top10(Card card1) {
        return ranks.indexOf(card1.getRank()) > 10;
    }

    private static boolean norm2(Card card1, Card card2) {
        return (card1.getSuit().equals(card2.getSuit())) ||
                (ranks.indexOf(card1.getRank()) - ranks.indexOf(card2.getRank()) < 5) ||
                ((card1.getRank().equals("a")) && (ranks.indexOf(card2) < 4));
    }

    private static Integer highCard(ArrayList<Card> arr) {
        Integer max = 0;
        for (Card a : arr)
            if (max < ranks.indexOf(a.getRank())) max = ranks.indexOf(a.getRank());
        return max;
    }

    private static int checkMax(ArrayList<Card> arr, boolean re) {
        int max = 0;
        int maxi = 0;
        int n = 1;
        for (int i = 0; i < arr.size(); i++) {
            n = 1;
            for (int j = 0; j < arr.size(); j++)
                if ((i != j) && (arr.get(i).getRank().equals(arr.get(j).getRank())))
                    n++;
            if (max < n) {
                max = n;
                maxi = i;
            }
        }
        if (n == 3) {
            String setrank = arr.get(maxi).getRank();
            arr.removeIf(card -> card.getRank().equals(setrank));
            if (checkMax(arr, false) == 2)
                return 5;
        }

        if ((re) && (n == 2)) {
            String setrank = arr.get(maxi).getRank();
            arr.removeIf(card -> card.getRank().equals(setrank));
            if ((checkMax(arr, false) == 2))
                return 6;
        }
        return n;
    }

    private static String check(ArrayList<Card> arr) {
        boolean flush = checkFlash(arr);
        boolean str = checkStr(arr);
        if (flush) {
            if (str)
                if (highCard(arr) == ranks.indexOf("a"))
                    return "Flush Royal";
                else return "Straight Flush";
        }
        int max = checkMax(arr, true);
        if (max == 4)
            return "Care";
        if (max == 5)
            return "Full house";
        if (str)
            return "Straight";
        if (flush)
            return "Flush";
        if (max == 3)
            return "Set";
        if (max == 6)
            return "Two pairs";
        if (max == 2)
            return "Pair";
        return "High card is " + ranks.get(highCard(arr)).toUpperCase();
    }
}
