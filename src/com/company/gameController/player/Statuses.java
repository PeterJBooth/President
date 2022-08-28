package com.company.gameController.player;

import java.util.HashMap;
import java.util.Map;

public class Statuses {

    String[] statusNames = {"President", "Vice President", "Upper Class", "Lower Class", "Peasant", "Vice Peasant"};
    Map<String, int[]> statusToIndexesOfCardsToSwap = new HashMap<>();

    public Statuses() {

        fillStatusToIndexesOfCardsToRetrieve();
    }

    private void fillStatusToIndexesOfCardsToRetrieve() {

        statusToIndexesOfCardsToSwap.put(statusNames[0], new int[]{0, 1});
        statusToIndexesOfCardsToSwap.put(statusNames[1], new int[]{0});
        statusToIndexesOfCardsToSwap.put(statusNames[4], new int[]{8});
        statusToIndexesOfCardsToSwap.put(statusNames[5], new int[]{7, 8});
    }


    public int[] getIndexesOfCardsToSwap(String status) {

        return statusToIndexesOfCardsToSwap.get(status);
    }
}

