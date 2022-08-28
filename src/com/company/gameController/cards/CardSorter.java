package com.company.gameController.cards;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class CardSorter {

    HashMap<String, Integer> rankOrder = new HashMap<>();

    public CardSorter() {

        fillRankOrderHashMap();
    }

    private void fillRankOrderHashMap() {

        rankOrder.put("J", 14);
        rankOrder.put("1", 13);
        rankOrder.put("K", 12);
        rankOrder.put("Q", 11);
        rankOrder.put("j", 10);
        rankOrder.put("T", 9);
        rankOrder.put("9", 8);
        rankOrder.put("8", 7);
        rankOrder.put("7", 6);
        rankOrder.put("6", 5);
        rankOrder.put("5", 4);
        rankOrder.put("4", 3);
        rankOrder.put("3", 2);
        rankOrder.put("2", 1);
    }


    public List<String> SortPlayersCards(List<String> playersCards) {

        Comparator<String> compareByRank =
                Comparator.comparing((String cardID) -> rankOrder.get(String.valueOf(cardID.charAt(0))));
        playersCards.sort(compareByRank);

        return playersCards;
    }
}
