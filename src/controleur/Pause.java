package controleur;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Niveau;

public class Pause {

    @FXML
    private AnchorPane lbl_pause;

    @FXML
    private Button btn_recommencer;

    @FXML
    private Button btn_quitter;

    @FXML
    private Button btn_fermer;

    public static Niveau niv;

    @FXML
    void fermer(ActionEvent event) throws IOException {
        // get a handle to the stage
        NiveauAPCControleur.pause = false;
        Stage stage = (Stage) btn_quitter.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void quitterPartie(ActionEvent event) throws IOException {
        NiveauAPCControleur.arret = true;
        // get a handle to the stage
        Stage stage = (Stage) btn_quitter.getScene().getWindow();
        // do what you have to do
        stage.close();

        Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");
    }

    @FXML
    void restart(ActionEvent event) throws IOException {
        NiveauAPCControleur.arret = true;
		Main.changementFenetre("../fxml/MenuListeNiveau.fxml", "FinibusPizza : Liste niveaux");
        Stage stage = (Stage) btn_recommencer.getScene().getWindow();
        stage.close();
    }
}
