package controleur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;

public class MenuControleurPersonnalisationRegleJeu implements Initializable{

    @FXML
    Button prjRetourPersonnalisation;

    @FXML
    Button prjRetourMenu;

    @FXML
    Button prjValider;

    @FXML
    Button prjParDefaut;

    @FXML
    Spinner<?> prjPourboireFacile;

    @FXML
    Spinner<?> prjPourboireNormal;

    @FXML
    Spinner<?> prjPourboireKaren;

    @FXML
    Spinner<?> prjMargeFacile;

    @FXML
    Spinner<?> prjMargeNormal;

    @FXML
    Spinner<?> prjMargeKaren;

    @FXML
    Spinner<?> prjMinIngredientFacile;

    @FXML
    Spinner<?> prjMinIngredientNormal;

    @FXML
    Spinner<?> prjMinIngredientKaren;

    @FXML
    Spinner<?> prjMaxIngredientFacile;

    @FXML
    Spinner<?> prjMaxIngredientNormal;

    @FXML
    Spinner<?> prjMaxIngredientKaren;

    @FXML
    Spinner<?> prjPrixAchatOignons;

    @FXML
    Spinner<?> prjPrixAchatChampignons;

    @FXML
    Spinner<?> prjPrixAchatOeufs;

    @FXML
    Spinner<?> prjPrixVenteOignons;

    @FXML
    Spinner<?> prjPrixVenteChampignons;

    @FXML
    Spinner<?> prjPrixVenteOeufs;

    @FXML
    Spinner<?> prjPrixAchatBacon;

    @FXML
    Spinner<?> prjPrixVenteBacon;

    @FXML
    Spinner<?> prjPrixAchatPoulet;

    @FXML
    Spinner<?> prjPrixVentePoulet;

    @FXML
    Spinner<?> prjPrixAchatChorizo;

    @FXML
    Spinner<?> prjPrixAchatFrommages;

    @FXML
    Spinner<?> prjPrixAchatTomates;

    @FXML
    Spinner<?> prjPrixVenteChorizo;

    @FXML
    Spinner<?> prjPrixVenteFrommages;

    @FXML
    Spinner<?> prjPrixVenteTomates;

    @FXML
    Spinner<?> prjPrixAchatCreme;

    @FXML
    Spinner<?> prjPrixVenteCreme;

    @FXML
    Spinner<?> prjPrixAchatSauceTomate;

    @FXML
    Spinner<?> prjPrixVenteSauceTomate;

    @FXML
    public void reset(ActionEvent event) {

    }

    @FXML
    public void retourMenu(ActionEvent event) throws IOException {
		Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");

    }

    @FXML
    public void retourPersonnalisation(ActionEvent event) throws IOException {
		Main.changementFenetre("../fxml/MenuParametres.fxml", "FinibusPizza : Personnalisation");

    }

    @FXML
    public void valider(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
