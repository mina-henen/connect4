package com.example.connect4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController extends Application {

    Stage window;
    @FXML
    private TextField kValue;
    int k;

    public MainMenuController() throws IOException {
    }

    public static void main(String[] args) {
        launch();
    }
    public void playWithPruning(ActionEvent event) {
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        k = Integer.parseInt(kValue.getText());
        if (k <= 0 || k > 7) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Value of k is not valid");
            errorAlert.setContentText("Please Enter a number from 1 to 7");
            errorAlert.showAndWait();
        }
        Controller c = new Controller();
        c.k = k;
        c.withPruning = true;
        Scene grid = new Scene(c.createContent());
        window.setScene(grid);
        window.show();
    }

    public void playWithoutPruning(ActionEvent event) {
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        k = Integer.parseInt(kValue.getText());
        if (k <= 0 || k > 7) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Value of k is not valid");
            errorAlert.setContentText("Please Enter a number from 1 to 7");
            errorAlert.showAndWait();
        }
        Controller c = new Controller();
        c.k = k;
        c.withPruning = false;
        Scene grid = new Scene(c.createContent());
        window.setScene(grid);
        window.show();
    }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene((new FXMLLoader(Controller.class.getResource("view.fxml"))).load(), 700, 700);
        stage.setTitle("Connect 4");
        stage.setScene(scene);
        stage.show();
    }

}
