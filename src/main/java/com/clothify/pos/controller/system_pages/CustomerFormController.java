package com.clothify.pos.controller.system_pages;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.CustomerBo;
import com.clothify.pos.dto.Customer;
import com.clothify.pos.util.BoType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFormController implements Initializable {
    @FXML
    public TextField searchContact;
    @FXML
    private DatePicker dateDob;
    @FXML
    private AnchorPane customerPane;
    @FXML
    private Text lblCustomerId;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private Text lblSearchCustId;
    @FXML
    private Text lblCustName;
    @FXML
    private Text lblCustContact;
    @FXML
    private Text lblCustEmail;
    @FXML
    private Text lblCustAddress;
    @FXML
    private Text lblDob;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtCustomerId;

    private final CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    @FXML
    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/order_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);
    }

    @FXML
    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/supplier_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);

    }

    @FXML
    public void btnInventoryOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/inventory_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);
    }

    @FXML
    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/product_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);
    }

    @FXML
    public void btnSalesOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/sales_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);
    }

    @FXML
    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/employee_page.fxml")).load();
        customerPane.getChildren().clear();
        customerPane.getChildren().add(parent);
    }

    @FXML
    public void btnCustomerOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION,"You are already on the Customer Page.");
    }

    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) {
        try {
            Customer customer = customerBo.search(searchContact.getText());
            txtCustomerId.setText(customer.getCustomerId());
            txtName.setText(customer.getCustomerName());
            txtAddress.setText(customer.getAddress());
            txtContact.setText(customer.getContact());
            txtEmail.setText(customer.getEmail());
            lblSearchCustId.setText(customer.getCustomerId());
            lblCustName.setText(customer.getCustomerName());
            lblCustAddress.setText(customer.getAddress());
            lblCustContact.setText(customer.getContact());
            lblCustEmail.setText(customer.getEmail());
            lblCustomerId.setText(customer.getCustomerId());
            lblDob.setText(customer.getDob()+"");
            LocalDate localDate = customer.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            dateDob.setValue(localDate);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Customer is not Registered. Please Register and try again");
            btnSearch.autosize();
        }
    }

    @FXML
    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            date = format.parse(dateDob.getValue().toString());
            Customer customer = new Customer(
                    lblCustomerId.getText(),
                    txtName.getText(),
                    txtContact.getText(),
                    txtEmail.getText(),
                    date,
                    txtAddress.getText()
            );
            boolean b = customerBo.persist(customer);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added !").show();
                clearFields();
                generateID();
            }else {
                new Alert(Alert.AlertType.ERROR, "Customer not Added !").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING,"Check the right data and try again");
            clearFields();
        }
    }

    @FXML
    public void btnRemoveOnAction(ActionEvent actionEvent) {
        boolean delete = customerBo.delete(txtCustomerId.getText());
        if (delete){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted successfully");
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Error in deleting customer. Try again");
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            date = format.parse(dateDob.getValue().toString());
            Customer customer = new Customer(
                    lblSearchCustId.getText(),
                    txtName.getText(),
                    txtContact.getText(),
                    txtEmail.getText(),
                    date,
                    txtAddress.getText()
            );
            boolean b = customerBo.update(customer);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated !").show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.ERROR, "Customer not Updated. Try again!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING,"Error occurred because of the data inserted");
        }
    }

    private void generateID() {
        long count = customerBo.count();
        if (count == 0) {
            lblCustomerId.setText("C0001");
            txtCustomerId.setText("C0001");
            return; // Return after setting the initial ID
        }

        String latestId = customerBo.getLatestId();
        if (latestId == null || latestId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Can't generate an CustomerId. Contact the development team for assistance.").show();
            return;
        }

        Pattern pattern = Pattern.compile("C(\\d+)");
        Matcher matcher = pattern.matcher(latestId);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            lblCustomerId.setText(String.format("C%04d", number));// Ensure the correct prefix
            txtCustomerId.setText(String.format("C%04d", number));
        } else {
            new Alert(Alert.AlertType.WARNING, "Can't generate an OrderID. Contact the development team for assistance.").show();
        }
    }

    private void clearFields(){
        txtCustomerId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        lblSearchCustId.setText("");
        lblCustName.setText("");
        lblCustAddress.setText("");
        lblCustContact.setText("");
        lblCustEmail.setText("");
        lblCustomerId.setText("");
        lblDob.setText("");
        searchContact.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateID();
    }
}
