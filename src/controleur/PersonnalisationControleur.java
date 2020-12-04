package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PersonnalisationControleur implements Initializable{
	
	@FXML
	Button btnMenuCreationNiveau;
	@FXML
	Button btnMenuModifNiv;
	@FXML
	Button btnPersoRegleGen;
	@FXML
	Button btnRetourMenu;
	
	public void affichageMenuCreationNiveau() throws IOException {
		Main.changementFenetre("../fxml/MenuCreationNiveau.fxml", "FinibusPizza : Création niveau");
	}
	
	public void affichageMenuModifNiv() throws IOException {
		Main.changementFenetre("../fxml/ModificationDeNiveau.fxml", "FinibusPizza : Modification de niveau");
	}
	
	public void AffichageMenuPersoRegleGen() throws IOException {
		Main.changementFenetre("../fxml/PersonnalisationRegleDeJeu.fxml", "FinibusPizza : Personnalisation règles générales");
	}
	
	public void retourMenu() throws IOException {
		Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
