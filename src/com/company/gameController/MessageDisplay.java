package com.company.gameController;

import javafx.scene.text.Text;

public class MessageDisplay {

    public Text messageText;

    public MessageDisplay(Text messageText) {

        this.messageText = messageText;
    }

    public void writeOpponentWonRoundText(String playerNameWhoNotKnocked) {

        messageText.setText(playerNameWhoNotKnocked + " Wins This Round!");
    }

    public void removeMessage() {

        messageText.setText("");
    }

    public void writeNewStatusMessage(String playerName, String status) {

        messageText.setText(playerName + " has got " + status + " status!");
    }

    public void writeGameEndMessage() {

        messageText.setText("The Game has Ended!");
    }


}
