package com.example.sutfood.guiview.managermenu;

import com.example.sutfood.controllers.LoginMenuController;
import com.example.sutfood.controllers.ManagerMenuController;
import com.example.sutfood.guiview.loginmenu.LoginMenuFXController;
import com.example.sutfood.model.enums.ResturantFoodType;
import com.example.sutfood.view.ManagerMenu;
import com.example.sutfood.view.enums.managermenu.ManagerMenuMessages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddRestaurantFXController {
    @FXML
    TextField restaurantName;
    @FXML
    TextField locationNode;
    @FXML
    TextField foodTypes;

    public void addRestaurant(ActionEvent actionEvent) throws Exception {
        if (!(restaurantName.getText().matches("\\w+") && locationNode.getText().matches("\\d+") &&
                foodTypes.getText().matches("[\\w\\s]+"))) ManagerMenuFXController.incompleteFieldsAlert();
        else {
            ResturantFoodType[] foodType = ResturantFoodType.getType(foodTypes.getText().split("\\s+"));
            ManagerMenuMessages result = ManagerMenuController.checkAddRestaurant(restaurantName.getText(), locationNode.getText(),
                    foodType);
            if (result != ManagerMenuMessages.RESTAURANT_ADDED) ManagerMenuFXController.alert(result);
            else {
                ManagerMenuFXController.success(result);
                new ManagerMenuGui().start(AddRestaurantGui.stage);
            }
        }
    }
}
