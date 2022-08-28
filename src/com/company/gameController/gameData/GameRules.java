package com.company.gameController.gameData;

import com.company.gameController.cards.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameRules {

    HashMap<String, Integer> rankOrder = new HashMap<>();

    public GameRules() {

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

    public boolean checkIfCanPlayCards(ArrayList<Card> chosenCards, GameInformation gameInformation) {

        if (chosenCards.size() < gameInformation.requiredNumberOfCards) {
            return false;
        }

        ArrayList<String> cardIDs = new ArrayList<>();
        for (Card card: chosenCards) {
            cardIDs.add(card.cardID);
        }

        if (gameInformation.roundHasStarted) {

            if (!Objects.equals(cardIDs.get(0), "empty")) {

                gameInformation.requiredNumberOfCards = chosenCards.size();
                gameInformation.roundHasStarted = false;
                return true;
            }
        }

        else {

            int lastPlaceCardRank = getCardRankNumber(
                    gameInformation.placedCards.get(gameInformation.placedCards.size() - 1));
            return !Objects.equals(cardIDs.get(0), "empty") && lastPlaceCardRank <= getCardRankNumber(cardIDs.get(0));
        }

        return false;
    }

    public boolean checkIfCanPlayCard(String cardID, GameInformation gameInformation) {

        int lastPlaceCardRank = getCardRankNumber(
                gameInformation.placedCards.get(gameInformation.placedCards.size() - 1));

        if (gameInformation.roundHasStarted) {

            if (!Objects.equals(cardID, "empty")) {

                gameInformation.requiredNumberOfCards = 1;
                gameInformation.roundHasStarted = false;
                return true;
            }
        }

        else {

            return !Objects.equals(cardID, "empty") && lastPlaceCardRank <= getCardRankNumber(cardID);
        }

        return false;
    }

    public int getCardRankNumber(String cardID) {

        return rankOrder.get(String.valueOf(cardID.charAt(0)));
    }

}
