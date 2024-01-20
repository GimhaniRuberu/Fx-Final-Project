package controller;

import Bo.BoFactory;
import Bo.custom.ChangeBo;
import Bo.custom.ItemBo;
import Bo.custom.impl.ChangeBoImpl;
import Bo.custom.impl.ItemBoImpl;
import Dao.util.BoType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDto;
import dto.tm.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public TableColumn option;

    private ItemBo itemBo = new ItemBoImpl();

    public void initialize(){
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        option.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadItemTable();

        txtCategory.getItems().addAll("Electrical", "Electronic");

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((ItemTm) newValue);
        });
    }

    private void loadItemTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

        try {
            List<ItemDto> dtoList  = itemBo.allItem();
            for (ItemDto dto:dtoList) {
                Button btn = new Button("Delete");
                ItemTm c = new ItemTm(
                        dto.getCode(),
                        dto.getCategory(),
                        dto.getName()
                );

                btn.setOnAction(actionEvent -> {
                    deleteItem(c.getCode());
                });

                tmList.add(c);
            }
            tblItem.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(ItemTm newValue) {
        if (newValue != null) {
            txtCode.setEditable(false);
            txtCode.setText(newValue.getCode());
            txtCategory.setValue(newValue.getCategory().toString());
            txtName.setText(newValue.getName());
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        ItemDto dto = new ItemDto(txtCode.getText(),
                txtCategory.getValue().toString(),
                txtName.getText()
        );

        try {
            boolean isUpdated = itemBo.updateItem(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Item "+dto.getCode()+" Updated!").show();
                loadItemTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnSave(ActionEvent actionEvent) {
        ItemDto dto = new ItemDto(txtCode.getText(),
                txtCategory.getValue().toString(),
                (txtName.getText())
        );

        try {
            boolean isSaved = itemBo.saveItem(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
                loadItemTable();
                clearFields();
            }

        } catch (SQLIntegrityConstraintViolationException ex){
            new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
   }

    private void clearFields() {
        tblItem.refresh();
        txtName.clear();
        txtCategory.setValue(null);
        txtCode.clear();
        txtCode.setEditable(true);
    }

//    public void btnDelete(ActionEvent actionEvent) {
//        ItemDto dto = new ItemDto(txtCode.getText(),
//                txtCategory.getValue().toString(),
//                (txtName.getText())
//        );
//        try {
//            boolean isDeleted = itemBo.deleteItem(dto);
//            if (isDeleted){
//                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
//                loadItemTable();
//                clearFields();
//            }else{
//                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
private void deleteItem(String code) {

    try {
        boolean isDeleted = itemBo.deleteItem(code);
        if (isDeleted){
            new Alert(Alert.AlertType.INFORMATION,"Item Deleted!").show();
            loadItemTable();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
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
