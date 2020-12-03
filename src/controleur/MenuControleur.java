package controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuControleur implements Initializable {
    @FXML
    Button btnMenuPers;
    @FXML
    Button btnQuitterApp;
    @FXML
    Button btnListeNiveau;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }
    public void affichageListeNiveau(){

    }
    public void affichagePers(){

    }
    public void quitterApp(){
        // get a handle to the stage
        Stage stage = (Stage) btnQuitterApp.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
