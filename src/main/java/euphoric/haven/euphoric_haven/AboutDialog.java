package euphoric.haven.euphoric_haven;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;
import javafx.stage.Window;

import java.io.IOException;

public class AboutDialog extends Dialog<Void> {

    public AboutDialog(Window owner) {
        try {
            FXMLLoader loader = new FXMLLoader(AboutDialog.class.getResource("about.fxml"));
            loader.setController(this);

            DialogPane dialogPane = loader.load();
            initOwner(owner);
            initModality(Modality.APPLICATION_MODAL);

            setResizable(true);
            setTitle("About the Application");
            setOnCloseRequest(event -> getDialogPane().getScene().getWindow().hide());
            setResultConverter(param -> {
                return null;
            });

            setDialogPane(dialogPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}