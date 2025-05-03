package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entities.Utilisateur;

import java.io.IOException;
import java.net.URL;

public class NavigationUtils {
    public static void navigateTo(String fxmlPath, Stage currentStage, Utilisateur utilisateur) {
        try {
            URL resource = NavigationUtils.class.getResource(fxmlPath);
            System.out.println("Chargement FXML depuis : " + fxmlPath + " -> " + resource);
            if (resource == null) {
                throw new IOException("Resource not found: " + fxmlPath);
            }
            
            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();
            
            // Set the utilisateur in the controller if it's a user-related view
            Object controller = loader.getController();
            if (controller instanceof controllers.utilisateur.MainController) {
                ((controllers.utilisateur.MainController) controller).setUser(utilisateur);
            } else if (controller instanceof controllers.utilisateur.ViewProfileController) {
                ((controllers.utilisateur.ViewProfileController) controller).setUser(utilisateur);
            } else if (controller instanceof controllers.utilisateur.EditProfileController) {
                ((controllers.utilisateur.EditProfileController) controller).setUser(utilisateur);
            }
            
            currentStage.setScene(new Scene(root));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Affiche le stacktrace complet dans la console
            AlertUtils.showError("Erreur", "Impossible de charger la vue: " + fxmlPath + "\n" + e.getMessage());
        }
    }

    public static void navigateTo(String fxmlPath, Stage currentStage) {
        navigateTo(fxmlPath, currentStage, null);
    }
} 