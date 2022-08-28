package com.company.gameController.cards;

import com.company.gameController.player.MainPlayer;
import com.company.gameController.player.Opponent;


import java.util.*;

public class DeckOfCards {

    ArrayList<String> cards = new ArrayList<>();
    CardSorter cardSorter = new CardSorter();

    public DeckOfCards() {

        fillDeckOfCards();
    }

    private void fillDeckOfCards() {

        cards.add("1C");
        cards.add("1D");
        cards.add("1H");
        cards.add("1S");
        cards.add("2C");
        cards.add("2D");
        cards.add("2H");
        cards.add("2S");
        cards.add("3C");
        cards.add("3D");
        cards.add("3H");
        cards.add("3S");
        cards.add("4C");
        cards.add("4D");
        cards.add("4H");
        cards.add("4S");
        cards.add("5C");
        cards.add("5D");
        cards.add("5H");
        cards.add("5S");
        cards.add("6C");
        cards.add("6D");
        cards.add("6H");
        cards.add("6S");
        cards.add("7C");
        cards.add("7D");
        cards.add("7H");
        cards.add("7S");
        cards.add("8C");
        cards.add("8D");
        cards.add("8H");
        cards.add("8S");
        cards.add("9C");
        cards.add("9D");
        cards.add("9H");
        cards.add("9S");
        cards.add("TC");
        cards.add("TD");
        cards.add("TH");
        cards.add("TS");
        cards.add("jC");
        cards.add("jD");
        cards.add("jH");
        cards.add("jS");
        cards.add("QC");
        cards.add("QD");
        cards.add("QH");
        cards.add("QS");
        cards.add("KC");
        cards.add("KD");
        cards.add("KH");
        cards.add("KS");
        cards.add("JR");
        cards.add("JB");
    }

    public void shuffleDeck() {

        Collections.shuffle(cards);
    }

    public void dealCardsToPlayers(MainPlayer mainPlayer, ArrayList<Opponent> opponentList) {

        dealCardsToOpponents(opponentList);
        dealCardsToMainPlayer(mainPlayer);
    }

    public void dealCardsToOpponents(ArrayList<Opponent> opponentList) {

        for (int i = 0; i < 5; i++) {

            dealCardsToOpponent(opponentList.get(i), (i * 9));
        }
    }

    public void dealCardsToOpponent(Opponent opponent, int startingIndex) {

        opponent.cards = cards.subList(startingIndex, (startingIndex + 9));
        opponent.cards = cardSorter.SortPlayersCards(opponent.cards);

    }

    public void dealCardsToMainPlayer(MainPlayer mainPlayer) {

        int startingIndex = 5 * 9;
        mainPlayer.mainPlayerCards.cardIDs = cards.subList(startingIndex, (startingIndex + 9));
        mainPlayer.mainPlayerCards.cardIDs = cardSorter.SortPlayersCards(mainPlayer.mainPlayerCards.cardIDs);

        mainPlayer.mainPlayerCards.createCardObjects(mainPlayer.mainPlayerCards.buttons);
        mainPlayer.mainPlayerCards.createCardsList();
        mainPlayer.mainPlayerCards.displayPlayersCards();
    }
}
