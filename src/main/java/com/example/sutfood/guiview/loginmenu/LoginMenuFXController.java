package com.example.sutfood.guiview.loginmenu;

import com.example.sutfood.controllers.LoginMenuController;
import com.example.sutfood.guiview.managermenu.ManagerMenuGui;
import com.example.sutfood.view.ManagerMenu;
import com.example.sutfood.view.enums.loginmenu.LoginMenuMessages;
import com.example.sutfood.view.enums.loginmenu.LoginMenuResults;
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

    public static void incompleteFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incomplete Fields");
        alert.setContentText("Complete all fields properly.");
        alert.show();
    }

    public static void alert(LoginMenuMessages menuMessages) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(menuMessages.toString());
        alert.setContentText(menuMessages.getMessage());
        alert.show();
    }

    public static void success(LoginMenuMessages menuMessages) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(menuMessages.toString());
        alert.setContentText(menuMessages.getMessage());
        alert.show();
    }

    public void userLogin(ActionEvent actionEvent) {
        if (!textField.getText().matches("\\w+") || !passwordField.getText().matches("\\S+"))
            incompleteFieldsAlert();
        else {
            LoginMenuMessages results = LoginMenuController.checkLoginUser(textField.getText(), passwordField.getText());
            if (results != LoginMenuMessages.USER_LOGIN_SUCCESSFUL) alert(results);
            else System.out.println("Login User");
        }
    }

    public void adminLogin(ActionEvent actionEvent) throws Exception {
        if (!textField.getText().matches("\\w+") || !passwordField.getText().matches("\\S+"))
            incompleteFieldsAlert();
        else {
            LoginMenuMessages results = LoginMenuController.checkLoginAdmin(textField.getText(), passwordField.getText());
            if (results != LoginMenuMessages.ADMIN_LOGIN_SUCCESSFUL) alert(results);
            new ManagerMenuGui().start(LoginMenuGui.stage);
        }
    }

    public void signUp(ActionEvent actionEvent) throws Exception {
        new InformationPage().start(LoginMenuGui.stage);
    }

    public void forgetPassword(ActionEvent actionEvent) {
    }
}
