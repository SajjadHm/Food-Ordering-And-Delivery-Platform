package com.example.sutfood.guiview.loginmenu;

import com.example.sutfood.Main;
import com.example.sutfood.model.Memory;
import com.example.sutfood.model.Read;
import com.example.sutfood.model.Save;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class LoginMenuGui extends Application {
    public static Stage stage;
    public static void main(String[] args) {
        Read.loadData();
        launch();
        Save.saveData();
    }
    @Override
    public void start(Stage stage) throws Exception {
        LoginMenuGui.stage = stage;
        URL url = Main.class.getResource("/FXML/loginmenu.fxml");
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
