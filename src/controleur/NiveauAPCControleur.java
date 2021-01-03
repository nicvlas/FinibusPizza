package controleur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

import java.util.Optional;
import java.io.File;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Client;
import modele.Commande;
import modele.Difficulte;
import modele.Ingredients;
import modele.Niveau;
import modele.Pate;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import java.util.*;

public class NiveauAPCControleur implements Initializable {
	
	@FXML
	Button btn_c1;
	@FXML
	Button btn_c2;
	@FXML
	Button btn_c3;
	@FXML
	ImageView img_c1;
	@FXML
	ImageView img_c2;
	@FXML
	ImageView img_c3;
	@FXML
    public Button btn_preppate;
	@FXML
    public Button btn_preppizza;
	@FXML
    public Button btn_gestion;
	@FXML
	public Button btn_cuisson;
	@FXML
	Label oignonNb;
	@FXML
    Label baconNb;
	@FXML
    Label fromageNb;
	@FXML
    Label champignonNb;
	@FXML
    Label pouletNb;
	@FXML
    Label tomateNb;
	@FXML
    Label oeufNb;
	@FXML
    Label chorizoNb;
	@FXML
	Label lbl_nomClient1;
	@FXML
	Label lbl_nomClient2;
	@FXML
	Label lbl_nomClient3;
	@FXML
    public ImageView base;
	
	@FXML
	public Button btn_menu;
	@FXML
    public Label lbl_temps;
    @FXML
	Label lbl_argent;
	@FXML
	Label nbClientRestant;
	
	@FXML
	Label c1Temps;
	@FXML
	Label c2Temps;
	@FXML
	Label c3Temps;
	@FXML
	ImageView img_principale;
	@FXML
	ImageView img_oignons;
	@FXML
	ImageView img_oeufs;
	@FXML
	ImageView img_poulet;
	@FXML
	ImageView img_fromage;
	@FXML
	ImageView img_creme;
	@FXML
	ImageView img_champignons;
	@FXML
	ImageView img_bacon;
	@FXML
	ImageView img_chorizo;
	@FXML
	ImageView img_tomates;
	@FXML
	ImageView img_sauceTomate;
	@FXML
	ImageView img_oignonPizza;
	@FXML
	ImageView img_oeufPizza;
	@FXML
	ImageView img_pouletPizza;
	@FXML
	ImageView img_fromagePizza;
	@FXML
	ImageView img_cremePizza;
	@FXML
	ImageView img_champignonPizza;
	@FXML
	ImageView img_baconPizza;
	@FXML
	ImageView img_chorizoPizza;
	@FXML
	ImageView img_tomatePizza;
	@FXML
	ImageView img_sauceTomatePizza;
	@FXML
	Label txt_oignons;
	@FXML
	Label txt_oeufs;
	@FXML
	Label txt_poulet;
	@FXML
	Label txt_fromage;
	@FXML
	Label txt_creme;
	@FXML
	Label txt_chapignon;
	@FXML
	Label txt_bacon;
	@FXML
	Label txt_chorizo;
	@FXML
	Label txt_tomate;
	@FXML
	Label txt_sauceTomate;
	@FXML
	Label txt_AVBase;
	@FXML
	Label lbl_resumePizza;
	@FXML
	Label lbl_prixVentePizza;
	@FXML
	Label txt_choixClientDebut;
	@FXML
	Label lblErreurPetrir;
	@FXML
	Button btnValiderPizza;
	@FXML
	Button btnSupprimer_Pizza;
	@FXML
	Pane paneCuisson;
	@FXML
	Pane panePrepPizza;
	@FXML
	Pane panePrepPate;
	@FXML
	Pane paneGestionPizza;
	@FXML
	private Label timerLabel = new Label();
	@FXML
	private Label lblPetrissage;
	@FXML
	private Button btnStop;
	@FXML
	private Button btnPetrir;
	@FXML
	private Button btnPause;

	private static Pate pateActuelle;

	private long start;
	private static final Integer STARTTIME = 5;
	private Timeline timeline;
	private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
	
	private static Commande[] lesCommandes;
	private static Niveau niveauActuel;
	private Button commandeClient;
	private ArrayList<Commande> clientsRestant;
	private int nbOignonsPizza;
	private int nbOeufsPizza;
	private int nbPouletPizza;
	private int nbFromagePizza;
	private int nbChampignonPizza;
	private int nbBaconPizza;
	private int nbChorizoPizza;
	private int nbTomatePizza;
	private int nbSauceTomatePizza;
	private int nbCremePizza;
	
    public Commande pizzaEnCours;
    public Commande pizzaEnCoursDePreparation;
    public Commande pizzaEnCoursDeCuisson;
    public Commande misDeCote;
    public int interval;
    public Timer timer;
    public Button btnValidationPizzaCote;
    public Button btnretourCommande;
    public Label textPizzaCompare;
    public ProgressBar progressBarCuisson;
    public Button btnCuisson;
    public Button btnSupprimerPizza;
    public Button btnSauvegardePizza;
    //A changer en false quand on quitte le menu pause !!!
    public static boolean pause = false;
    public static boolean arret = false;
    private static int scorecuisson;
    public Integer[] tempsCuisson;
    public ProgressBar progressBarMarg;
    private Timer timerC;
    private int cuissonTimer = 0;
    private int cuissonTimerMarge = 0;
    private int marge;
    private double progressF = 0;
    private int clientActuel = 0;
    private ArrayList<String> tmpI = new ArrayList<String>();
	private ArrayList<String> tmpB = new ArrayList<String>();
	private ArrayList<String> tmpP = new ArrayList<String>();
	private ArrayList<String[]> tmpI2 = new ArrayList<String[]>();
	private ArrayList<String[]> tmpB2 = new ArrayList<String[]>();
	
