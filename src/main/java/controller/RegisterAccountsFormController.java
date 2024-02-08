package controller;

import Bo.custom.RegisterBo;
import Bo.custom.impl.RegisterBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RegisterDto;
import dto.tm.RegisterTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterAccountsFormController {

    public JFXTextField txtUserName;
    public JFXTextField txtEmail;
    public JFXTextField txtContactNum;
    public JFXComboBox txtJobRole;
    public ImageView userImg;
    public Label lblRegister;
    @FXML
    private AnchorPane Pane;

    private RegisterBo registerBo = new RegisterBoImpl();

    private ObservableList<RegisterTm> tmList = FXCollections.observableArrayList();

    public void initialize() {
        txtJobRole.getItems().addAll("Employee","Manager","Owner");
    }
    public void RegisterBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        RegisterDto dto = new RegisterDto(
                txtEmail.getText(),
                txtContactNum.getText(),
                txtJobRole.getValue().toString(),
                txtUserName.getText()

        );

        boolean isSaved = registerBo.saveOrder(dto);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION, "Registered!").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) Pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
