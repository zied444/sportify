import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.DatabaseInitializer;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Initialize the database
            DatabaseInitializer.initialize();
            
            // Load the login view
            Parent root = FXMLLoader.load(getClass().getResource("/utilisateur/login.fxml"));

            // Create the scene with specific dimensions
            Scene scene = new Scene(root ,900, 700);

            // Configure the stage
            primaryStage.setTitle("Sportify");
            
            try {
                primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/imgs/logo.png")));
            } catch (Exception e) {
                System.out.println("Logo not found: " + e.getMessage());
            }

            primaryStage.setMaximized(true);
            primaryStage.setMinWidth(900);
            primaryStage.setMinHeight(700);

            primaryStage.setScene(scene);
            
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error starting application: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
} 