package com.andrewn.java2304javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class HelloButtonActionHandler implements EventHandler<ActionEvent> {
    private Label labelToChangeText;

    public HelloButtonActionHandler() {
    }

    public HelloButtonActionHandler(Label labelToChangeText) {
        this.labelToChangeText = labelToChangeText;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Кнопка hello нажата");
        labelToChangeText.setText("Hello world!");
    }
}
