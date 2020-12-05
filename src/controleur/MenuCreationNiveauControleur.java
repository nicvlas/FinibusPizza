package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuCreationNiveauControleur implements Initializable {
    @FXML
    Button btnReset;
    @FXML
    Button btnTester;
    @FXML
    Button btnValider;
    @FXML
    Button btnValideJouer;
    @FXML
    Label nomNiveau;
    @FXML
    ComboBox selectDifficulte;
    @FXML
    Spinner minNbIng;
    @FXML
    Spinner maxNbIng;
    @FXML
    Spinner margeTresor;
    @FXML
    Spinner margeTemps;
    @FXML
    Spinner nbFacileClient;
    @FXML
    Spinner nbNormalClient;
    @FXML
    Spinner nbKarenClient;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void verificationValidation(){

    }
    public void retourMenu(ActionEvent actionEvent) throws IOException {
		Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");
    }

    public void retourMenuPers(ActionEvent actionEvent) throws IOException {
		Main.changementFenetre("../fxml/MenuParametres.fxml", "FinibusPizza : Personnalisation");
    }

    public void choixDifficulte(ActionEvent actionEvent) {
    }

    public void validerEtJouer(ActionEvent actionEvent) {
    }

    public void validerCrea(ActionEvent actionEvent) {
    }

    public void tester(ActionEvent actionEvent) {
    }

    public void reset(ActionEvent actionEvent) {
    }
}
