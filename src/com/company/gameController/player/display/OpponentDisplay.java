package com.company.gameController.player.display;

import com.company.gameController.cards.CardFilePaths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class OpponentDisplay {

    Text cardsNumberText;
    Text hasKnockedText;
    Text statusText;
    public ImageView opponentCardsImage;
    CardFilePaths cardFilePaths = new CardFilePaths();

    public OpponentDisplay(Text cardsNumberText, Text hasKnockedText, Text statusText, ImageView opponentCardsImage) {

        this.cardsNumberText = cardsNumberText;
        this.hasKnockedText = hasKnockedText;
        this.statusText = statusText;
        this.opponentCardsImage = opponentCardsImage;
    }

    public void writeHasKnocked () {

        hasKnockedText.setText("Has Knocked");
    }

    public void removeHasKnockedText() {

        hasKnockedText.setText("");
    }

    public void setCardsNumberText(int cardNumber) {

        cardsNumberText.setText(String.valueOf(cardNumber));
    }

    public void highlightPlayer() {
        setCardsToRed();
    }

    public void setCardsToRed(){

        opponentCardsImage.setImage(new Image(cardFilePaths.getCardFilePath("RB")));
    }

    public void setCardsToBlack(){

        opponentCardsImage.setImage(new Image(cardFilePaths.getCardFilePath("BB")));
    }

    public void displayNewStatus(String status) {

        statusText.setText("(" + status + ")");
    }


}
