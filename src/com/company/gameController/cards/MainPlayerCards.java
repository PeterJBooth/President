package com.company.gameController.cards;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class MainPlayerCards {

    public final Button[] buttons;
    public HBox cardsBox;

    public Card card1;
    Card card2;
    Card card3;
    Card card4;
    Card card5;
    Card card6;
    Card card7;
    Card card8;
    Card card9;

    public List<String> cardIDs = new ArrayList<>();
    public ArrayList<Card> cardsList = new ArrayList<>();
    public ArrayList<Card> chosenCards = new ArrayList<>();

    public MainPlayerCards(Button[] buttons, HBox cardsBox) {

        this.cardsBox = cardsBox;
        this.buttons = buttons;
    }

    public void createCardObjects(Button[] buttons) {

        card1 = new Card(buttons[0], cardIDs.get(0), "card1");
        card2 = new Card(buttons[1], cardIDs.get(1), "card2");
        card3 = new Card(buttons[2], cardIDs.get(2), "card3");
        card4 = new Card(buttons[3], cardIDs.get(3), "card4");
        card5 = new Card(buttons[4], cardIDs.get(4), "card5");
        card6 = new Card(buttons[5], cardIDs.get(5), "card6");
        card7 = new Card(buttons[6], cardIDs.get(6), "card7");
        card8 = new Card(buttons[7], cardIDs.get(7), "card8");
        card9 = new Card(buttons[8], cardIDs.get(8), "card9");
    }

    public void createCardsList() {

        cardsList.add(card1);
        cardsList.add(card2);
        cardsList.add(card3);
        cardsList.add(card4);
        cardsList.add(card5);
        cardsList.add(card6);
        cardsList.add(card7);
        cardsList.add(card8);
        cardsList.add(card9);
    }

    public void recreateCardsList() {

        cardsList.clear();
        createCardsList();
    }

    public void displayPlayersCards(){

        for (Card card: cardsList) {
            card.cardDisplay.addCardToBtn(card.cardID);
        }
    }

    public void chooseOrUnchooseCard(ActionEvent mouseEvent) {

        Card card = getCardFromButtonEvent(mouseEvent);

        if (chosenCards.isEmpty()) {

            chooseCard(card);
        }

        else if (!chosenCards.contains(card)) {

            boolean isRepeatedNumber = checkIsRepeatedNumber(card.cardID);
            if (!isRepeatedNumber) {

                unchooseAllCards();
            }

            chooseCard(card);
        }

        else {

            unchooseCard(card);
        }
    }

    public Card getCardFromButtonEvent(ActionEvent mouseEvent) {

        String eventString = mouseEvent.getSource().toString();

        for (Card card: cardsList) {
            if (eventString.contains(card.cardName)) {

                return card;
            }
        }
        return null;
    }

    public void chooseCard(Card card){

        chosenCards.add(card);
        card.cardDisplay.moveButtonUp();
    }

    public void unchooseAllCards() {

        for (Card card: chosenCards) {

            card.cardDisplay.moveButtonDown();
        }
        chosenCards.clear();
    }

    public void unchooseCard(Card card){

        chosenCards.remove(card);
        card.cardDisplay.moveButtonDown();
    }

    public boolean checkIsRepeatedNumber(String cardID) {

        int newCardNumber = cardID.charAt(0);
        int alreadyChosenCardNumber = chosenCards.get(0).cardID.charAt(0);

        return newCardNumber == alreadyChosenCardNumber;
    }

    public ArrayList<String> getChosenCardIDs(){

        ArrayList<String> chosenCardIDs = new ArrayList<>();
        for (Card chosenCard: chosenCards) {
            chosenCardIDs.add(chosenCard.cardID);
        }
        return chosenCardIDs;
    }
}
