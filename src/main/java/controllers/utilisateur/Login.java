package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import service.UtilisateurService;
import entities.Utilisateur;

public class Login {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink registerLink;

    private UtilisateurService utilisateurService = new UtilisateurService();

    @FXML
    public void initialize() {
        // Charger le CSS une fois que la scène est disponible
        emailField.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
            }
        });
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        Utilisateur utilisateur = utilisateurService.connecter(email, password);

        if (utilisateur != null) {
            System.out.println("Bienvenue " + utilisateur.getPrenom());
            // TODO: Rediriger vers Dashboard
        } else {
            showAlert("Erreur de connexion", "Email ou mot de passe incorrect.");
        }
    }

    @FXML
    private void handleRegisterLink(ActionEvent event) {
        // TODO: Aller vers l'écran Register
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
