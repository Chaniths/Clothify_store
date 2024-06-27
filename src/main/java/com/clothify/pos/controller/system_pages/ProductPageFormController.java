package com.clothify.pos.controller.system_pages;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.ProductBo;
import com.clothify.pos.bo.custom.SupplierBo;
import com.clothify.pos.dto.Product;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductPageFormController implements Initializable {
    @FXML
    public AnchorPane productPane;
    @FXML
    private Text lblProductId;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private TableView<Product> tblProduct;
    @FXML
    private TableColumn<Product,String> colProductId;
    @FXML
    private TableColumn<Product,String> colProductName;
    @FXML
    private TableColumn<Product,String> colSupplierId;
    @FXML
    private TableColumn<Product,String> colUnitPrice;
    @FXML
    private TableColumn<Product,String> colCategory;
    @FXML
    private TableColumn<Product,String> colQty;
    @FXML
    private JFXComboBox<String> cmbCategory;
    @FXML
    private TextField txtProductId;
    @FXML
    private JFXComboBox<String> cmbSupplier;
    @FXML
    private JFXTextField txtProductName;

   private final ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
   private final SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

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


    public void btnAddProductOnAction() {
        try {
            Product product = new Product(
                    lblProductId.getText(),
                    txtProductName.getText(),
                    cmbSupplier.getValue(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    cmbCategory.getValue(),
                    Integer.parseInt(txtQty.getText())
            );

            boolean b = productBo.persist(product);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Product Successfully added.");
                cleanFields();
                generateID();
            }else {
                new Alert(Alert.AlertType.ERROR,"Product not  added.Check if all the text fields are filled.");
                cleanFields();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING,"Data can't bind Check if all the text fields are filled.");
        }

    }

    public void btnRemoveOnAction() {
        if(!Objects.equals(txtProductId.getText(), "")){
            boolean delete = productBo.delete(txtProductId.getText());
            if(delete){
                new Alert(Alert.AlertType.CONFIRMATION,"Product deleted Success.");
                cleanFields();
            }else {
                new Alert(Alert.AlertType.ERROR,"Product not deleted. Check the ID and try again.");
                cleanFields();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Enter the productId before deleting.");
        }

    }

    public void btnUpdateOnAction() {
        try {
            Product product = new Product(
                    txtProductId.getText(),
                    txtProductName.getText(),
                    cmbSupplier.getValue(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    cmbCategory.getValue(),
                    Integer.parseInt(txtQty.getText())
            );

            boolean b = productBo.update(product);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Product Successfully updated.");
                cleanFields();
            }else {
                new Alert(Alert.AlertType.ERROR,"Product not updated.");
                cleanFields();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING,"Data can't bind Check if all the text fields are filled.");
        }
    }

    public void btnSearchOnAction() {
        if (!Objects.equals(txtProductId.getText(), "")) {
            Product product = productBo.search(txtProductId.getText());
            txtProductName.setText(product.getProductName());
            cmbCategory.setValue(product.getCategory());
            cmbSupplier.setValue(product.getSupplierId());
            txtQty.setText(product.getQty()+"");
            txtUnitPrice.setText(product.getUnitPrice()+"");
        }else{
            new Alert(Alert.AlertType.WARNING,"Enter the Product ID to search.");
        }


    }

    public void btnGenerateReport(ActionEvent actionEvent) {
    }

    private void cleanFields(){
        txtProductId.setText("");
        txtProductName.setText("");
        txtQty.setText("");
        txtUnitPrice.setText("");
        cmbSupplier.setValue("");
        cmbSupplier.setValue("");

    }

    private void generateID() {
        long count = productBo.count();
        if (count == 0) {
            lblProductId.setText("P0001");
            return; // Return after setting the initial ID
        }

        String latestId = productBo.getLatestId();
        if (latestId == null || latestId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Can't generate an OrderID. Contact the development team for assistance.").show();
            return;
        }

        Pattern pattern = Pattern.compile("P(\\d+)");
        Matcher matcher = pattern.matcher(latestId);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            lblProductId.setText(String.format("P%04d", number)); // Ensure the correct prefix
        } else {
            new Alert(Alert.AlertType.WARNING, "Can't generate an OrderID. Contact the development team for assistance.").show();
        }
    }

    private void loadTable(){
        ObservableList<Product> products = productBo.searchAll();
        tblProduct.setItems(products);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateID();
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        loadAllCategories();
        loadAllSupplierIds();
        loadTable();

    }
}
