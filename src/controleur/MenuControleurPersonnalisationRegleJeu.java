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
    public Spinner<Integer> prjPrixAchatOignons = new Spinner<Integer>(1,12,10);
    
    @FXML
    Spinner<Integer> prjPrixAchatChampignons= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixAchatOeufs= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixVenteOignons= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixVenteChampignons= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixVenteOeufs= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixAchatBacon= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixVenteBacon= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixAchatPoulet= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixVentePoulet= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixAchatChorizo= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixAchatFrommages= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixAchatTomates= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixVenteChorizo= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixVenteFrommages= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixVenteTomates= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixAchatCreme= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixVenteCreme= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixAchatSauceTomate= new Spinner<Integer>(1,12,10);

    @FXML
    Spinner<Integer> prjPrixVenteSauceTomate= new Spinner<Integer>(1,12,10);

 
    /**
     * Réablit les valeurs par défaut
     */
    @FXML
    public void resetDefaut() {
    	//setValue(1) pour l'instant, à voir car problème avec la récupération de la valeur initiale
        prjPrixAchatOignons.getValueFactory().setValue(1);
        prjPrixAchatChampignons.getValueFactory().setValue(1);
        prjPrixAchatOeufs.getValueFactory().setValue(1);
        prjPrixVenteOignons.getValueFactory().setValue(1);
        prjPrixVenteChampignons.getValueFactory().setValue(1);
        prjPrixVenteOeufs.getValueFactory().setValue(1);
        prjPrixAchatBacon.getValueFactory().setValue(1);
        prjPrixVenteBacon.getValueFactory().setValue(1);
        prjPrixAchatPoulet.getValueFactory().setValue(1);
        prjPrixVentePoulet.getValueFactory().setValue(1);
        prjPrixAchatChorizo.getValueFactory().setValue(1);
        prjPrixAchatFrommages.getValueFactory().setValue(1);
        prjPrixAchatTomates.getValueFactory().setValue(1);
        prjPrixVenteChorizo.getValueFactory().setValue(1);
        prjPrixVenteFrommages.getValueFactory().setValue(1);
        prjPrixVenteTomates.getValueFactory().setValue(1);
        prjPrixAchatCreme.getValueFactory().setValue(1);
        prjPrixVenteCreme.getValueFactory().setValue(1);
        prjPrixAchatSauceTomate.getValueFactory().setValue(1);
        prjPrixVenteSauceTomate.getValueFactory().setValue(1);
    }

    /**
     * Retourne au menu principal
     * @param event
     * @throws IOException
     */
    @FXML
    public void retourMenu(ActionEvent event) throws IOException {
		Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");

    }

    /**
     * Retourne au menu de la personnalisation
     * @param event
     * @throws IOException
     */
    @FXML
    public void retourPersonnalisation(ActionEvent event) throws IOException {
		Main.changementFenetre("../fxml/MenuParametres.fxml", "FinibusPizza : Personnalisation");

    }

    /**
     * Valide les paramètres
     * @param event
     */
    @FXML
    public void valider(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
