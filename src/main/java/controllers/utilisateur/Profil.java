package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class Profil {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private ChoiceBox<String> roleChoiceBox;

    @FXML
    private TextField preferencesField;

    @FXML
    private Button saveButton;

    @FXML
    private void initialize() {
        roleChoiceBox.getItems().addAll("Athlete", "Coach", "Administrateur");
    }

    @FXML
    private void handleSave(ActionEvent event) {
        // TODO: Mettre à jour le profil de l'utilisateur
    }
}
