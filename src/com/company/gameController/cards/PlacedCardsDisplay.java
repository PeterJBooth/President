package com.company.gameController.cards;

import com.company.gameController.player.Opponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class PlacedCardsDisplay {

    CardFilePaths cardFilePaths = new CardFilePaths();
    ImageView[] cardsPlacedImages;

    public PlacedCardsDisplay(ImageView[] cardsPlacedImages){

        this.cardsPlacedImages = cardsPlacedImages;
    }

    public void addCardToPileIfOpponentPlayedCard(Opponent opponent) {

        if (!opponent.hasKnocked) {
            addCardsToPile(opponent.cardsPlayed);
        }
    }

    public void addCardsToPile(List<String> cardsPlayed) {

        for (String cardID: cardsPlayed) {

            addCardToPile(cardID);
        }
    }

    public void addCardToPile(String cardID) {

        Image cardImage = new Image(cardFilePaths.getCardFilePath(cardID));

        displayCardImage(cardsPlacedImages[3], cardsPlacedImages[2].getImage());
        displayCardImage(cardsPlacedImages[2], cardsPlacedImages[1].getImage());
        displayCardImage(cardsPlacedImages[1], cardsPlacedImages[0].getImage());
        displayCardImage(cardsPlacedImages[0], cardImage);
    }

    public void displayCardImage(ImageView cardPlaced, Image cardImage) {

        cardPlaced.setImage(cardImage);
        cardPlaced.setFitHeight(200);
        cardPlaced.setPreserveRatio(true);
    }

    public void removeCardsFromPile() {

        Image blankImage = new Image(cardFilePaths.getCardFilePath("W"));

        displayCardImage(cardsPlacedImages[0], blankImage);
        displayCardImage(cardsPlacedImages[1], blankImage);
        displayCardImage(cardsPlacedImages[2], blankImage);
        displayCardImage(cardsPlacedImages[3], blankImage);

    }
}
