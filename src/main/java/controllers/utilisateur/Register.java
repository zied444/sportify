package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import service.UtilisateurService;
import entities.Utilisateur;

public class Register {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox<String> roleChoiceBox;

    @FXML
    private TextField preferencesField;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    private UtilisateurService utilisateurService = new UtilisateurService();

    @FXML
    private void initialize() {
        roleChoiceBox.getItems().addAll("Athlete", "Coach", "Administrateur");
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        Utilisateur utilisateur = new Utilisateur(
                nomField.getText(),
                prenomField.getText(),
                emailField.getText(),
                passwordField.getText(),
                roleChoiceBox.getValue(),
                preferencesField.getText()
        );
        utilisateurService.ajouterUtilisateur(utilisateur);
        showAlert("Succès", "Compte créé avec succès !");
        // TODO: Retourner vers Login
    }

    @FXML
    private void handleBack(ActionEvent event) {
        // TODO: Retourner vers Login
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
