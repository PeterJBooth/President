package com.company.gameController.player;

import com.company.gameController.gameData.GameInformation;
import com.company.gameController.gameData.GameRules;
import com.company.gameController.cards.CardSorter;
import com.company.gameController.cards.PlacedCardsDisplay;
import com.company.gameController.player.display.OpponentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.*;
import java.util.stream.Collectors;

public class Opponent extends Player {

    public List<String> cards = new ArrayList<>();
    public List<String> cardsPlayed = new ArrayList<>();
    CardSorter cardSorter = new CardSorter();
    public OpponentDisplay opponentDisplay;
    public GameRules gameRules = new GameRules();

    public Opponent(Text cardsNumberText, Text hasKnockedText, Text statusText, ImageView opponentCardsImage, String playerName) {
        super(playerName);

        createOpponentDisplay(cardsNumberText, hasKnockedText, statusText, opponentCardsImage);

    }

    private void createOpponentDisplay(Text cardsNumberText, Text hasKnockedText, Text statusText, ImageView opponentCardsImage) {

        opponentDisplay = new OpponentDisplay(cardsNumberText, hasKnockedText, statusText, opponentCardsImage);
    }

    public void swapCardsWithOtherPlayer(List<String> cardIDs, int[] cardIDIndexes) {

        for (int i = 0; i < cardIDs.size(); i++) {
            cards.set(cardIDIndexes[i], cardIDs.get(i));
        }

        cards = cardSorter.SortPlayersCards(cards);
    }

    public void playCardOrKnock(GameInformation gameInformation, PlacedCardsDisplay placedCardsDisplay){

        cardsPlayed.clear(); // Remove previously placed cards

        if (gameInformation.requiredNumberOfCards > 1) playMultipleCards(gameInformation);

        else playOneCard(gameInformation);

        opponentDisplay.setCardsToBlack();

        if (cardsPlayed.isEmpty()){

            knock(gameInformation);
        }

        else {

            updateCardNumberText();
            gameInformation.updatePlayerAndCardInformation((ArrayList<String>) cardsPlayed, this);
            placedCardsDisplay.addCardToPileIfOpponentPlayedCard(this);
        }
    }

    public void playMultipleCards(GameInformation gameInformation){

        List<List<String>> cardGroups = createGroupsOfEqualRankedCards(gameInformation.requiredNumberOfCards);

        for (List<String> cardGroup: cardGroups) {

            boolean canPlayCard = gameRules.checkIfCanPlayCard(cardGroup.get(0), gameInformation);

            if (canPlayCard) {

                for (String card: cardGroup) {

                    cardsPlayed.add(card);
                    cards.set(cards.indexOf(card), "empty");
                    decrementCardsNumber();
                    // System.out.println(card);
                }
                break;
            }

        }
    }

    public void playOneCard(GameInformation gameInformation) {

        for (String card : cards) {

            boolean canPlayCard = gameRules.checkIfCanPlayCard(card, gameInformation);
            if (canPlayCard) {

                cardsPlayed.add(card);
                cards.set(cards.indexOf(card), "empty");
                decrementCardsNumber();
                // System.out.println(card);
                break;
            }
        }
    }

    public List<List<String>> createGroupsOfEqualRankedCards(int groupSize) {

        List<List<String>> cardGroups = new ArrayList<>();

        String[] card_ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "j", "Q", "K", "1", "J"};

        for (String card_rank: card_ranks) {
            List<String> group = cards.stream().filter(card -> String.valueOf(card.charAt(0)).equals(card_rank)).collect(Collectors.toList());
            if (group.size() >= groupSize) {
                cardGroups.add(group);
            }
        }

        return cardGroups;
    }

    private void knock(GameInformation gameInformation) {

        hasKnocked = true;
        opponentDisplay.writeHasKnocked();
        gameInformation.numberOfPlayersThatKnocked ++;
    }

    private void updateCardNumberText() {

        opponentDisplay.setCardsNumberText(cardNumber);
    }

    public void setToNotHasKnocked() {

        hasKnocked = false;
        opponentDisplay.removeHasKnockedText();
    }

}
