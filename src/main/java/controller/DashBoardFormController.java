package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class DashBoardFormController {

    @FXML
    private AnchorPane DashBoardPane;

    @FXML
    void ManageAccountsBtnOnAction(ActionEvent event) {

    }

    @FXML
    void ManageItemsBtnOnAction(ActionEvent event) {

    }

    @FXML
    void ManageReportsBtnOnAction(ActionEvent event) {

    }

    @FXML
    void OrderDetailsBtnOnAction(ActionEvent event) {

    }

    @FXML
    void PlaceOrderBtnOnAction(ActionEvent event) {

    }

    public void PlaceOrderBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) DashBoardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void OrderDetailsBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) DashBoardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/OrderDetailsForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ManageItemsBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) DashBoardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ManageItemsForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ManageReportsBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) DashBoardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ManageReportsForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ManageAccountsBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) DashBoardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterAccountsForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RegisterAccountsBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) DashBoardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterAccountsForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CreateAccountsBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) DashBoardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CreateAccountsForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backButtonOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) DashBoardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void CustomerBtnOnAction(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) DashBoardPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
