package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import modele.Niveau;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    static Niveau niveau;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String[]> tmp = listeNiveau();
        comboListNivClass.getItems().add("Select");
        comboListNivPerso.getItems().add("Select");
        comboListNivClass.getSelectionModel().select(0);
        comboListNivPerso.getSelectionModel().select(0);
        for(int i = 0; i < tmp.size(); i++){
            comboListNivClass.getItems().add(tmp.get(i)[0] + " " + tmp.get(i)[1]);
        }
        ArrayList<String[]> tmp1 = listeNiveauPers();
        for(int i = 0; i < tmp1.size(); i++){
            comboListNivPerso.getItems().add(tmp1.get(i)[0] + " " + tmp1.get(i)[1]);
        }
        btnListNivClass.setDisable(true);
        btnListNivPerso.setDisable(true);
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
            throw new InternalError(element + " ne correspond pas � un String d'un fichier contenant des ingr�dients traitables");
        }
        return retour;
    }
    /*public void setNiv(){
        niveau = new Niveau()
    }*/
    public Niveau getNiv(){
        return niveau;
    }
    //A faire avec le setNiv
    public void validationChoixListNivPerso(){

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
    //A faire avec le setNiv
    public void validationChoixNivClass(){

    }

}
