package FinibusPizza;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


public class Niveau {
	//Nom du niveau
	private String nom;
	//Difficulte du niveau, à choisir selon l'enumératuon difficulté
	private Difficulte difficulte; 
	//Liste contenant au maximum les trois types de clients possibles en tant que clé enfin de savoir le nombre de clients par type
	private HashMap<Difficulte, Integer> clients = new HashMap<Difficulte, Integer>();
	//liste contenant des commandes à générer
	private ArrayList<Commande> commandes = new ArrayList<Commande>();
	//!------A modifier type selon comment gérer le temps Valsior ------!
	//Temps total de partie(calculable + marge!)
	private float tempsPartie; 
	//!-----Possible que l'on doive le faire selon l'argent -----!
	//Trois temps, les trois désignant donc les trois étoiles obtenables (calculable !)
	private float[] scoreAuTemps;
	//!-----Type à vérifier-------!
	//Tresorerie de début de partie(calculable + marge!)
	private float tresorerie;
	//total clientèle
	private int nbClients;
	//Nb ingredients autorisé
	private int[] nbIngredients = new int[2];
	public Niveau(String nom, Difficulte difficulte, int nbPremierTypeClient, int nbDeuxiemeTypeClient, int nbTroisiemeTypeClient, float margeTresorerie, float margeTemps, int minIng, int maxIng) {
		this.nbClients = nbPremierTypeClient + nbDeuxiemeTypeClient + nbTroisiemeTypeClient;
		if(margeTresorerie < 0 || margeTemps < 0f || nbPremierTypeClient < 0 || nbDeuxiemeTypeClient < 0 || nbTroisiemeTypeClient < 0 || minIng <= 0 || this.nbClients == 0 || maxIng <= 0) {
			throw new IllegalArgumentException("Les valeurs numériques ne peuvent être négative !");
		} else if (minIng > maxIng) {
			throw new IllegalArgumentException("La valeur d'ingrédients minimum doit être inférieur à celle d'ingrédients maximal");
		}
		this.nom = nom;
		this.difficulte=difficulte;
		clients.put(Difficulte.Facile, nbPremierTypeClient);
		clients.put(Difficulte.Normal, nbDeuxiemeTypeClient);
		clients.put(Difficulte.Karen, nbTroisiemeTypeClient);
		this.nbIngredients[0]=minIng;
		this.nbIngredients[1]=maxIng;
		//!--Calculer selon les commandes---!//
		//settresorerie(margeTresorerie);
		//!----A faire avec les autres----!//
		//setTempsPartie(margeTemps);
	}
	/**
	 * Permet d'obtenir la tresorerie du niveau
	 * @return la tresorerie du niveau
	 */
	public float gettresorerie() {
		return this.tresorerie;
	}
	/**
	 * Permet de calculer la trésorerie du niveau, en y ajoutant une marge donnée dans la création du niveau
	 * @param tresorerie
	 * @parem marge 
	 */
	public void settresorerie(float marge) {
		//!---Calcul à faire selon les ingrédients voulus par les clients et donc leurs prix !----
		float tresorerie = 0;
		this.tresorerie = tresorerie * marge;
	}
	/**
	 * Permet d'obtenir les réglages du score, calculé selon le temps total de jeu
	 * @return un tableau de float contenant les trois temps correspondant aux trois étoiles de score obtenables
	 */
	public float[] getScoreAuTemps() {
		return scoreAuTemps;
	}
	/**
	 * Permet de paramétrer les scores obtenables d'un coup
	 */
	public void setScoreAuTemps() {
		this.setScoreAuTemps1();
		this.setScoreAuTemps2();
		this.setScoreAuTemps3();
	}
	/**
	 * Permet de paramétrer le score le plus bas
	 */
	public void setScoreAuTemps1() {
		//------! Calcul a faire, totalement faux ----- !
		float scoreAuTemps = 0;
		this.scoreAuTemps[0] = scoreAuTemps;
	}
	/**
	 * Permet de paramétrer le score moyen
	 */
	public void setScoreAuTemps2() {
		//------! Calcul a faire, totalement faux ----- !
		float scoreAuTemps = getTempsPartie()*(1/3);
		this.scoreAuTemps[1] = scoreAuTemps;
	}
	/**
	 * Permet de paramétrer le score le plus haut
	 */
	public void setScoreAuTemps3() {
		//------! Calcul a faire, totalement faux ----- !
		float scoreAuTemps = getTempsPartie()*(2/3);
		this.scoreAuTemps[2] = scoreAuTemps;
	}
	
