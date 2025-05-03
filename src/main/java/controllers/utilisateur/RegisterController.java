package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import entities.Utilisateur;
import service.UtilisateurService;
import utils.AlertUtils;
import utils.NavigationUtils;

public class RegisterController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private ChoiceBox<String> roleField;
    @FXML
    private TextField preferencesField;
    @FXML
    private Button registerButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button backButton;

    private final UtilisateurService utilisateurService = new UtilisateurService();

    @FXML
    private void initialize() {
        // Initialize ChoiceBox items
        roleField.getItems().addAll("Athlete", "Coach", "Admin");
        // Activer le bouton d'inscription uniquement si tous les champs sont remplis
        registerButton.setDisable(true);
        emailField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());
        passwordField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());
        nomField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());
        prenomField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());
        roleField.valueProperty().addListener((obs, oldVal, newVal) -> validateFields());
        preferencesField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());

        registerButton.setOnAction(event -> handleRegister());
        cancelButton.setOnAction(event -> handleCancel());
        backButton.setOnAction(event -> handleBack());
    }

    private void validateFields() {
        boolean isValid = !emailField.getText().isEmpty() && 
                         !passwordField.getText().isEmpty() && 
                         !nomField.getText().isEmpty() && 
                         !prenomField.getText().isEmpty() &&
                         roleField.getValue() != null &&
                         !preferencesField.getText().isEmpty();
        registerButton.setDisable(!isValid);
    }

    private void handleRegister() {
        String email = emailField.getText();
        String password = passwordField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String role = roleField.getValue();
        String preferences = preferencesField.getText();

        if (email.isEmpty() || password.isEmpty() || nom.isEmpty() || prenom.isEmpty() || role == null || preferences.isEmpty()) {
            AlertUtils.showError("Erreur", "Veuillez remplir tous les champs");
            return;
        }

        // Vérifier le format de l'email
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            AlertUtils.showError("Erreur", "Format d'email invalide");
            return;
        }

        // Vérifier la longueur du mot de passe
        if (password.length() < 6) {
            AlertUtils.showError("Erreur", "Le mot de passe doit contenir au moins 6 caractères");
            return;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(password);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setRole(role);
        utilisateur.setPreferencesSportives(preferences);

        utilisateurService.ajouterUtilisateur(utilisateur);
        AlertUtils.showSuccess("Succès", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
        returnToLogin();
    }

    private void handleCancel() {
        returnToLogin();
    }

    private void returnToLogin() {
        NavigationUtils.navigateTo("/utilisateur/login.fxml", (Stage) registerButton.getScene().getWindow());
    }

    @FXML
    private void handleBack() {
        handleCancel();
    }
} 