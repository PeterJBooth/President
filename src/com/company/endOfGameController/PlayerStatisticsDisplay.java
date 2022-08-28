package com.company.endOfGameController;
import com.company.gameController.gameData.GamesStats;
import javafx.scene.text.Text;
import java.util.HashMap;

public class PlayerStatisticsDisplay {

    String playerName;

    Text playerScoreText;
    String[] statuses = {"President", "Vice President", "Upper Class", "Lower Class", "Peasant", "Vice Peasant"};

    HashMap<String, Text> statusToStatusText = new HashMap<>();
    HashMap<String, Integer> statusToPoints = new HashMap<>();

    GamesStats gamesStats;

    public PlayerStatisticsDisplay(String playerName, Text[] playerStatisticsTexts, GamesStats gamesStats) {

        this.playerName = playerName;
        this.gamesStats = gamesStats;
        this.playerScoreText = playerStatisticsTexts[6];

        fillStatusToStatusTextHashMap(playerStatisticsTexts);
        fillStatusToPointsHashMap();
    }

    private void fillStatusToStatusTextHashMap(Text[] playerStatisticsText) {

        statusToStatusText.put(statuses[0], playerStatisticsText[0]);
        statusToStatusText.put(statuses[1], playerStatisticsText[1]);
        statusToStatusText.put(statuses[2], playerStatisticsText[2]);
        statusToStatusText.put(statuses[3], playerStatisticsText[3]);
        statusToStatusText.put(statuses[4], playerStatisticsText[4]);
        statusToStatusText.put(statuses[5], playerStatisticsText[5]);
    }

    private void fillStatusToPointsHashMap() {

        statusToPoints.put(statuses[0], 4);
        statusToPoints.put(statuses[1], 2);
        statusToPoints.put(statuses[2], 1);
        statusToPoints.put(statuses[3], 0);
        statusToPoints.put(statuses[4], -1);
        statusToPoints.put(statuses[5], -2);
    }

    public void displayStatistics() {

        for (String status : statuses) {

            int numberOfTimesStatusWasGained = gamesStats.playersStatistics.get(playerName).get(status);
            statusToStatusText.get(status).setText(Integer.toString(numberOfTimesStatusWasGained));
        }

        displayScore();
    }

    private void displayScore() {

        int score = calculateScore();
        playerScoreText.setText(Integer.toString(score));
    }

    private int calculateScore() {

        int score = 0;

        for (String status : statuses) {

            int numberOfTimesStatusWasGained = gamesStats.playersStatistics.get(playerName).get(status);
            int pointsForStatus = statusToPoints.get(status);
            score += pointsForStatus * numberOfTimesStatusWasGained;
        }

        return score;
    }
}