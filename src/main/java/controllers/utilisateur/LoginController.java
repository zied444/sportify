package controllers.utilisateur;

import entities.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import interfaces.IService;
import service.UtilisateurService;
import utils.AlertUtils;
import utils.NavigationUtils;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    private IService<Utilisateur> utilisateurService;

    public LoginController() {
        this.utilisateurService = new UtilisateurService();
    }

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

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            AlertUtils.showError("Erreur", "Veuillez remplir tous les champs");
            return;
        }

        Utilisateur user = ((UtilisateurService) utilisateurService).connecter(email, password);
        if (user != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/utilisateur/main.fxml"));
                Parent root = loader.load();

                MainController mainController = loader.getController();
                mainController.setUser(user);

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                AlertUtils.showError("Erreur", "Impossible de charger la vue principale");
            }
        } else {
            AlertUtils.showError("Erreur", "Email ou mot de passe incorrect");
        }
    }

    @FXML
    private void handleRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/utilisateur/Register.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Ajout pour le débogage
            AlertUtils.showError("Erreur", "Impossible de charger la vue d'inscription: " + e.getMessage());
        }
    }
}
