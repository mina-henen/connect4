package com.example.connect4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Main {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}