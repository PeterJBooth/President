package com.company;

import com.company.endOfGameController.PlayerStatisticsDisplay;
import com.company.gameController.gameData.GamesStats;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class EndOfGameController {

    @FXML
    Text player1Status1Text, player1Status2Text, player1Status3Text, player1Status4Text, player1Status5Text, player1Status6Text;
    @FXML
    Text player2Status1Text, player2Status2Text, player2Status3Text, player2Status4Text, player2Status5Text, player2Status6Text;
    @FXML
    Text player3Status1Text, player3Status2Text, player3Status3Text, player3Status4Text, player3Status5Text, player3Status6Text;
    @FXML
    Text player4Status1Text, player4Status2Text, player4Status3Text, player4Status4Text, player4Status5Text, player4Status6Text;
    @FXML
    Text player5Status1Text, player5Status2Text, player5Status3Text, player5Status4Text, player5Status5Text, player5Status6Text;
    @FXML
    Text player6Status1Text, player6Status2Text, player6Status3Text, player6Status4Text, player6Status5Text, player6Status6Text;
    @FXML
    Text player1ScoreText, player2ScoreText, player3ScoreText, player4ScoreText, player5ScoreText, player6ScoreText;
    @FXML
    Text GamesPlayedCountText;

    PlayerStatisticsDisplay player1StatisticsDisplay;
    PlayerStatisticsDisplay player2StatisticsDisplay;
    PlayerStatisticsDisplay player3StatisticsDisplay;
    PlayerStatisticsDisplay player4StatisticsDisplay;
    PlayerStatisticsDisplay player5StatisticsDisplay;
    PlayerStatisticsDisplay player6StatisticsDisplay;

    public GamesStats gamesStats;

    public void initializeController() {

        createPlayerStatisticsDisplayObjects();
        createGameStatisticsDisplay();
    }

    private void createPlayerStatisticsDisplayObjects() {

        player1StatisticsDisplay = new PlayerStatisticsDisplay("Player 1",
                new Text[]{player1Status1Text, player1Status2Text, player1Status3Text, player1Status4Text,
                        player1Status5Text, player1Status6Text, player1ScoreText}, gamesStats);

        player2StatisticsDisplay = new PlayerStatisticsDisplay("Player 2",
                new Text[]{player2Status1Text, player2Status2Text, player2Status3Text, player2Status4Text,
                        player2Status5Text, player2Status6Text, player2ScoreText}, gamesStats);

        player3StatisticsDisplay = new PlayerStatisticsDisplay("Player 3",
                new Text[]{player3Status1Text, player3Status2Text, player3Status3Text, player3Status4Text,
                        player3Status5Text, player3Status6Text, player3ScoreText}, gamesStats);

        player4StatisticsDisplay = new PlayerStatisticsDisplay("Player 4",
                new Text[]{player4Status1Text, player4Status2Text, player4Status3Text, player4Status4Text,
                        player4Status5Text, player4Status6Text, player4ScoreText}, gamesStats);

        player5StatisticsDisplay = new PlayerStatisticsDisplay("Player 5",
                new Text[]{player5Status1Text, player5Status2Text, player5Status3Text, player5Status4Text,
                        player5Status5Text, player5Status6Text, player5ScoreText}, gamesStats);

        player6StatisticsDisplay = new PlayerStatisticsDisplay("Player 6",
                new Text[]{player6Status1Text, player6Status2Text, player6Status3Text, player6Status4Text,
                        player6Status5Text, player6Status6Text, player6ScoreText}, gamesStats);
    }

    public void createGameStatisticsDisplay() {

        incrementAndDisplayNumberOfGamesPlayed();

        player1StatisticsDisplay.displayStatistics();
        player2StatisticsDisplay.displayStatistics();
        player3StatisticsDisplay.displayStatistics();
        player4StatisticsDisplay.displayStatistics();
        player5StatisticsDisplay.displayStatistics();
        player6StatisticsDisplay.displayStatistics();
    }

    private void incrementAndDisplayNumberOfGamesPlayed() {

        gamesStats.numberOfGamesPlayed ++;
        GamesPlayedCountText.setText(String.valueOf(gamesStats.numberOfGamesPlayed));
    }

    @FXML
    public void clickPlay() throws IOException {

        restartGame();
    }

    public void restartGame() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/GameScene.fxml"));

        Parent root = loader.load();

        Stage primaryStage = (Stage) player1ScoreText.getScene().getWindow();

        GameController gameController = loader.getController();
        gameController.gamesStats = gamesStats;
        gameController.initializeController();

        primaryStage.setTitle("President");
        primaryStage.setScene(new Scene(root));

        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("assets/cards/ace_of_hearts.png"));


        primaryStage.show();
    }

    @FXML
    void clickExit() {

        Platform.exit();
        System.exit(0);
    }
}

