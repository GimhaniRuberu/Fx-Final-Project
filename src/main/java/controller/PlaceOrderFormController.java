package controller;

import Bo.custom.*;
import Bo.custom.impl.*;
import Dao.custom.ItemDao;
import Dao.custom.impl.ItemDaoImpl;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dto.*;
import dto.tm.PlaceOrderTm;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFormController {
    public JFXComboBox comEmail;
    public JFXComboBox comCusId;
    public JFXComboBox comItemCode;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtCategory;
    public JFXTextField txtDes;
    public Label lblTime;
    public JFXTextField txtName;
    public JFXTreeTableView tblPlaceOrd;
    public TreeTableColumn colEmail;
    public TreeTableColumn colItemCode;
    public TreeTableColumn colCategory;
    public TreeTableColumn colDes;
    public TreeTableColumn colCusId;
    public TreeTableColumn colAdditionalFee;
    public TreeTableColumn colAdvance;
    public JFXTextField txtAdvance;
    public JFXTextField txtAddionalFee;
    public Label lblOrdId;
    public TreeTableColumn colOption;
    public TreeTableColumn colUserEmail;
    public TreeTableColumn colDescription;
    public TreeTableColumn colOrdId;
    public Label lblTotal;
    private CustomerBo customerBo = new CustomerBoImpl();
    private ItemBo itemBo = new ItemBoImpl();
    private RegisterBo registerBo = new RegisterBoImpl();
    private ItemDao itemDao = new ItemDaoImpl();
    private List<CustomerDto> customers;
    private List<ItemDto> items;
    private List<RegisterDto> register;
    private double Advance=0;
    private PlaceOrderBo placeOrderBo= new PlaceOrderBoImpl();
    private OrderBo orderBo= new OrderBoImpl();

    private double total = 0;

    private ObservableList<PlaceOrderTm> tmList = FXCollections.observableArrayList();

    public void initialize(){
        colOrdId.setCellValueFactory(new TreeItemPropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        colCategory.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        colAdvance.setCellValueFactory(new TreeItemPropertyValueFactory<>("advance"));
        colDescription.setCellValueFactory(new TreeItemPropertyValueFactory<>("des"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        try {
            register = registerBo.allUsers();
            items = itemBo.allItems();
            customers = customerBo.allCustomers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadUserIds();
        loadItemCodes();
        loadCustomerIds();


        comEmail.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (RegisterDto dto:register) {
                    dto.getEmail();

            }
        });

        comCusId.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (CustomerDto dto:customers) {
                if (dto.getCustomerId().equals(newValue.toString())){
                    txtName.setText(dto.getName());
                }
            }
        });

        comItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (ItemDto dto:items) {
                if (dto.getItemCode().equals(newValue.toString())){
                    txtCategory.setText(dto.getCategory());
                    txtDes.setText(dto.getName());
                }
            }
        });
        setOrderId();
        calculateTime();
    }

    private void setOrderId() {
        try {
            lblOrdId.setText(placeOrderBo.generateId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        ObservableList list = FXCollections.observableArrayList();

        for (ItemDto dto:items) {
            list.add(dto.getItemCode());
        }
        comItemCode.setItems(list);
    }

    private void loadCustomerIds() {
        ObservableList list = FXCollections.observableArrayList();

        for (CustomerDto dto:customers) {
            list.add(dto.getCustomerId());
        }
        comCusId.setItems(list);
    }

    private void loadUserIds() {
        ObservableList list = FXCollections.observableArrayList();

        for (RegisterDto dto:register) {
            list.add(dto.getEmail());
        }
        comEmail.setItems(list);
    }


    public AnchorPane pane;

    public void btnAddCart(ActionEvent event) {
        JFXButton btn = new JFXButton("Delete");

        PlaceOrderTm tm = new PlaceOrderTm(
                lblOrdId.getText(),
                comItemCode.getValue().toString(),
                txtCategory.getText(),
                Double.parseDouble(txtAdvance.getText()),
                txtDes.getText(),
                btn
        );

        btn.setOnAction(actionEvent -> {
            tmList.remove(tm);
            total-=tm.getAdvance();
            lblTotal.setText(String.format("%.2f",total));
            tblPlaceOrd.refresh();
        });
        boolean isExist = false;
        for (PlaceOrderTm placeorder:tmList) {
            if (placeorder.getOrderId().equals(tm.getOrderId())){
                placeorder.setAdvance(placeorder.getAdvance()+tm.getAdvance());
                isExist = true;
                total+= tm.getAdvance();
            }
        }
        if (!isExist){
            tmList.add(tm);
            total+=tm.getAdvance();
        }

        lblTotal.setText(String.format("%.2f",total));

        TreeItem treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
        tblPlaceOrd.setRoot(treeItem);
        tblPlaceOrd.setShowRoot(false);
    }

    public void btnPlaceOrd(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        List<PlaceOrderDto> list = new ArrayList<>();
        for (PlaceOrderTm tm:tmList) {
            list.add(new PlaceOrderDto(
                    lblOrdId.getText(),
                    Double.parseDouble(txtAdvance.getText()),
                    comItemCode.getValue().toString(),
                    txtCategory.getText(),
                    txtDes.getText()
            ));
        }

        PlaceOrderDto dto = new PlaceOrderDto(
                lblOrdId.getText(),
                Double.parseDouble(txtAdvance.getText()),
                comItemCode.getValue().toString(),
                txtCategory.getText(),
                txtDes.getText()
        );
        OrderDto dto2 = new OrderDto(
                lblOrdId.getText(),
                comCusId.getValue().toString(),
                ("not yet"),
                txtCategory.getText(),
                ("Processing"),
                lblTime.getText(),
                (0.00),
                Double.parseDouble(txtAdvance.getText()),
                (0.00),
                (0.00)
        );


        try {
            boolean isSaved = placeOrderBo.saveOrder(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Order Saved!").show();
                setOrderId();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        orderBo.saveOrder(dto2);
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

    private void calculateTime() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
