package com.company;

import com.company.GameSubController;
import com.company.gameController.cards.MainPlayerCards;
import com.company.gameController.gameData.GamesStats;
import com.company.gameController.player.Players;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class GameController {

    @FXML
    ImageView cardPlaced1, cardPlaced2, cardPlaced3, cardPlaced4;
    @FXML
    Text cardsNumberText2, cardsNumberText3, cardsNumberText4, cardsNumberText5, cardsNumberText6;
    @FXML
    Text hasKnockedText2, hasKnockedText3, hasKnockedText4, hasKnockedText5, hasKnockedText6;
    @FXML
    Text statusText1, statusText2, statusText3, statusText4, statusText5, statusText6;
    @FXML
    ImageView opponentCardsImage2, opponentCardsImage3, opponentCardsImage4, opponentCardsImage5, opponentCardsImage6;

    @FXML
    Text messageText;
    @FXML
    Button knockButton;

    @FXML
    HBox cardsBox;
    @FXML
    Button card1, card2, card3, card4, card5, card6, card7, card8, card9;

    GameSubController gameSubController;
    Players players;
    MainPlayerCards mainPlayerCards;
    public GamesStats gamesStats;

    public void initializeController() {

        createCardsObject();
        createPlayersObjects();
        createGameSubControllerObject();
    }

    public void createCardsObject(){

        mainPlayerCards = new MainPlayerCards(new Button[] {card1, card2, card3, card4, card5, card6, card7, card8, card9}, cardsBox);
    }

    private void createPlayersObjects() {

        players = new Players(new Text[] {cardsNumberText2, cardsNumberText3, cardsNumberText4, cardsNumberText5, cardsNumberText6},
                              new Text[] {hasKnockedText2, hasKnockedText3, hasKnockedText4, hasKnockedText5, hasKnockedText6},
                              new Text[] {statusText1, statusText2, statusText3, statusText4, statusText5, statusText6},
                              new ImageView[] {opponentCardsImage2, opponentCardsImage3, opponentCardsImage4, opponentCardsImage5, opponentCardsImage6},
                              knockButton,
                              mainPlayerCards);
    }

    private void createGameSubControllerObject() {

        ImageView[] cardsPlacedImages = new ImageView[]{cardPlaced1, cardPlaced2, cardPlaced3, cardPlaced4};
        gameSubController = new GameSubController(gamesStats, players, messageText, cardsPlacedImages);
    }

    @FXML
    private void clickCard(ActionEvent mouseEvent) {

        mainPlayerCards.chooseOrUnchooseCard(mouseEvent);
    }

    @FXML
    public void clickPlayCard () {

        gameSubController.playMainPlayerCardOrCardsAndEndTurn();
    }

    @FXML
    public void knock() {

        gameSubController.MakeMainPlayerKnock();
    }
}
