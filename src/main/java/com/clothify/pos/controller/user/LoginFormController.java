package com.clothify.pos.controller.user;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.LoginBo;
import com.clothify.pos.util.BoType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private JFXComboBox cmbRole;
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

    private final LoginBo loginBo = BoFactory.getInstance().getBo(BoType.LOGIN);

    public void btnLoginOnAction() throws IOException {

        if(Boolean.TRUE.equals(loginBo.accountVerify(txtEmail.getText(), txtPassword.getText()))){
            resizeWindow();
        }else{
            new Alert(Alert.AlertType.ERROR,"Login has been failed check username and password again. If new User Create an account.").show();
        }
    }

    public void linkForgotPasswordOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/login/forgot_password.fxml")).load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(parent);

    }

    public void linkCreateAccountOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/login/register_page.fxml")).load();
        loginPane.getChildren().clear();
        loginPane.getChildren().add(parent);

    }



    public void resizeWindow() throws IOException {
        Parent newPane = new FXMLLoader(getClass().getResource("/view/system_pages/order_page.fxml")).load();

        // Ensure the new pane is an AnchorPane and set its preferred size and other size constraints
        if (newPane instanceof AnchorPane) {
            AnchorPane anchorPane = (AnchorPane) newPane;
            anchorPane.setPrefSize(1200, 800);
            anchorPane.setMaxSize(1200, 800);
            anchorPane.setMinSize(1200, 800);
        }
        // Resize the parent pane to fit the new pane
        loginPane.setPrefSize(1200, 800);
        loginPane.setMaxSize(1200, 800);
        loginPane.setMinSize(1200, 800);

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

    private void loadInitialValues() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("ADMIN");
        list.add("USER");
        cmbRole.setItems(list);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadInitialValues();
        linkCreateAccount.autosize();
        linkForgotPassword.autosize();
        btnLogin.setDefaultButton(true);
    }


}
