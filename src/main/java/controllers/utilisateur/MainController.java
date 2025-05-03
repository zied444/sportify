package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import entities.Utilisateur;
import utils.NavigationUtils;

public class MainController {
    @FXML
    private Button viewProfileButton;
    @FXML
    private Button programsButton;
    @FXML
    private Button dashboardButton;
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
        dashboardButton.setOnAction(event -> handleDashboard());
        editProfileButton.setOnAction(event -> handleEditProfile());
        logoutButton.setOnAction(event -> handleLogout());
        deleteAccountButton.setOnAction(event -> handleDeleteAccount());
        backButton.setOnAction(event -> handleBack());
    }

    public void setUser(Utilisateur user) {
        this.currentUser = user;
    }

    private void handleViewProfile() {
        NavigationUtils.navigateTo("/utilisateur/view_profile.fxml", (Stage) viewProfileButton.getScene().getWindow(), currentUser);
    }

    private void handlePrograms() {
        NavigationUtils.navigateTo("/utilisateur/Programmes.fxml", (Stage) programsButton.getScene().getWindow(), currentUser);
    }

    private void handleDashboard() {
        NavigationUtils.navigateTo("/utilisateur/dashboard.fxml", (Stage) dashboardButton.getScene().getWindow(), currentUser);
    }

    private void handleEditProfile() {
        NavigationUtils.navigateTo("/utilisateur/edit_profile.fxml", (Stage) editProfileButton.getScene().getWindow(), currentUser);
    }

    private void handleLogout() {
        NavigationUtils.navigateTo("/utilisateur/login.fxml", (Stage) logoutButton.getScene().getWindow());
    }

    private void handleDeleteAccount() {
        // À adapter selon la logique de suppression dans UtilisateurService
        handleLogout();
    }

    private void handleBack() {
        NavigationUtils.navigateTo("/utilisateur/login.fxml", (Stage) backButton.getScene().getWindow());
    }
} 