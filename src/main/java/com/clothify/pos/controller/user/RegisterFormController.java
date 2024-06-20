package com.clothify.pos.controller.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RegisterFormController {
    @FXML
    private AnchorPane registerPane;
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

    public void linkGoToLoginOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/login/login_page.fxml")).load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(parent);

    }

    public void btnSignUpOnAction() throws IOException {
        if (txtEmail.getText().isEmpty()){
            btnSignUp.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Enter Email to Continue !").show();
        }
        Parent parent = new FXMLLoader(getClass().getResource("/view/login/login_page.fxml")).load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(parent);

    }


}
