package controller;

import Bo.BoFactory;
import Dao.util.BoType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ManageItemsFormController {
    public JFXComboBox txtCategory;
    public JFXTextField txtCode;
    public JFXTextField txtName;
    public TableView tblItem;
    public TableColumn colCategory;
    public TableColumn colCode;
    public TableColumn colName;
    public Label lblItem;
    public AnchorPane pane;



    public void btnUpdate(ActionEvent actionEvent) {
//        ItemDto dto = new ItemDto(txtId.getText(),
//                txtName.getText(),
//                txtAddress.getText(),
//                Double.parseDouble(txtSalary.getText())
//        );
//
//        try {
//            boolean isUpdated = customerBo.updateCustomer(dto);
//            if (isUpdated){
//                new Alert(Alert.AlertType.INFORMATION,"Customer "+dto.getId()+" Updated!").show();
//                loadCustomerTable();
//                clearFields();
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void btnSave(ActionEvent actionEvent) {
//        ItemDto dto = new ItemDto(txtCode.getText(),
//                txtCategory.getValue().toString(),
//                (txtCode.getText()),
//                (txtName.getText())
//        );
//
//        try {
//            boolean isSaved = itemBo.saveItem(dto);
//            if (isSaved){
//                new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
////                loadItems();
////                clearFields();
//            }
//
//        } catch (SQLIntegrityConstraintViolationException ex){
//            new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
   }

    public void btnDelete(ActionEvent actionEvent) {
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
}
