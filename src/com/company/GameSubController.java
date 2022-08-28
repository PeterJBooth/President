package com.company;

import com.company.gameController.MessageDisplay;
import com.company.gameController.cards.DeckOfCards;
import com.company.gameController.cards.PlacedCardsDisplay;
import com.company.gameController.gameData.GameInformation;
import com.company.gameController.gameData.GameRules;
import com.company.gameController.gameData.GamesStats;
import com.company.gameController.player.Opponent;
import com.company.gameController.player.Player;
import com.company.gameController.player.Players;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameSubController {

    GamesStats gamesStats;
    Players players;
    Text messageText;
    ImageView[] cardsPlacedImages;
    PlacedCardsDisplay placedCardsDisplay;
    MessageDisplay messageDisplay;

    DeckOfCards deckOfCards = new DeckOfCards();
    GameInformation gameInformation = new GameInformation();
    GameRules gameRules = new GameRules();

    GameSubController(GamesStats gamesStats, Players players, Text messageText, ImageView[] cardsPlacedImages) {

        this.gamesStats = gamesStats;
        this.players = players;
        this.messageText = messageText;
        this.cardsPlacedImages = cardsPlacedImages;

        placedCardsDisplay = new PlacedCardsDisplay(cardsPlacedImages);
        messageDisplay = new MessageDisplay(messageText);

        startGame();
    }

    private void startGame() {

        deckOfCards.shuffleDeck();
        deckOfCards.dealCardsToPlayers(players.mainPlayer, players.opponentsList);

        ifPlayedPreviouslySetNewStatusAndSwapPlayersCards();
    }

    private void ifPlayedPreviouslySetNewStatusAndSwapPlayersCards() {

        if (gamesStats.numberOfGamesPlayed > 0) {

            players.setAndDisplayAllPlayersStatus(gamesStats);
            players.swapPlayersCards(messageDisplay);
        }
    }

    public void playMainPlayerCardOrCardsAndEndTurn(){

        boolean canPlayCard = players.mainPlayer.checkIfMainPlayerCanPlayCard(gameRules, gameInformation);
        if (!canPlayCard) return;

        players.mainPlayer.playCardOrCards(placedCardsDisplay, gameInformation);

        finishTurn(players.mainPlayer);
    }

    public void MakeMainPlayerKnock(){

        boolean canKnock = players.mainPlayer.checkIfMainPlayerCanKnock();
        if (!canKnock) return;

        players.mainPlayer.setToHasKnocked(gameInformation);

        finishTurn(players.mainPlayer);
    }

    public void finishTurn(Player player){

        if (players.checkIfMainPlayer(player)) {
            players.mainPlayer.isPlayerTurn = false;
        }

        ifPlayerHasFinishedUpdateStatusOrEndGame(player);

        boolean aPlayerHasWonRound = gameInformation.checkIfAPlayerHasWonTheRound();

        if (aPlayerHasWonRound) restartRound();

        else playNextPlayer(player);
    }

    public void runOneOpponentTurn(Opponent opponent) throws InterruptedException {

        if (gameInformation.gameHasEnded) return;

        if (opponent.hasKnocked || opponent.hasNoCard) playNextPlayer(opponent);

        else opponentPlay(opponent);
    }

    public void playNextPlayer(Player player) {

        Player nextPlayer = players.getNextPlayer(players.playersList.indexOf(player));
        playPlayer(nextPlayer);
    }

    public void playPlayer(Player player) {

        if (players.checkIfMainPlayer(player)) {
            runMainPlayerTurn();
        }

        else {

            try {
                runOneOpponentTurn((Opponent) player);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void opponentPlay(Opponent opponent) {

        opponent.opponentDisplay.highlightPlayer();

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {

            messageDisplay.removeMessage();

            opponent.playCardOrKnock(gameInformation, placedCardsDisplay);

            finishTurn(opponent);
        });

        pause.play();
    }

    public void runMainPlayerTurn(){

        if (players.mainPlayer.checkIfHasFinishedOrKnocked()) playNextPlayer(players.mainPlayer);

        else players.mainPlayer.isPlayerTurn = true;

    }

    public void ifPlayerHasFinishedUpdateStatusOrEndGame(Player player) {

        if (player.cardNumber == 0) {

            player.hasNoCard = true;
            player.setNewStatus();

            gameInformation.numberOfActivePlayers --;

            messageDisplay.writeNewStatusMessage(player.playerName, player.status);
            players.displayPlayerStatus(player);

            players.incrementFinishingNumberOfPlayersStillPlaying();

            endGameIfOnePlayerRemains();
        }
    }

    public void endGameIfOnePlayerRemains() {

        if (gameInformation.numberOfActivePlayers == 1) {
            endGame();
        }
    }

    public void restartRound() {

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {

            Player roundWinner = gameInformation.playerWhoPlacedLastCard;

            messageDisplay.writeOpponentWonRoundText(roundWinner.playerName);
            placedCardsDisplay.removeCardsFromPile();
            players.resetPlayersKnockState();
            gameInformation.resetRoundInformation();
            playPlayer(roundWinner);
        });
        pause.play();
    }

    private void endGame() {

        gameInformation.gameHasEnded = true;

        players.setAndDisplayLastPlayerStatus();

        messageDisplay.writeGameEndMessage();
        placedCardsDisplay.removeCardsFromPile();

        gamesStats.updateAllPlayersStatistics(players);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {

            displayEndOfGameMenu();
        });
        pause.play();
    }

    public void displayEndOfGameMenu() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/EndOfGameScene.fxml"));

        Parent root = null;

        try {
             root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EndOfGameController endOfGameController = loader.getController();
        endOfGameController.gamesStats = gamesStats;
        endOfGameController.initializeController();

        Stage primaryStage = (Stage) messageText.getScene().getWindow();

        primaryStage.setTitle("President");
        primaryStage.setScene(new Scene(root));

        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("assets/cards/ace_of_hearts.png"));

        primaryStage.show();
    }
}
