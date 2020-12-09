package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import modele.Difficulte;
import modele.Niveau;
//import sun.tools.tree.ThisExpression;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import com.sun.javafx.scene.web.Printable;

public class MenuListeNiveauControleur implements Initializable {
    @FXML
    Button btnListNivClass;
    @FXML
    ComboBox comboListNivClass;
    @FXML
    Button btnListNivPerso;
    @FXML
    ComboBox comboListNivPerso;
    @FXML
    Button btnRetourMenu;

    private String[] niveau;
    
    private ArrayList<String[]> tmp;
    private ArrayList<String[]> tmp1;
    
    
    private String nom = "";
    private String difficulteString;
    private Difficulte difficulte;
    private int nb1TypeClient = 0;
	private int nb2TypeClient = 0;
	private int nb3TypeClient = 0;
	private Float margeTresor = 0f;
	private int minIng = 0;
	private int maxIng = 0;
    
	public static Niveau niv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	niv = null;
        tmp = listeNiveau();
        comboListNivClass.getItems().add("Select");
        comboListNivPerso.getItems().add("Select");
        comboListNivClass.getSelectionModel().select(0);
        comboListNivPerso.getSelectionModel().select(0);
        for(int i = 0; i < tmp.size(); i++){
            comboListNivClass.getItems().add(tmp.get(i)[0] + " " + tmp.get(i)[1]);
        }
        tmp1 = listeNiveauPers();
        for(int i = 0; i < tmp1.size(); i++){
            comboListNivPerso.getItems().add(tmp1.get(i)[0] + " " + tmp1.get(i)[1]);
        }
        btnListNivClass.setDisable(true);
        btnListNivPerso.setDisable(true);
        comboListNivClass.setVisibleRowCount(6);
    }

    public void retourMenu() throws IOException {
        Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");
    }
    /*
     * Récupération des niveaux classiques dans le fichier de sauvegarde
     */
    private ArrayList<String[]> listeNiveau() {
        ArrayList<String[]> tmp = null;
        try {
            tmp = new ArrayList<String[]>();
            //lire le fichier
            FileReader file = new FileReader(getClass().getResource("../textes/niveaux.txt").getFile());
            BufferedReader buffer = new BufferedReader(file);
            String tmpB = buffer.readLine();
            // parcourir le fichier
            while (tmpB != null) {
                tmp.add(elementsNiveau(tmpB));
                tmpB = buffer.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
    }
    /*
     * Récupération des niveaux personnalisés dans le fichier de sauvegarde
     */
    private ArrayList<String[]> listeNiveauPers() {
        ArrayList<String[]> tmp = null;
        try {
            tmp = new ArrayList<String[]>();
            //lire le fichier
            FileReader file = new FileReader(getClass().getResource("../textes/niveauxPers.txt").getFile());
            BufferedReader buffer = new BufferedReader(file);
            String tmpB = buffer.readLine();
            // parcourir le fichier
            while (tmpB != null) {
                tmp.add(elementsNiveau(tmpB));
                tmpB = buffer.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
    }
    private String[] elementsNiveau(String element) {
        String[] retour = element.split( "/" );
        if(retour.length != 8) {
            throw new InternalError(element + " ne correspond pas Ã¯Â¿Â½ un String d'un fichier contenant des ingrÃ¯Â¿Â½dients traitables");
        }
        return retour;
    }
    /*
     * Choix du niveau dans la ComboBox niveau personnalisé
     */
    public void choixListNivPerso(){
        if(this.comboListNivPerso.getSelectionModel().getSelectedIndex() != 0){
            this.comboListNivClass.setDisable(true);
            btnListNivPerso.setDisable(false);
            //Récupération du niveau personnalisé
            niveau = tmp1.get(this.comboListNivPerso.getSelectionModel().getSelectedIndex()-1);
        } else {
            this.comboListNivClass.setDisable(false);
            btnListNivPerso.setDisable(true);
        }
    }
    /*
     * Choix du niveau dans la ComboBox niveau classique
     */
    public void ListNivClassChoix(){
        if(this.comboListNivClass.getSelectionModel().getSelectedIndex() != 0){
            this.comboListNivPerso.setDisable(true);
            btnListNivClass.setDisable(false);
          //Récupération du niveau classique
            niveau = tmp.get(this.comboListNivClass.getSelectionModel().getSelectedIndex()-1);
        } else {
            this.comboListNivPerso.setDisable(false);
            btnListNivClass.setDisable(true);
        }
    }
    
    /*
     * Validation du choix d'un niveau classique
     */
    public void validationChoixNivClass(){
    	//Rempli les éléments d'un niveau et créer un niveau
    	try {
    		nom = niveau[0];
        	difficulteString = niveau[1];
        	switch(difficulteString) {
        	case "Facile":difficulte =Difficulte.Facile;break;
        	case "Normal":difficulte =Difficulte.Normal;break;
        	case "Karen":difficulte =Difficulte.Karen;break;
        	}
        	nb1TypeClient = Integer.parseInt(niveau[2]);
        	nb2TypeClient = Integer.parseInt(niveau[3]);
        	nb3TypeClient = Integer.parseInt(niveau[4]);
        	margeTresor = Float.parseFloat(niveau[5]);
        	minIng = Integer.parseInt(niveau[6]);
        	maxIng = Integer.parseInt(niveau[7]);
        	System.out.println(nom+" "+difficulte+" "+nb1TypeClient+" "+nb2TypeClient+" "+nb3TypeClient+" "+margeTresor+" "+minIng+" "+maxIng);
        	niv = new Niveau(nom, difficulte, nb1TypeClient, nb2TypeClient, nb3TypeClient, margeTresor, minIng, maxIng, true);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
    
    /*
     * Validation du choix d'un niveau personnalisé
     */
    public void validationChoixListNivPerso(){
    	//Rempli les éléments d'un niveau et créer un niveau
    	try {
    		nom = niveau[0];
        	difficulteString = niveau[1];
        	switch(difficulteString) {
        	case "Facile":difficulte =Difficulte.Facile;break;
        	case "Normal":difficulte =Difficulte.Normal;break;
        	case "Karen":difficulte =Difficulte.Karen;break;
        	}
        	nb1TypeClient = Integer.parseInt(niveau[2]);
        	nb2TypeClient = Integer.parseInt(niveau[3]);
        	nb3TypeClient = Integer.parseInt(niveau[4]);
        	margeTresor = Float.parseFloat(niveau[5]);
        	minIng = Integer.parseInt(niveau[6]);
        	maxIng = Integer.parseInt(niveau[7]);
        	System.out.println(nom+" "+difficulte+" "+nb1TypeClient+" "+nb2TypeClient+" "+nb3TypeClient+" "+margeTresor+" "+minIng+" "+maxIng);
        	niv = new Niveau(nom, difficulte, nb1TypeClient, nb2TypeClient, nb3TypeClient, margeTresor, minIng, maxIng, true);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
}
