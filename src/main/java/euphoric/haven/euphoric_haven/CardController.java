package euphoric.haven.euphoric_haven;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController  implements Initializable {

    @FXML
    private Label descLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label titleLabel;

    final Place place;

    public CardController(Place place) {
        this.place = place;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText(place.getName());
        descLabel.setText(place.getDescription());
        ratingLabel.setText(String.valueOf(place.getRating()));
        imageView.setImage(new Image(String.valueOf(getClass().getResource(place.getResourcePath()))));
    }
}
