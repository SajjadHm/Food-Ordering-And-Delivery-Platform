package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class UserHistorySceneController {

    public Label labeltest;

    public void setLabeltest(Label labeltest) {
        this.labeltest.setText("testtttt");
    }

    public void setTotalPrice(String totalPrice) {

    }

    @FXML
    private TextArea totalPrice;

    public void setTotalPrice(MouseEvent mouseEvent)
    {
        this.totalPrice.setText("45IRT");
    }
}
