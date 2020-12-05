package controleur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

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
    Spinner<Integer> prjPourboireFacile;

    @FXML
    Spinner<Integer> prjPourboireNormal;

    @FXML
    Spinner<Integer> prjPourboireKaren;

    @FXML
    Spinner<Double> prjMargeFacile;

    @FXML
    Spinner<Double> prjMargeNormal;

    @FXML
    Spinner<Double> prjMargeKaren;

    @FXML
    Spinner<Integer> prjMinIngredientFacile;

    @FXML
    Spinner<Integer> prjMinIngredientNormal;

    @FXML
    Spinner<Integer> prjMinIngredientKaren;

    @FXML
    Spinner<Integer> prjMaxIngredientFacile;

    @FXML
    Spinner<Integer> prjMaxIngredientNormal;

    @FXML
    Spinner<Integer> prjMaxIngredientKaren;

    @FXML
    Spinner<Integer> prjPrixAchatOignons;
    
    @FXML
    Spinner<Integer> prjPrixAchatChampignons;

    @FXML
    Spinner<Integer> prjPrixAchatOeufs;

    @FXML
    Spinner<Integer> prjPrixVenteOignons;

    @FXML
    Spinner<Integer> prjPrixVenteChampignons;

    @FXML
    Spinner<Integer> prjPrixVenteOeufs;

    @FXML
    Spinner<Integer> prjPrixAchatBacon;

    @FXML
    Spinner<Integer> prjPrixVenteBacon;

    @FXML
    Spinner<Integer> prjPrixAchatPoulet;

    @FXML
    Spinner<Integer> prjPrixVentePoulet;

    @FXML
    Spinner<Integer> prjPrixAchatChorizo;

    @FXML
    Spinner<Integer> prjPrixAchatFrommages;

    @FXML
    Spinner<Integer> prjPrixAchatTomates;

    @FXML
    Spinner<Integer> prjPrixVenteChorizo;

    @FXML
    Spinner<Integer> prjPrixVenteFrommages;

    @FXML
    Spinner<Integer> prjPrixVenteTomates;

    @FXML
    Spinner<Integer> prjPrixAchatCreme;

    @FXML
    Spinner<Integer> prjPrixVenteCreme;

    @FXML
    Spinner<Integer> prjPrixAchatSauceTomate;

    @FXML
    Spinner<Integer> prjPrixVenteSauceTomate;

 
    /**
     * Réablit les valeurs par défaut
     */
    @FXML
    public void resetDefaut() {
    	//setValue(1) pour l'instant, à voir car problème avec la récupération de la valeur initiale
        prjPrixAchatOignons.getValueFactory().setValue(1);
        prjPrixAchatChampignons.getValueFactory().setValue(1);
        prjPrixAchatOeufs.getValueFactory().setValue(2);
        prjPrixVenteOignons.getValueFactory().setValue(2);
        prjPrixVenteChampignons.getValueFactory().setValue(2);
        prjPrixVenteOeufs.getValueFactory().setValue(3);
        prjPrixAchatBacon.getValueFactory().setValue(3);
        prjPrixVenteBacon.getValueFactory().setValue(4);
        prjPrixAchatPoulet.getValueFactory().setValue(3);
        prjPrixVentePoulet.getValueFactory().setValue(4);
        prjPrixAchatChorizo.getValueFactory().setValue(3);
        prjPrixAchatFrommages.getValueFactory().setValue(2);
        prjPrixAchatTomates.getValueFactory().setValue(1);
        prjPrixVenteChorizo.getValueFactory().setValue(4);
        prjPrixVenteFrommages.getValueFactory().setValue(3);
        prjPrixVenteTomates.getValueFactory().setValue(2);
        prjPrixAchatCreme.getValueFactory().setValue(2);
        prjPrixVenteCreme.getValueFactory().setValue(3);
        prjPrixAchatSauceTomate.getValueFactory().setValue(2);
        prjPrixVenteSauceTomate.getValueFactory().setValue(3);
        
        prjPourboireFacile.getValueFactory().setValue(10);
        prjPourboireNormal.getValueFactory().setValue(10);
        prjPourboireKaren.getValueFactory().setValue(10);
        
        prjMargeFacile.getValueFactory().setValue(1.2);
        prjMargeNormal.getValueFactory().setValue(1.1);
        prjMargeKaren.getValueFactory().setValue(1.0);
        
        prjMinIngredientFacile.getValueFactory().setValue(2);
        prjMinIngredientNormal.getValueFactory().setValue(3);
        prjMinIngredientKaren.getValueFactory().setValue(4);
        
        prjMaxIngredientFacile.getValueFactory().setValue(3);
        prjMaxIngredientNormal.getValueFactory().setValue(4);
        prjMaxIngredientKaren.getValueFactory().setValue(5);
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
		System.out.println(prjPrixAchatOignons.getValueFactory().getValue());
	}

}
