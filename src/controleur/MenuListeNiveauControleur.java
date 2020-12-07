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
        comboListNivPerso.setVisibleRowCount(6);
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
    public Niveau getNiv(){
        return niveau;
    }
    //A faire avec le setNiv
    public void setNivPers(){
        ArrayList<String[]> listenv = listeNiveauPers();
        String[] niveauS = listenv.get(this.comboListNivPerso.getSelectionModel().getSelectedIndex());
        Difficulte difficulte = null;
        switch( niveauS[1]){
            case "Normal":
                difficulte = Difficulte.Normal;
                break;
            case "Karen":
                difficulte = Difficulte.Karen;
                break;
            default:
                difficulte = Difficulte.Facile;
                break;
        }
        niveau = new Niveau(niveauS[0], difficulte,Integer.valueOf(niveauS[2]),Integer.valueOf(niveauS[3]),Integer.valueOf(niveauS[4]), Float.valueOf(niveauS[5]),Integer.valueOf(niveauS[6]),Integer.valueOf(niveauS[7]),false);
                //9
    }
    public void setNivClass(){
        ArrayList<String[]> listenv = listeNiveau();
        String[] niveauS = listenv.get(this.comboListNivClass.getSelectionModel().getSelectedIndex());
        Difficulte difficulte = null;
        switch( niveauS[1]){
            case "Normal":
                difficulte = Difficulte.Normal;
                break;
            case "Karen":
                difficulte = Difficulte.Karen;
                break;
            default:
                difficulte = Difficulte.Facile;
                break;
        }
        niveau = new Niveau(niveauS[0], difficulte,Integer.valueOf(niveauS[2]),Integer.valueOf(niveauS[3]),Integer.valueOf(niveauS[4]), Float.valueOf(niveauS[5]),Integer.valueOf(niveauS[6]),Integer.valueOf(niveauS[7]),false);
        //9
    }
    public void validationChoixListNivPerso() throws IOException {
        setNivPers();
        Main.changementFenetre("../NiveauAvantPriseCommande.fxml", "FinibusPizza : Jeu");
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
    public void validationChoixNivClass() throws IOException {
        setNivClass();
        Main.changementFenetre("../NiveauAvantPriseCommande.fxml", "FinibusPizza : Jeu");
    }

}
