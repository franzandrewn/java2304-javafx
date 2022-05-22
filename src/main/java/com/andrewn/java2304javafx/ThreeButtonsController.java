package com.andrewn.java2304javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ThreeButtonsController {
    @FXML
    public Button firstButton;
    @FXML
    public Button secondButton;
    @FXML
    public Button thirdButton;

    @FXML
    public void onFirstButtonClick(ActionEvent actionEvent) {
        secondButton.setText("Привет из 1 кнопки");
        secondButton.setBackground(new Background(new BackgroundFill(Color.CYAN, new CornerRadii(3), Insets.EMPTY)));
        secondButton.setTextFill(Color.BLUE);
    }

    @FXML
    public void onSecondButtonClick(ActionEvent actionEvent) {
        thirdButton.setText("Привет из 2 кнопки");
    }

    @FXML
    public void onThirdButtonClick(ActionEvent actionEvent) {
        firstButton.setText("Привет из 3 кнопки");
        secondButton.setText("Привет из 3 кнопки");
    }

    public void keyPressedFirstButton(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.R || keyEvent.getCode() == KeyCode.ESCAPE) {
            firstButton.setText("Кнопка 1");
        }
    }
}
