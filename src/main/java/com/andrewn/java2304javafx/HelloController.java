package com.andrewn.java2304javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    public Button helloButton;
    @FXML
    public Label welcomeText;

    private int buttonClickCount = 0;

    @FXML
    public void onHelloButtonClick(ActionEvent actionEvent) {
        buttonClickCount++;
        if (buttonClickCount % 2 == 1) {
            welcomeText.setText("Hello");
            welcomeText.setStyle("-fx-background-color: pink");
        } else {
            welcomeText.setText("Привет");
            welcomeText.setStyle("-fx-background-color: yellow");
        }
    }

    @FXML
    public void onMouseEntered(MouseEvent mouseEvent) {
        helloButton.setText("На мне мышь");
    }

    @FXML
    public void onMouseExited(MouseEvent mouseEvent) {
        helloButton.setText("Мышь ушла");
    }
}