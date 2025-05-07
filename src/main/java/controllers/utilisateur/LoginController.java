package controllers.utilisateur;

import controllers.admin.AdminDashboardController;
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

    private UtilisateurService utilisateurService;
    private Stage stage;

    public LoginController() {
        this.utilisateurService = new UtilisateurService();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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

        try {
            Utilisateur user = utilisateurService.connecter(email, password);
            if (user != null) {
                System.out.println("Authentification réussie pour : " + user.getEmail());
                
                // Rediriger vers la vue appropriée selon le rôle
                if (user.getRole().equals("ADMIN")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/dashboard.fxml"));
                        Parent root = loader.load();
                        
                        AdminDashboardController controller = loader.getController();
                        controller.setCurrentAdmin(user);
                        
                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        AlertUtils.showError("Erreur", "Impossible de charger le tableau de bord administrateur : " + e.getMessage());
                    }
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/utilisateur/main.fxml"));
                        Parent root = loader.load();
                        
                        MainController controller = loader.getController();
                        controller.setUser(user);
                        
                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        AlertUtils.showError("Erreur", "Impossible de charger la vue principale: " + e.getMessage());
                    }
                }
            } else {
                AlertUtils.showError("Erreur", "Email ou mot de passe incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.showError("Erreur", "Une erreur est survenue lors de la connexion : " + e.getMessage());
        }
    }

    @FXML
    private void handleRegister() {
        try {
            // Utiliser le stage stocké s'il est disponible
            if (stage != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/utilisateur/Register.fxml"));
                Parent root = loader.load();
                
                // Set the stage in the controller
                RegisterController registerController = loader.getController();
                registerController.setStage(stage);
                
                stage.setScene(new Scene(root));
                stage.show();
                return;
            }

            // Sinon, essayer de récupérer le stage depuis le bouton
            if (registerButton != null && registerButton.getScene() != null) {
                Stage currentStage = (Stage) registerButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/utilisateur/Register.fxml"));
                Parent root = loader.load();
                
                // Set the stage in the controller
                RegisterController registerController = loader.getController();
                registerController.setStage(currentStage);
                
                currentStage.setScene(new Scene(root));
                currentStage.show();
                return;
            }

            // En dernier recours, essayer de récupérer le stage depuis n'importe quel nœud
            if (emailField != null && emailField.getScene() != null) {
                Stage currentStage = (Stage) emailField.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/utilisateur/Register.fxml"));
                Parent root = loader.load();
                
                // Set the stage in the controller
                RegisterController registerController = loader.getController();
                registerController.setStage(currentStage);
                
                currentStage.setScene(new Scene(root));
                currentStage.show();
                return;
            }

            throw new IllegalStateException("Impossible de trouver le stage pour la navigation");
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.showError("Erreur", "Impossible de charger la vue d'inscription : " + e.getMessage());
        }
    }
}
