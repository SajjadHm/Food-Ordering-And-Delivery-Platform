package com.example.scenebuildertest;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class HelloController {

    public Pane paneRest,paneTime,panePath;
    public Text textTimeR,textTime,textPathUser,textPathRest;

    public void onRestPath() {
        paneRest.setVisible(true);
        paneTime.setVisible(false);
        panePath.setVisible(false);
        textTimeR.setText("Time R");
        textPathRest.setText("Path R");
    }
    public void onPath() {
        paneRest.setVisible(false);
        paneTime.setVisible(false);
        panePath.setVisible(true);
        textPathUser.setText("Path U");
    }
    public void onTime() {
        paneRest.setVisible(false);
        paneTime.setVisible(true);
        panePath.setVisible(false);
        textTime.setText("Time U");
    }
    public void onBack() {
        paneRest.setVisible(false);
        paneTime.setVisible(false);
        panePath.setVisible(false);
    }
}