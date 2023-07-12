package com.example.sutfood.guiview.loginmenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginMenuFXController {
    @FXML
    TextField textField;

    @FXML
    PasswordField passwordField;

    private void incompleteFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incomplete Fields");
        alert.setContentText("Complete all fields for login.");
        alert.show();
    }

    public void userLogin(ActionEvent actionEvent) {
        if (!textField.getText().matches("\\w+") || !passwordField.getText().matches("\\w+"))
            incompleteFieldsAlert();
    }

    public void adminLogin(ActionEvent actionEvent) {
        if (!textField.getText().matches("\\w+") || !passwordField.getText().matches("\\w+"))
            incompleteFieldsAlert();
    }

    public void signUp(ActionEvent actionEvent) {
    }

    public void forgetPassword(ActionEvent actionEvent) {
    }
}
