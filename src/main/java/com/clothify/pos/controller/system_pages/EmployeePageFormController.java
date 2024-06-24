package com.clothify.pos.controller.system_pages;

import com.clothify.pos.bo.BoFactory;
import com.clothify.pos.bo.custom.EmployeeBo;
import com.clothify.pos.dto.Employee;
import com.clothify.pos.util.BoType;
import com.jfoenix.controls.JFXTextField;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeePageFormController implements Initializable {

    @FXML
    private AnchorPane employeePane;
    @FXML
    private TextField txtEmployeeId;
    @FXML
    private Text lblEmployeeName;
    @FXML
    private Text lblEmployeeAge;
    @FXML
    private Text lblNic;
    @FXML
    private Text lblContact;
    @FXML
    private Text lblAddress;
    @FXML
    private Text lblRecruited;
    @FXML
    private JFXTextField txtEmployeeName;
    @FXML
    private JFXTextField txtEmployeeAge;
    @FXML
    private JFXTextField txtNic;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private DatePicker txtDate;
    @FXML
    private Text lblEmployeeId;

    private final EmployeeBo employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    public void btnOrderOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/order_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnProductOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/product_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnInventoryOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/inventory_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnSupplierOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/supplier_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnEmployeeOnAction()  {
       new Alert(Alert.AlertType.INFORMATION,"You are already on the Employee Page");
    }

    public void btnSalesOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/sales_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnCustomerOnAction() throws IOException {
        Parent parent = new FXMLLoader(getClass().getResource("/view/system_pages/customer_page.fxml")).load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(parent);
    }

    public void btnSearchOnAction() {
        try {
            Employee employee = employeeBo.search(txtEmployeeId.getText());
            txtEmployeeName.setText(employee.getEmployeeName());
            txtEmployeeAge.setText(employee.getAge()+"");
            txtAddress.setText(employee.getAddress());
            txtContact.setText(employee.getContact());
            txtNic.setText(employee.getNationalId());
            lblEmployeeId.setText(employee.getEmployeeId());
            lblEmployeeName.setText(employee.getEmployeeName());
            lblNic.setText(employee.getNationalId());
            lblAddress.setText(employee.getAddress());
            lblEmployeeAge.setText(employee.getAge()+"");
            lblContact.setText(employee.getContact());
            lblRecruited.setText(employee.getRecruited()+"");
            LocalDate localDate = employee.getRecruited().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            txtDate.setValue(localDate);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"There is no EmployeeId on the system similar to that.Add the Employee and try again.");
        }
    }

    public void btnAddOnAction() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            date= format.parse(txtDate.getValue().toString());
            Employee employee = new Employee(
                    lblEmployeeId.getText(),
                    txtEmployeeName.getText(),
                    Integer.parseInt(txtEmployeeAge.getText()),
                    txtNic.getText(),
                    txtContact.getText(),
                    txtAddress.getText(),
                    date
            );
            boolean b = employeeBo.persist(employee);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee added success");
                cleanFields();
                generateID();
            }else{
                new Alert(Alert.AlertType.ERROR,"Employee not added.");
                cleanFields();
            }

        } catch (ParseException e) {
            new Alert(Alert.AlertType.WARNING,"Error when data binding to database.");
        }
    }

    public void btnUpdateOnAction() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            date= format.parse(txtDate.getValue().toString());
            Employee employee = new Employee(
                    lblEmployeeId.getText(),
                    txtEmployeeName.getText(),
                    Integer.parseInt(txtEmployeeAge.getText()),
                    txtNic.getText(),
                    txtContact.getText(),
                    txtAddress.getText(),
                    date
            );
            boolean b = employeeBo.update(employee);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee updated success");
                cleanFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"Employee not updated.");
                cleanFields();
            }

        } catch (ParseException e) {
            new Alert(Alert.AlertType.WARNING,"Error when data binding to database.");
        }
    }

    public void btnRemoveOnAction() {
        boolean delete = employeeBo.delete(txtEmployeeId.getText());
        if(delete){
            new Alert(Alert.AlertType.CONFIRMATION,"Employee deleted success");
            cleanFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Id does not match.Try again");
            cleanFields();
        }

    }

    private void generateID() {
        int count = employeeBo.count();
        if (count == 0) {
            lblEmployeeId.setText("E0001");
            return; // Return after setting the initial ID
        }

        String latestId = employeeBo.getLatestId();
        if (latestId == null || latestId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Can't generate an OrderID. Contact the development team for assistance.").show();
            return;
        }

        Pattern pattern = Pattern.compile("E(\\d+)");
        Matcher matcher = pattern.matcher(latestId);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            lblEmployeeId.setText(String.format("E%04d", number)); // Ensure the correct prefix
        } else {
            new Alert(Alert.AlertType.WARNING, "Can't generate an OrderID. Contact the development team for assistance.").show();
        }
    }

    private void cleanFields(){
        txtEmployeeId.setText("");
        txtEmployeeAge.setText("");
        txtEmployeeName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtNic.setText("");
        lblEmployeeId.setText("");
        lblEmployeeName.setText("");
        lblNic.setText("");
        lblAddress.setText("");
        lblEmployeeAge.setText("");
        lblContact.setText("");
        lblRecruited.setText("");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateID();
    }
}
