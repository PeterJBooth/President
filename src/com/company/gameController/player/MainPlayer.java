package com.company.gameController.player;

import com.company.gameController.gameData.GameInformation;
import com.company.gameController.gameData.GameRules;
import com.company.gameController.cards.Card;
import com.company.gameController.cards.CardSorter;
import com.company.gameController.cards.MainPlayerCards;
import com.company.gameController.cards.PlacedCardsDisplay;
import com.company.gameController.player.display.MainPlayerDisplay;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.List;

public class MainPlayer extends Player {

    public boolean isPlayerTurn = true;
    public MainPlayerDisplay mainPlayerDisplay;
    public MainPlayerCards mainPlayerCards;
    CardSorter cardSorter = new CardSorter();


    public MainPlayer(String playerName, Button knockButton, Text statusText, MainPlayerCards mainPlayerCards) {
        super(playerName);

        this.mainPlayerCards = mainPlayerCards;
        createMainPlayerDisplay(knockButton, statusText);
    }

    public void swapCardsWithOpponent(List<String> cardIDs, int[] cardIDIndexes) {

        for (int i = 0; i < cardIDs.size(); i++) {

            mainPlayerCards.cardIDs.set(cardIDIndexes[i], cardIDs.get(i));
        }

        cardSorter.SortPlayersCards(mainPlayerCards.cardIDs);

        mainPlayerCards.createCardObjects(mainPlayerCards.buttons);
        mainPlayerCards.recreateCardsList();
        mainPlayerCards.displayPlayersCards();

        mainPlayerCards.unchooseAllCards();
        isPlayerTurn = true;
    }

    private void createMainPlayerDisplay(Button knockButton, Text statusText) {

        mainPlayerDisplay = new MainPlayerDisplay(knockButton, statusText);
    }

    public boolean checkIfMainPlayerCanPlayCard(GameRules gameRules, GameInformation gameInformation) {

        if (checkIfHasFinishedOrKnocked() || !isPlayerTurn) return false;

        if (mainPlayerCards.chosenCards.isEmpty()) return false;

        return gameRules.checkIfCanPlayCards(mainPlayerCards.chosenCards, gameInformation);
    }

    public boolean checkIfMainPlayerCanKnock() {

      return (!checkIfHasFinishedOrKnocked() && isPlayerTurn);
    }

    public void playCardOrCards(PlacedCardsDisplay placedCardsDisplay, GameInformation gameInformation) {

        for (Card card: mainPlayerCards.chosenCards) {

            playCard(card, placedCardsDisplay);
        }

        gameInformation.updatePlayerAndCardInformation(mainPlayerCards.getChosenCardIDs(), this);

        mainPlayerCards.chosenCards.clear();

    }

    public void playCard(Card card, PlacedCardsDisplay placedCardsDisplay){

        // Remove card from hand
        card.cardDisplay.removeCardFromHand(mainPlayerCards.cardsBox);
        decrementCardsNumber();

        // Add card to center
        placedCardsDisplay.addCardToPile(card.cardID);
    }

    public void endMainPlayersTurn() {

        isPlayerTurn = false;
    }

    public void setToHasKnocked(GameInformation gameInformation) {

        hasKnocked = true;
        mainPlayerDisplay.setKnockButtonToKnocked();

        gameInformation.numberOfPlayersThatKnocked ++;
    }

    public void setToNotHasKnocked() {

        hasKnocked = false;
        mainPlayerDisplay.resetKnockButton();
    }

    public void DisplayMainPlayersNewStatus(String status) {

        mainPlayerDisplay.displayNewStatus(status);
    }
}
