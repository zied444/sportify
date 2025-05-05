package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import entities.Utilisateur;
import utils.NavigationUtils;
import utils.AlertUtils;
import java.io.IOException;

public class MainController {
    @FXML
    private Button viewProfileButton;
    @FXML
    private Button programsButton;

    @FXML
    private Button editProfileButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button deleteAccountButton;
    @FXML
    private Button backButton;

    private Utilisateur currentUser;

    @FXML
    private void initialize() {
        viewProfileButton.setOnAction(event -> handleViewProfile());
        programsButton.setOnAction(event -> handlePrograms());
       /* dashboardButton.setOnAction(event -> handleDashboard());*/
        editProfileButton.setOnAction(event -> handleEditProfile());
        logoutButton.setOnAction(event -> handleLogout());
        deleteAccountButton.setOnAction(event -> handleDeleteAccount());
        backButton.setOnAction(event -> handleBack());
    }

    public void setUser(Utilisateur user) {
        this.currentUser = user;
        if (user != null) {
            System.out.println("MainController: User set successfully - " + user.getEmail());
        } else {
            System.out.println("MainController: Warning - User set to null");
        }
    }

    private void handleViewProfile() {
        if (currentUser == null) {
            AlertUtils.showError("Erreur", "Vous devez être connecté pour voir votre profil");
            return;
        }
        NavigationUtils.navigateTo("/utilisateur/view_profile.fxml", (Stage) viewProfileButton.getScene().getWindow(), currentUser);
    }

    private void handlePrograms() {
        if (currentUser == null) {
            AlertUtils.showError("Erreur", "Vous devez être connecté pour accéder aux programmes");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/utilisateur/Programmes.fxml"));
            Parent root = loader.load();
            
            ProgramsController programsController = loader.getController();
            programsController.setUser(currentUser);
            
            Stage stage = (Stage) programsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            AlertUtils.showError("Erreur", "Impossible de charger la vue des programmes");
        }
    }



    private void handleEditProfile() {
        if (currentUser == null) {
            AlertUtils.showError("Erreur", "Vous devez être connecté pour modifier votre profil");
            return;
        }
        NavigationUtils.navigateTo("/utilisateur/edit_profile.fxml", (Stage) editProfileButton.getScene().getWindow(), currentUser);
    }

    private void handleLogout() {
        NavigationUtils.navigateTo("/utilisateur/login.fxml", (Stage) logoutButton.getScene().getWindow());
    }

    private void handleDeleteAccount() {
        // À adapter selon la logique de suppression dans UtilisateurService
        handleLogout();
    }

    public void handleBack() {
        NavigationUtils.navigateTo("/utilisateur/login.fxml", (Stage) backButton.getScene().getWindow());
    }
} 