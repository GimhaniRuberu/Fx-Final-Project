package controller;

import Bo.custom.OrderBo;
import Bo.custom.PlaceOrderBo;
import Bo.custom.impl.OrderBoImpl;
import Bo.custom.impl.PlaceOrderBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import dto.tm.OrderTm;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderDetailsFormController {
    public AnchorPane pane;
    public AnchorPane pane1;
    public JFXComboBox comOrdId;
    public JFXComboBox comAdditionalItem;
    public JFXTextField txtAddionalFee;
    public JFXTextField txtCategory;
    public JFXTextField txtDes;
    public Label lblDate;
    public TableColumn colOrdId;
    public TableColumn colCusId;
    public TableColumn colAddionalItem;
    public TableColumn colCategory;
    public TableColumn colStatus;
    public TableColumn colDate;
    public TableColumn colTotalCost;
    public TableColumn colAdvance;
    public TableColumn colFinalCost;
    public JFXTextField txtStatus;
    public Label lblTime;
    public TableColumn colCusName;
    public TableColumn colAdditionalItemCost;
    public Label lblCusName;
    public Label lblTotalCost;
    public Label lblAdvance;
    public Label lblFinalCost;
    public TableView tblOrd;
    public TreeTableColumn colOption;
    public Label lblOrdId;

    private double totalcost;
    private double finalcost;

    private List<PlaceOrderDto> placeOrder;
    private OrderBo orderBo = new OrderBoImpl();
    private PlaceOrderBo placeOrderBo = new PlaceOrderBoImpl();

    private ObservableList<OrderTm> tmList = FXCollections.observableArrayList();

    public void initialize(){
        colOrdId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colAddionalItem.setCellValueFactory(new PropertyValueFactory<>("additionalItem"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        colAdditionalItemCost.setCellValueFactory(new PropertyValueFactory<>("additionalItemFee"));
        colFinalCost.setCellValueFactory(new PropertyValueFactory<>("finalCost"));
        loadTblOrd();


        comAdditionalItem.getItems().addAll("Electrical", "Electronic");

        tblOrd.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((OrderTm) newValue);
        });

        try {
            placeOrder = placeOrderBo.allOrders();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setOrderId();
        calculateTime();
    }

    private void loadTblOrd() {
        ObservableList<OrderTm> tmList = FXCollections.observableArrayList();
        try {
            List<OrderDto> dtoList  = orderBo.allOrders();
            for (OrderDto dto:dtoList) {
                OrderTm tm = new OrderTm(
                        dto.getOrderId(),
                        dto.getCustomerId(),
                        dto.getAdditionalItem(),
                        dto.getCategory(),
                        dto.getStatus(),
                        dto.getDate(),
                        dto.getTotalCost(),
                        dto.getAdvance(),
                        dto.getAdditionalItemFee(),
                        dto.getFinalCost()
                );
                tmList.add(tm);
            }

            tblOrd.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setOrderId() {
        try {
            lblOrdId.setText(orderBo.generateId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        OrderDto dto = new OrderDto(
                lblOrdId.getText(),
                lblCusName.getText(),
                comAdditionalItem.getValue().toString(),
                txtCategory.getText(),
                txtStatus.getText(),
                lblDate.getText(),
                Double.parseDouble(lblTotalCost.getText()),
                Double.parseDouble(lblAdvance.getText()),
                Double.parseDouble(txtAddionalFee.getText()),
                Double.parseDouble(lblFinalCost.getText())
        );

        try {
            boolean isUpdated = orderBo.updateOrder(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Order "+dto.getOrderId()+" Updated!").show();
                loadTblOrd();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void setData(OrderTm newValue) {
        if (newValue != null) {
            lblOrdId.setText(newValue.getOrderId());
            lblCusName.setText(newValue.getCusName());
            comAdditionalItem.setValue(newValue.getAdditionalItem().toString());
            txtCategory.setText(newValue.getCategory());
            txtStatus.setText(newValue.getStatus());
            lblDate.setText(newValue.getDate());
            lblTotalCost.setText(String.valueOf(newValue.getTotalCost()));
            lblAdvance.setText(String.valueOf(newValue.getAdvance()));
            txtAddionalFee.setText(String.valueOf(newValue.getAdditionalItemFee()));
            lblFinalCost.setText(String.valueOf(newValue.getFinalCost()));
        }
    }

    private void clearFields() {
        tblOrd.refresh();
        lblOrdId.setText(null);
        lblCusName.setText(null);
        comAdditionalItem.setValue(null);
        txtCategory.clear();
        txtStatus.clear();
        lblDate.setText(null);
        lblTotalCost.setText(null);
        lblAdvance.setText(null);
        txtAddionalFee.clear();
        lblFinalCost.setText(null);
    }

    public void btnBack(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }    }

    public void btnProcessing(ActionEvent actionEvent) {
        txtStatus.setText("Processing");
    }
    public void btnPrepared(ActionEvent actionEvent) {
        txtStatus.setText("Preparing");
    }

    public void btnCompleted(ActionEvent actionEvent) {
        txtStatus.setText("Completed");
    }

    private void calculateTime() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    public void feeAct(KeyEvent keyEvent) {
        try{
            totalcost=4000+Double.parseDouble(txtAddionalFee.getText());
                    lblTotalCost.setText(String.valueOf(totalcost));
            finalcost=totalcost-Double.parseDouble(lblAdvance.getText());
                    lblFinalCost.setText(String.valueOf(finalcost));
        } catch (NumberFormatException nfe){
            System.out.println(nfe);
        }
    }
}
