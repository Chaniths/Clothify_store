package com.clothify.pos.controller.user;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.LoginBo;
import com.clothify.pos.bo.custom.impl.LoginBoImpl;
import com.clothify.pos.dto.Login;
import com.clothify.pos.util.BoType;
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
import java.util.Map;

public class ForgotPasswordFormController {

   private final LoginBo loginBo = BoFactory.getInstance().getBo(BoType.LOGIN);

    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXButton btnSendOtp;
    @FXML
    private JFXTextField txtOtp;
    @FXML
    private JFXButton btnResetPassword;
    @FXML
    private Hyperlink linkGoToLogin;
    @FXML
    private JFXPasswordField txtConfirmPassword;
    @FXML
    private JFXPasswordField txtNewPassword;
    @FXML
    private AnchorPane forgotPasswordPane;

    public void linkGoToLoginOnAction() throws IOException {

        Parent parent = new FXMLLoader(getClass().getResource("/view/login/login_page.fxml")).load();
        forgotPasswordPane.getChildren().clear();
        forgotPasswordPane.getChildren().add(parent);

    }

    //forgot password page has to be added the admin type and loginId

    public void btnResetPasswordOnAction() throws IOException {
        Map<String, String> emailData = loginBo.getEmailData(txtEmail.getText());
        if(emailData.get("email").equals(txtEmail.getText())){
            Boolean b = loginBo.update(
                    new Login(
                            emailData.get("loginId"),
                            emailData.get("adminType"),
                            txtEmail.getText(),
                            "",
                            txtConfirmPassword.getText()
                    ),
                    (txtOtp).getText()
            );
            if(Boolean.TRUE.equals(b)){
                Parent parent = new FXMLLoader(getClass().getResource("/view/login/login_page.fxml")).load();
                forgotPasswordPane.getChildren().clear();
                forgotPasswordPane.getChildren().add(parent);
            }else {
                new Alert(Alert.AlertType.ERROR, " Email verification Failed").show();
                clearFields();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, " Entered email is not signed in before. If you don't have an account create an account").show();
        }

    }

    public void btnSendOtpOnAction() {
        Boolean emailInfo = loginBo.getEmailInfo(txtEmail.getText());
        if (Boolean.TRUE.equals(emailInfo)){
            btnSendOtp.setDisable(true);
        }
    }



    public void clearFields(){
        txtOtp.setText("");
        txtEmail.setText("");
        txtNewPassword.setText("");
        txtConfirmPassword.setText("");

    }
}
