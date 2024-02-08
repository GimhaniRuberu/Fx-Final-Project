package controller;

import Bo.custom.ChangeBo;
import Bo.custom.RegisterBo;
import Bo.custom.impl.ChangeBoImpl;
import Bo.custom.impl.RegisterBoImpl;
import com.jfoenix.controls.JFXTextField;
import dto.ChangeDto;
import dto.RegisterDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ChangePasswordFormController {

    public AnchorPane pane;
    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtChangePassword;

    @FXML
    private JFXTextField txtConfirmPassword;

    private ChangeBo changeBo = new ChangeBoImpl();

    public void SubmitBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        System.out.println(txtEmail.getText());
        System.out.println(txtChangePassword.getText());
        System.out.println(txtConfirmPassword.getText());
        ChangeDto dto = new ChangeDto(
                txtEmail.getText(),
                txtChangePassword.getText(),
                txtConfirmPassword.getText()
        );

        boolean isSaved = changeBo.saveChange(dto);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION, "Success!").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
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
