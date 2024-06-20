package com.clothify.pos.controller.system_pages;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class SupplierPageFormController {
    public JFXButton btnCustomer;
    @FXML
    private JFXTextField txtSupplierName;
    @FXML
    private JFXTextField txtProductId;
    @FXML
    private JFXTextField txtProductName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtCompany;
    @FXML
    private TableView tblSupplier;
    @FXML
    private TableColumn colSupplierId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colProductId;
    @FXML
    private TableColumn colProductName;
    @FXML
    private TableColumn colContact;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colCompany;
    @FXML
    private JFXButton btnAddCustomer;
    @FXML
    private JFXButton btnDeleteSupplier;
    @FXML
    private Text lblSupplierId;
    @FXML
    private JFXButton btnUpdateSupplier;
    @FXML
    private AnchorPane supplierPane;
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
        supplierPane.getChildren().clear();
        supplierPane.getChildren().add(parent);
    }

    public void btnProductOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/product_page.fxml")).load();
        supplierPane.getChildren().clear();
        supplierPane.getChildren().add(parent);
    }

    public void btnInventoryOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/inventory_page.fxml")).load();
        supplierPane.getChildren().clear();
        supplierPane.getChildren().add(parent);
    }

    public void btnSupplierOnAction() {
        new Alert(Alert.AlertType.INFORMATION,"You are already on the Supplier Page");
    }

    public void btnEmployeeOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/employee_page.fxml")).load();
        supplierPane.getChildren().clear();
        supplierPane.getChildren().add(parent);
    }

    public void btnSalesOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/sales_page.fxml")).load();
        supplierPane.getChildren().clear();
        supplierPane.getChildren().add(parent);
    }
    public void btnCustomerOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/customer_page.fxml")).load();
        supplierPane.getChildren().clear();
        supplierPane.getChildren().add(parent);
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
    }


}
