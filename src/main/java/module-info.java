module Food.Ordering.And.Delivery.Platform {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.sutfood.guiview.loginmenu to javafx.fxml;
    exports com.example.sutfood.guiview.loginmenu ;
    exports com.example.sutfood;
    opens com.example.sutfood to javafx.fxml;
    opens com.example.sutfood.guiview.managermenu to javafx.fxml;
    exports com.example.sutfood.guiview.managermenu ;
    opens com.example.sutfood.model.resturant to javafx.fxml;
    exports com.example.sutfood.model.resturant ;
    exports com.example.sutfood.model to json.simple;
    opens com.example.sutfood.model;
}