package com.example.sutfood.guiview.managermenu;

import com.example.sutfood.model.Memory;
import com.example.sutfood.model.accounts.Manager;
import com.example.sutfood.model.resturant.Restaurant;
import com.example.sutfood.view.enums.loginmenu.LoginMenuMessages;
import com.example.sutfood.view.enums.managermenu.ManagerMenuMessages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;

public class ManagerMenuFXController {
    @FXML
    TableView table;

    public static void incompleteFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incomplete Fields");
        alert.setContentText("Complete all fields properly.");
        alert.show();
    }

    public static void alert(ManagerMenuMessages menuMessages) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(menuMessages.toString());
        alert.setContentText(menuMessages.getMessage());
        alert.show();
    }

    public static void success(ManagerMenuMessages menuMessages) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(menuMessages.toString());
        alert.setContentText(menuMessages.getMessage());
        alert.show();
    }

    public void initialize() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        for (String id : ((Manager) Memory.getCurrentAccount()).getRestaurantsID())
            restaurants.add(Memory.getRestaurant(id));
        TableColumn<Restaurant, String> idColumn = new TableColumn("id");
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Restaurant, String> nameColumn = new TableColumn("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Restaurant, String> locationColumn = new TableColumn("location");
        locationColumn.setCellValueFactory(new PropertyValueFactory("location"));
        TableColumn<Restaurant, String> foodTypesColumn = new TableColumn("foodTypes");
        foodTypesColumn.setCellValueFactory(new PropertyValueFactory("foodTypes"));


        table.getColumns().addAll(idColumn, nameColumn, locationColumn, foodTypesColumn);
        table.getItems().addAll(restaurants);

        table.setRowFactory( tv -> {
                TableRow<Restaurant> row = new TableRow<>();
                row.setOnMouseClicked( event -> {
                    if (!row.isEmpty()) {
                        Restaurant rowData = row.getItem();
                        try {
                            select(rowData);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                return row;
    });
    }

    public void addRestaurnat(ActionEvent actionEvent) throws Exception {
        new AddRestaurantGui().start(ManagerMenuGui.stage);
    }

    public void select(Restaurant selectedRow) throws Exception {
        new showRestaurant().start(ManagerMenuGui.stage);
    }
    public void selecting(MouseEvent mouseEvent) {

    }
}
