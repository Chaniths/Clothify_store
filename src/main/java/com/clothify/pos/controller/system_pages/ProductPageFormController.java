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

public class ProductPageFormController {
    @FXML
    public AnchorPane productPane;
    public JFXButton btnCustomer;
    @FXML
    private Text lblProductId;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXButton btnAddPic;
    @FXML
    private TableView tblProduct;
    @FXML
    private TableColumn colProductId;
    @FXML
    private TableColumn colProductName;
    @FXML
    private TableColumn colSupplierId;
    @FXML
    private TableColumn colUnitPrice;
    @FXML
    private TableColumn colCategory;
    @FXML
    private TableColumn colQty;
    @FXML
    private JFXButton btnAddProduct;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXComboBox cmbCategory;
    @FXML
    private TextField txtProductId;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXComboBox cmbSupplier;
    @FXML
    private JFXTextField txtProductName;
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
        productPane.getChildren().clear();
        productPane.getChildren().add(parent);
    }

    public void btnProductOnAction() {
        new Alert(Alert.AlertType.INFORMATION,"You are already on the Product Page");
    }

    public void btnInventoryOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/inventory_page.fxml")).load();
        productPane.getChildren().clear();
        productPane.getChildren().add(parent);
    }

    public void btnSupplierOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/supplier_page.fxml")).load();
        productPane.getChildren().clear();
        productPane.getChildren().add(parent);
    }

    public void btnEmployeeOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/employee_page.fxml")).load();
        productPane.getChildren().clear();
        productPane.getChildren().add(parent);
    }

    public void btnSalesOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/sales_page.fxml")).load();
        productPane.getChildren().clear();
        productPane.getChildren().add(parent);
    }
    public void btnCustomerOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/customer_page.fxml")).load();
        productPane.getChildren().clear();
        productPane.getChildren().add(parent);
    }

    public void btnAddPicOnAction(ActionEvent actionEvent) {
    }

    public void btnAddProductOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }


}
