package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControleurEcranVictoire {

	 	@FXML
	    private Label lbl_nomniveau;

	    @FXML
	    private Button btn_recommencer;

	    @FXML
	    private Button btn_fermer;

	    @FXML
	    private Label lbl_etatvictoire;

	    @FXML
	    Button btn_suivant_edit;

	    @FXML
	    Pane pane1etoile;

	    @FXML
	    ImageView img_etoile1;

	    @FXML
	    Pane pane3etoiles;

	    @FXML
	    ImageView img_etoile31;

	    @FXML
	    ImageView img_etoile33;

	    @FXML
	    ImageView img_etoile32;

	    @FXML
	    Pane pane2etoiles;

	    @FXML
	    ImageView img_etoile21;

	    @FXML
	    ImageView img_etoile22;



	@FXML
	void fermer(ActionEvent event) {
        Stage stage = (Stage) btn_fermer.getScene().getWindow();
        stage.close();
	}

	@FXML
	void recommencer(ActionEvent event) throws IOException {
		Main.changementFenetre("../fxml/MenuListeNiveau.fxml", "FinibusPizza : Liste niveaux");
        Stage stage = (Stage) btn_recommencer.getScene().getWindow();
        stage.close();
	}

	@FXML
	void suivant_edit(ActionEvent event) throws IOException {
		if(btn_suivant_edit.getText()=="Suivant") {
			Main.changementFenetre("../fxml/MenuListeNiveau.fxml", "FinibusPizza : Liste niveaux");
		}
		else{
			Main.changementFenetre("../fxml/ModificationDeNiveau.fxml", "FinibusPizza : Modification de niveau");
		}

        Stage stage = (Stage) btn_suivant_edit.getScene().getWindow();
        stage.close();
	}

    public void initialize(URL location, ResourceBundle resources) {

    }
	/**
	 * Affiche le nombre d'étoiles nécessaire
	 *
	 * @param nb : nombre d'étoiles à afficher
	 */
	public void setVisibleEtoile(Integer nb) {
		switch (nb) {
			case 0: {
				break;
			}
			case 1: {
				pane1etoile.setVisible(true);
				break;
			}
			case 2: {
				pane2etoiles.setVisible(true);
				break;
			}
			case 3: {
				pane3etoiles.setVisible(true);
				break;
			}
		}
	}

	void setLabelNiveau(String txt) {
		lbl_nomniveau.setText(txt);
	}

	void setLabelEtat(String txt) {
		lbl_etatvictoire.setText(txt);
	}

	void setBtnText(String txt) {
		btn_suivant_edit.setText(txt);
	}

}
