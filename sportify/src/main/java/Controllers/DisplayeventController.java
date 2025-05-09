package Controllers;

import Models.Evenement;
import Services.EvenementService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.effect.DropShadow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class DisplayeventController {

    @FXML
    private VBox eventsContainer;

    private final EvenementService evenementService = new EvenementService();

    @FXML
    public void initialize() {
        refreshEventList();
    }

    // Méthode pour rafraîchir la liste des événements
    private void refreshEventList() {
        try {
            eventsContainer.getChildren().clear(); // Vider le conteneur
            List<Evenement> evenements = evenementService.display();
            for (Evenement e : evenements) {
                HBox card = createEventCard(e);
                eventsContainer.getChildren().add(card);
            }
        } catch (Exception ex) {
            showAlert("Erreur", "Impossible de charger les événements : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private HBox createEventCard(Evenement event) {
        HBox card = new HBox();
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-padding: 15;");
        card.setSpacing(15);
        card.setEffect(new DropShadow(10, Color.rgb(0, 0, 0, 0.2)));
        card.setPrefWidth(760); // Ajuster la largeur pour correspondre à la ScrollPane

        // Image de l'événement
        ImageView imageView = new ImageView();
        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
        imageView.setPreserveRatio(true);
        try {
            if (event.getNom().toLowerCase().contains("adrenaline")) {
                imageView.setImage(new Image(getClass().getResource("/images/adrenaline_premium.jpg").toExternalForm()));
            } else if (event.getNom().toLowerCase().contains("marathon")) {
                imageView.setImage(new Image(getClass().getResource("/images/marathon_event.jpg").toExternalForm()));
            } else {
                // Vérifier si une image personnalisée existe pour cet événement
                String customImagePath = "/images/event_" + event.getNom().toLowerCase().replace(" ", "_") + ".jpg";
                if (getClass().getResource(customImagePath) != null) {
                    imageView.setImage(new Image(getClass().getResource(customImagePath).toExternalForm()));
                } else {
                    imageView.setImage(null); // Pas d'image par défaut
                }
            }
        } catch (Exception e) {
            imageView.setImage(null); // Pas d'image en cas d'erreur
            System.err.println("Erreur lors du chargement de l'image pour l'événement " + event.getNom() + " : " + e.getMessage());
        }

        // Conteneur pour les informations
        VBox infoContainer = new VBox();
        infoContainer.setSpacing(8);
        infoContainer.setAlignment(Pos.CENTER); // Centrer les éléments horizontalement
        infoContainer.setPrefWidth(500); // Ajuster la largeur pour équilibrer l'espace

        // Titre de l'événement
        Label titleLabel = new Label(event.getNom());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #232946; -fx-alignment: center;");
        titleLabel.setPrefWidth(500); // S'assurer que le label utilise toute la largeur
        titleLabel.setAlignment(Pos.CENTER); // Centrer le texte dans le label

        // Date
        Label dateLabel = new Label("Date: " + event.getDate());
        dateLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #232946; -fx-alignment: center;");
        dateLabel.setPrefWidth(500);
        dateLabel.setAlignment(Pos.CENTER);

        // Lieu
        Label locationLabel = new Label("Lieu: " + event.getLieu());
        locationLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #232946; -fx-alignment: center;");
        locationLabel.setPrefWidth(500);
        locationLabel.setAlignment(Pos.CENTER);

        // Type
        Label typeLabel = new Label("Type: " + event.getType());
        typeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #232946; -fx-alignment: center;");
        typeLabel.setPrefWidth(500);
        typeLabel.setAlignment(Pos.CENTER);

        // Places disponibles
        Label placesLabel = new Label("Places disponibles: " + event.getNbr_places());
        placesLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #232946; -fx-alignment: center;");
        placesLabel.setPrefWidth(500);
        placesLabel.setAlignment(Pos.CENTER);

        // Bouton Afficher
        Button afficherButton = new Button("Afficher");
        afficherButton.setStyle("-fx-background-color: #ff8906; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-padding: 8 15;");

        // Bouton Modifier
        Button modifierButton = new Button("Modifier");
        modifierButton.setStyle("-fx-background-color: #43ea4a; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-padding: 8 15;");

        // Bouton Supprimer
        Button supprimerButton = new Button("Supprimer");
        supprimerButton.setStyle("-fx-background-color: #e84545; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-padding: 8 15;");

        // Conteneur horizontal pour les boutons
        HBox buttonBox = new HBox(10, afficherButton, modifierButton, supprimerButton);
        buttonBox.setAlignment(Pos.CENTER); // Centrer les boutons

        // Ajouter tous les éléments au conteneur d'informations
        infoContainer.getChildren().addAll(titleLabel, dateLabel, locationLabel, typeLabel, placesLabel, buttonBox);

        // Ajouter l'image et les informations à la carte
        card.getChildren().addAll(imageView, infoContainer);

        // Ajouter un effet de survol
        card.setOnMouseEntered(e -> card.setStyle("-fx-background-color: #f8f9fa; -fx-background-radius: 10; -fx-padding: 15;"));
        card.setOnMouseExited(e -> card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-padding: 15;"));

        // Associer les actions aux boutons
        afficherButton.setOnAction(e -> handleAfficherClick(event));
        modifierButton.setOnAction(e -> handleModifierClick(event));
        supprimerButton.setOnAction(e -> handleSupprimerClick(event));

        return card;
    }

    private void handleAfficherClick(Evenement event) {
        try {
            // Charger la vue de détail de l'événement
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/detailevent.fxml"));
            javafx.scene.Parent root = loader.load();

            // Obtenir le contrôleur et passer les données de l'événement
            DetaileventController controller = loader.getController();
            controller.setEventData(event);

            // Créer une nouvelle scène
            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Détails de l'événement");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            showAlert("Erreur", "Impossible de charger les détails de l'événement : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void handleModifierClick(Evenement event) {
        try {
            // Charger la vue de modification de l'événement
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Modifyevent.fxml"));
            javafx.scene.Parent root = loader.load();

            // Obtenir le contrôleur et passer les données de l'événement
            ModifyeventController controller = loader.getController();
            controller.setEventData(event);

            // Créer une nouvelle scène
            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Modifier l'événement");
            stage.setScene(scene);

            // Rafraîchir la liste des événements lorsque la fenêtre de modification est fermée
            stage.setOnHidden(e -> refreshEventList());

            stage.show();
        } catch (Exception ex) {
            showAlert("Erreur", "Impossible de charger la fenêtre de modification : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void handleSupprimerClick(Evenement event) {
        // Afficher une boîte de dialogue de confirmation
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmer la suppression");
        confirmationAlert.setHeaderText("Supprimer l'événement");
        confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer l'événement \"" + event.getNom() + "\" ?");

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                try {
                    // Supprimer l'événement de la base de données
                    evenementService.delete(event);
                    // Rafraîchir la liste des événements
                    refreshEventList();
                    // Afficher un message de succès
                    showAlert("Succès", "L'événement \"" + event.getNom() + "\" a été supprimé avec succès.");
                } catch (Exception ex) {
                    showAlert("Erreur", "Impossible de supprimer l'événement : " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void handleAddClick() {
        try {
            // Charger la vue d'ajout d'événement
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Addevent.fxml"));
            javafx.scene.Parent root = loader.load();

            // Créer une nouvelle scène
            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Ajouter un événement");
            stage.setScene(scene);

            // Rafraîchir la liste des événements lorsque la fenêtre d'ajout est fermée
            stage.setOnHidden(e -> refreshEventList());

            stage.show();
        } catch (Exception ex) {
            showAlert("Erreur", "Impossible de charger la fenêtre d'ajout : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}