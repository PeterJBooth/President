package com.company.gameController.cards;

import javafx.scene.control.Button;

public class Card {

    Button cardButton;
    public String cardID;
    public String cardName;
    public CardDisplay cardDisplay;

    Card (Button cardButton, String cardID, String cardName) {

        this.cardButton = cardButton;
        this.cardID = cardID;
        this.cardName = cardName;
        cardDisplay = new CardDisplay(cardButton);
    }
}
