package Controllers;

import Models.Evenement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DetaileventController {

    @FXML
    private Label titleLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label lieuLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label placesLabel;
    @FXML
    private ImageView eventImageView;

    public void setEventData(Evenement event) {
        // Mettre à jour le titre
        titleLabel.setText(event.getNom());

        // Mettre à jour les informations
        nomLabel.setText(event.getNom());
        dateLabel.setText(event.getDate().toString());
        lieuLabel.setText(event.getLieu());
        typeLabel.setText(event.getType());
        placesLabel.setText(String.valueOf(event.getNbr_places()));

        // Vous pouvez ajouter une image par défaut ou charger une image spécifique
        // eventImageView.setImage(new Image("/images/default-event.jpg"));
    }

    @FXML
    private void handleClose() {
        // Fermer la fenêtre
        Stage stage = (Stage) titleLabel.getScene().getWindow();
        stage.close();
    }
}