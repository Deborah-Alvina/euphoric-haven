package euphoric.haven.euphoric_haven;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class App extends javafx.application.Application implements Initializable {
    final DatabaseConnector connector = DatabaseConnector.getInstance();

    @FXML
    private SplitMenuButton stateBox;

    @FXML
    private SplitMenuButton cityBox;

    //        private String[] state={"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarkhand","West Bengal"};
    @FXML
    private Label statelabel;

    @FXML
    private Label citylabel;

    @FXML
    private ListView<Card> placesView;

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Euphoric Haven");
        stage.setScene(scene);
        stage.show();
        showLoginDialog(stage);
        this.stage = stage;
    }

    void setupComponents() {
        List<State> states = connector.getAllStates();
        stateBox.getItems().clear();
        stateBox.getItems().addAll(FXCollections.observableList(states.stream().map(this::getMenuItem).toList()));
    }

    void showLoginDialog(Stage stage) {
        LoginDialog loginDialog = new LoginDialog(stage);
        loginDialog.showAndWait().ifPresentOrElse(aBoolean -> {
            if (aBoolean == null) {
                Platform.exit();
            }
            if (!aBoolean) {
                Platform.exit();
            }
        }, Platform::exit);
    }

    @FXML
    void onAbout(ActionEvent ignoredEvent) {
        AboutDialog aboutDialog = new AboutDialog(stage);
        aboutDialog.showAndWait();
    }

    @FXML
    void onLogout(ActionEvent ignoredEvent) {
        LoginDialog loginDialog = new LoginDialog(stage);
        loginDialog.setOnCloseRequest(param -> Platform.exit());
        loginDialog.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

    MenuItem getMenuItem(State state) {
        var item = new MenuItem(state.getName());
        // set the 'state' as the user data, it can be accessed later when needed using getUserData function on the item object.
        item.setUserData(state);
        item.setOnAction(this::getState);
        return item;
    }

    MenuItem getMenuItem(City city) {
        var item = new MenuItem(city.getName());
        item.setUserData(city);
        item.setOnAction(this::getCity);
        return item;
    }


    Card getPlaceItem(Place place) {
        return new Card(place);
    }

    public void getState(ActionEvent event) {
        State myState = (State) ((MenuItem) event.getSource()).getUserData();
        stateBox.setText(myState.getName());
        statelabel.setText(myState.getName());
        var cities = connector.getCitiesForState(myState);
        System.out.println(myState.getName());
        System.out.println(cities.size());
        cityBox.getItems().clear();
        cityBox.getItems().addAll(FXCollections.observableList(cities.stream().map(this::getMenuItem).toList()));
    }

    public void getCity(ActionEvent event) {
        City city = (City) ((MenuItem) event.getSource()).getUserData();
        cityBox.setText(city.getName());
        citylabel.setText(city.getName());
        var places = connector.getPlacesForCity(city);
        placesView.getItems().clear();
        placesView.getItems().addAll(FXCollections.observableList(places.stream().map(this::getPlaceItem).toList()));
//        placesView.getItems().addAll(FXCollections.observableList(places.stream().map(this::getPlaceItem).toList()));
//        placesView.getItems().addAll(FXCollections.observableList(places.stream().map(this::getPlaceItem).toList()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupComponents();
    }
}