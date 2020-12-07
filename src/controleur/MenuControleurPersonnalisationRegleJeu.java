package controleur;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    Spinner<Double> prjPrixVenteCreme;

    @FXML
    Spinner<Integer> prjPrixAchatSauceTomate;

    @FXML
    Spinner<Double> prjPrixVenteSauceTomate;
    
    @FXML
    Spinner<Integer> prjPrixAchatPate;

    @FXML
    Spinner<Integer> prjPrixVentePate;


 
    /**
     * Réablit les valeurs par défaut
     * @throws IOException 
     */
    @FXML
    public void resetDefaut() throws IOException {    	
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
        prjPrixVenteCreme.getValueFactory().setValue(2.5);
        prjPrixAchatSauceTomate.getValueFactory().setValue(1);
        prjPrixVenteSauceTomate.getValueFactory().setValue(1.5);
        
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
        
        prjPrixVentePate.getValueFactory().setValue(7);
        prjPrixAchatPate.getValueFactory().setValue(5);
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
    	try {
    		//bases
		   String bases = "tomateBase/"+prjPrixAchatSauceTomate.getValue()+"/"+prjPrixVenteSauceTomate.getValue()+"\n" +
				   			"crèmeBase/"+prjPrixAchatCreme.getValue()+"/"+prjPrixVenteCreme.getValue();

		   File fileBases = new File("src/textes/bases.txt");

		   // créer le fichier s'il n'existe pas
		   if (!fileBases.exists()) {
			   fileBases.createNewFile();
		   }

		   FileWriter fwBases = new FileWriter(fileBases.getAbsoluteFile());
		   BufferedWriter bwBases = new BufferedWriter(fwBases);
		   bwBases.write(bases);
		   bwBases.close();
		   
		   System.out.println("Modification terminée bases !");


		   //ingrédients
		   String ingredients = "oignon/"+prjPrixAchatOignons.getValue()+"/"+prjPrixVenteOignons.getValue()+"\n" +
		   			"champignon/"+prjPrixAchatChampignons.getValue()+"/"+prjPrixVenteChampignons.getValue()+"\n" +
		   			"oeuf/"+prjPrixAchatOeufs.getValue()+"/"+prjPrixVenteOeufs.getValue()+"\n" +
		   			"Chorizo/"+prjPrixAchatChorizo.getValue()+"/"+prjPrixVenteChorizo.getValue()+"\n" +
		   			"Poulet/"+prjPrixAchatPoulet.getValue()+"/"+prjPrixVentePoulet.getValue()+"\n" +
		   			"Bacon/"+prjPrixAchatBacon.getValue()+"/"+prjPrixVenteBacon.getValue()+"\n" +
		   			"fromage/"+prjPrixAchatFrommages.getValue()+"/"+prjPrixVenteFrommages.getValue()+"\n" +
		   			"tomate/"+prjPrixAchatTomates.getValue()+"/"+prjPrixVenteTomates.getValue();

			File fileIngredients = new File("src/textes/ingredients.txt");
			
			// créer le fichier s'il n'existe pas
			if (!fileIngredients.exists()) {
				fileIngredients.createNewFile();
			}
			
			FileWriter fwIngredient = new FileWriter(fileIngredients.getAbsoluteFile());
			BufferedWriter bwIngredient = new BufferedWriter(fwIngredient);
			bwIngredient.write(ingredients);
			bwIngredient.close();

		   System.out.println("Modification terminée ingrédient!");
		   
		   //pâte
		   String pate = "Pate/"+prjPrixAchatPate.getValue()+"/"+prjPrixVentePate.getValue();

		   File filePate = new File("src/textes/pates.txt");
			
			// créer le fichier s'il n'existe pas
			if (!filePate.exists()) {
				filePate.createNewFile();
			}
			
			FileWriter fwPate = new FileWriter(filePate.getAbsoluteFile());
			BufferedWriter bwPate = new BufferedWriter(fwPate);
			bwPate.write(pate);
			bwPate.close();

		   System.out.println("Modification terminée pâte !");
		   
		   //clients
		   String client = "Facile/"+prjPourboireFacile.getValue()+"/"+prjMargeFacile.getValue()+"/"+prjMinIngredientFacile.getValue()+"/"+prjMaxIngredientFacile.getValue()+"\n" +
				   			"Normal/"+prjPourboireNormal.getValue()+"/"+prjMargeNormal.getValue()+"/"+prjMinIngredientNormal.getValue()+"/"+prjMaxIngredientNormal.getValue()+"\n" +
				   			"Karen/"+prjPourboireKaren.getValue()+"/"+prjMargeKaren.getValue()+"/"+prjMinIngredientKaren.getValue()+"/"+prjMaxIngredientKaren.getValue();

			File fileClient = new File("src/textes/clients.txt");
			
			// créer le fichier s'il n'existe pas
			if (!fileClient.exists()) {
				fileClient.createNewFile();
			}
			
			FileWriter fwClients = new FileWriter(fileClient.getAbsoluteFile());
			BufferedWriter bwClients = new BufferedWriter(fwClients);
			bwClients.write(client);
			bwClients.close();

		   System.out.println("Modification terminée clients !");
		   
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//Récupération des lignes ingrédients
		try {
			BufferedReader ing = new BufferedReader(new FileReader("src/textes/ingredients.txt"));
			
			String lineOignons = ing.readLine();
			String[] tabOignons = lineOignons.split("/");
			prjPrixAchatOignons.getValueFactory().setValue(Integer.parseInt(tabOignons[1]));
			prjPrixVenteOignons.getValueFactory().setValue(Integer.parseInt(tabOignons[2]));
			
			String lineChampignons = ing.readLine();
			String[] tabChampignons = lineChampignons.split("/");
			prjPrixAchatChampignons.getValueFactory().setValue(Integer.parseInt(tabChampignons[1]));
			prjPrixVenteChampignons.getValueFactory().setValue(Integer.parseInt(tabChampignons[2]));
			
			String lineOeufs = ing.readLine();
			String[] tabOeufs = lineOeufs.split("/");
			prjPrixAchatOeufs.getValueFactory().setValue(Integer.parseInt(tabOeufs[1]));
			prjPrixVenteOeufs.getValueFactory().setValue(Integer.parseInt(tabOeufs[2]));
			
			String lineChorizo = ing.readLine();
			String[] tabChorizo = lineChorizo.split("/");
			prjPrixAchatChorizo.getValueFactory().setValue(Integer.parseInt(tabChorizo[1]));
			prjPrixVenteChorizo.getValueFactory().setValue(Integer.parseInt(tabChorizo[2]));
			
			String linePoulet = ing.readLine();
			String[] tabPoulet = linePoulet.split("/");
			prjPrixAchatPoulet.getValueFactory().setValue(Integer.parseInt(tabPoulet[1]));
			prjPrixVentePoulet.getValueFactory().setValue(Integer.parseInt(tabPoulet[2]));
			
			String lineBacon = ing.readLine();
			String[] tabBacon = lineBacon.split("/");
			prjPrixAchatBacon.getValueFactory().setValue(Integer.parseInt(tabBacon[1]));
			prjPrixVenteBacon.getValueFactory().setValue(Integer.parseInt(tabBacon[2]));
			
			String lineFromage = ing.readLine();
			String[] tabFromage = lineFromage.split("/");
			prjPrixAchatFrommages.getValueFactory().setValue(Integer.parseInt(tabFromage[1]));
			prjPrixVenteFrommages.getValueFactory().setValue(Integer.parseInt(tabFromage[2]));
			
			String lineTomate = ing.readLine();
			String[] tabTomate = lineTomate.split("/");
			prjPrixAchatTomates.getValueFactory().setValue(Integer.parseInt(tabTomate[1]));
			prjPrixVenteTomates.getValueFactory().setValue(Integer.parseInt(tabTomate[2]));
			
			ing.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Récupération lignes clients
		try {
			BufferedReader cli = new BufferedReader(new FileReader("src/textes/clients.txt"));
			
			String lineFacile = cli.readLine();
			String[] tabFacile = lineFacile.split("/");
			prjMargeFacile.getValueFactory().setValue(Double.parseDouble(tabFacile[1]));
			prjPourboireFacile.getValueFactory().setValue(Integer.parseInt(tabFacile[2]));
			prjMinIngredientFacile.getValueFactory().setValue(Integer.parseInt(tabFacile[3]));
			prjMaxIngredientFacile.getValueFactory().setValue(Integer.parseInt(tabFacile[4]));

			String lineNormal = cli.readLine();
			String[] tabNormal = lineNormal.split("/");
			prjMargeNormal.getValueFactory().setValue(Double.parseDouble(tabNormal[1]));
			prjPourboireNormal.getValueFactory().setValue(Integer.parseInt(tabNormal[2]));
			prjMinIngredientNormal.getValueFactory().setValue(Integer.parseInt(tabNormal[3]));
			prjMaxIngredientNormal.getValueFactory().setValue(Integer.parseInt(tabNormal[4]));
			
			String lineKaren = cli.readLine();
			String[] tabKaren = lineKaren.split("/");
			prjMargeKaren.getValueFactory().setValue(Double.parseDouble(tabKaren[1]));
			prjPourboireKaren.getValueFactory().setValue(Integer.parseInt(tabKaren[2]));
			prjMinIngredientKaren.getValueFactory().setValue(Integer.parseInt(tabKaren[3]));
			prjMaxIngredientKaren.getValueFactory().setValue(Integer.parseInt(tabKaren[4]));
			
			cli.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Récupération ligne pate
		try {
			BufferedReader pate = new BufferedReader(new FileReader("src/textes/pates.txt"));
			
			String linePate = pate.readLine();
			String[] tabPate= linePate.split("/");
			
			prjPrixAchatPate.getValueFactory().setValue(Integer.parseInt(tabPate[1]));
			prjPrixVentePate.getValueFactory().setValue(Integer.parseInt(tabPate[2]));

			pate.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Récupération lignes bases
		try {
			BufferedReader bases = new BufferedReader(new FileReader("src/textes/bases.txt"));
			
			String lineBaseTomate = bases.readLine();
			String[] tabBaseTomate= lineBaseTomate.split("/");
			prjPrixAchatSauceTomate.getValueFactory().setValue(Integer.parseInt(tabBaseTomate[1]));
			prjPrixVenteSauceTomate.getValueFactory().setValue(Double.parseDouble(tabBaseTomate[2]));
			
			String lineBaseCreme = bases.readLine();
			String[] tabBaseCreme= lineBaseCreme.split("/");
			prjPrixAchatCreme.getValueFactory().setValue(Integer.parseInt(tabBaseCreme[1]));
			prjPrixVenteCreme.getValueFactory().setValue(Double.parseDouble(tabBaseTomate[2]));
	
			bases.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}