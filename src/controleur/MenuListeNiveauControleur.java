package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> tmp = listeNomNiveau();
        for(int i = 0; i < tmp.size(); i++){
            comboListNivClass.getItems().add(tmp.get(i));
        }
        ArrayList<String> tmp1 = listeNomNiveauPers();
        for(int i = 0; i < tmp1.size(); i++){
            comboListNivPerso.getItems().add(tmp1.get(i));
        }
    }

    public void retourMenu(){

    }
    private ArrayList<String> listeNomNiveau(){
        ArrayList<String[]> tmp  = listeNiveau();
        ArrayList<String> tmp1 = new ArrayList<String>();
        for(int i = 0; i < tmp.size(); i++){
            tmp1.add(tmp.get(i)[0]);
        }
        return tmp1;
    }
    private ArrayList<String> listeNomNiveauPers(){
        ArrayList<String[]> tmp  = listeNiveauPers();
        ArrayList<String> tmp1 = new ArrayList<String>();
        for(int i = 0; i < tmp.size(); i++){
            tmp1.add(tmp.get(i)[0]);
        }
        return tmp1;
    }
    private ArrayList<String[]> listeNiveau() {
        ArrayList<String[]> tmp = null;
        try {
            tmp = new ArrayList<String[]>();
            //lire le fichier
            FileReader file = new FileReader(getClass().getResource("../textes/niveaux").getFile());
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
            FileReader file = new FileReader(getClass().getResource("../textes/niveauxPers").getFile());
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
            throw new InternalError(element + " ne correspond pas � un String d'un fichier contenant des ingr�dients traitables");
        }
        return retour;
    }
    public void validationChoixListNivPerso(){

    }
    public void choixListNivPerso(){

    }
    public void ListNivClassChoix(){

    }
    public void validationChoixNivClass(){

    }

}
