package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entities.Utilisateur;
import service.UtilisateurService;
import utils.AlertUtils;
import utils.NavigationUtils;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    private final UtilisateurService utilisateurService = new UtilisateurService();

    @FXML
    private void initialize() {
        // Activer le bouton de connexion uniquement si les champs sont remplis
        loginButton.setDisable(true);
        emailField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());
        passwordField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());

        loginButton.setOnAction(event -> handleLogin());
        registerButton.setOnAction(event -> handleRegister());
    }

    private void validateFields() {
        boolean isValid = !emailField.getText().isEmpty() && !passwordField.getText().isEmpty();
        loginButton.setDisable(!isValid);
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            AlertUtils.showError("Erreur", "Veuillez remplir tous les champs");
            return;
        }

        Utilisateur utilisateur = utilisateurService.connecter(email, password);
        if (utilisateur != null) {
            NavigationUtils.navigateTo("/utilisateur/main.fxml", (Stage) loginButton.getScene().getWindow(), utilisateur);
        } else {
            AlertUtils.showError("Erreur", "Email ou mot de passe incorrect");
        }
    }

    private void handleRegister() {
        NavigationUtils.navigateTo("/utilisateur/Register.fxml", (Stage) registerButton.getScene().getWindow());
    }
}