	Timeline secondes = new Timeline(
			new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						checkGameOver("../fxml/EcranVictoire.fxml");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					for(int i = 0;i<lesCommandes.length;i++) {
						if(lesCommandes[i]!=null  && !pause)
						{
							Client clientCom = lesCommandes[i].getUnClient();
							clientCom.setTempsClient(clientCom.getTempsClient() -1);
						}
					}
					if(!pause && !arret){
						AffichetempsClient();
						nbClientRestant.setText("Client restant : "+clientsRestant.size());
						lbl_argent.setText("Trésorerie : "+(niveauActuel.getTresorerietmp()));
					}
					if(arret){
						secondes.stop();
					}
				}
			}));
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		arret = false;
		pause = false;
		clientsRestant = new ArrayList<Commande>();
		niveauActuel = MenuListeNiveauControleur.niv;
		lesCommandes = new Commande[3];
		niveauActuel.genererCommande();
		for (Commande _commande : niveauActuel.getCommandes())
			clientsRestant.add(_commande);
		lesCommandes[0] = clientsRestant.get(0);
		clientsRestant.remove(0);
		lesCommandes[1] = clientsRestant.get(0);
		clientsRestant.remove(0);
		lesCommandes[2] = clientsRestant.get(0);
		clientsRestant.remove(0);
		changementImageClient(lesCommandes[0].getUnClient(), img_c1);
		changementImageClient(lesCommandes[1].getUnClient(), img_c2);
		changementImageClient(lesCommandes[2].getUnClient(), img_c3);
		lbl_nomClient1.setText(lesCommandes[0].getUnClient().getNomClient());
		lbl_nomClient2.setText(lesCommandes[1].getUnClient().getNomClient());
		lbl_nomClient3.setText(lesCommandes[2].getUnClient().getNomClient());
		definirListeIngredient();
		AffichetempsClient();
		nbClientRestant.setText("Client restant : "+clientsRestant.size());
		try {
			changementClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
		lbl_argent.setText("Trésorerie : "+niveauActuel.getTresorerietmp());
		initialisationAllPizza();
		secondes.setCycleCount(Timeline.INDEFINITE);
		secondes.play();
        interval = niveauActuel.getTempsPartie();
        //interval = niveauActuel.getTempstmp();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(interval > 0 && !pause)
                {
                    Platform.runLater(() -> lbl_temps.setText(interval +" sec"));
                    //System.out.println(interval);
                    interval--;
                    niveauActuel.setTempstmp(interval);
                }
                if(arret  && !pause){
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 1000,1000);
	}
	/*
	 * Récupérer la liste des ingrédients sous forme de listes
	 */
	public void definirListeIngredient() {
		try {
			//lire le fichier
			FileReader fileI = new FileReader(getClass().getResource("../textes/ingredients.txt").getFile());
			FileReader fileB = new FileReader(getClass().getResource("../textes/bases.txt").getFile());
			FileReader fileP = new FileReader(getClass().getResource("../textes/pates.txt").getFile());
			BufferedReader bufferI = new BufferedReader(fileI);
			BufferedReader bufferB = new BufferedReader(fileB);
			BufferedReader bufferP = new BufferedReader(fileB);
			String tmpBB = bufferI.readLine();
			String tmpBB2 = bufferB.readLine();
			String tmpBB3 = bufferP.readLine();
			// parcourir le fichier
			while (tmpBB != null) {
				tmpI.add(tmpBB);
				tmpBB = bufferI.readLine();
			}
			while (tmpBB2 != null) {
				tmpB.add(tmpBB2);
				tmpBB2 = bufferB.readLine();
			}
			while (tmpBB3 != null) {
				tmpP.add(tmpBB3);
				tmpBB3 = bufferP.readLine();
			}
			bufferI.close();
			bufferB.close();
			bufferP.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		for(String s : tmpI)
			tmpI2.add(elementsIngredients(s));
		for(String s : tmpB)
			tmpB2.add(elementsIngredients(s));
	}
	/*
	 * Initialise le nombre d'ingrédients sur la pizza
	 */
	public void initialisationNbIngredientPizza() 
	{
		nbOignonsPizza = 0;
		nbOeufsPizza = 0;
		nbPouletPizza = 0;
		nbFromagePizza = 0;
		nbChampignonPizza = 0;
		nbBaconPizza = 0;
		nbChorizoPizza = 0;
		nbTomatePizza = 0;
		nbSauceTomatePizza = 0;
		nbCremePizza = 0;
	}
	/*
	 * Initialisation de tous les paramètres de gestion d'une pizza à zero
	 */
	public void initialisationAllPizza(){
    	initialisationNbIngredientPizza();
    	menuReset();
    	lblPetrissage.setText("Pétrissage en attente");
    	btn_gestion.setDisable(true);
    	progressBarCuisson.setProgress(0);
        progressBarMarg.setProgress(0);
		txt_oignons.setText("x"+nbOignonsPizza);
		txt_oeufs.setText("x"+nbOeufsPizza);
		txt_poulet.setText("x"+nbPouletPizza);
		txt_fromage.setText("x"+nbFromagePizza);
		txt_chapignon.setText("x"+nbChampignonPizza);
		txt_bacon.setText("x"+nbBaconPizza);
		txt_chorizo.setText("x"+nbChorizoPizza);
		txt_tomate.setText("x"+nbTomatePizza);
		txt_sauceTomate.setText("x"+nbSauceTomatePizza);
		txt_creme.setText("x"+nbCremePizza);
		img_oignonPizza.setVisible(false);
		img_oeufPizza.setVisible(false);
		img_pouletPizza.setVisible(false);
		img_fromagePizza.setVisible(false);
		img_cremePizza.setVisible(false);
		img_champignonPizza.setVisible(false);
		img_baconPizza.setVisible(false);
		img_chorizoPizza.setVisible(false);
		img_tomatePizza.setVisible(false);
		img_sauceTomatePizza.setVisible(false);
		this.pizzaEnCoursDePreparation = null;
        this.pizzaEnCoursDeCuisson = null;
        this.misDeCote = null;
        this.pizzaEnCours = null;
        btnValiderPizza.setDisable(true);
        DropShadow resetShadow = new DropShadow();
    	resetShadow.setRadius(0);
    	resetShadow.setOffsetX(0);
    	resetShadow.setOffsetY(0);
    	if(clientActuel == 0)
    		btn_c1.setEffect(resetShadow);
    	if(clientActuel == 1)
    		btn_c2.setEffect(resetShadow);
    	if(clientActuel == 2)
    		btn_c3.setEffect(resetShadow);
    	btnChoixClientActif(false);
    	btnPetrir.setDisable(false);
		btnStop.setDisable(true);
		lblErreurPetrir.setVisible(false);
    }
	/*
	 * Ouverture le menu de préparation de la pâte
	 */
	public void menuPreparationPate() {
		paneCuisson.setVisible(false);
		panePrepPizza.setVisible(false);
		panePrepPate.setVisible(true);
		paneGestionPizza.setVisible(false);
		btn_preppate.setDisable(false);
		btn_preppizza.setDisable(true);
		btn_cuisson.setDisable(true);
		btn_gestion.setDisable(true);
	}
	/*
	 * Ouverture le menu de préparation de la pizza
	 */
	public void menuPreparationPizza() {
		paneCuisson.setVisible(false);
		panePrepPizza.setVisible(true);
		panePrepPate.setVisible(false);
		paneGestionPizza.setVisible(false);
		btn_preppate.setDisable(true);
		btn_preppizza.setDisable(false);
		btn_cuisson.setDisable(true);
		btn_gestion.setDisable(true);
	}
	/*
	 * Ouverture le menu de cuisson de la pizza
	 */
	public void menuCuissonPizza() {
		paneCuisson.setVisible(true);
		panePrepPizza.setVisible(false);
		panePrepPate.setVisible(false);
		paneGestionPizza.setVisible(false);
		btn_preppate.setDisable(true);
		btn_preppizza.setDisable(true);
		btn_cuisson.setDisable(false);
		btn_gestion.setDisable(true);
	}
	/*
	 * Ouverture le menu de la gestion de la pizza
	 */
	public void menuGestionPizza() {
		paneCuisson.setVisible(false);
		panePrepPizza.setVisible(false);
		panePrepPate.setVisible(false);
		paneGestionPizza.setVisible(true);
		btn_preppate.setDisable(true);
		btn_preppizza.setDisable(true);
		btn_cuisson.setDisable(true);
		btn_gestion.setDisable(false);
	}
	/*
	 * Remettre à zero le menu 
	 */
	public void menuReset()
	{
		paneCuisson.setVisible(false);
		panePrepPizza.setVisible(false);
		panePrepPate.setVisible(false);
		paneGestionPizza.setVisible(false);
		txt_choixClientDebut.setVisible(true);
		btn_preppate.setDisable(true);
		btn_preppizza.setDisable(true);
		btn_cuisson.setDisable(true);
		btn_gestion.setDisable(true);
		btnValiderPizza.setDisable(true);
	}
	/*
	 * Remet à zero les éléments de la commande choisie
	 */
	public void resetCommandeAffichage(){
		base.setVisible(false);
        baconNb.setText("x0");
        pouletNb.setText("x0");
        chorizoNb.setText("x0");
        oeufNb.setText("x0");
        oignonNb.setText("x0");
        tomateNb.setText("x0");
        fromageNb.setText("x0");
        champignonNb.setText("x0");
        /*textPizzaCompare.setText("Comparer avec");
        btnChoixClientActif(true);
        btnretourCommande.setOpacity(0);
        btnretourCommande.setDisable(true);
        btnValidationPizzaCote.setDisable(true);*/
    }
	/*
	 * Active ou désactive les boutons des clients
	 */
	public void btnChoixClientActif(boolean t){
        btn_c3.setDisable(t);
        btn_c2.setDisable(t);
        btn_c1.setDisable(t);
    }
	
	/*
	 * Change l'image du client
	 * @param unClient : sert à savoir si le client est Karen
	 * @param _img : ImageView à changer
	 */
	public void changementImageClient(Client unClient,ImageView _img) {
		if(unClient.getTypeClient().equals(Difficulte.Karen)) {
			_img.setImage(new Image(getClass().getResourceAsStream("../img/clientK.png")));
		}else {
			Random rd = new Random();
			String choixImage = "../img/client"+(rd.nextInt(5 - 1)+1)+".png";
			_img.setImage(new Image(getClass().getResourceAsStream(choixImage)));
		}
	}
	/*
	 * Change le client
	 */
	public void changementClient() throws IOException {
		if(clientsRestant.size()>0) {
			if(lesCommandes[0]!=null) {
				if(lesCommandes[0].getUnClient().getTempsClient() <= 0) {
					if(lesCommandes[0].equals(pizzaEnCours))
					{
						supprimerPizza();
						menuReset();
						resetCommandeAffichage();
						initialisationAllPizza();
						btnPause.setDisable(false);
					}
					niveauActuel.setTresorerietmp(niveauActuel.getTresorerietmp()-2);
					lesCommandes[0] = clientsRestant.get(0);
					clientsRestant.remove(0);
					changementImageClient(lesCommandes[0].getUnClient(), img_c1);
					lbl_nomClient1.setText(lesCommandes[0].getUnClient().getNomClient());
					btn_c1.setDisable(false);
				}
			}
			if(lesCommandes[1]!=null) {
				if(lesCommandes[1].getUnClient().getTempsClient() <= 0) {
					if(lesCommandes[1].equals(pizzaEnCours))
					{
						supprimerPizza();
						menuReset();
						resetCommandeAffichage();
						initialisationAllPizza();
						btnPause.setDisable(false);
					}
					niveauActuel.setTresorerietmp(niveauActuel.getTresorerietmp()-2);
					lesCommandes[1] = clientsRestant.get(0);
					clientsRestant.remove(0);
					changementImageClient(lesCommandes[1].getUnClient(), img_c2);
					lbl_nomClient2.setText(lesCommandes[1].getUnClient().getNomClient());
					btn_c2.setDisable(false);

				}
			}
			if(lesCommandes[2]!=null) {
				if(lesCommandes[2].getUnClient().getTempsClient() <= 0) {
					if(lesCommandes[2].equals(pizzaEnCours))
					{
						supprimerPizza();
						menuReset();
						resetCommandeAffichage();
						initialisationAllPizza();
						btnPause.setDisable(false);
					}
					niveauActuel.setTresorerietmp(niveauActuel.getTresorerietmp()-2);
					lesCommandes[2] = clientsRestant.get(0);
					clientsRestant.remove(0);
					changementImageClient(lesCommandes[2].getUnClient(), img_c3);
					lbl_nomClient3.setText(lesCommandes[2].getUnClient().getNomClient());
					btn_c3.setDisable(false);
				}
			}
		}else {
			if(lesCommandes[0]!=null) {
				if(lesCommandes[0].getUnClient().getTempsClient() <= 0)
				{
					if(lesCommandes[0].equals(pizzaEnCours))
					{
						supprimerPizza();
						menuReset();
						resetCommandeAffichage();
						initialisationAllPizza();
						btnPause.setDisable(false);
					}
					niveauActuel.setTresorerietmp(niveauActuel.getTresorerietmp()-2);
					img_c1.setImage(new Image(getClass().getResourceAsStream("../img/clientVide.png")));
					lesCommandes[0] = null;
					btn_c1.setDisable(true);
				}
			} else {
				btn_c1.setDisable(true);
				img_c1.setImage(new Image(getClass().getResourceAsStream("../img/clientVide.png")));
				lbl_nomClient1.setText("");
				c1Temps.setText("Fin");
			}
			if(lesCommandes[1]!=null) {
				if(lesCommandes[1].getUnClient().getTempsClient() <= 0) {
					if(lesCommandes[1].equals(pizzaEnCours))
					{
						supprimerPizza();
						menuReset();
						resetCommandeAffichage();
						initialisationAllPizza();
						btnPause.setDisable(false);
					}
					niveauActuel.setTresorerietmp(niveauActuel.getTresorerietmp()-2);
					img_c2.setImage(new Image(getClass().getResourceAsStream("../img/clientVide.png")));
					lesCommandes[1] = null;
					btn_c2.setDisable(true);
				}
			}else {
				btn_c2.setDisable(true);
				img_c2.setImage(new Image(getClass().getResourceAsStream("../img/clientVide.png")));
				lbl_nomClient2.setText("");
				c2Temps.setText("Fin");
			}
			if(lesCommandes[2]!=null) {
				if(lesCommandes[2].getUnClient().getTempsClient() <= 0){
					if(lesCommandes[2].equals(pizzaEnCours))
					{
						supprimerPizza();
						menuReset();
						resetCommandeAffichage();
						initialisationAllPizza();
						btnPause.setDisable(false);
					}
					niveauActuel.setTresorerietmp(niveauActuel.getTresorerietmp()-2);
					img_c3.setImage(new Image(getClass().getResourceAsStream("../img/clientVide.png")));
					lesCommandes[2] = null;
					btn_c3.setDisable(true);
				}else {
					btn_c3.setDisable(true);
					img_c3.setImage(new Image(getClass().getResourceAsStream("../img/clientVide.png")));
					lbl_nomClient3.setText("");
					c3Temps.setText("Fin");
				}
			}
		}
	}
	/*
	 * Changement de client après la vente d'une commande
	 * @param index : le client qui est servit
	 */
	public void changementClientApresVente(int index) throws IOException {
		if(clientsRestant.size()>0) {
			if(index == 0) {
				System.out.println("index : " + index);
				lesCommandes[0] = clientsRestant.get(0);
				clientsRestant.remove(0);
				changementImageClient(lesCommandes[0].getUnClient(), img_c1);
				lbl_nomClient1.setText(lesCommandes[0].getUnClient().getNomClient());
				btn_c1.setDisable(false);
			}
			if(index == 1) {
				System.out.println("index : " + index);
				lesCommandes[1] = clientsRestant.get(0);
				clientsRestant.remove(0);
				changementImageClient(lesCommandes[1].getUnClient(), img_c2);
				lbl_nomClient2.setText(lesCommandes[1].getUnClient().getNomClient());
				btn_c2.setDisable(false);

			}
			if(index == 2) {
				System.out.println("index : " + index);
				lesCommandes[2] = clientsRestant.get(0);
				clientsRestant.remove(0);
				changementImageClient(lesCommandes[2].getUnClient(), img_c3);
				lbl_nomClient3.setText(lesCommandes[2].getUnClient().getNomClient());
				btn_c3.setDisable(false);
			}
		}
		else{
			System.out.println(index);
			if(index == 0){
				System.out.println("index pas client restant: " + index);
				img_c1.setImage(new Image(getClass().getResourceAsStream("../img/clientVide.png")));
				lesCommandes[0].getUnClient().setTempsClient(0);
				lesCommandes[0] = null;
				c1Temps.setText("Fin");
				btn_c1.setDisable(true);
			}
			if(index == 1){
				System.out.println("index pas client restant: " + index);
				img_c2.setImage(new Image(getClass().getResourceAsStream("../img/clientVide.png")));
				lesCommandes[1].getUnClient().setTempsClient(0);
				lesCommandes[1] = null;
				c2Temps.setText("Fin");
				btn_c2.setDisable(true);
			}
			if(index == 2){
				System.out.println("index pas client restant: " + index);
				img_c3.setImage(new Image(getClass().getResourceAsStream("../img/clientVide.png")));
				lesCommandes[2].getUnClient().setTempsClient(0);
				lesCommandes[2] = null;
				c3Temps.setText("Fin");
				btn_c3.setDisable(true);
			}
		}
	}
	/*
	 * Affiche le temps du client
	 */
	public void AffichetempsClient() {
		if(lesCommandes[0] != null)
		{
			if(lesCommandes[0].getUnClient().getTempsClient() <= 0){
				c1Temps.setText("Fin");
				btn_c1.setDisable(true);
				lbl_nomClient1.setText("");
				try {
					changementClientApresVente(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
				c1Temps.setText(lesCommandes[0].getUnClient().getTempsClient()+"");

		}
		if(lesCommandes[1] != null) {
			if(lesCommandes[1].getUnClient().getTempsClient() <= 0){
				c2Temps.setText("Fin");
				btn_c2.setDisable(true);
				lbl_nomClient2.setText("");
				try {
					changementClientApresVente(1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
				c2Temps.setText(lesCommandes[1].getUnClient().getTempsClient()+"");
		}
		if(lesCommandes[2] != null) {
			if(lesCommandes[2].getUnClient().getTempsClient() <= 0){
				c3Temps.setText("Fin");
				btn_c3.setDisable(true);
				lbl_nomClient3.setText("");
				try {
					changementClientApresVente(2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
				c3Temps.setText(lesCommandes[2].getUnClient().getTempsClient()+"");
		}
	}

	/*
	 * Place l'ingrédient sur la pizza
	 */
	public void mettreIngredient(MouseEvent e) {
		btnCuisson.setDisable(false);
        btnCuisson.setText("Cuisson");
        btnSupprimerPizza.setDisable(true);
        btnSauvegardePizza.setDisable(true);
		ImageView imgView = (ImageView)e.getSource();
		Ingredients unIngredientAMettre;
		switch (imgView.getId()) {
			case "img_creme":{
				if(mettreIngredientNb(txt_creme)) {
					nbCremePizza++;
					txt_creme.setText("x"+nbCremePizza);
					img_cremePizza.setImage(new Image(getClass().getResourceAsStream("../img/pizza_creme.png")));
					img_cremePizza.setVisible(true);
					unIngredientAMettre = new Ingredients(tmpB2.get(1)[0], Float.parseFloat(tmpB2.get(1)[1]), Float.parseFloat(tmpB2.get(1)[2]));
					niveauActuel.retraitArgentIng(unIngredientAMettre, 1);
				}
			}break;
			case "img_sauceTomate":{
				if(mettreIngredientNb(txt_sauceTomate)) {
					nbSauceTomatePizza++;
					txt_sauceTomate.setText("x"+nbSauceTomatePizza);
					img_sauceTomatePizza.setImage(new Image(getClass().getResourceAsStream("../img/pizza_sauceTomate.png")));
					img_sauceTomatePizza.setVisible(true);
					unIngredientAMettre = new Ingredients(tmpB2.get(0)[0], Float.parseFloat(tmpB2.get(0)[1]), Float.parseFloat(tmpB2.get(0)[2]));
					niveauActuel.retraitArgentIng(unIngredientAMettre, 1);
				}
			}break;
			case "img_oignons":{
				if(mettreIngredientNb(txt_oignons)) {
					nbOignonsPizza++;
					txt_oignons.setText("x"+nbOignonsPizza);
					img_oignonPizza.setImage(new Image(getClass().getResourceAsStream("../img/pizza_oignons.png")));
					img_oignonPizza.setVisible(true);
					unIngredientAMettre = new Ingredients(tmpI2.get(0)[0], Float.parseFloat(tmpI2.get(0)[1]), Float.parseFloat(tmpI2.get(0)[2]));
					niveauActuel.retraitArgentIng(unIngredientAMettre, 1);
				}
			}break;
			case "img_oeufs":{
				if(mettreIngredientNb(txt_oeufs)) {
					nbOeufsPizza++;
					txt_oeufs.setText("x"+nbOeufsPizza);
					img_oeufPizza.setImage(new Image(getClass().getResourceAsStream("../img/pizza_oeufs.png")));
					img_oeufPizza.setVisible(true);
					unIngredientAMettre = new Ingredients(tmpI2.get(2)[0], Float.parseFloat(tmpI2.get(2)[1]), Float.parseFloat(tmpI2.get(2)[2]));
					niveauActuel.retraitArgentIng(unIngredientAMettre, 1);
				}
			}break;
			case "img_poulet":{
				if(mettreIngredientNb(txt_poulet)) {
					nbPouletPizza++;
					txt_poulet.setText("x"+nbPouletPizza);
					img_pouletPizza.setImage(new Image(getClass().getResourceAsStream("../img/pizza_poulet.png")));
					img_pouletPizza.setVisible(true);
					unIngredientAMettre = new Ingredients(tmpI2.get(4)[0], Float.parseFloat(tmpI2.get(4)[1]), Float.parseFloat(tmpI2.get(4)[2]));
					niveauActuel.retraitArgentIng(unIngredientAMettre, 1);
				}
			}break;
			case "img_fromage":{
				if(mettreIngredientNb(txt_fromage)) {
					nbFromagePizza++;
					txt_fromage.setText("x"+nbFromagePizza);
					img_fromagePizza.setImage(new Image(getClass().getResourceAsStream("../img/pizza_fromage.png")));
					img_fromagePizza.setVisible(true);
					unIngredientAMettre = new Ingredients(tmpI2.get(6)[0], Float.parseFloat(tmpI2.get(6)[1]), Float.parseFloat(tmpI2.get(6)[2]));
					niveauActuel.retraitArgentIng(unIngredientAMettre, 1);
				}
			}break;
			case "img_champignons":{
				if(mettreIngredientNb(txt_chapignon)) {
					nbChampignonPizza++;
					txt_chapignon.setText("x"+nbChampignonPizza);
					img_champignonPizza.setImage(new Image(getClass().getResourceAsStream("../img/pizza_champignon.png")));
					img_champignonPizza.setVisible(true);
					unIngredientAMettre = new Ingredients(tmpI2.get(1)[0], Float.parseFloat(tmpI2.get(1)[1]), Float.parseFloat(tmpI2.get(1)[2]));
					niveauActuel.retraitArgentIng(unIngredientAMettre, 1);
				}
			}break;
			case "img_bacon":{
				if(mettreIngredientNb(txt_bacon)) {
					nbBaconPizza++;
					txt_bacon.setText("x"+nbBaconPizza);
					img_baconPizza.setImage(new Image(getClass().getResourceAsStream("../img/pizza_bacon.png")));
					img_baconPizza.setVisible(true);
					unIngredientAMettre = new Ingredients(tmpI2.get(5)[0], Float.parseFloat(tmpI2.get(5)[1]), Float.parseFloat(tmpI2.get(5)[2]));
					niveauActuel.retraitArgentIng(unIngredientAMettre, 1);
				}
			}break;
			case "img_chorizo":{
				if(mettreIngredientNb(txt_chorizo)) {
					nbChorizoPizza++;
					txt_chorizo.setText("x"+nbChorizoPizza);
					img_chorizoPizza.setImage(new Image(getClass().getResourceAsStream("../img/pizza_chorizo.png")));
					img_chorizoPizza.setVisible(true);
					unIngredientAMettre = new Ingredients(tmpI2.get(3)[0], Float.parseFloat(tmpI2.get(3)[1]), Float.parseFloat(tmpI2.get(3)[2]));
					niveauActuel.retraitArgentIng(unIngredientAMettre, 1);
				}
			}break;
			case "img_tomates":{
				if(mettreIngredientNb(txt_tomate)) {
					nbTomatePizza++;
					txt_tomate.setText("x"+nbTomatePizza);
					img_tomatePizza.setImage(new Image(getClass().getResourceAsStream("../img/pizza_tomates.png")));
					img_tomatePizza.setVisible(true);
					unIngredientAMettre = new Ingredients(tmpI2.get(7)[0], Float.parseFloat(tmpI2.get(7)[1]), Float.parseFloat(tmpI2.get(7)[2]));
					niveauActuel.retraitArgentIng(unIngredientAMettre, 1);
				}
			}break;
		};
	}
	/*
	 * Indique si l'utilisateur peut mettre l'ingrédient
	 * @param lb : le label de l'ingrédient à mettre
	 */
	public boolean mettreIngredientNb(Label lb)
	{
		if(!lb.getId().equals("txt_creme") && !lb.getId().equals("txt_sauceTomate")) {
			if(nbCremePizza==0 && nbSauceTomatePizza==0) {
				txt_AVBase.setText("ATTENTION : Vous devez mettre la base (Creme ou Sauce Tomate) en premier !");
				return false;
			}
			else {
				txt_AVBase.setText("");
				return true;
			}
				
		}
		else {
			if(nbCremePizza == 1 || nbSauceTomatePizza == 1) {
				txt_AVBase.setText("ATTENTION : Vous ne pouvez mettre qu'une seule base !");
				return false;
			}
			else{
				txt_AVBase.setText("");
				btnValiderPizza.setDisable(false);
				return true;
			}
		}
	}
	
	
	/*
	 * Création de la pizza misDeCote
	 */
	public void validerPizzaPreparation(ActionEvent event) {
		HashMap<Ingredients, Integer> ingredientsActuels = new HashMap<Ingredients, Integer>();
		if(nbOignonsPizza > 0)
			ingredientsActuels.put(new Ingredients(tmpI2.get(0)[0], Float.parseFloat(tmpI2.get(0)[1]), Float.parseFloat(tmpI2.get(0)[2])), nbOignonsPizza);
		if(nbChampignonPizza > 0)
			ingredientsActuels.put(new Ingredients(tmpI2.get(1)[0], Float.parseFloat(tmpI2.get(1)[1]), Float.parseFloat(tmpI2.get(1)[2])), nbChampignonPizza);
		if(nbOeufsPizza > 0)
			ingredientsActuels.put(new Ingredients(tmpI2.get(2)[0], Float.parseFloat(tmpI2.get(2)[1]), Float.parseFloat(tmpI2.get(2)[2])), nbOeufsPizza);
		if(nbChorizoPizza > 0)
			ingredientsActuels.put(new Ingredients(tmpI2.get(3)[0], Float.parseFloat(tmpI2.get(3)[1]), Float.parseFloat(tmpI2.get(3)[2])), nbChorizoPizza);
		if(nbPouletPizza > 0)
			ingredientsActuels.put(new Ingredients(tmpI2.get(4)[0], Float.parseFloat(tmpI2.get(4)[1]), Float.parseFloat(tmpI2.get(4)[2])), nbPouletPizza);
		if(nbBaconPizza > 0)
			ingredientsActuels.put(new Ingredients(tmpI2.get(5)[0], Float.parseFloat(tmpI2.get(5)[1]), Float.parseFloat(tmpI2.get(5)[2])), nbBaconPizza);
		if(nbFromagePizza > 0)
			ingredientsActuels.put(new Ingredients(tmpI2.get(6)[0], Float.parseFloat(tmpI2.get(6)[1]), Float.parseFloat(tmpI2.get(6)[2])), nbFromagePizza);
		if(nbTomatePizza > 0)
			ingredientsActuels.put(new Ingredients(tmpI2.get(7)[0], Float.parseFloat(tmpI2.get(7)[1]), Float.parseFloat(tmpI2.get(7)[2])), nbTomatePizza);
		if(nbSauceTomatePizza > 0)
			ingredientsActuels.put(new Ingredients(tmpB2.get(0)[0], Float.parseFloat(tmpB2.get(0)[1]), Float.parseFloat(tmpB2.get(0)[2])), nbSauceTomatePizza);
		if(nbCremePizza > 0)
			ingredientsActuels.put(new Ingredients(tmpB2.get(1)[0], Float.parseFloat(tmpB2.get(1)[1]), Float.parseFloat(tmpB2.get(1)[2])), nbCremePizza);
		Client unClient1 = pizzaEnCours.getUnClient();
		Pate unePate = pizzaEnCours.getLaPate();
		pizzaEnCoursDePreparation = new Commande(unClient1, ingredientsActuels, unePate);
		//System.out.println(pizzaEnCoursDePreparation.toString());
		menuCuissonPizza();
	}
	
	public String[] elementsIngredients(String element) {
		String[] retour = element.split( "/" );
		if(retour.length != 3 && retour.length != 4) {
			throw new InternalError(element + " ne correspond pas ï¿½ un String d'un fichier contenant des ingrédients traitables");
		}
		return retour;
	}
	/*
	 * Suppression de la pizza
	 */
	public void supprimerPizza() {
		initialisationNbIngredientPizza();
		txt_oignons.setText("x"+nbOignonsPizza);
		txt_oeufs.setText("x"+nbOeufsPizza);
		txt_poulet.setText("x"+nbPouletPizza);
		txt_fromage.setText("x"+nbFromagePizza);
		txt_chapignon.setText("x"+nbChampignonPizza);
		txt_bacon.setText("x"+nbBaconPizza);
		txt_chorizo.setText("x"+nbChorizoPizza);
		txt_tomate.setText("x"+nbTomatePizza);
		txt_sauceTomate.setText("x"+nbSauceTomatePizza);
		txt_creme.setText("x"+nbCremePizza);
		img_oignonPizza.setVisible(false);
		img_oeufPizza.setVisible(false);
		img_pouletPizza.setVisible(false);
		img_fromagePizza.setVisible(false);
		img_cremePizza.setVisible(false);
		img_champignonPizza.setVisible(false);
		img_baconPizza.setVisible(false);
		img_chorizoPizza.setVisible(false);
		img_tomatePizza.setVisible(false);
		img_sauceTomatePizza.setVisible(false);
		this.pizzaEnCoursDePreparation = null;
        this.pizzaEnCoursDeCuisson = null;
        this.misDeCote = null;
        lblPetrissage.setText("Pétrissage en attente");
		menuPreparationPate();
    }
	
	/*
	 * Remise à zero la commande des clients
	 */
	public void supprimerPizzaCommande() {
		oignonNb.setText("x"+0);
		oeufNb.setText("x"+0);
		pouletNb.setText("x"+0);
		fromageNb.setText("x"+0);
		champignonNb.setText("x"+0);
		baconNb.setText("x"+0);
		chorizoNb.setText("x"+0);
		tomateNb.setText("x"+0);
	}
	
    /**
     * Appuie bouton menu
     */
    public void pause() {
        pause=!pause;
    }
    public void afficherCommande(Commande c){
        //Parcourir le Hashmap avec la boucle For
    	System.out.println(c.toString());
        for (Map.Entry m : c.getLesIngredients().entrySet()) {
            System.out.println("ID: "+((Ingredients)m.getKey()).getNom()+", Nom: "+m.getValue());
            String tmp = ((Ingredients)m.getKey()).getNom().toLowerCase(Locale.ROOT);
            // } else if(tmp.compareTo("crèmeBase") ==0){
            switch(tmp){
                case "tomatebase":
                    base.setImage(new Image(getClass().getResource("../img/sauceTomate.png").toExternalForm()));
                    break;
                case "cremebase":
                	System.out.println("creme");
                    base.setImage(new Image(getClass().getResource("../img/creme.png").toExternalForm()));
                    break;
                case "bacon":
                    baconNb.setText("x" + m.getValue());
                    break;
                case "poulet":
                    pouletNb.setText("x" +m.getValue());
                    break;
                case "chorizo":
                    chorizoNb.setText("x" +m.getValue());
                    break;
                case "oeuf":
                    oeufNb.setText("x" +m.getValue());
                    break;
                case "oignon":
                    oignonNb.setText("x" +m.getValue());
                    break;
                case "tomate":
                    tomateNb.setText("x" +m.getValue());
                    break;
                case "fromage":
                    fromageNb.setText("x" +m.getValue());
                    break;
                case "champignon":
                    champignonNb.setText("x" +m.getValue());
                    break;
            }
        }
        base.setVisible(true);
    }

	/*
	 * Prend la commande
	 * @param e : détecte le bouton qui a fait l'action
	 */
	public void prendreCommande(ActionEvent e) {
		commandeClient = (Button)e.getSource();
		txt_choixClientDebut.setVisible(false);
		supprimerPizzaCommande();
		switch(commandeClient.getId()) {
			case "btn_c1":{
				System.out.println("Client1");
				tempsCuisson = this.lesCommandes[0].cuire();
		        btnChoixClientActif(true);
		        DropShadow shadow = new DropShadow();
		        btn_c1.setEffect(shadow);
		        afficherCommande(this.lesCommandes[0]);
		        pizzaEnCours = lesCommandes[0];
		        clientActuel = 0;
		        //lesCommandes[0] = null;
			}break;
			case "btn_c2":{
				System.out.println("Client2");
				tempsCuisson = this.lesCommandes[1].cuire();
		        btnChoixClientActif(true);
		        DropShadow shadow = new DropShadow();
		        btn_c2.setEffect(shadow);
		        afficherCommande(this.lesCommandes[1]);
		        pizzaEnCours = lesCommandes[1];
		        clientActuel = 1;
		        //lesCommandes[1] = null;
			}break;
			case "btn_c3":{
				System.out.println("Client3");
				tempsCuisson = this.lesCommandes[2].cuire();
		        btnChoixClientActif(true);
		        DropShadow shadow = new DropShadow();
		        btn_c3.setEffect(shadow);
		        afficherCommande(this.lesCommandes[2]);
		        pizzaEnCours = lesCommandes[2];
		        clientActuel = 2;
		        //lesCommandes[2] = null;
			}break;
		}
		btn_preppate.setDisable(false);
		menuPreparationPate();
	}
	/*
	 * Vente de la pizza
	 */
	public void retourCommande(ActionEvent actionEvent) {
        afficherCommande(pizzaEnCours);
        textPizzaCompare.setText("Comparer avec");
        btnretourCommande.setOpacity(0);
        btnretourCommande.setDisable(true);
    }

    public void cuisson(ActionEvent actionEvent) {
    	scorecuisson = 0;
        pizzaEnCoursDeCuisson = pizzaEnCoursDePreparation;
        progressBarCuisson.setProgress(0);
        progressBarMarg.setProgress(0);
        marge = tempsCuisson[1] - tempsCuisson[0];
        btnSupprimerPizza.setDisable(true);
        btnSauvegardePizza.setDisable(true);
        if(btnCuisson.getText().compareTo("Arret") == 0){
            timerC.purge();
            timerC.cancel();
            if(cuissonTimerMarge > marge){
                btnCuisson.setText("bruler");
                btnSupprimerPizza.setDisable(false);
            } else if(cuissonTimer <= tempsCuisson[0]){
                scorecuisson = 1;
                btnSupprimerPizza.setDisable(false);
                btnSauvegardePizza.setDisable(false);
            } else if(cuissonTimer + cuissonTimerMarge < tempsCuisson[1] || cuissonTimer == tempsCuisson[0]){
                btnSupprimerPizza.setDisable(false);
                btnSauvegardePizza.setDisable(false);
                scorecuisson = 3;

            }
            pizzaEnCoursDePreparation = null;
            //Mettre en place verification cuisson
            btnCuisson.setDisable(true);

        }else{
            timerC = new Timer();
            timerC.scheduleAtFixedRate(new TimerTask() {
                double progressF1 = 0;
                double progressF = 0;
                public void run() {
                    if( tempsCuisson[0]> cuissonTimer && !pause) {
                        cuissonTimer++;
                        double progress = (double) ((double) cuissonTimer / (double) tempsCuisson[0]);
                        progressF = (progress > 1) ? 1 : progress;
                        progressBarCuisson.setProgress(progressF);
                    }
                    if(progressF == 1 && !pause && cuissonTimerMarge < marge  && !pause){
                        cuissonTimerMarge++;
                        double progress1 = (double) ((double) cuissonTimerMarge / (double) marge);
                        progressF1 = (progress1 > 1) ? 1 : progress1;
                        progressBarMarg.setProgress(progressF1);
                    }
                    if(arret  && !pause){
                        timerC.cancel();
                        timerC.purge();
                    }

                }
            }, 1000,1000);
            btnCuisson.setText("Arret");
        }

        cuissonTimerMarge = 0;
        cuissonTimer = 0;
    }

    public void sauvegardePizza(ActionEvent actionEvent) {
        this.misDeCote = this.pizzaEnCoursDeCuisson;
        System.out.println("Pizza sauvegardée");
        this.pizzaEnCoursDeCuisson = null;
        this.pizzaEnCoursDePreparation=null;
		menuGestionPizza();
		lblPetrissage.setText("Pétrissage en attente");
		btnCuisson.setText("Cuisson");
		//lbl_prixVentePizza.setText("Prix de vente : "+misDeCote.getVenteCommande());
		String resumePizza = "";
		for(Ingredients ing : misDeCote.getLesIngredients().keySet()) {
			resumePizza+=" "+ing.getNom()+" x"+misDeCote.getLesIngredients().get(ing)+",";
		}
		lbl_resumePizza.setText("Résumé de la pizza : "+resumePizza);
    }
    /*
     * Vendre la pizza (menu gestion pizza)
     */
    public void vendreLaPizza() throws IOException {
    	niveauActuel.setScoreCuisson(scorecuisson);
    	niveauActuel.ventePizza(pizzaEnCours, misDeCote);
    	this.pizzaEnCours=null;
    	initialisationAllPizza();
    	menuReset();
    	System.out.println("Vendre la pizza");
    	//lesCommandes[clientActuel] = null;
    	changementClientApresVente(clientActuel);
    	btnChoixClientActif(false);
		DropShadow resetShadow = new DropShadow();
    	resetShadow.setRadius(0);
    	resetShadow.setOffsetX(0);
    	resetShadow.setOffsetY(0);
    	if(clientActuel == 0)
    		btn_c1.setEffect(resetShadow);
    	if(clientActuel == 1)
    		btn_c2.setEffect(resetShadow);
    	if(clientActuel == 2)
    		btn_c3.setEffect(resetShadow);
    }
    /*
     * Jeter la pizza (menu gestion pizza)
     */
    public void jeterLaPizza() {
    	System.out.println("Jeter la pizza");
    	lblPetrissage.setText("Pétrissage en attente");
    	menuPreparationPate();
    	btnChoixClientActif(false);
		DropShadow resetShadow = new DropShadow();
    	resetShadow.setRadius(0);
    	resetShadow.setOffsetX(0);
    	resetShadow.setOffsetY(0);
    	if(clientActuel == 0)
    		btn_c1.setEffect(resetShadow);
    	if(clientActuel == 1)
    		btn_c2.setEffect(resetShadow);
    	if(clientActuel == 2)
    		btn_c3.setEffect(resetShadow);
    	initialisationAllPizza();
    	
    }
    
    
	/**
	 * Met sur pause (stop les timers)
	 *
	 * @param event
	 * @throws IOException
	 * @author Nicolas
	 */
    @FXML
    void pause(ActionEvent event) throws IOException {
        //secondes.stop();
        if (timeline != null) {
            timeline.stop();
        }
        pause = !pause;
        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Pause");
        alert.setHeaderText(null);
        alert.setContentText("Pause");
        ButtonType quitter = new ButtonType("Quitter la partie");
        ButtonType fermer = new ButtonType("Fermer");
        alert.getButtonTypes().setAll(quitter, fermer);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == fermer){
            NiveauAPCControleur.pause = !pause;
            alert.close();
        }
        else if (result.get() == quitter){
            NiveauAPCControleur.arret = true;
			NiveauAPCControleur.pause = !pause;
            Main.changementFenetre("../fxml/MenuListeNiveau.fxml" , "FinibusPizza : Liste des niveaux");
        }
        if (timeline != null)
            timeline.play();
    }

	/**
	 * Pétrir la pâte. Génère une pâte dans pateActuelle. Enregistre l'heure
	 * système du clic.
	 *
	 * @param event
	 * @author Nicolas
	 * @throws IOException 
	 */
	@FXML
	void petrir(ActionEvent event) throws IOException {
		redirigerVictoire("../fxml/EcranVictoire.fxml", 3);
		lblErreurPetrir.setVisible(false);
		// nouvelle pâte
		pateActuelle = niveauActuel.generationPate();
		// on perd de l'argent
		niveauActuel.retraitArgentIng(pateActuelle);
		btnPause.setDisable(true);

		pateActuelle = niveauActuel.generationPate();

		System.out.println(pateActuelle.toString());

		timerLabel.setDisable(false);
		// Bind the timerLabel text property to the timeSeconds property
		timerLabel.textProperty().bind(timeSeconds.asString());
		timerLabel.setTextFill(Color.RED);
		timerLabel.setStyle("-fx-font-size: 4em;");

		if (timeline != null) {
			timeline.stop();
		}

		timeSeconds.set(STARTTIME);
		timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(STARTTIME + 1), new KeyValue(timeSeconds, 0)));
		timeline.playFromStart();

		start = System.currentTimeMillis();// on prend l'heure systeme
		// System.out.println(start);
		btnPetrir.setDisable(true);// on bloque le bouton
		btnStop.setDisable(false);
		lblPetrissage.setText("Pétrissage en cours !");

	}

	/**
	 * Arrête le pétrissage. Compare l'heure système d'arrêt de pétrissage à
	 * celle du commencement. Si >5 ou <5, on recommence petrir(). Si =5, on
	 * passe à la pose des ingrédients
	 *
	 * @param event
	 * @throws IOException
	 * @author Nicolas
	 */
	@FXML
	void stop(ActionEvent event) throws IOException {
		long end = System.currentTimeMillis(); // on prend l'heure système de
												// fin de tâche
		// System.out.println(end);
		 long elapsedTime = (end - start) / 1000; // différence
		//long elapsedTime = 5;
		btnStop.setDisable(true);// bloquage bouton

		// si on a pétri moins ou plus que le temps de pétrissage
		if (elapsedTime < 5 || elapsedTime > 5) {
			System.out.println(elapsedTime + " pas bien pétri");
			lblErreurPetrir.setVisible(true);
			//checkGameOver("../fxml/EcranVictoire.fxml");
			lblPetrissage.setText("Pétrissage en attente");
			btnPause.setDisable(false);
			btnPetrir.setDisable(false);
			btnStop.setDisable(true);
			timeline.playFromStart();
			timeline.stop();
			timeSeconds.setValue(5);
			timerLabel.setDisable(true);
		}

		// Réussite
		else {
			lblErreurPetrir.setVisible(false);
			pateActuelle.setEstReussite(true);
			// Pop-up alertant de la réussite
			btnPause.setDisable(false);
			lblPetrissage.setText("Pétrissage réussi !");
			btnPetrir.setDisable(false);
			btnStop.setDisable(false);
			btn_preppizza.setDisable(false);
			btn_preppate.setDisable(true);
			menuPreparationPizza();
		}
	}

	/**
	 * Permet d'ouvrir l'écran de victoire et d'afficher les informations selon
	 * le niveau (nom du niveau et nombre d'étoiles). Enregistre dans un fichier
	 * texte le score associé au niveau
	 *
	 * @param chemin
	 *            d'accès à l'écran
	 * @param score
	 * @author Nicolas
	 */
	void redirigerVictoire(String chemin, Integer score) throws IOException {
		
		System.out.println(niveauActuel.getScore());
		System.out.println(score);
		System.out.println("tempstmp : "+niveauActuel.getTempstmp() + " tresorerietmp "+niveauActuel.getTresorerietmp());
		if(interval>0) {
			
		}
		// Ouvre la fenêtre de la victoire
		FXMLLoader loader = new FXMLLoader(getClass().getResource(chemin));
		Parent root = loader.load();
		ControleurEcranVictoire controllerVictoire = loader.getController();
		Stage primaryStage = new Stage();
		primaryStage.setTitle("PizzaFinibus : Bravo !");
		Main.stage.close();
		
		// Affiche le nom du niveau
		controllerVictoire.setLabelNiveau(niveauActuel.getNom());
		
		// Affiche le message
		if (score > 0) {
			// Affiche le nombre d'étoiles correspondant au score
			controllerVictoire.setVisibleEtoile(score);
			controllerVictoire.setLabelEtat("Bravo !\nScore : "+score);
			// si c'est un niveau test
			if (niveauActuel.isTmp()) {
				controllerVictoire.btn_suivant_edit.setText("Éditer");
				controllerVictoire.btn_suivant_edit.setVisible(true);
			}
			// si c'est le dernier niveau classique OU si c'est un niveau
			// personnalisé
			if (niveauActuel.isEstDernier() || niveauActuel.isEstPerso()) {
				controllerVictoire.btn_suivant_edit.setVisible(false);
			}
		} else {
			primaryStage.setTitle("FinibusPizza : Vous avez échoué");
			controllerVictoire.setLabelEtat("GAME OVER");
		}
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		File file = new File("src/textes/score.txt");
		// créer le fichier s'il n'existe pas
		if (!file.exists()) {
			file.createNewFile();
		}

		// On écrit dans un fichier le nom du niveau et le score
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(niveauActuel.getNom() + "/" + score + "\n");
		bw.close();
	}

	/**
	 * Vérifie l'état de la trésorerie pour le game over
	 * @param chemin
	 * @throws IOException
	 * @author Nicolas
	 */
	void checkGameOver(String chemin) throws IOException{
		if(niveauActuel.getTresorerietmp() <= 0 || interval<=0 || lesCommandes[0] == null && lesCommandes[1] == null && lesCommandes[2] == null){
			niveauActuel.setScore();
			/*if(niveauActuel.getTresorerietmp() <= 0 || interval<=0 )
				redirigerVictoire(chemin, 0);
			else*/
			redirigerVictoire(chemin, niveauActuel.getScore());
			secondes.stop();
			arret = !arret;
		}
	}

	@FXML
	void retourMenu(ActionEvent e) throws IOException{
		Main.changementFenetre("../fxml/Menu.fxml", "FinibusPizza : Menu");
	}
    
	
}
