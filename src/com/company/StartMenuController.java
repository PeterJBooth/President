package com.company;

import com.company.gameController.gameData.GamesStats;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenuController {

    @FXML
    Button startBtn;

    @FXML
    public void clickStart() throws IOException {

        startGame();
    }

    public void startGame() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/GameScene.fxml"));

        Parent root = loader.load();
        Stage primaryStage = (Stage) startBtn.getScene().getWindow();

        GameController gameController = loader.getController();
        // Set all the game information to default
        gameController.gamesStats = new GamesStats();
        gameController.initializeController();

        primaryStage.setTitle("President");
        primaryStage.setScene(new Scene(root));

        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);

        primaryStage.getIcons().add(new Image("assets/cards/ace_of_hearts.png"));
        primaryStage.show();
    }


    @FXML
    public void clickRules() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/RulesScene.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Rules");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("assets/cards/ace_of_hearts.png"));
        stage.show();
    }
}
