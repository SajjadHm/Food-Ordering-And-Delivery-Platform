package com.example.sutfood.guiview.managermenu;

import com.example.sutfood.guiview.loginmenu.LoginMenuGui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class ManagerMenuGui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenuGui.class.getResource("FXML/managermenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
