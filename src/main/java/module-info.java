module Food.Ordering.And.Delivery.Platform {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sutfood.guiview to javafx.fxml;
    exports com.example.sutfood.guiview ;
    exports com.example.sutfood;
    opens com.example.sutfood to javafx.fxml;
}