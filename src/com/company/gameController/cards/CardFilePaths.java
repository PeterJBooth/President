package com.company.gameController.cards;

import java.util.HashMap;

public class CardFilePaths {

    HashMap<String, String> nameToFileName = new HashMap<>();
    String cardDirectory = "C:\\Users\\Peter\\IdeaProjects\\President\\src\\assets\\cards\\";

    public CardFilePaths(){

        nameToFileName.put("TC", "10_of_clubs.png");
        nameToFileName.put("TD", "10_of_diamonds.png");
        nameToFileName.put("TH", "10_of_hearts.png");
        nameToFileName.put("TS", "10_of_spades.png");
        nameToFileName.put("2C", "2_of_clubs.png");
        nameToFileName.put("2D", "2_of_diamonds.png");
        nameToFileName.put("2H", "2_of_hearts.png");
        nameToFileName.put("2S", "2_of_spades.png");
        nameToFileName.put("3C", "3_of_clubs.png");
        nameToFileName.put("3D", "3_of_diamonds.png");
        nameToFileName.put("3H", "3_of_hearts.png");
        nameToFileName.put("3S", "3_of_spades.png");
        nameToFileName.put("4C", "4_of_clubs.png");
        nameToFileName.put("4D", "4_of_diamonds.png");
        nameToFileName.put("4H", "4_of_hearts.png");
        nameToFileName.put("4S", "4_of_spades.png");
        nameToFileName.put("5C", "5_of_clubs.png");
        nameToFileName.put("5D", "5_of_diamonds.png");
        nameToFileName.put("5H", "5_of_hearts.png");
        nameToFileName.put("5S", "5_of_spades.png");
        nameToFileName.put("6C", "6_of_clubs.png");
        nameToFileName.put("6D", "6_of_diamonds.png");
        nameToFileName.put("6H", "6_of_hearts.png");
        nameToFileName.put("6S", "6_of_spades.png");
        nameToFileName.put("7C", "7_of_clubs.png");
        nameToFileName.put("7D", "7_of_diamonds.png");
        nameToFileName.put("7H", "7_of_hearts.png");
        nameToFileName.put("7S", "7_of_spades.png");
        nameToFileName.put("8C", "8_of_clubs.png");
        nameToFileName.put("8D", "8_of_diamonds.png");
        nameToFileName.put("8H", "8_of_hearts.png");
        nameToFileName.put("8S", "8_of_spades.png");
        nameToFileName.put("9C", "9_of_clubs.png");
        nameToFileName.put("9D", "9_of_diamonds.png");
        nameToFileName.put("9H", "9_of_hearts.png");
        nameToFileName.put("9S", "9_of_spades.png");
        nameToFileName.put("1C", "ace_of_clubs.png");
        nameToFileName.put("1D", "ace_of_diamonds.png");
        nameToFileName.put("1H", "ace_of_hearts.png");
        nameToFileName.put("1S", "ace_of_spades.png");
        nameToFileName.put("JB", "black_joker.png");
        nameToFileName.put("jC", "jack_of_clubs.png");
        nameToFileName.put("jD", "jack_of_diamonds.png");
        nameToFileName.put("jH", "jack_of_hearts.png");
        nameToFileName.put("jS", "jack_of_spades.png");
        nameToFileName.put("KC", "king_of_clubs.png");
        nameToFileName.put("KD", "king_of_diamonds.png");
        nameToFileName.put("KH", "king_of_hearts.png");
        nameToFileName.put("KS", "king_of_spades.png");
        nameToFileName.put("QC", "queen_of_clubs.png");
        nameToFileName.put("QD", "queen_of_diamonds.png");
        nameToFileName.put("QH", "queen_of_hearts.png");
        nameToFileName.put("QS", "queen_of_spades.png");
        nameToFileName.put("JR", "red_joker.png");
        nameToFileName.put("W", "white.png");
        nameToFileName.put("RB", "backofcards_red.png");
        nameToFileName.put("BB", "backofcards.png");
    }

    public String getCardFilePath(String cardID) {

        return cardDirectory + nameToFileName.get(cardID);

    }
}
