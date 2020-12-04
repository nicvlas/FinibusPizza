package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

import javafx.fxml.Initializable;

public class MenuModificationNiveau implements Initializable {

	@FXML
	 Label mnNomDuNiveau;

	@FXML
	 ChoiceBox<?> mnSelectDifficulte;

	@FXML
	 Spinner<?> mnMinIngredient;

	@FXML
	 Spinner<?> mnMaxIngredient;

	@FXML
	 Spinner<?> mnMargeTresorerie;

	@FXML
	 Spinner<?> mnMargeTemps;

	@FXML
	 Spinner<?> mnNbFacile;

	@FXML
	 Spinner<?> mnNbNormal;

	@FXML
	 Spinner<?> mnNbKaren;

	@FXML
	 ChoiceBox<?> mnSelectNomNiveau;

	@FXML
	 Button mnModifier;

	@FXML
	 Button mnSupprimer;

	@FXML
	 Button mnRetourPersonnsalisation;

	@FXML
	 Button mnRetourMenu;
	
    public void modifier(ActionEvent event) {

    }

    public void retourMenu(ActionEvent e) throws IOException {
		Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");
    }

    public void retourPersonnalisation() throws IOException {
		Main.changementFenetre("../fxml/MenuParametres.fxml", "FinibusPizza : Personnalisation");
    }

    public void suppr(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
