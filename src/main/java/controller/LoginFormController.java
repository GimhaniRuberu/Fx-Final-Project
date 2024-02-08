package controller;

import Bo.custom.CreateUserBo;
import Bo.custom.impl.CreateUserBoImpl;
import com.jfoenix.controls.JFXTextField;
import dto.CreateUserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {

    public JFXTextField txtEmail;
    public JFXTextField TxtPassword;
    @FXML
    private AnchorPane pane;
    public JFrame l;

    @FXML
    private ImageView userimg;

    private CreateUserBo createUserBo = new CreateUserBoImpl();
    private List<CreateUserDto> users;

    public void LoginBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        
        String email = txtEmail.getText();
        String password = TxtPassword.getText();
        users = createUserBo.allUsers();


        if (isValidEmail(email)) {
            if (isValidPass(password)) {

//
                    Stage stage = (Stage) pane.getScene().getWindow();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
                        stage.centerOnScreen();
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password!").show();
                txtEmail.clear();
                TxtPassword.clear();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Email!").show();
            txtEmail.clear();
            TxtPassword.clear();
        }
    }
    private boolean isValidEmail(String email) {
        boolean isExist = false;
        for (CreateUserDto dto:users) {
            if (dto.getEmail().equals(email)){
                isExist = true;
            }else {
                isExist = false;
            }
        }
        return isExist;
    }

    private boolean isValidPass(String Password) {
        boolean isExist = false;
        for (CreateUserDto dto:users) {
            if (dto.getPassword().equals(Password)){
                isExist = true;
            }else {
                isExist = false;
            }
        }
        return isExist;
    }




    public void lblChangePassword(MouseEvent mouseEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ChangePasswordForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
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
