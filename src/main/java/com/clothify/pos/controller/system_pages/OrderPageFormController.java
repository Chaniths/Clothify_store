package com.clothify.pos.controller.system_pages;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class OrderPageFormController{
    public JFXButton btnCustomer;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXTextField txtProductName;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private TableView tblOrder;
    @FXML
    private TableColumn colProductId;
    @FXML
    private TableColumn colProductName;
    @FXML
    private TableColumn colQty;
    @FXML
    private TableColumn colAmount;
    @FXML
    private TableColumn colAvailable;
    @FXML
    private JFXComboBox isAvailable;
    @FXML
    private TextField txtContact;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private Text lblOrderId;
    @FXML
    private Text lblCustomerId;
    @FXML
    private Text lblContact;
    @FXML
    private Text lblCustomerName;
    @FXML
    private Text lblTotal;
    @FXML
    private JFXButton btnCustomerOrder;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXButton btnReceipt;
    @FXML
    private JFXComboBox cmbProductId;
    @FXML
    private AnchorPane orderPane;
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

    public void btnOrderOnAction() {
        new Alert(Alert.AlertType.INFORMATION,"You are already on the Order Page").show();
    }

    public void btnProductOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/product_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }

    public void btnInventoryOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/inventory_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }

    public void btnSupplierOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/supplier_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }

    public void btnEmployeeOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/employee_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }

    public void btnSalesOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/sales_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }
    public void btnCustomerOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/customer_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }


    public void btnReceiptOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void btnCustomerOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }


}
