package com.clothify.pos.controller.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class RegisterFormController {

    @FXML
    private Hyperlink linkGoToLogin;
    @FXML
    private JFXButton btnSignUp;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPassword;

    public void linkGoToLoginOnAction(ActionEvent actionEvent) {
        String text = txtEmail.getText();
    }

    public void btnSignUpOnAction(ActionEvent actionEvent) {
        String text = txtEmail.getText();
    }
}
