package com.clothify.pos.controller.system_pages;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.CustomerBo;
import com.clothify.pos.dto.Customer;
import com.clothify.pos.util.BoType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerFormController {
    public DatePicker dateDob;
    @FXML
    private AnchorPane customerPane;
    @FXML
    private Text lblCustomerId;
    @FXML
    private TextField SearchContact;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private Text lblSearchCustId;
    @FXML
    private Text lblCustName;
    @FXML
    private Text lblCustContact;
    @FXML
    private Text lblCustEmail;
    @FXML
    private Text lblCustAddress;
    @FXML
    private Text lblDob;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtCustomerId;

    private final CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void btnOrderOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/order_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);
    }

    public void btnSupplierOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/supplier_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);

    }

    public void btnInventoryOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/inventory_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);
    }

    public void btnProductOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/product_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);
    }

    public void btnSalesOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/sales_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);
    }

    public void btnEmployeeOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/employee_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);
    }

    public void btnCustomerOnAction() {
        new Alert(Alert.AlertType.INFORMATION,"You are already on the Customer Page.");
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnAddCustomerOnAction() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            date = format.parse(dateDob.getValue().toString());
            Customer customer = new Customer(
                    lblCustomerId.getText(),
                    txtName.getText(),
                    txtContact.getText(),
                    txtEmail.getText(),
                    date,
                    txtAddress.getText()
            );
            boolean b = customerBo.saveCustomer(customer);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added !").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Customer not Added !").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }
}
