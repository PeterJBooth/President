package com.company.gameController.player.display;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MainPlayerDisplay {

    Button knockButton;
    Text statusText;
    public MainPlayerDisplay(Button knockButton, Text statusText) {

        this.knockButton = knockButton;
        this.statusText = statusText;
    }

    public void setKnockButtonToKnocked() {

        knockButton.setText("You Have\n Knocked!");
    }

    public void resetKnockButton() {

        knockButton.setText("Knock");
    }

    public void displayNewStatus(String status) {

        statusText.setText(status);
    }
}
