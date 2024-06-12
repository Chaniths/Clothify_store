package com.clothify.pos.controller.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class LoginFormController {
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Hyperlink linkForgotPassword;
    @FXML
    private Hyperlink linkCreateAccount;
    @FXML
    private JFXPasswordField txtPassword;

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String text = txtEmail.getText();
    }

    public void linkForgotPasswordOnAction(ActionEvent actionEvent) {
        String text = txtEmail.getText();
    }

    public void linkCreateAccountOnAction(ActionEvent actionEvent) {
        String text = txtEmail.getText();
    }
}