	/**
	 * Permet d'obtenir la liste HashMap des clients, en clef le type et en données, le nombre
	 * @return Une HashMap
	 */
	public HashMap<Difficulte, Integer> getClients() {
		return clients;
	}
	/**
	 * Permet d'obtenir le temps de la partie 
	 * @return le temps de la partie autorisée et max
	 */
	public float getTempsPartie() {
		return tempsPartie;
	}
	/**
	 * Permet de calculer le temps de la partie
	 * @param marge
	 */
	public void setTempsPartie(float marge) {
		//!---Calcul à faire selon le temps imparti des clients !----
		float tempsPartie = 0;
		this.tempsPartie = tempsPartie * marge;
		setScoreAuTemps();
	}
	/**
	 * Permet d'obtenir la difficulté du niveau 
	 * @return la difficulté
	 */
	public Difficulte getDifficulte() {
		return difficulte;
	}
	/**
	 * Permet d'obtenir le nom du niveau
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}
	public boolean genererCommande() {
		//!----Pour lecture fichier ingrédients ---!//
		/*
	    ArrayList<String> tmp = new ArrayList<String>();
	    try {
	      //lire le fichier
	      FileReader file = new FileReader("C:\\Users\\david\\git\\FinibusPizza\\src\\FinibusPizza\\textes\\ingredients");
	      BufferedReader buffer = new BufferedReader(file);
	      tmp.add(buffer.readLine());
	      int i = 1;
	      // parcourir le fichier
	      while(tmp.get(tmp.size()-1) != null) {
	          if(i>2){
	          	tmp.add(buffer.readLine());
	          } 
	          i++;
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }*/
        Iterator iterator = clients.entrySet().iterator();
        //Parcours HashMapClients
        while (iterator.hasNext()) {
          Map.Entry mapentry = (Map.Entry) iterator.next();
          //Distribution des commandes de manière aléatoire dans commandes
          for(int i1=(int) mapentry.getValue(); i1>0;i1--) {
        	  Random r = new Random();
              int place = r.nextInt(this.nbClients);
              //Verification si la place est libre
              while(this.commandes.get(place) != null) {
            	  place = r.nextInt(this.nbClients);
              }
              //generation commande selon client obtenu
              //!--- A faire quand le type d'ingrédients sera inclu dans clients

              //generation client
              
              //Generation nbIngredients total dans la pizza, en oubliant la pate et la base !
              Random r1 = new Random();
              int nbIngredientsTotal = r.nextInt((this.nbIngredients[1] - this.nbIngredients[0]) + 1) + this.nbIngredients[0];          
              
              //Generation Pates
              
              //Generation Base 
              Random r2 = new Random();
              
              //calcul temps preparation
              
              Commande c = new Commande(null, null, null, 0);
              //Client unClient, HashMap<Integer, Ingredients> lesIngredients, Pate laPate, int tempsPreparation
              this.commandes.add(place, c);
              System.out.println(place);
        	  //
          }
        }
		return true;
	}
	public static void main(String[] args) {
		Niveau n = new Niveau("h", Difficulte.Karen, 2, 3, 4, 3.3f, 3.3f,10,15);
		n.genererCommande();
	}
	public void setScore() {
		//!---selon le temps de cuisson, le temps et le respect des ingrédients----!
	}

}

