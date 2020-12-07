package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import modele.Difficulte;
import modele.Niveau;

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
    private int nb1TypeClient = 0;
	private int nb2TypeClient = 0;
	private int nb3TypeClient = 0;
	private Float margeTresor = 0f;
	private int minIng = 0;
	private int maxIng = 0;
    
	public static Niveau niv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            throw new InternalError(element + " ne correspond pas ï¿½ un String d'un fichier contenant des ingrï¿½dients traitables");
        }
        return retour;
    }
    public void validationChoixListNivPerso(){
    	int index = comboListNivPerso.getSelectionModel().getSelectedIndex();
    	String[] niveauSelect = tmp1.get(index);
    	String nom = niveauSelect[0];
    	Difficulte diff;
    	if(niveauSelect[1] == "Facile")
    		diff = Difficulte.Facile;
    	else if(niveauSelect[1] == "Normal")
    		diff = Difficulte.Normal;
    	else
    		diff = Difficulte.Karen;
    	try {
    		int nb1TypeClient = Integer.parseInt(niveauSelect[2]);
        	int nb2TypeClient = Integer.parseInt(niveauSelect[3]);
        	int nb3TypeClient = Integer.parseInt(niveauSelect[4]);
        	Float margeTresor = Float.parseFloat(niveauSelect[5]);
        	int minIng = Integer.parseInt(niveauSelect[6]);
        	int maxIng = Integer.parseInt(niveauSelect[7]);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    	niv = new Niveau(nom, diff, nb1TypeClient, nb2TypeClient, nb3TypeClient, margeTresor, minIng, maxIng,false);
    	//Main.changementFenetre("../fxml/Niveau.fxml", "FinibusPizza : Niveau");
    }
    
    public void choixListNivPerso(){
        if(this.comboListNivPerso.getSelectionModel().getSelectedIndex() != 0){
            this.comboListNivClass.setDisable(true);
            btnListNivPerso.setDisable(false);
        } else {
            this.comboListNivClass.setDisable(false);
            btnListNivPerso.setDisable(true);
        }
    }
    public void ListNivClassChoix(){
        if(this.comboListNivClass.getSelectionModel().getSelectedIndex() != 0){
            this.comboListNivPerso.setDisable(true);
            btnListNivClass.setDisable(false);
        } else {
            this.comboListNivPerso.setDisable(false);
            btnListNivClass.setDisable(true);
        }
    }
    public void validationChoixNivClass() throws IOException{
    	int index = comboListNivClass.getSelectionModel().getSelectedIndex();
    	String[] niveauSelect = tmp.get(index);
    	String nom = niveauSelect[0];
    	Difficulte diff;
    	if(niveauSelect[1] == "Facile")
    		diff = Difficulte.Facile;
    	else if(niveauSelect[1] == "Normal")
    		diff = Difficulte.Normal;
    	else
    		diff = Difficulte.Karen;
    	try {
    		int nb1TypeClient = Integer.parseInt(niveauSelect[2]);
        	int nb2TypeClient = Integer.parseInt(niveauSelect[3]);
        	int nb3TypeClient = Integer.parseInt(niveauSelect[4]);
        	Float margeTresor = Float.parseFloat(niveauSelect[5]);
        	int minIng = Integer.parseInt(niveauSelect[6]);
        	int maxIng = Integer.parseInt(niveauSelect[7]);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    	niv = new Niveau(nom, diff, nb1TypeClient, nb2TypeClient, nb3TypeClient, margeTresor, minIng, maxIng,false);
    	//Main.changementFenetre("../fxml/Niveau.fxml", "FinibusPizza : Niveau");
    }

}
