package com.company.gameController.player;

import com.company.gameController.gameData.GamesStats;
import com.company.gameController.MessageDisplay;
import com.company.gameController.cards.MainPlayerCards;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.*;

public class Players {

    public MainPlayer mainPlayer;
    public Opponent player2;
    Opponent player3;
    Opponent player4;
    Opponent player5;
    Opponent player6;

    public Statuses statuses = new Statuses();

    public ArrayList<Player> playersList = new ArrayList<>();
    public ArrayList<Opponent> opponentsList = new ArrayList<>();

    public Players(Text[] cardsNumberText, Text[] hasKnockedText, Text[] statusText, ImageView[] opponentCardsImage, Button knockButton, MainPlayerCards mainPlayerCards) {

        createOpponents(cardsNumberText, hasKnockedText, statusText, opponentCardsImage);
        createMainPlayer(knockButton, statusText, mainPlayerCards);

        createOpponentsArrayList();
        createPlayersArrayList();
    }

    private void createMainPlayer(Button knockButton, Text[] statusText, MainPlayerCards mainPlayerCards) {

        mainPlayer = new MainPlayer("Player 1", knockButton, statusText[0], mainPlayerCards);
    }

    private void createOpponents(Text[] cardsNumberText, Text[] hasKnockedText, Text[] statusText, ImageView[] opponentCardsImage){

        player2 = new Opponent(cardsNumberText[0], hasKnockedText[0], statusText[1], opponentCardsImage[0], "Player 2");
        player3 = new Opponent(cardsNumberText[1], hasKnockedText[1], statusText[2], opponentCardsImage[1], "Player 3");
        player4 = new Opponent(cardsNumberText[2], hasKnockedText[2], statusText[3], opponentCardsImage[2], "Player 4");
        player5 = new Opponent(cardsNumberText[3], hasKnockedText[3], statusText[4], opponentCardsImage[3], "Player 5");
        player6 = new Opponent(cardsNumberText[4], hasKnockedText[4], statusText[5], opponentCardsImage[4], "Player 6");
    }

    private void createOpponentsArrayList() {

        opponentsList.add(player2);
        opponentsList.add(player3);
        opponentsList.add(player4);
        opponentsList.add(player5);
        opponentsList.add(player6);
    }

    private void createPlayersArrayList() {

        playersList.add(mainPlayer);
        playersList.add(player2);
        playersList.add(player3);
        playersList.add(player4);
        playersList.add(player5);
        playersList.add(player6);
    }

    public void setAndDisplayAllPlayersStatus(GamesStats gamesStats) {

        for (Player player: playersList) {

            player.setPlayersStatusFromPreviousGame(gamesStats);

            displayPlayerStatus(player);
        }

    }

    public void setAndDisplayLastPlayerStatus() {

        for (Player player: playersList) {

            if (!player.hasNoCard) {

                player.hasNoCard = true;
                player.setNewStatus();
                displayPlayerStatus(player);
            }
        }
    }

    public void displayPlayerStatus(Player player){

        if (checkIfMainPlayer(player)) {
            // MainPlayer
            mainPlayer.DisplayMainPlayersNewStatus(mainPlayer.status);
        }

        else {
            // Opponent
            Opponent opponent = (Opponent) player;
            opponent.opponentDisplay.displayNewStatus(opponent.status);
        }
    }

    public boolean checkIfMainPlayer(Player player) {

        return Objects.equals(player.playerName, "Player 1");
    }

    public void swapPlayersCards(MessageDisplay messageDisplay) {

        List<String> PresidentsWorstTwoCards = getTopOrWorstCardsOfPlayerWithGivenStatus("President");
        List<String> VicePresidentsWorstCard = getTopOrWorstCardsOfPlayerWithGivenStatus("Vice President");
        List<String> PeasantsTopCard = getTopOrWorstCardsOfPlayerWithGivenStatus("Peasant");
        List<String> VicePeasantsTopTwoCards = getTopOrWorstCardsOfPlayerWithGivenStatus("Vice Peasant");

        messageDisplay.messageText.setText("Peasant and Vice Peasant Swap Your BEST Cards\n" +
                                           "President and Vice President Swap Your WORST Cards\n");

        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(e -> {

            swapPlayersTopOrWorstCardsWithNewCards("President", VicePeasantsTopTwoCards);
            swapPlayersTopOrWorstCardsWithNewCards("Vice President", PeasantsTopCard);
            swapPlayersTopOrWorstCardsWithNewCards("Peasant", VicePresidentsWorstCard);
            swapPlayersTopOrWorstCardsWithNewCards("Vice Peasant", PresidentsWorstTwoCards);

            messageDisplay.messageText.setText("");
        });
        pause.play();
    }

    public List<String> getTopOrWorstCardsOfPlayerWithGivenStatus(String status) {

        Player player = getPlayerWithGivenStatus(status);
        return getTopOrWorseCards(player);
    }

    public Player getPlayerWithGivenStatus(String status) {

        for (Player player: playersList) {

            if (Objects.equals(player.status, status)) {

                if (checkIfMainPlayer(player)){
                    mainPlayer.isPlayerTurn = false;
                }

                return player;
            }
        }
        return null;
    }

    public List<String> getTopOrWorseCards(Player player) {

        List<String> cardOrCards;

        int[] cardIDIndexes = statuses.getIndexesOfCardsToSwap(player.status);

        if (checkIfMainPlayer(player)) {

            cardOrCards = getTopOrWorseCardFromMainPlayer(cardIDIndexes);
        }

        else {

            cardOrCards = getTopOrWorseCardFromOpponent((Opponent) player, cardIDIndexes);
        }

        return cardOrCards;
    }

    public List<String> getTopOrWorseCardFromMainPlayer(int[] cardIDIndexes) {

        List<String> cardOrCards = new ArrayList<>();

        for (int index: cardIDIndexes) cardOrCards.add(mainPlayer.mainPlayerCards.cardIDs.get(index));

        return cardOrCards;
    }

    public List<String> getTopOrWorseCardFromOpponent(Opponent opponent, int[] cardIDIndexes) {

        List<String> cardOrCards = new ArrayList<>();

        for (int index: cardIDIndexes) cardOrCards.add(opponent.cards.get(index));

        return cardOrCards;
    }

    public void swapPlayersTopOrWorstCardsWithNewCards(String status, List<String> cardIDs) {

        Player player = getPlayerWithGivenStatus(status);

        int[] cardIDIndexes = statuses.getIndexesOfCardsToSwap(status);

        if (checkIfMainPlayer(player)) {

            mainPlayer.swapCardsWithOpponent(cardIDs, cardIDIndexes);
        }

        else {

            Opponent opponent = (Opponent) player;
            opponent.swapCardsWithOtherPlayer(cardIDs, cardIDIndexes);
        }
    }

    public Player getNextPlayer(int previousPlayerIndex) {

        return playersList.get((previousPlayerIndex + 1) % 6);
    }

    public void resetPlayersKnockState() {

        mainPlayer.setToNotHasKnocked();

        for (Opponent opponent : opponentsList) {

            opponent.setToNotHasKnocked();
        }
    }

    public void incrementFinishingNumberOfPlayersStillPlaying() {

        for (Player player : playersList) {

            if (!player.hasNoCard) {

                player.finishingOrder++;
            }
        }
    }
}

