package com.example.sutfood.guiview.loginmenu;

import com.example.sutfood.controllers.LoginMenuController;
import com.example.sutfood.view.enums.loginmenu.LoginMenuMessages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InformationPageFXController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    TextField firstname;
    @FXML
    TextField lastname;
    @FXML
    TextField locationNode;

    public void userSignup(ActionEvent actionEvent) throws Exception {
        if (!username.getText().matches("\\w+") || !password.getText().matches("\\S+") ||
                !lastname.getText().matches("\\w+") || !firstname.getText().matches("\\w+") ||
                !locationNode.getText().matches("\\d+")) LoginMenuFXController.incompleteFieldsAlert();
        else {
            LoginMenuMessages result = LoginMenuController.checkAddUser(username.getText(), password.getText());
            if (result != LoginMenuMessages.USER_ACCOUNT_CREATED) LoginMenuFXController.alert(result);
            else {
                LoginMenuFXController.success(result);
                LoginMenuController.addUser(username.getText(), password.getText(), firstname.getText(),
                        lastname.getText(), locationNode.getText());
                new LoginMenuGui().start(LoginMenuGui.stage);
            }
        }
    }

    public void adminSignup(ActionEvent actionEvent) throws Exception {
        if (!username.getText().matches("\\w+") || !password.getText().matches("\\S+") ||
                !lastname.getText().matches("\\w+") || !firstname.getText().matches("\\w+")) LoginMenuFXController.incompleteFieldsAlert();
        else {
            LoginMenuMessages result = LoginMenuController.checkAddAdmin(username.getText(), password.getText());
            if (result != LoginMenuMessages.USER_ACCOUNT_CREATED) LoginMenuFXController.alert(result);
            else {
                LoginMenuFXController.success(result);
                LoginMenuController.addAdmin(username.getText(), password.getText(), firstname.getText(),
                        lastname.getText());
                new LoginMenuGui().start(LoginMenuGui.stage);
            }
        }
    }
}
