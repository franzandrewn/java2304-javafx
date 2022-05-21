module com.andrewn.java2304javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.andrewn.java2304javafx to javafx.fxml;
    exports com.andrewn.java2304javafx;
}