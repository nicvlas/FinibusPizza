package controleur;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.event.IIOReadUpdateListener;

//import com.sun.management.internal.VMOptionCompositeData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.fxml.Initializable;

public class MenuModificationNiveau implements Initializable {

	@FXML
	 Label mnNomDuNiveau;

	@FXML
	 ComboBox<String> mnSelectDifficulte;

	@FXML
	 Spinner<Integer> mnMinIngredient;

	@FXML
	 Spinner<Integer> mnMaxIngredient;

	@FXML
	 Spinner<Integer> mnMargeTresorerie;

	@FXML
	 Spinner<Integer> mnNbFacile;

	@FXML
	 Spinner<Integer> mnNbNormal;

	@FXML
	 Spinner<Integer> mnNbKaren;

	@FXML
	ComboBox<String> mnSelectNomNiveau;
	
	@FXML
	ComboBox<String> mnSelectNomNiveauPers;

	@FXML
	 Button mnModifier;

	@FXML
	 Button mnSupprimer;

	@FXML
	 Button mnRetourPersonnsalisation;

	@FXML
	 Button mnRetourMenu;
	
	private static File file;
	private static ArrayList<String[]> listNiveau;
	private static ArrayList<String[]> listNiveauPers;
	private String nomDuNiveau;
	private String difficulte;
	private int nb1TypeClient;
	private int nb2TypeClient;
	private int nb3TypeClient;
	private int margeTresor;
	private int minIngredient;
	private int maxIngredient;
	
