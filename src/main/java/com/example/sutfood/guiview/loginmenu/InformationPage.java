package com.example.sutfood.guiview.loginmenu;

import com.example.sutfood.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class InformationPage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = Main.class.getResource("/FXML/informationpage.fxml");
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
