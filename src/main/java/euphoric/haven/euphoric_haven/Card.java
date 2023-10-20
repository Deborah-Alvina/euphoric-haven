package euphoric.haven.euphoric_haven;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Card extends VBox {

    CardController controller;

    Node childVBox;

    final Place place;

    public Card(Place place) {
        this.place = place;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("card.fxml"));
        loader.setControllerFactory(param -> controller = new CardController(place));
        try {
            childVBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().add(childVBox);
    }
}
