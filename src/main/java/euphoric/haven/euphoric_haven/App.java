package euphoric.haven.euphoric_haven;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class App extends javafx.application.Application {
    @FXML
    private SplitMenuButton cityMenuButton;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    void onTapGo(ActionEvent event) {
        TextInputDialog dlg = new TextInputDialog();
        dlg.setTitle("Enter Destination");
        dlg.setHeaderText("Enter city name");
        // Showing the text input dialog and waiting for user input
        dlg.showAndWait();
        if (dlg.getResult().isEmpty()) {
            // since the user input is empty, we do nothing.
            return;
        } else {
            // TODO: process the user input of city name
            System.out.println(dlg.getResult());
        }
    }

    @FXML
    void onTapHome(ActionEvent event) {

    }

    @FXML
    void onTapLonavala(ActionEvent event) {
        cityMenuButton.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    void onTapMumbai(ActionEvent event) {
        cityMenuButton.setText(((MenuItem) event.getSource()).getText());
    }
}