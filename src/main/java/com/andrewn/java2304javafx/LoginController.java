package com.andrewn.java2304javafx;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LoginController extends BaseController {
    private static final String LOGIN = "ADMIN";
    private static final String PASSWORD = "Passw0rd";

    public TextField loginField;
    public PasswordField passwordField;
    public Button loginButton;

    public void login(ActionEvent actionEvent) {
        String gotLogin = loginField.getText();
        String gotPassword = passwordField.getText();
        if (gotLogin.equals(LOGIN) && gotPassword.equals(PASSWORD)) {
            loginButton.setBorder(new Border(
                    new BorderStroke(Color.BLACK,
                            BorderStrokeStyle.SOLID,
                            new CornerRadii(3),
                            new BorderWidths(2))));
            goToNextScene();
        } else {
            loginButton.setBorder(new Border(
                    new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(3),
                    new BorderWidths(2))));
        }
    }
}
