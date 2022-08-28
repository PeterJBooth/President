package com.company;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx. scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("scenes/StartMenu.fxml"));
        primaryStage.setTitle("President");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("assets/cards/ace_of_hearts.png"));
        primaryStage.show();
    }
}
