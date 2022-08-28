package com.company.gameController.cards;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CardDisplay {

    Button cardButton;
    CardFilePaths cardFilePaths = new CardFilePaths();


    CardDisplay (Button cardButton) {

        this.cardButton = cardButton;
    }

    public void addCardToBtn(String cardID) {

        ImageView imageView = createCardImageView(cardID);
        cardButton.setGraphic(imageView);
    }

    public ImageView createCardImageView(String cardID) {

        Image cardImage = new Image(cardFilePaths.getCardFilePath(cardID));
        ImageView imageView = new ImageView(cardImage);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    public void moveButtonUp() {

        cardButton.setTranslateY(-50);
    }

    public void moveButtonDown() {

        cardButton.setTranslateY(0);
    }

    public void removeCardFromHand(HBox cardsBox) {

        cardsBox.getChildren().remove(this.cardButton);

    }

}
