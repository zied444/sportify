package controllers.utilisateur;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class Programmes {

    @FXML
    private TableView<?> programTable;

    @FXML
    private TableColumn<?, ?> colProgramme;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colNiveau;

    @FXML
    private Button subscribeButton;

    @FXML
    private Button backButton;

    @FXML
    private void handleSubscribe(ActionEvent event) {
        // TODO: Inscrire au programme sélectionné
    }

    @FXML
    private void handleBack(ActionEvent event) {
        // TODO: Retourner au Dashboard
    }
}
