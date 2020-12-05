package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import modele.Niveau;

public class MenuCreationNiveauControleur implements Initializable {
    //Pour tester, créer fichier temporaire contenant le niveau créer. SI la page est fermée, on ne fait rien et ça s'effacera. Si l'utilisateur appuye
    //sur effacer dans la page d'edition, on efface le fichier et reset l'interface. si il est validé on récupère la donnée si elle est dans un fichier tmp.
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
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 1);
        minNbIng.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 16, 3);

        minNbIng.setValueFactory(valueFactory1);
        selectDifficulte.getItems().add("");
        selectDifficulte.getItems().add("Normal");
        selectDifficulte.getItems().add("Karen");
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

    public void minChangement(){
        SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.parseInt(minNbIng.getValue().toString())+1, 16,Integer.parseInt(maxNbIng.getValue().toString()));
        maxNbIng.setValueFactory(valueFactory1);
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,Integer.parseInt(maxNbIng.getValue().toString()),Integer.parseInt(minNbIng.getValue().toString()));
        minNbIng.setValueFactory(valueFactory);

    }
    public void maxChangement(){
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,Integer.parseInt(maxNbIng.getValue().toString()),Integer.parseInt(minNbIng.getValue().toString()));
        minNbIng.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.parseInt(minNbIng.getValue().toString())+1, 16,Integer.parseInt(maxNbIng.getValue().toString()));
        maxNbIng.setValueFactory(valueFactory1);
    }

    public void margeTresorChangement(MouseEvent mouseEvent) {
    }

    public void margeTempsChangement(MouseEvent mouseEvent) {
    }

    public void nbFacileChangement(MouseEvent mouseEvent) {
    }

    public void nbNormalChangement(MouseEvent mouseEvent) {
    }

    public void nbKarenChangement(MouseEvent mouseEvent) {
    }
}
