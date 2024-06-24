package com.clothify.pos.controller.user;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.LoginBo;
import com.clothify.pos.dto.Login;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {
    @FXML
    private JFXPasswordField txtVerifyPassword;
    @FXML
    private JFXComboBox cmbUserType;
    @FXML
    private AnchorPane registerPane;
    @FXML
    private Hyperlink linkGoToLogin;
    @FXML
    private JFXButton btnSignUp;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPassword;

    private final LoginBo loginBo = BoFactory.getInstance().getBo(BoType.LOGIN);

    public void linkGoToLoginOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/login/login_page.fxml")).load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(parent);

    }

   // generate a Login id .Then put the values into the new Login()
    public void btnSignUpOnAction() throws IOException {
        if (verifyFields() && txtPassword.getText().equals(txtVerifyPassword.getText())){
            loginBo.persist(
                    new Login(
                            "",
                            cmbUserType.getTypeSelector(),
                            txtEmail.getText(),
                            "",
                            txtVerifyPassword.getText()
                    )
            );
        }else {
            new Alert(Alert.AlertType.ERROR, "Enter Email and Password to Continue. The Passwords also should be matched").show();
        }
        Parent parent = new FXMLLoader(getClass().getResource("/view/login/login_page.fxml")).load();
        registerPane.getChildren().clear();
        registerPane.getChildren().add(parent);

    }

    public boolean verifyFields (){
        if(txtEmail.getText().isEmpty() && txtPassword.getText().isEmpty()){
            return false;
        }
        return true;
    }

    private void loadInitialValues() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("ADMIN");
        list.add("USER");
        cmbUserType.setItems(list);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadInitialValues();
        linkGoToLogin.autosize();
        btnSignUp.autosize();
    }
}
