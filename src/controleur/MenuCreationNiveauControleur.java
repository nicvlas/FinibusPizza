package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import modele.Niveau;

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
    Spinner nbFacileClient;
    @FXML
    Spinner nbNormalClient;
    @FXML
    Spinner nbKarenClient;
    //Verification si données entrantes, sinon faire ce qu'il y a écrit
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        miseAZero();
        selectDifficulte.getItems().add("Facile");
        selectDifficulte.getItems().add("Normal");
        selectDifficulte.getItems().add("Karen");
    }
    public void verificationExistenceDonnesEntrantes() throws IOException {
    }
    public void miseAZero(){
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 1);
        minNbIng.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 16, 3);

        maxNbIng.setValueFactory(valueFactory1);
        selectDifficulte.getSelectionModel().select(-1);
        SpinnerValueFactory<Integer> valueFactory2 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 16,0);
        SpinnerValueFactory<Integer> valueFactory3 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 16,0);
        SpinnerValueFactory<Integer> valueFactory4 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 16,0);
        nbFacileClient.setValueFactory(valueFactory2);
        nbNormalClient.setValueFactory(valueFactory3);
        nbKarenClient.setValueFactory(valueFactory4);
        SpinnerValueFactory<Integer> valueFactory5 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100,0);
        margeTresor.setValueFactory(valueFactory5);
        verificationValidation();
    }
    //fx:controller="controleur.MenuCreationNiveauControleur"
    //Verifier si il y a au moins une données dans les trois nb des types de clients et si la difficulté a été choisi
    public void verificationValidation(){
        boolean nbClient = Integer.parseInt(nbFacileClient.getValue().toString()) != 0 || Integer.parseInt(nbNormalClient.getValue().toString()) != 0 || Integer.parseInt(nbKarenClient.getValue().toString()) != 0;
        if(nbClient && this.selectDifficulte.getSelectionModel().getSelectedIndex() !=-1){
            btnTester.setDisable(false);
            btnValider.setDisable(false);
            btnValideJouer.setDisable(false);
        } else {
            btnTester.setDisable(true);
            btnValider.setDisable(true);
            btnValideJouer.setDisable(true);

        }
    }
    public void retourMenu(ActionEvent actionEvent) throws IOException {
		Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");
    }

    public void retourMenuPers(ActionEvent actionEvent) throws IOException {
		Main.changementFenetre("../fxml/MenuParametres.fxml", "FinibusPizza : Personnalisation");
    }
    //Donner les données de ce niveau à la page suivant SANS tmp dans le constructeur
    //Enregistrer le niveau dans le fichier pers
    public void validerEtJouer(ActionEvent actionEvent) {
    }
    //Enregistrer le niveau dans le fichier pers
    public void validerCrea(ActionEvent actionEvent) {
    }
    //Donner les données de ce niveau à la page suivant SANS tmp dans le constructeur
    public void tester(ActionEvent actionEvent) {
    }
    //TOut effacer
    public void reset(ActionEvent actionEvent) {
        miseAZero();
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

}
