module euphoric.haven.euphoric_haven {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;

    opens euphoric.haven.euphoric_haven to javafx.fxml, org.mongodb.bson;
    exports euphoric.haven.euphoric_haven;
}