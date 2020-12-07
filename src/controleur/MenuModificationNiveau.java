package controleur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
	 ChoiceBox<String> mnSelectDifficulte;

	@FXML
	 Spinner<Integer> mnMinIngredient;

	@FXML
	 Spinner<Integer> mnMaxIngredient;

	@FXML
	 Spinner<Float> mnMargeTresorerie;

	@FXML
	 Spinner<Float> mnMargeTemps;

	@FXML
	 Spinner<Integer> mnNbFacile;

	@FXML
	 Spinner<Integer> mnNbNormal;

	@FXML
	 Spinner<Integer> mnNbKaren;

	@FXML
	 ChoiceBox<String> mnSelectNomNiveau;

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
		ArrayList<String[]>listNiveau = listeNiveau();
		mnSelectNomNiveau.getItems().add("Select");
		mnSelectNomNiveau.getSelectionModel().select(0);
		for(int i = 0; i<listNiveau.size(); i++) {
			mnSelectNomNiveau.getItems().add(listNiveau.get(i)[0]);
		}
		mnModifier.setDisable(true);
		mnModifier.setDisable(true);
	}
	
	private ArrayList<String[]> listeNiveau() {
        ArrayList<String[]> listNiveau = null;
        try {
        	listNiveau = new ArrayList<String[]>();
            //lire le fichier
            FileReader file = new FileReader(getClass().getResource("../textes/niveaux").getFile());
            BufferedReader buffer = new BufferedReader(file);
            String niveauDeLaListe = buffer.readLine();
            // parcourir le fichier
            while (niveauDeLaListe != null) {
            	listNiveau.add(elementsNiveau(niveauDeLaListe));
            	niveauDeLaListe = buffer.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNiveau;
    }
	
	private String[] elementsNiveau(String element) {
        String[] retour = element.split( "/" );
        if(retour.length != 8) {
            throw new InternalError(element + " ne correspond pas ï¿½ un String d'un fichier contenant des ingrï¿½dients traitables");
        }
        return retour;
    }

}
