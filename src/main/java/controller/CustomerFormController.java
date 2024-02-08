package controller;

import Bo.custom.CustomerBo;
import Bo.custom.impl.CustomerBoImpl;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDto;
import dto.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {
    public AnchorPane pane;
    public JFXTextField txtCusId;
    public JFXTextField txtContactNum;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public TableView tblCustomer;
    public TableColumn colCusId;
    public TableColumn colName;
    public TableColumn colContactNum;
    public TableColumn colEmail;
    public TableColumn option;

    private CustomerBo customerBo = new CustomerBoImpl();

    private ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

    public void initialize(){
        colCusId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colContactNum.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        option.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomerTable();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((CustomerTm) newValue);
        });
    }

    private void loadCustomerTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> dtoList  = customerBo.allCustomers();
            for (CustomerDto dto:dtoList) {
                Button btn= new Button("Delete");
                CustomerTm tm = new CustomerTm(
                        dto.getCustomerId(),
                        dto.getName(),
                        dto.getContactNo(),
                        dto.getEmail(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteCustomer(tm.getCustomerId());
                });
                tmList.add(tm);
            }
            tblCustomer.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(CustomerTm newValue) {
        if (newValue != null) {
            txtCusId.setEditable(false);
            txtCusId.setText(newValue.getCustomerId());
            txtName.setText(newValue.getName());
            txtContactNum.setText(newValue.getContactNo());
            txtEmail.setText(newValue.getEmail());
        }
    }

    private void clearFields() {
        tblCustomer.refresh();
        txtEmail.clear();
        txtContactNum.clear();
        txtName.clear();
        txtCusId.clear();
        txtCusId.setEditable(true);
    }

    public void btnUpdate(ActionEvent actionEvent) {
        CustomerDto dto = new CustomerDto(
                txtCusId.getText(),
                txtName.getText(),
                txtContactNum.getText(),
                txtEmail.getText()
        );

        try {
            boolean isUpdated = customerBo.updateCustomer(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Customer "+dto.getCustomerId()+" Updated!").show();
                loadCustomerTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnSave(ActionEvent actionEvent) {
        List<CustomerDto> list = new ArrayList<>();
        for (CustomerTm tm:tmList) {
            list.add(new CustomerDto(
                    tm.getCustomerId(),
                    tm.getName(),
                    tm.getContactNo(),
                    tm.getEmail()
            ));
        }

        CustomerDto dto = new CustomerDto(
                txtCusId.getText(),
                txtName.getText(),
                txtContactNum.getText(),
                txtEmail.getText()

        );

        try {
            boolean isSaved = customerBo.saveCustomer(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
                loadCustomerTable();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchBtn(ActionEvent actionEvent) {
    }

    public void btnBack(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteCustomer(String code) {

        try {
            boolean isDeleted = customerBo.deleteCustomer(code);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                loadCustomerTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
