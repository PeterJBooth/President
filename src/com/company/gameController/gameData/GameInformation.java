package com.company.gameController.gameData;
import com.company.gameController.player.Player;

import java.util.ArrayList;

public class GameInformation {

    public ArrayList<String> placedCards = new ArrayList<>();
    public Player playerWhoPlacedLastCard = null;

    public boolean roundHasStarted = true;
    public boolean gameHasEnded;

    public int requiredNumberOfCards = 1;

    public int numberOfActivePlayers = 6;
    public int numberOfHighestCardCompetitors = 6;
    public int numberOfPlayersThatKnocked = 0;

    public void updatePlayerAndCardInformation(ArrayList<String> cardsPlayed, Player player) {

        updateCardInformation(cardsPlayed);
        updatePlayerInformation(player);
    }

    public void updateCardInformation(ArrayList<String> cardsPlayed) {

        placedCards.addAll(cardsPlayed);
    }

    public void updatePlayerInformation(Player player) {

        playerWhoPlacedLastCard = player;
        numberOfHighestCardCompetitors = numberOfActivePlayers - 1;
    }

    public boolean checkIfAPlayerHasWonTheRound() {

        return (numberOfHighestCardCompetitors - numberOfPlayersThatKnocked) == 0;
    }

    public void resetRoundInformation() {

        roundHasStarted = true;
        requiredNumberOfCards = 1;
        numberOfPlayersThatKnocked = 0;
    }


}
