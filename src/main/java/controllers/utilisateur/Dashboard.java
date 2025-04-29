package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class Dashboard {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button programsButton;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button logoutButton;

    @FXML
    private void handlePrograms(ActionEvent event) {
        // TODO: Aller vers Programmes.fxml
    }

    @FXML
    private void handleEditProfile(ActionEvent event) {
        // TODO: Aller vers Profil.fxml
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        // TODO: Retourner à Login.fxml
    }
}
