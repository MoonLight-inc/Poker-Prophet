package com.moonlight.pokerprophet;

import android.util.Log;

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
    public static String tag = "tag_123";

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


    public static int checkHand() {

        Card card1 = cards_curr.get(0);
        Card card2 = cards_curr.get(1);
        if ((pair(card1, card2) && (top10(card1))) || ((top10(card1) && top10(card2)) && norm2(card1, card2)))
            return 1;
        if (top10(card1) && (top10(card2)))
            return 2;
        if (norm2(card1, card2))
            return 3;
        return 4;
    }

    public static Integer prophet() {
        Card card1, card2, card3, card4, card5, card6, card7;
        ArrayList<Card> arr = null;
        System.out.println("Prophet called. Array size = " + cards_curr.size());
        int k;
        switch (cards_curr.size()) {
            case 2:
                card1 = cards_curr.get(0);
                card2 = cards_curr.get(1);
                if ((pair(card1, card2) && (top10(card1))) || ((top10(card1) && top10(card2)) && norm2(card1, card2)))
                    return 1;
                if (top10(card1) && (top10(card2)))
                    return 2;
                if (norm2(card1, card2))
                    return 3;
                return 4;
            case 5:
                return check(cards_curr);
            case 6:
                k = 15;
                for (Card card : cards_curr) {
                    arr = new ArrayList<>(cards_curr);
                    arr.remove(card);
                    int c = check(arr);
                    if (k > c)
                        k = c;

                }
                return k;
            case 7:
                k = 15;
                ArrayList<ArrayList<Card>> comp_arr = new ArrayList<ArrayList<Card>>();
                for (Card c1 : cards_curr)
                    for (Card c2 : cards_curr)
                        if (!((c1.getRank().equals(c2.getRank())) && (c1.getSuit().equals(c2.getSuit())))) {
                            Log.wtf("tag3", "c1 =" + c1 + " c2 = " + c2);
                            arr = new ArrayList<>(cards_curr);
                            Log.wtf("tag3", "ARR   == " + arr);
                            arr.remove(c1);
                            Log.wtf("tag3", "ARR-c1== " + arr);
                            arr.remove(c2);
                            Log.wtf("tag3", "ARR-c2== " + arr);
                            comp_arr.add(arr);
                        }


//                int c = check((ArrayList<Card>) arr.toArray());
//                Log.wtf("tag3", "ARR == " + arr + " == " + c + "   CURR = " + cards_curr);
//                if (k > c)
//                    k = c;


                return k;
            default:
                return null;
        }
    }


    public static ArrayList<Card>[] getCurrentArray() {
        ArrayList<ArrayList<Card>> result_arr = new ArrayList();
        ArrayList<Card> arr;
        ArrayList<Card>[] arr_buff;
        switch (cards_curr.size()) {
            case 5:
                Log.wtf(tag, "5 array: " + new ArrayList[]{cards_curr}.toString());
                return new ArrayList[]{cards_curr};
            case 6:
                for (Card card : cards_curr) {
                    arr = new ArrayList<>(cards_curr);
                    arr.remove(card);
                    result_arr.add(arr);
                }
                arr_buff = new ArrayList[result_arr.size()];
                result_arr.toArray(arr_buff);
                Log.wtf(tag, "6 array: " + arr_buff);
                return arr_buff;
            case 7:
                for (Card c1 : cards_curr)
                    for (Card c2 : cards_curr)
                        if (!((c1.getRank().equals(c2.getRank())) && (c1.getSuit().equals(c2.getSuit())))) {
                            arr = new ArrayList<>(cards_curr);
                            arr.remove(c1);
                            arr.remove(c2);
                            result_arr.add(arr);
                        }
                arr_buff = new ArrayList[result_arr.size()];
                result_arr.toArray(arr_buff);
                Log.wtf(tag, "7 array: " + result_arr.toArray());
                return arr_buff;
            default:
                return null;
        }
    }


    private static boolean checkFlash(ArrayList<Card> arr) {
        Log.wtf(tag, "Flush " + arr);
        for (int i = 1; i < arr.size() - 1; i++) {
            Log.wtf(tag, arr.get(0).getSuit() + " == " + arr.get(i).getSuit() + " == !" + !arr.get(0).getSuit().equals(arr.get(i).getSuit()));
            if (!arr.get(0).getSuit().equals(arr.get(i).getSuit()))
                return false;
        }
        return true;
    }

    private static boolean checkStr(ArrayList<Card> arr) {
        ArrayList<String> ranks_curr = new ArrayList<>();
        arr.forEach(card -> ranks_curr.add(card.getRank()));
        for (int i = 0; i < ranks.size() - 4; i++) {
            Log.wtf(tag, "Str " + arr + " [contains]  " + ranks.subList(i, i + 5) + " == " + ranks_curr.containsAll(ranks.subList(i, i + 5)));
            if (ranks_curr.containsAll(ranks.subList(i, i + 5)))
                return true;
        }
        return false;
    }

    private static boolean pair(Card card1, Card card2) {
        Log.wtf(tag, "Pair " + card1.toString().toUpperCase() + " " + card2.toString().toUpperCase() + " == " + card1.getRank().equals(card2.getRank()));
        return card1.getRank().equals(card2.getRank());
    }

    private static boolean top10(Card card1) {
        return ranks.indexOf(card1.getRank()) > 10;
    }

    private static boolean norm2(Card card1, Card card2) {
        return (card1.getSuit().contains(card2.getSuit())) ||
                (ranks.indexOf(card1.getRank()) - ranks.indexOf(card2.getRank()) < 5) ||
                ((card1.getRank().contains("a")) && (ranks.indexOf(card2.getRank()) < 4));
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
        Log.wtf(tag, "CheckMax max = " + max + "  maxi = " + maxi + "   N = " + n + "  ARR == " + arr + " " + re);
        if ((re) && (max == 3)) {
            String setrank = arr.get(maxi).getRank();
            arr.removeIf(card -> card.getRank().equals(setrank));
            int check2 = checkMax(arr, false);
            if ((check2 == 2) || (check2 == 3))
                return 5;
        }

        if ((re) && (max == 2)) {
            String setrank = arr.get(maxi).getRank();
            arr.removeIf(card -> card.getRank().equals(setrank));
            int c = checkMax(arr, false);
            if ((c == 3))
                return 5;
            if ((c == 2))
                return 6;
        }
        return max;
    }

    public static Integer check(ArrayList<Card> arr) {
        Log.wtf(tag, "<CHECK> ===========================================================");
        Log.wtf(tag, "Array = " + arr);
        boolean flush = checkFlash(arr);
        boolean str = checkStr(arr);
        if (flush) {
            if (str)
                if (highCard(arr) == ranks.indexOf("a"))
                    return 5;
                else return 6;
        }
        int max = checkMax(arr, true);
        Log.wtf(tag, "MAX == " + max);
        if (max == 4)
            return 7;
        if (max == 5)
            return 8;
        if (str)
            return 9;
        if (flush)
            return 10;
        if (max == 3)
            return 11;
        if (max == 6)
            return 12;
        if (max == 2)
            return 13;
        return 14;
    }
}
