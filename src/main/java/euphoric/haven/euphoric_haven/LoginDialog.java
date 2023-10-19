package euphoric.haven.euphoric_haven;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Window;

import java.io.IOException;

public class LoginDialog extends Dialog<Boolean> {

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane login;

    @FXML
    private AnchorPane signup;

    @FXML
    private TextField nameSignUp;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordSignUp;

    @FXML
    private TextField username;

    @FXML
    private TextField usernameSignUp;


    public LoginDialog(Window owner) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml")), loginLoader = new FXMLLoader(App.class.getResource("login_part.fxml")), signupLoader = new FXMLLoader(App.class.getResource("signup_part.fxml"));
        fxmlLoader.setController(this);
        loginLoader.setController(this);
        signupLoader.setController(this);
        try {
            DialogPane dialogPane = fxmlLoader.load();
            login = loginLoader.load();
            signup = signupLoader.load();
            login.setUserData("login");
            signup.setUserData("signup");
            initOwner(owner);
            initModality(Modality.APPLICATION_MODAL);
            setTitle("Login");
            setDialogPane(dialogPane);
            setOnCloseRequest(event -> {
            });
            final Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
            okButton.addEventFilter(
                    ActionEvent.ACTION,
                    event -> {
                        if (borderPane.getCenter().getUserData() == login.getUserData()) {
                            var loggedIn = DatabaseConnector.getInstance().loginUser(username.getText(), password.getText());
                            if (loggedIn) {
                                setResult(true);
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error logging in!");
                                alert.setContentText("The username and password does not match.");
                                alert.getButtonTypes().clear();
                                alert.getButtonTypes().add(ButtonType.CLOSE);
                                alert.showAndWait();
                                event.consume();
                            }
                        } else {
                            var username=DatabaseConnector.getInstance().signupUser(nameSignUp.getText(),passwordSignUp.getText(),usernameSignUp.getText());
                            // Show successful sign up
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Signed Up Successfully!");
                            alert.setContentText("Welcome " + username + "!");
                            alert.getButtonTypes().clear();
                            alert.getButtonTypes().add(ButtonType.OK);
                            alert.showAndWait();
                            setResult(true);
                        }
                    }
            );

            setResultConverter(buttonType -> {
                if (borderPane.getCenter().getUserData() == login.getUserData()) {
                    if (buttonType == ButtonType.OK) {
                        var loggedIn = DatabaseConnector.getInstance().loginUser(username.getText(), password.getText());
                        if (loggedIn) {
                            return true;
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error logging in!");
                            alert.setContentText("The username and password does not match.");
                            alert.getButtonTypes().clear();
                            alert.getButtonTypes().add(ButtonType.CLOSE);
                            alert.showAndWait();
                            return false;
                        }
                    }
                } else {
                    var username=DatabaseConnector.getInstance().signupUser(nameSignUp.getText(),passwordSignUp.getText(),usernameSignUp.getText());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Signed Up Successfully!");
                    alert.setContentText("Welcome " + username + "!");
                    alert.getButtonTypes().clear();
                    alert.getButtonTypes().add(ButtonType.OK);
                    alert.showAndWait();
                    return true;
                }
                return null;
            });

            setOnShowing(event -> {
                borderPane.setCenter(login);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onAboutUsClicked(ActionEvent event) {
        AboutDialog aboutDialog = new AboutDialog(getOwner());
        aboutDialog.showAndWait();
    }

    @FXML
    void onLoginClicked(ActionEvent event) {
        borderPane.setCenter(login);
    }

    @FXML
    void onSignupClicked(ActionEvent event) {
        borderPane.setCenter(signup);
    }
}
