package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entities.Utilisateur;
import utils.AlertUtils;
import service.UtilisateurService;

import java.io.IOException;

public class EditProfileController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private Utilisateur currentUser;

    @FXML
    private void initialize() {
        // Activer le bouton de sauvegarde uniquement si tous les champs sont remplis
        saveButton.setDisable(true);
        emailField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());
        passwordField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());
        nomField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());
        prenomField.textProperty().addListener((obs, oldVal, newVal) -> validateFields());

        saveButton.setOnAction(event -> handleSave());
        cancelButton.setOnAction(event -> handleCancel());
    }

    private void validateFields() {
        boolean isValid = !emailField.getText().isEmpty() && 
                         !passwordField.getText().isEmpty() && 
                         !nomField.getText().isEmpty() && 
                         !prenomField.getText().isEmpty();
        saveButton.setDisable(!isValid);
    }

    public void setUser(Utilisateur user) {
        this.currentUser = user;
        emailField.setText(user.getEmail());
        passwordField.setText(user.getMotDePasse());
        nomField.setText(user.getNom());
        prenomField.setText(user.getPrenom());
    }

    public void handleSave() {
        String email = emailField.getText();
        String password = passwordField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();

        if (email.isEmpty() || password.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
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

        currentUser.setEmail(email);
        currentUser.setMotDePasse(password);
        currentUser.setNom(nom);
        currentUser.setPrenom(prenom);

        UtilisateurService utilisateurService = new UtilisateurService();
        utilisateurService.modifierUtilisateur(currentUser);
        AlertUtils.showSuccess("Succès", "Profil mis à jour avec succès");
        returnToMain();
    }

    private void handleCancel() {
        returnToMain();
    }

    @FXML
    private void handleBack() {
        handleCancel();
    }

    private void returnToMain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/utilisateur/main.fxml"));
            Parent root = loader.load();
            
            MainController controller = loader.getController();
            controller.setUser(currentUser);
            
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            AlertUtils.showError("Erreur", "Une erreur est survenue lors du retour à la page principale");
        }
    }
} 