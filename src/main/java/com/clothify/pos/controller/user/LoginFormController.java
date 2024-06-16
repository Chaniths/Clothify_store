package com.clothify.pos.controller.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController{


    @FXML
    private AnchorPane loginPane;
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

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        resizeWindow();
    }

    public void linkForgotPasswordOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/login/forgot_password.fxml")).load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(parent);

    }

    public void linkCreateAccountOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/login/register_page.fxml")).load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(parent);

    }



    public void resizeWindow() throws IOException {
        Parent newPane = new FXMLLoader(getClass().getResource("/view/system_pages/order_page.fxml")).load();

        // Ensure the new pane is an AnchorPane and set its preferred size and other size constraints
        if (newPane instanceof AnchorPane) {
            AnchorPane anchorPane = (AnchorPane) newPane;
            anchorPane.setPrefSize(1200, 900);
            anchorPane.setMaxSize(1200, 900);
            anchorPane.setMinSize(1200, 900);
        }
        // Resize the parent pane to fit the new pane
        loginPane.setPrefSize(1200, 900);
        loginPane.setMaxSize(1200, 900);
        loginPane.setMinSize(1200, 900);

        // Clear the existing children of loginPane
        loginPane.getChildren().clear();

        // Add the new pane to loginPane
        loginPane.getChildren().add(newPane);


        // Force layout pass to ensure sizes are recalculated
        loginPane.layout();

        // Optionally resize the stage to fit the new size
        Stage stage = (Stage) loginPane.getScene().getWindow();
        stage.sizeToScene();

    }


}
