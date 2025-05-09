package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger l'interface d'affichage des événements
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Displayevent.fxml"));
            javafx.scene.Parent root = loader.load();
            
            // Créer la scène
            Scene scene = new Scene(root);
            
            // Configurer la fenêtre principale
            primaryStage.setTitle("Liste des Événements");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false); // Empêche le redimensionnement de la fenêtre
            
            // Afficher la fenêtre
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
