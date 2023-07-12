package com.example.sutfood.guiview.managermenu;

import com.example.sutfood.Main;
import com.example.sutfood.model.resturant.Restaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class showRestaurant extends Application {
    public static Stage stage;

    public static Restaurant restaurant;
    @Override
    public void start(Stage stage) throws Exception {
        showRestaurant.stage = stage;
        //showRestaurant.restaurant = restaurant;
        URL url = Main.class.getResource("/FXML/showrestaurant'.fxml");
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
