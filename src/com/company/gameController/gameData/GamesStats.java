package com.company.gameController.gameData;

import com.company.gameController.player.Player;
import com.company.gameController.player.Players;

import java.util.HashMap;

public class GamesStats {

    public int numberOfGamesPlayed = 0;
    public HashMap<String, HashMap<String, Integer>> playersStatistics = new HashMap<>();

    public GamesStats() {

        fillPlayersInfoHashMap();
    }

    private void fillPlayersInfoHashMap() {

        for (int i = 1; i < 7; i++) {

            String playerName = "Player " + i;
            fillPlayerInfoHashMap(playerName);
        }
    }

    private void fillPlayerInfoHashMap(String playerName) {

        HashMap<String, Integer> playerInfo = new HashMap<>();

        playerInfo.put("President", 0);
        playerInfo.put("Vice President", 0);
        playerInfo.put("Upper Class", 0);
        playerInfo.put("Lower Class", 0);
        playerInfo.put("Peasant", 0);
        playerInfo.put("Vice Peasant", 0);
        playerInfo.put("finishing order", 1);

        playersStatistics.put(playerName, playerInfo);
    }

    public void ifPlayedPreviouslySetNewStatus(Players players) {

        if (numberOfGamesPlayed > 0) {

            players.setAndDisplayAllPlayersStatus(this);
        }
    }

    public void updateAllPlayersStatistics(Players players) {

        for (Player player: players.playersList) {

            updatePlayerStatistics(player);
        }
    }

    public void updatePlayerStatistics(Player player) {

        int countOfStatus = playersStatistics.get(player.playerName).get(player.status);
        countOfStatus++;
        playersStatistics.get(player.playerName).put(player.status, countOfStatus);

        // set players finishing order
        playersStatistics.get(player.playerName).put("finishing order", player.finishingOrder);
    }
}

