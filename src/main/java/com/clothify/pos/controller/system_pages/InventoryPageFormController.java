package com.clothify.pos.controller.system_pages;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryPageFormController implements Initializable {

    public JFXButton btnCustomer;
    @FXML
    private AnchorPane inventoryPane;
    @FXML
    private TableView tblInventory;
    @FXML
    private TableColumn colProductId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colCategory;
    @FXML
    private TableColumn colSupplierId;
    @FXML
    private TableColumn colUnitPrice;
    @FXML
    private TableColumn colTotalPrice;
    @FXML
    private TableColumn colQtyOnHand;
    @FXML
    private TableColumn colReceivedQty;
    @FXML
    private TextField SearchProdId;
    @FXML
    private JFXTextField txtProductId;
    @FXML
    private JFXTextField txtProductName;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXTextField txtCategory;
    @FXML
    private JFXTextField txtQtyOnHand;
    @FXML
    private JFXTextField txtReceivedQty;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtSupplierId;
    @FXML
    private JFXTextField txtInventoryTotal;
    @FXML
    private JFXButton btnAddInventory;
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
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(parent);
    }

    public void btnProductOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/product_page.fxml")).load();
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(parent);
    }

    public void btnInventoryOnAction() {
        new Alert(Alert.AlertType.INFORMATION,"You are already on the InventoryEntity Page").show();
    }

    public void btnSupplierOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/supplier_page.fxml")).load();
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(parent);
    }

    public void btnEmployeeOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/employee_page.fxml")).load();
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(parent);
    }

    public void btnSalesOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/sales_page.fxml")).load();
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(parent);
    }

    public void btnCustomerOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/customer_page.fxml")).load();
        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(parent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnOrder.setDefaultButton(true);

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnAddInventoryOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }


}