    public void modifier(ActionEvent event) {
    	updateValues();
    	//Contenu du fichier texte
    	String contenuFichier = "";
    	Float margeTresorerie = margeTresor/10f;
    	// Si c'est une modification de niveau classique ou personnalisé -> remplacer les éléments
    	if(this.mnSelectNomNiveau.getSelectionModel().getSelectedIndex() != 0) {
    		file = new File(getClass().getResource("../textes/niveaux.txt").getFile());
    		for(String[] t : listNiveau) {
    			if(t[0].equals(nomDuNiveau))
    				contenuFichier += ""+nomDuNiveau+"/"+difficulte+"/"+nb1TypeClient+"/"+nb2TypeClient+"/"+nb3TypeClient+"/"+margeTresorerie+"/"+minIngredient+"/"+maxIngredient+"\n";
    			else
    				contenuFichier += ""+t[0]+"/"+t[1]+"/"+t[2]+"/"+t[3]+"/"+t[4]+"/"+t[5]+"/"+t[6]+"/"+t[7]+"\n";
    		}
    	}
    	else {
    		file = new File(getClass().getResource("../textes/niveauxPers.txt").getFile());
    		for(String[] t : listNiveauPers) {
    			if(t[0].equals(nomDuNiveau)) 
    				contenuFichier += ""+nomDuNiveau+"/"+difficulte+"/"+nb1TypeClient+"/"+nb2TypeClient+"/"+nb3TypeClient+"/"+margeTresorerie+"/"+minIngredient+"/"+maxIngredient+"\n";
    			else
    				contenuFichier += ""+t[0]+"/"+t[1]+"/"+t[2]+"/"+t[3]+"/"+t[4]+"/"+t[5]+"/"+t[6]+"/"+t[7]+"\n";
    		}
    	}
    	try {
    		FileWriter fileWriter = new FileWriter(file, false);
    		fileWriter.write(contenuFichier);
    		fileWriter.close();
    	}
    	catch (IOException e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
    /*
     * Retour à la fenêtre du menu
     */
    public void retourMenu(ActionEvent e) throws IOException {
		Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");
    }
    /*
     * Retour à la fenêtre de personnalisation
     */
    public void retourPersonnalisation() throws IOException {
		Main.changementFenetre("../fxml/MenuParametres.fxml", "FinibusPizza : Personnalisation");
    }
    /*
     * Supprimer le niveau sélectionné
     */
    public void suppr(ActionEvent event) {
    	String contenuFichier = "";
    	// Si c'est une suppression de niveau classique ou personnalisé -> écraser le fichier sans le niveau choisi
    	if(this.mnSelectNomNiveau.getSelectionModel().getSelectedIndex() != 0) {
    		file = new File(getClass().getResource("../textes/niveaux.txt").getFile());
    		for(String[] t : listNiveau) {
    			if(!t[0].equals(nomDuNiveau))
    				contenuFichier += ""+t[0]+"/"+t[1]+"/"+t[2]+"/"+t[3]+"/"+t[4]+"/"+t[5]+"/"+t[6]+"/"+t[7]+"\n";
    		}
    		this.mnSelectNomNiveau.getSelectionModel().select(0);
    	}
    	else {
    		file = new File(getClass().getResource("../textes/niveauxPers.txt").getFile());
    		for(String[] t : listNiveauPers) {
    			if(!t[0].equals(nomDuNiveau)) 
    				contenuFichier += ""+t[0]+"/"+t[1]+"/"+t[2]+"/"+t[3]+"/"+t[4]+"/"+t[5]+"/"+t[6]+"/"+t[7]+"\n";
    		}
    		this.mnSelectNomNiveauPers.getSelectionModel().select(0);
    	}
    	try {
    		FileWriter fileWriter = new FileWriter(file, false);
    		fileWriter.write(contenuFichier);
    		fileWriter.close();
    	}
    	catch (IOException e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	updateList();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		updateList();
		mnSelectDifficulte.getItems().add("Facile");
		mnSelectDifficulte.getItems().add("Normal");
		mnSelectDifficulte.getItems().add("Karen");
	}
	/*
	 * Mettre à jour les données du niveau modifié
	 */
	private void updateValues() {
		difficulte = mnSelectDifficulte.getValue();
		nb1TypeClient = mnNbFacile.getValue();
		nb2TypeClient = mnNbNormal.getValue();
		nb3TypeClient = mnNbKaren.getValue();
		margeTresor = mnMargeTresorerie.getValue();
		minIngredient = mnMinIngredient.getValue();
		maxIngredient = mnMaxIngredient.getValue();
	}
	/*
	 * Mettre à jour les listes de niveaux
	 */
	private void updateList() {
		listNiveau = listeNiveau();
		listNiveauPers = listeNiveauPers();
		mnSelectNomNiveau.getItems().clear();
		mnSelectNomNiveau.getItems().add("Select");
		mnSelectNomNiveauPers.getItems().clear();
		mnSelectNomNiveauPers.getItems().add("Select");
		mnSelectNomNiveau.getSelectionModel().select(0);
		mnSelectNomNiveauPers.getSelectionModel().select(0);
		for(int i = 0; i<listNiveau.size(); i++) {
			mnSelectNomNiveau.getItems().add(listNiveau.get(i)[0]);
		}
		for(int i = 0; i<listNiveauPers.size(); i++) {
			mnSelectNomNiveauPers.getItems().add(listNiveau.get(i)[0]);
		}
		mnModifier.setDisable(true);
		mnSupprimer.setDisable(true);
	}
	/*
	 * Récupération des données du fichier des niveaux classiques
	 */
	private ArrayList<String[]> listeNiveau() {
        ArrayList<String[]> listNiveau = null;
        try {
        	listNiveau = new ArrayList<String[]>();
            //lire le fichier
            FileReader file = new FileReader(getClass().getResource("../textes/niveaux.txt").getFile());
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
	/*
	 * Récupération des données du fichier des niveaux personnalisés
	 */
	private ArrayList<String[]> listeNiveauPers(){
		ArrayList<String[]> listeNiveau = null;
        try {
        	listeNiveau = new ArrayList<String[]>();
            //lire le fichier
            FileReader file = new FileReader(getClass().getResource("../textes/niveauxPers.txt").getFile());
            BufferedReader buffer = new BufferedReader(file);
            String niveauDeLaListe = buffer.readLine();
            // parcourir le fichier
            while (niveauDeLaListe != null) {
            	listeNiveau.add(elementsNiveau(niveauDeLaListe));
            	niveauDeLaListe = buffer.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeNiveau;
	}
	
	private String[] elementsNiveau(String element) {
        String[] retour = element.split( "/" );
        if(retour.length != 8) {
            throw new InternalError(element + " ne correspond pas Ã¯Â¿Â½ un String d'un fichier contenant des ingrÃ¯Â¿Â½dients traitables");
        }
        return retour;
    }
	/*
	 * Place les éléments modifiables selon le choix du niveau classique
	 */
	public void ListNivChoix(ActionEvent event) {
		if(this.mnSelectNomNiveau.getSelectionModel().getSelectedIndex() == 0) {
			mnModifier.setDisable(true);
			mnSupprimer.setDisable(true);
		}
		else {
			mnSelectNomNiveauPers.getSelectionModel().select(0);
			mnModifier.setDisable(false);
			mnSupprimer.setDisable(false);
			nomDuNiveau = this.mnSelectNomNiveau.getSelectionModel().getSelectedItem();
			//System.out.println(niveauChoisi);
			for(String[] t : listNiveau) {
				//le niveau a été trouvé, placement des éléments dans les controls
				if(t[0].equals(nomDuNiveau)) {
					this.mnNomDuNiveau.setText(nomDuNiveau);
					if(t[1].equals("Facile")){
						difficulte = "Facile";
						this.mnSelectDifficulte.getSelectionModel().select(0);
					}
					else if(t[1].equals("Normal")) {
						difficulte = "Normal";
						this.mnSelectDifficulte.getSelectionModel().select(1);
					}
					else {
						difficulte = "Karen";
						this.mnSelectDifficulte.getSelectionModel().select(2);
					}
					nb1TypeClient = Integer.parseInt(t[2]);
					nb2TypeClient = Integer.parseInt(t[3]);
					nb3TypeClient = Integer.parseInt(t[4]);
					margeTresor = Math.round((Float.parseFloat(t[5])*10));
					minIngredient = Integer.parseInt(t[6]);
					maxIngredient = Integer.parseInt(t[7]);
					this.mnNbFacile.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,nb1TypeClient));
					this.mnNbNormal.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,nb2TypeClient));
					this.mnNbKaren.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,nb3TypeClient));
					this.mnMargeTresorerie.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, margeTresor));
					this.mnMinIngredient.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,minIngredient));
					this.mnMaxIngredient.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,maxIngredient));
				}
		
			}
		}
	}
	/*
	 * Place les éléments modifiables selon le choix du niveau personnalisé
	 */
	public void ListNivChoixPers(ActionEvent event)
	{
		if(this.mnSelectNomNiveauPers.getSelectionModel().getSelectedIndex() == 0) {
			mnModifier.setDisable(true);
			mnSupprimer.setDisable(true);
		}
		else {
			mnSelectNomNiveau.getSelectionModel().select(0);
			mnModifier.setDisable(false);
			mnSupprimer.setDisable(false);
			nomDuNiveau = this.mnSelectNomNiveauPers.getSelectionModel().getSelectedItem();
			//System.out.println(niveauChoisi);
			for(String[] t : listNiveauPers) {
				//le niveau a été trouvé, placement des éléments dans les controls
				if(t[0].equals(nomDuNiveau)) {
					this.mnNomDuNiveau.setText(nomDuNiveau);
					if(t[1].equals("Facile")){
						difficulte = "Facile";
						this.mnSelectDifficulte.getSelectionModel().select(0);
					}
					else if(t[1].equals("Normal")) {
						difficulte = "Normal";
						this.mnSelectDifficulte.getSelectionModel().select(1);
					}
					else {
						difficulte = "Karen";
						this.mnSelectDifficulte.getSelectionModel().select(2);
					}
					nb1TypeClient = Integer.parseInt(t[2]);
					nb2TypeClient = Integer.parseInt(t[3]);
					nb3TypeClient = Integer.parseInt(t[4]);
					margeTresor = Math.round((Float.parseFloat(t[5])*10));
					minIngredient = Integer.parseInt(t[6]);
					maxIngredient = Integer.parseInt(t[7]);
					this.mnNbFacile.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,nb1TypeClient));
					this.mnNbNormal.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,nb2TypeClient));
					this.mnNbKaren.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,nb3TypeClient));
					this.mnMargeTresorerie.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, margeTresor));
					this.mnMinIngredient.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,minIngredient));
					this.mnMaxIngredient.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,maxIngredient));
				}
		
			}
		}
	}
}
