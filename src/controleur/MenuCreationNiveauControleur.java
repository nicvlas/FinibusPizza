package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import modele.Difficulte;
import modele.Niveau;

public class MenuCreationNiveauControleur implements Initializable{
    @FXML
    Label avertissement;
    @FXML
    Label reussite;
    @FXML
    Button btnReset;
    @FXML
    Button btnValider;
    @FXML
    Label nomNiveau;
    @FXML
    ComboBox selectDifficulte;
    @FXML
    Spinner minNbIng;
    @FXML
    Spinner maxNbIng;
    @FXML
    Spinner margeTresor;
    @FXML
    Spinner nbFacileClient;
    @FXML
    Spinner nbNormalClient;
    @FXML
    Spinner nbKarenClient;
    static Niveau niv;
    //Verification si données entrantes, sinon faire ce qu'il y a écrit
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        miseAZero();
        selectDifficulte.getItems().add("Facile");
        selectDifficulte.getItems().add("Normal");
        selectDifficulte.getItems().add("Karen");
        nomNivADonner();
    }
    public static Niveau getNiv(){
        return niv;
    }
    public void setNiv(boolean t){
        String difficulteTmp = this.selectDifficulte.getSelectionModel().getSelectedItem().toString();
        Difficulte difficulte = null;
        switch(difficulteTmp){
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
        int nb1TypeClient = (int) this.nbFacileClient.getValue();
        int nb2TypeClient = (int) this.nbNormalClient.getValue();
        int nb3TypeClient = (int) this.nbKarenClient.getValue();
        float margeTresor = (float)(Float.valueOf(this.margeTresor.getValue().toString())/100);
        int minIng = (int) this.minNbIng.getValue();
        int maxIng = (int) this.maxNbIng.getValue();
        niv = new Niveau(this.nomNiveau.getText().toString(),
                difficulte,
                nb1TypeClient,
                nb2TypeClient,
                nb3TypeClient,
                margeTresor + 1,
                minIng,
                maxIng,
                t);

    }
    //A faire lors création du controlleur du jeu, inclure dans le initialize !!!
    public void verificationExistenceDonnesEntrantes() throws IOException {
    }
    public void miseAZero(){
        this.reussite.setOpacity(0);
        this.avertissement.setOpacity(0);
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 14, 1);
        minNbIng.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 16, 3);

        maxNbIng.setValueFactory(valueFactory1);
        selectDifficulte.getSelectionModel().select(-1);
        SpinnerValueFactory<Integer> valueFactory2 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 16,0);
        SpinnerValueFactory<Integer> valueFactory3 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 16,0);
        SpinnerValueFactory<Integer> valueFactory4 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 16,0);
        nbFacileClient.setValueFactory(valueFactory2);
        nbNormalClient.setValueFactory(valueFactory3);
        nbKarenClient.setValueFactory(valueFactory4);
        SpinnerValueFactory<Integer> valueFactory5 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100,0);
        margeTresor.setValueFactory(valueFactory5);
        verificationValidation();
    }
    //fx:controller="controleur.MenuCreationNiveauControleur"
    //Verifier si il y a au moins une données dans les trois nb des types de clients et si la difficulté a été choisi
    public void verificationValidation(){
        boolean nbClient = Integer.parseInt(nbFacileClient.getValue().toString()) != 0 || Integer.parseInt(nbNormalClient.getValue().toString()) != 0 || Integer.parseInt(nbKarenClient.getValue().toString()) != 0;
        if(nbClient && this.selectDifficulte.getSelectionModel().getSelectedIndex() !=-1){

            btnValider.setDisable(false);
        } else {
            btnValider.setDisable(true);

        }
    }
    public void retourMenu(ActionEvent actionEvent) throws IOException {
		Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");
    }

    public void retourMenuPers(ActionEvent actionEvent) throws IOException {
		Main.changementFenetre("../fxml/MenuParametres.fxml", "FinibusPizza : Personnalisation");
    }
    private ArrayList<String> listeNiveauPers() {
        ArrayList<String> tmp = null;
        try {
            tmp = new ArrayList<String>();
            //lire le fichier
            FileReader file = new FileReader(getClass().getResource("../textes/niveauxPers.txt").getFile());
            BufferedReader buffer = new BufferedReader(file);
            String tmpB = buffer.readLine();
            // parcourir le fichier
            while (tmpB != null) {
                tmp.add(tmpB);
                tmpB = buffer.readLine();
            }
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tmp;
    }
    public boolean verificationExistanceNiv() throws IOException {
        String nomFichier=getClass().getResource("../textes/niveauxPers.txt").getFile();
        File file = new File(nomFichier);
        long filenb = file.length();
        System.out.println(filenb + "Taille");
        if(filenb == 0)
        {
            return false;
        }
        ArrayList<String> tmpList = listeNiveauPers();
        String difficulte = this.selectDifficulte.getSelectionModel().getSelectedItem().toString();
        String nb1TypeClient = this.nbFacileClient.getValue() + "";
        String nb2TypeClient = this.nbNormalClient.getValue() + "";
        String nb3TypeClient = this.nbKarenClient.getValue() + "";
        String margeTresor = (float)(Float.valueOf(this.margeTresor.getValue().toString())/100 + 1) + "";
        String minIng = this.minNbIng.getValue() + "";
        String maxIng = this.maxNbIng.getValue() + "";
        String niveauS = difficulte + "/" + nb1TypeClient + "/" + nb2TypeClient + "/" + nb3TypeClient + "/" + margeTresor + "/" + minIng + "/" + maxIng;
        for(int i = 0; i < tmpList.size(); i++){
            System.out.println(i);
            System.out.println(tmpList.get(i));
            System.out.println(niveauS);
            System.out.println(tmpList.get(i).replaceAll("^[^/]*/",""));
            if(niveauS.compareTo(tmpList.get(i).replaceAll("^[^/]*/","").toString()) == 0){
                return true;
            }
        }
        return false;
    }
    private String[] elementsNiveau(String element) {
        String[] retour = element.split( "/" );
        if(retour.length != 8) {
            throw new InternalError(element + " ne correspond pas � un String d'un fichier contenant des ingr�dients traitables");
        }
        return retour;
    }
    private void nomNivADonner(){
        if(listeNiveauPers().size() != 0) {
            String[] tmp = elementsNiveau(listeNiveauPers().get(listeNiveauPers().size() - 1));
            int tmp1 = Integer.valueOf(tmp[0].replaceAll("Niveau", "")) + 1;
            this.nomNiveau.setText("Niveau" + tmp1);
        } else {
            this.nomNiveau.setText("Niveau1");
        }
    }
    //Enregistrer le niveau dans le fichier
    public void validerCrea() throws IOException {
        if (verificationExistanceNiv()){
            this.avertissement.setOpacity(1);
            this.reussite.setOpacity(0);
        } else {
            String difficulte = this.selectDifficulte.getSelectionModel().getSelectedItem().toString();
            String nb1TypeClient = this.nbFacileClient.getValue() + "";
            String nb2TypeClient = this.nbNormalClient.getValue() + "";
            String nb3TypeClient = this.nbKarenClient.getValue() + "";
            String margeTresor = (float)(Float.valueOf(this.margeTresor.getValue().toString())/100 + 1) + "";
            String minIng = this.minNbIng.getValue() + "";
            String maxIng = this.maxNbIng.getValue() + "";

            String niveauS = this.nomNiveau.getText() + "/" + difficulte + "/" + nb1TypeClient + "/" + nb2TypeClient + "/" + nb3TypeClient + "/" + margeTresor + "/" + minIng + "/" + maxIng;
            System.out.println(niveauS);
            //Enregistrement dans fichier niveauxPers
            String nomFichier=getClass().getResource("../textes/niveauxPers.txt").getFile();
            File file = new File(nomFichier);
            long filenb = file.length();
            FileWriter fw = new FileWriter(nomFichier,true);
            fw.write("\n"+niveauS);
            fw.close();
            nomNivADonner();
            this.reussite.setOpacity(1);
            this.avertissement.setOpacity(0);
        }
    }
    //TOut effacer
    public void reset(ActionEvent actionEvent) {
        miseAZero();
    }

    public void minChangement(){
        SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.parseInt(minNbIng.getValue().toString())+1, 16,Integer.parseInt(maxNbIng.getValue().toString()));
        maxNbIng.setValueFactory(valueFactory1);
            int max = (Integer.parseInt(maxNbIng.getValue().toString()) > 14)?14:Integer.parseInt(maxNbIng.getValue().toString());
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,max,Integer.parseInt(minNbIng.getValue().toString()));
        minNbIng.setValueFactory(valueFactory);

    }
    public void maxChangement(){
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,Integer.parseInt(maxNbIng.getValue().toString()),Integer.parseInt(minNbIng.getValue().toString()));
        minNbIng.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.parseInt(minNbIng.getValue().toString())+1, 16,Integer.parseInt(maxNbIng.getValue().toString()));
        maxNbIng.setValueFactory(valueFactory1);
    }

}
