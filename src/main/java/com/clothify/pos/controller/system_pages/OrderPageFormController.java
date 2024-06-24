package com.clothify.pos.controller.system_pages;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.CustomerBo;
import com.clothify.pos.bo.custom.InventoryBo;
import com.clothify.pos.bo.custom.OrderBo;
import com.clothify.pos.bo.custom.ProductBo;
import com.clothify.pos.dto.*;
import com.clothify.pos.util.BoType;
import com.jfoenix.controls.JFXButton;
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
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderPageFormController implements Initializable {

    @FXML
    private JFXTextField txtQtyOnHand;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXTextField txtProductName;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private TableView<CartTbl> tblOrder;
    @FXML
    private TableColumn<CartTbl,Double> colUnitPrice;
    @FXML
    private TableColumn<CartTbl,String> colProductId;
    @FXML
    private TableColumn<CartTbl,String> colProductName;
    @FXML
    private TableColumn<CartTbl,Integer> colQty;
    @FXML
    private TableColumn<CartTbl,Double> colAmount;
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
    private JFXComboBox<String> cmbProductId;
    @FXML
    private AnchorPane orderPane;

    private final OrderBo orderBo = BoFactory.getInstance().getBo(BoType.ORDER);
    private final CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    private final ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);
    private final InventoryBo inventoryBo = BoFactory.getInstance().getBo(BoType.INVENTORY);

    private  ObservableList<CartTbl> cartList = FXCollections.observableArrayList();

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

    public void btnCancelOnAction() {
         clearAll();
    }

    public void btnCustomerOrderOnAction() {
        generateID();
        //when the order place change the inventory qty on hand value like qtyOnHand - qty

    }

    public void txtCalculateAmount() {
        int i = Integer.parseInt(txtQty.getText());
        double v = Double.parseDouble(txtUnitPrice.getText());
        double amount = i * v ;
        txtAmount.setText(amount + "");
    }

    public void btnAddToCartOnAction() {
        if(Integer.parseInt(txtQty.getText()) < Integer.parseInt(txtQtyOnHand.getText())){
            CartTbl cartTbl = new CartTbl(
                    cmbProductId.getValue(),
                    txtProductName.getText(),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Double.parseDouble(txtAmount.getText())
            );
            cartList.add(cartTbl);
            tblOrder.setItems(cartList);
            calcNetTotal();
        }else{
            new Alert(Alert.AlertType.ERROR,"You have selected more qty than the stock value.");
        }

    }

    public void calcNetTotal(){
        double ttl=0;
        for (CartTbl cartObj : cartList){
            ttl+=cartObj.getAmount();
        }
        lblTotal.setText(String.valueOf(ttl));
    }

    public void btnSearchOnAction() {
        Customer customer = customerBo.search(txtContact.getText());
        lblCustomerId.setText(customer.getCustomerId());
        lblCustomerName.setText(customer.getCustomerName());
        lblContact.setText(customer.getContact());

    }

    private void generateID() {
        int count = orderBo.count();
        if (count == 0) {
            lblOrderId.setText("O0001");
            return; // Return after setting the initial ID
        }

        String latestId = orderBo.getLatestId();
        if (latestId == null || latestId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Can't generate an OrderID. Contact the development team for assistance.").show();
            return;
        }

        Pattern pattern = Pattern.compile("O(\\d+)");
        Matcher matcher = pattern.matcher(latestId);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            lblOrderId.setText(String.format("O%04d", number)); // Ensure the correct prefix
        } else {
            new Alert(Alert.AlertType.WARNING, "Can't generate an OrderID. Contact the development team for assistance.").show();
        }
    }

    public void clearAll(){
        txtProductName.setText("");
        txtAmount.setText("");
        txtContact.setText("");
        txtQty.setText("");
        lblCustomerId.setText("");
        lblCustomerName.setText("");
        lblContact.setText("");
        lblTotal.setText("");
        tblOrder.getColumns().clear();

    }



    private void loadProductIdValues(){
        cmbProductId.setItems(productBo.getAllProductIds());
    }

    private void setProductDataForLbl(String productId){
        Inventory inventory = inventoryBo.search(productId);
        txtProductName.setText(inventory.getProductName());
        txtUnitPrice.setText(inventory.getUnitPrice()+"");
        txtQtyOnHand.setText(inventory.getQtyOnHand()+"");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateID();
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        loadProductIdValues();
        cmbProductId.getSelectionModel().selectedItemProperty()
                .addListener((observable,oldValue,newValue)->
                setProductDataForLbl(newValue));
    }


}
