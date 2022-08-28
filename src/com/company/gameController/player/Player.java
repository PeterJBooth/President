package com.company.gameController.player;

import com.company.gameController.gameData.GamesStats;

import java.util.HashMap;

public class Player {

    public boolean hasKnocked = false;
    public boolean hasNoCard = false;
    public int cardNumber = 9;
    public int finishingOrder = 1;
    public String status = "Default";
    public String playerName = "";

    public HashMap<Integer, String> finishingOrderToStatus = new HashMap<>();

    Player(String playerName) {

        this.playerName = playerName;
        createFinishingOrderToStatus();
    }

    public void createFinishingOrderToStatus() {

        finishingOrderToStatus.put(1, "President");
        finishingOrderToStatus.put(2, "Vice President");
        finishingOrderToStatus.put(3, "Upper Class");
        finishingOrderToStatus.put(4, "Lower Class");
        finishingOrderToStatus.put(5, "Peasant");
        finishingOrderToStatus.put(6, "Vice Peasant");
    }

    public void setPlayersStatusFromPreviousGame(GamesStats gamesStats) {

        int previousFinishingOrder = gamesStats.playersStatistics.get(playerName).get("finishing order");
        status = finishingOrderToStatus.get(previousFinishingOrder);
    }

    public void decrementCardsNumber() {

        cardNumber --;
    }

    public boolean checkIfHasFinishedOrKnocked() {

        return hasKnocked || hasNoCard;
    }

    public void setNewStatus() {

        status = finishingOrderToStatus.get(finishingOrder);
    }
}
