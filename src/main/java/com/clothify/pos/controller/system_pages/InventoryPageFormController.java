package com.clothify.pos.controller.system_pages;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.InventoryBo;
import com.clothify.pos.bo.custom.ProductBo;
import com.clothify.pos.bo.custom.SupplierBo;
import com.clothify.pos.dto.Inventory;
import com.clothify.pos.util.BoType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InventoryPageFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbCategory;
    @FXML
    private JFXComboBox<String> cmbSupplier;
    @FXML
    private AnchorPane inventoryPane;
    @FXML
    private TableView<Inventory> tblInventory;
    @FXML
    private TableColumn<Inventory,String> colProductId;
    @FXML
    private TableColumn<Inventory,String> colName;
    @FXML
    private TableColumn<Inventory,String> colCategory;
    @FXML
    private TableColumn<Inventory,String> colSupplierId;
    @FXML
    private TableColumn<Inventory,String> colUnitPrice;
    @FXML
    private TableColumn<Inventory,String> colTotalPrice;
    @FXML
    private TableColumn<Inventory,String> colQtyOnHand;
    @FXML
    private TableColumn<Inventory,String> colReceivedQty;
    @FXML
    private TextField searchProdId;
    @FXML
    private JFXTextField txtProductId;
    @FXML
    private JFXTextField txtProductName;
    @FXML
    private JFXTextField txtQtyOnHand;
    @FXML
    private JFXTextField txtReceivedQty;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtInventoryTotal;


    private final InventoryBo inventoryBo = BoFactory.getInstance().getBo(BoType.INVENTORY);
    private final SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

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



    public void btnSearchOnAction() {
        try {
            Inventory inventory = inventoryBo.search(searchProdId.getText());
            txtProductId.setText(inventory.getProductId());
            txtProductName.setText(inventory.getProductName());
            cmbCategory.setValue(inventory.getCategory());
            cmbSupplier.setValue(inventory.getSupplierId());
            txtQtyOnHand.setText(inventory.getQtyOnHand()+"");
            txtUnitPrice.setText(inventory.getUnitPrice()+"");
            txtInventoryTotal.setText(inventory.getTotalInventoryPrice()+"");
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Invalid product ID.Check the product ID.").show();
        }
    }

    public void btnAddInventoryOnAction() {
        try {
            int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText()) + Integer.parseInt(txtReceivedQty.getText());
            double totalInventoryPrice = qtyOnHand * Double.parseDouble(txtUnitPrice.getText());
            Inventory inventory = new Inventory(
                    null,
                    txtProductId.getText(),
                    txtProductName.getText(),
                    cmbCategory.getValue(),
                    cmbSupplier.getValue(),
                    qtyOnHand,
                    Integer.parseInt(txtReceivedQty.getText()),
                    Double.parseDouble(txtUnitPrice.getText()),
                    totalInventoryPrice
            );
            boolean b = inventoryBo.update(inventory);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Inventory added successfully.").show();
                cleanField();
            }else {
                new Alert(Alert.AlertType.ERROR,"Inventory  not added.").show();
                cleanField();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING,"Data can't bind Check if all the text fields are filled.").show();
        }
    }

    public void btnUpdateOnAction() {
        try {
            int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText()) + Integer.parseInt(txtReceivedQty.getText());
            Inventory inventory = new Inventory(
                    null,
                    txtProductId.getText(),
                    txtProductName.getText(),
                    cmbCategory.getValue(),
                    cmbSupplier.getValue(),
                    qtyOnHand,
                    Integer.parseInt(txtReceivedQty.getText()),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Double.parseDouble(txtInventoryTotal.getText())
            );
            boolean b = inventoryBo.update(inventory);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Inventory updated successfully.").show();
                cleanField();
            }else {
                new Alert(Alert.AlertType.ERROR,"Inventory not updated.").show();
                cleanField();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING,"Data can't bind Check if all the text fields are filled.").show();
        }
    }

    public void btnRemoveOnAction() {
        new Alert(Alert.AlertType.WARNING,"Make sure tou have deleted the specific Product too from the system").show();
        if(!Objects.equals(searchProdId.getText(), "")){
            boolean delete = inventoryBo.delete(txtProductId.getText());
            if(delete){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier deleted success.").show();
                cleanField();
            }else{
                new Alert(Alert.AlertType.WARNING,"Enter a correct Supplier ID.").show();
                cleanField();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Enter the Supplier ID to search the supplier.").show();
        }
    }

    @FXML
    public void btnLoadInventoryTblOnAction(ActionEvent actionEvent) {
        loadTable();
    }

    public void btnGenerateReportOnAction(ActionEvent actionEvent) {
    }

    private void loadTable(){
        ObservableList<Inventory> inventories = inventoryBo.searchAll();
        tblInventory.setItems(inventories);
    }

    private void loadAllSupplierIds(){
        ObservableList<String> allIds = supplierBo.getAllIds();
        cmbSupplier.setItems(allIds);

    }

    private void loadAllCategories(){
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.addAll("Shirt/T shirt","Pants/Jeans","Shorts","Outerwear","Activewear",
                "Dresses","Tops","Bottoms/Skirts","Swimwear","Sleepwear","School Uniforms",
                "Hats","Belts","Handbags","Sunglasses","Wallets","Sneakers/Shoes","Sandals/Slippers",
                "Boots","Heels"
        );
        cmbCategory.setItems(categories);
    }

    @FXML
    public void calTotalInventory(KeyEvent keyEvent) {
        //add inventory on hand to this . some problem has happened inventory data not getting.
        //int inventoryOnHand = inventoryBo.getInventoryOnHand(searchProdId.getText());
        int totalInventory =  Integer.parseInt(txtReceivedQty.getText());
        double inventoryPrice = totalInventory * Double.parseDouble(txtUnitPrice.getText());
        txtInventoryTotal.setText(inventoryPrice+"");
    }

    private void cleanField(){
        searchProdId.setText("");
        txtProductId.setText("");
        txtProductName.setText("");
        cmbSupplier.setValue("Select Supplier");
        cmbCategory.setValue("Select Category");
        txtQtyOnHand.setText("");
        txtReceivedQty.setText("");
        txtUnitPrice.setText("");
        txtInventoryTotal.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colReceivedQty.setCellValueFactory(new PropertyValueFactory<>("receivedQty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalInventoryPrice"));
        loadAllCategories();
        loadAllSupplierIds();

    }


}
