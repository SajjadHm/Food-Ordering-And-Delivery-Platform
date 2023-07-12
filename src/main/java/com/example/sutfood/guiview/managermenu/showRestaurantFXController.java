package com.example.sutfood.guiview.managermenu;

import com.example.sutfood.model.Memory;
import com.example.sutfood.model.accounts.Manager;
import com.example.sutfood.model.resturant.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class showRestaurantFXController {
    @FXML
    TableView table;

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
                    select(rowData);
                }
            });
            return row;
        });
    }

    public void select(Restaurant selectedRow) {

    }

    public void addRestaurnat(ActionEvent actionEvent) {
    }

    public void selecting(MouseEvent mouseEvent) {
    }
}
