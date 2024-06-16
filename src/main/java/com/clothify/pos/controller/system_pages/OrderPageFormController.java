package com.clothify.pos.controller.system_pages;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OrderPageFormController {
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

    public void btnOrderOnAction(ActionEvent actionEvent) {
        btnOrder.setDisable(btnOrder.isHover());
    }

    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("view/system_pages/product_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }

    public void btnInventoryOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("view/system_pages/inventory_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("view/system_pages/supplier_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("view/system_pages/employee_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }

    public void btnSalesOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("view/system_pages/sales_page.fxml")).load();
        orderPane.getChildren().clear();
        orderPane.getChildren().add(parent);
    }
}
