package com.clothify.pos.controller.system_pages;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.SupplierBo;
import com.clothify.pos.dto.Supplier;
import com.clothify.pos.util.BoType;
import com.jfoenix.controls.JFXTextField;
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

public class SupplierPageFormController implements Initializable {

    @FXML
    private TextField txtSupplierId;
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
    private TableView<Supplier> tblSupplier;
    @FXML
    private TableColumn<Supplier,String> colSupplierId;
    @FXML
    private TableColumn<Supplier,String> colName;
    @FXML
    private TableColumn<Supplier,String> colProductId;
    @FXML
    private TableColumn<Supplier,String> colProductName;
    @FXML
    private TableColumn<Supplier,String> colContact;
    @FXML
    private TableColumn<Supplier,String> colEmail;
    @FXML
    private TableColumn<Supplier,String> colCompany;
    @FXML
    private Text lblSupplierId;
    @FXML
    private AnchorPane supplierPane;


    private final SupplierBo supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

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

    public void btnAddCustomerOnAction() {
        try {
            Supplier supplier = new Supplier(
                    lblSupplierId.getText(),
                    txtSupplierName.getText(),
                    txtProductId.getText(),
                    txtProductName.getText(),
                    txtContact.getText(),
                    txtEmail.getText(),
                    txtCompany.getText()
            );
            boolean b = supplierBo.persist(supplier);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Added successfully.");
                clearField();
                generateID();

            }else{
                new Alert(Alert.AlertType.ERROR,"Supplier not Added.");
                clearField();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING,"Data can't bind Check if all the text fields are filled.");
        }
    }

    public void btnDeleteSupplierOnAction() {
        if(!Objects.equals(txtSupplierId.getText(), "")){
            boolean delete = supplierBo.delete(txtSupplierId.getText());
            if(delete){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier deleted success.");
                clearField();
            }else{
                new Alert(Alert.AlertType.WARNING,"Enter a correct Supplier ID.");
                clearField();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Enter the Supplier ID to search the supplier.");
        }

    }

    public void btnUpdateSupplierOnAction() {
        try {
            Supplier supplier = new Supplier(
                    txtSupplierId.getText(),
                    txtSupplierName.getText(),
                    txtProductId.getText(),
                    txtProductName.getText(),
                    txtContact.getText(),
                    txtEmail.getText(),
                    txtCompany.getText()
            );
            boolean b = supplierBo.update(supplier);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Updated successfully.");
                clearField();
            }else{
                new Alert(Alert.AlertType.ERROR,"Supplier not Updated.");
                clearField();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING,"Data can't bind Check if all the text fields are filled.");
        }
    }


    public void btnSearchOnAction() {
        if(!Objects.equals(txtSupplierId.getText(), "")){
            try {
                Supplier supplier = supplierBo.search(txtSupplierId.getText());
                txtSupplierName.setText(supplier.getSupplierName());
                txtProductId.setText(supplier.getProductId());
                txtProductName.setText(supplier.getProductName());
                txtContact.setText(supplier.getContact());
                txtEmail.setText(supplier.getEmail());
                txtCompany.setText(supplier.getCompany());
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"There is no supplier related to the ID. Add the supplier to the system");
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Enter the Supplier ID to search the supplier.");
        }

    }

    private void loadTable(){
        ObservableList<Supplier> suppliers = supplierBo.searchAll();
        tblSupplier.setItems(suppliers);
    }

    private void generateID() {
        int count = supplierBo.count();
        if (count == 0) {
            lblSupplierId.setText("S0001");
            return; // Return after setting the initial ID
        }

        String latestId = supplierBo.getLatestId();
        if (latestId == null || latestId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Can't generate an OrderID. Contact the development team for assistance.").show();
            return;
        }

        Pattern pattern = Pattern.compile("S(\\d+)");
        Matcher matcher = pattern.matcher(latestId);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            lblSupplierId.setText(String.format("S%04d", number)); // Ensure the correct prefix
        } else {
            new Alert(Alert.AlertType.WARNING, "Can't generate an OrderID. Contact the development team for assistance.").show();
        }
    }

    private void clearField(){
        txtProductId.setText("");
        txtProductName.setText("");
        txtSupplierId.setText("");
        txtSupplierName.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtCompany.setText("");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateID();
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        loadTable();

    }

    public void btnGenerateReportOnAction(ActionEvent actionEvent) {
    }
}
