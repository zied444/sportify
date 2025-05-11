package Controllers;

import Models.Evenement;
import Services.EvenementService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;

public class AddeventController {

    @FXML
    private DatePicker txtdate;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtlieu;

    @FXML
    private TextField txtnbplaces;

    @FXML
    private TextField txtnom;

    @FXML
    private ComboBox<String> txttype;

    private EvenementService evenementService;

    public void initialize() {
        evenementService = new EvenementService();
        // Initialiser les types d'événements possibles
        txttype.getItems().addAll("Course", "Marathon", "Tournoi", "Entraînement", "Autre");
    }

    @FXML
    void Ajouter(ActionEvent event) {
        try {
            // Vérifier que tous les champs sont remplis
            if (txtnom.getText().isEmpty() || txtlieu.getText().isEmpty() ||
                txtdate.getValue() == null || txttype.getValue() == null ||
                txtdescription.getText().isEmpty() || txtnbplaces.getText().isEmpty()) {
                showAlert("Erreur", "Veuillez remplir tous les champs");
                return;
            }

            // Créer un nouvel événement
            Evenement evenement = new Evenement();
            evenement.setNom(txtnom.getText());
            evenement.setLieu(txtlieu.getText());
            evenement.setDate(Date.valueOf(txtdate.getValue()));
            evenement.setType(txttype.getValue());
            evenement.setDescription(txtdescription.getText());
            evenement.setNbr_places(Integer.parseInt(txtnbplaces.getText()));
            evenement.setUtilisateur_id(1); // À remplacer par l'ID de l'utilisateur connecté

            // Ajouter l'événement
            evenementService.create(evenement);

            // Afficher un message de succès
            showAlert("Succès", "L'événement a été ajouté avec succès");

            // Réinitialiser les champs
            clearFields();

        } catch (NumberFormatException e) {
            showAlert("Erreur", "Le nombre de places doit être un nombre valide");
        } catch (Exception e) {
            showAlert("Erreur", e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        txtnom.clear();
        txtlieu.clear();
        txtdate.setValue(null);
        txttype.setValue(null);
        txtdescription.clear();
        txtnbplaces.clear();
    }
}
