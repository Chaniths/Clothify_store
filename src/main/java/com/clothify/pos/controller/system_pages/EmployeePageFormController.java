package com.clothify.pos.controller.system_pages;

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

public class EmployeePageFormController {
    public JFXButton btnCustomer;
    @FXML
    private AnchorPane employeePane;
    @FXML
    private TextField txtEmployeeId;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private Text lblEmployeeName;
    @FXML
    private Text lblEmployeeAge;
    @FXML
    private Text lblNic;
    @FXML
    private Text lblContact;
    @FXML
    private Text lblAddress;
    @FXML
    private Text lblRecruited;
    @FXML
    private JFXTextField txtEmployeeName;
    @FXML
    private JFXTextField txtEmployeeAge;
    @FXML
    private JFXTextField txtNic;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private DatePicker txtDate;
    @FXML
    private Text lblEmployeeId;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXButton btnOrder;
    @FXML
    private JFXButton btnProduct;
    @FXML
    private JFXButton btnInventory;
    @FXML
    private JFXButton btnSupplier;
    @FXML
    private JFXButton btnEmployee;
    @FXML
    private JFXButton btnSales;

    public void btnOrderOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/order_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnProductOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/product_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnInventoryOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/inventory_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnSupplierOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/supplier_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnEmployeeOnAction()  {
       new Alert(Alert.AlertType.INFORMATION,"You are already on the Employee Page");
    }

    public void btnSalesOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/sales_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnCustomerOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/customer_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }


}
