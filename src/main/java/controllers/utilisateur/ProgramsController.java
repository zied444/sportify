package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import entities.Utilisateur;
import utils.AlertUtils;

import java.io.IOException;

public class ProgramsController {
    @FXML
    private VBox programsContainer;
    @FXML
    private Button backButton;
    
    private Utilisateur currentUser;

    @FXML
    public void initialize() {
        // Initialisation des programmes
        loadPrograms();
    }

    public void setUser(Utilisateur user) {
        this.currentUser = user;
    }

    private void loadPrograms() {
        // TODO: Charger les programmes depuis la base de données
        // Pour l'instant, on ajoute des exemples
        programsContainer.getChildren().clear();
        
        // Exemple de programmes
        String[] programs = {
            "Programme Débutant",
            "Programme Intermédiaire",
            "Programme Avancé",
            "Programme Perte de Poids",
            "Programme Prise de Masse"
        };

        for (String program : programs) {
            Label programLabel = new Label(program);
            programLabel.getStyleClass().add("program-label");
            programsContainer.getChildren().add(programLabel);
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/utilisateur/main.fxml"));
            Parent root = loader.load();
            
            MainController mainController = loader.getController();
            mainController.setUser(currentUser);
            
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            AlertUtils.showError("Erreur", "Impossible de charger la vue principale");
        }
    }
} 