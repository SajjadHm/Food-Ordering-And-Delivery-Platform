package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

//    public void OnsajjadClicked(ActionEvent actionEvent)
//    {
////        Scene scene = new Scene(welcomeText.getParent(), 1200, 620);
//        Stage stage = new Stage();
//        stage.setTitle("sajjad!");
////        stage.setScene(scene);
//        stage.show();
//
//    }
}