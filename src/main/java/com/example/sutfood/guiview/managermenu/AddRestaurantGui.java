package com.example.sutfood.guiview.managermenu;

import com.example.sutfood.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class AddRestaurantGui extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        AddRestaurantGui.stage = stage;
        URL url = Main.class.getResource("/FXML/addrestaurant.fxml");
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
