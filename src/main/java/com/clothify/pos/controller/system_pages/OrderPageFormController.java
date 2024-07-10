package com.clothify.pos.controller.system_pages;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.*;
import com.clothify.pos.dto.*;
import com.clothify.pos.util.BoType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import jakarta.mail.MessagingException;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;
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
    private TableColumn<CartTbl, Double> colUnitPrice;
    @FXML
    private TableColumn<CartTbl, String> colProductId;
    @FXML
    private TableColumn<CartTbl, String> colProductName;
    @FXML
    private TableColumn<CartTbl, Integer> colQty;
    @FXML
    private TableColumn<CartTbl, Double> colAmount;
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
    private final OrderDetailsBo orderDetailsBo = BoFactory.getInstance().getBo(BoType.OrderDetail);

    private ObservableList<CartTbl> cartList = FXCollections.observableArrayList();

    public void btnOrderOnAction() {
        new Alert(Alert.AlertType.INFORMATION, "You are already on the Order Page").show();
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



    public void btnReceiptOnAction() throws IOException {
        String latestId = orderBo.getLatestId();
        File file = new File("/Users/chanithwijekoon/JavaFx Projects/Clothify_Store/src/main/resources/reportPdf/ordersPdf/"+latestId);
        if (file.exists()){
            if (Desktop.isDesktopSupported()){
                Desktop.getDesktop().open(file);
            }else {
                new Alert(Alert.AlertType.ERROR,"Report Not Found..!!!").show();
            }
       }
    }

    public void btnCancelOnAction() {
        clearAll();
    }
    public void btnCustomerOrderOnAction() {
        //when the order place change the inventory qty on hand value like qtyOnHand - qty

        try {
            List<OrderDetail> orderDetailList = new ArrayList<>();
            List<CartTbl> reportCartList = new ArrayList<>();
            Date currentDate = new Date();
            Order order = new Order(
                    lblOrderId.getText(),
                    lblCustomerId.getText(),
                    lblCustomerName.getText(),
                    lblContact.getText(),
                    currentDate,
                    orderDetailList,
                    Double.parseDouble(lblTotal.getText()),
                    Boolean.TRUE
            );
            boolean orderDetailsAdded = false;
            boolean inventoryAdded = false;
            for (CartTbl cartTbl : cartList) {
                OrderDetail orderDetail = new OrderDetail(
                        null,
                        cartTbl.getProductId(),
                        lblCustomerId.getText(),
                        cartTbl.getQty(),
                        cartTbl.getAmount()
                );
                orderDetailList.add(orderDetail);
                reportCartList.add(cartTbl);
                orderDetailsAdded = orderDetailsBo.persist(orderDetail);
                if (orderDetailsAdded) {
                    inventoryAdded=inventoryBo.updateStock(cartTbl.getProductId(), cartTbl.getQty());
                }
            }
            System.out.println(reportCartList);
            JasperDesign design = JRXmlLoader.load(new File("/Users/chanithwijekoon/JavaFx Projects/Clothify_Store/src/main/resources/reports/Invoice.jrxml"));

            HashMap<String, Object> parameters = new HashMap<>();
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            Date date = new Date();

            String savePath = "/Users/chanithwijekoon/JavaFx Projects/Clothify_Store/src/main/resources/reportPdf/ordersPdf/" + lblOrderId.getText() + ".pdf";

            // Ensure the save directory exists
            File saveDir = new File(savePath).getParentFile();
            if (!saveDir.exists()) {
                if (!saveDir.mkdirs()) {
                    throw new RuntimeException("Failed to create directories for path: " + savePath);
                }
            }

            Customer customer = customerBo.search(lblContact.getText());
            parameters.put("customerName",customer.getCustomerName());
            parameters.put("email",customer.getEmail());
            parameters.put("address",customer.getAddress());
            parameters.put("contact",customer.getContact());
            parameters.put("OrderId",lblOrderId.getText());
            parameters.put("total",Double.parseDouble(lblTotal.getText()));
            parameters.put("date",date);
            parameters.put("invoice","##"+lblOrderId.getText());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportCartList);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperViewer.viewReport(jasperPrint,false);
            JasperExportManager.exportReportToPdfFile(jasperPrint,savePath);

            boolean b = orderBo.persist(order);
            if (b){
                File file = new File(savePath);
                orderBo.sendEmail(customer.getEmail(),
                        "Your order has been placed success. Here's details of the order",
                        file);
                new Alert(Alert.AlertType.CONFIRMATION,"Email to the customer sent Success.").show();
            }
            if(b && orderDetailsAdded && inventoryAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Order Added successfully").show();
                generateID();
                clearAll();
            }else{
                new Alert(Alert.AlertType.ERROR,"Order not Added").show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Error in data binding check the details or contact.").show();
            clearAll();
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnAddToCartOnAction() {
        if (Integer.parseInt(txtQty.getText()) < Integer.parseInt(txtQtyOnHand.getText())) {
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
        } else {
            new Alert(Alert.AlertType.ERROR, "You have selected more qty than the stock value.");
        }

    }

    @FXML
    public void txtCalculateAmount(javafx.scene.input.KeyEvent keyEvent) {

        int i = Integer.parseInt(txtQty.getText());
        double v = Double.parseDouble(txtUnitPrice.getText());
        double amount = i * v;
        txtAmount.setText(amount + "");

    }

    private void calcNetTotal() {
        double ttl = 0;
        for (CartTbl cartObj : cartList) {
            ttl += cartObj.getAmount();
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
        long count = orderBo.count();
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

    public void clearAll() {
        txtProductName.setText("");
        txtQtyOnHand.setText("");
        txtUnitPrice.setText("");
        //cmbProductId.setValue("");
        txtAmount.setText("");
        txtContact.setText("");
        txtQty.setText("");
        lblCustomerId.setText("");
        lblCustomerName.setText("");
        lblContact.setText("");
        lblTotal.setText("");
        tblOrder.getColumns().clear();

    }


    private void loadProductIdValues() {
        cmbProductId.setItems(productBo.getAllProductIds());
    }

    private void setProductDataForLbl(String productId) {
        Inventory inventory = inventoryBo.search(productId);
        txtProductName.setText(inventory.getProductName());
        txtUnitPrice.setText(inventory.getUnitPrice() + "");
        txtQtyOnHand.setText(inventory.getQtyOnHand() + "");

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
                .addListener((observable, oldValue, newValue) ->
                        setProductDataForLbl(newValue));
    }


}
