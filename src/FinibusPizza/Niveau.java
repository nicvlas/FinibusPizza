package FinibusPizza;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Niveau {
	//Nom du niveau
	private String nom;
	//Difficulte du niveau, à choisir selon l'enumératuon difficulté
	private Difficulte difficulte; 
	//Liste contenant au maximum les trois types de clients possibles en tant que clé enfin de savoir le nombre de clients par type
	private HashMap<Client, Integer> clients = new HashMap<Client, Integer>();
	//liste contenant des commandes à générer
	private ArrayList<Commande> commandes = new ArrayList<Commande>();
	//!------A modifier type selon comment gérer le temps Valsior ------!
	//Temps total de partie(calculable + marge!)
	private float tempsPartie; 
	//!-----Possible que l'on doive le faire selon l'argent -----!
	//Trois temps, les trois désignant donc les trois étoiles obtenables (calculable !)
	private float[] scoreAuTemps;
	//Temps selon respect ingrédients
	private float[] scoreAIngredients;
	//!-----Type à vérifier-------!
	//Tresorerie de début de partie(calculable + marge!)
	private float tresorerie;
	
	public Niveau(String nom, Difficulte difficulte, int nbPremierTypeClient, int nbDeuxiemeTypeClient, int nbTroisiemeTypeClient, float margeTresorerie, float margeTemps) {
		this.nom = nom;
		this.difficulte=difficulte;
		//!----- Faire l'inclusion des types clients quand l'enumération sera faite -----!//
		//settresorerie(margeTresorerie);
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
		float tresorerie = (Float) null;
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
	public HashMap<Client, Integer> getClients() {
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
		float tempsPartie = (Float) null;
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
	    int i;
	    String line = new String();
	    
	    try {
	      //lire le fichier file.txt
	      FileReader file = new FileReader("C:\\Users\\david\\git\\FinibusPizza\\src\\FinibusPizza\\textes\\ingredients");
	      BufferedReader buffer = new BufferedReader(file);
	      String tmp = buffer.readLine();
	      // parcourir le fichier
	      while(tmp != null) {
	          line += tmp + "\n";
	          tmp = buffer.readLine();
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    System.out.println(line);
		return true;
	}
	public static void main(String[] args) {
		Niveau n = new Niveau("h", Difficulte.Facile, 1, 1, 1, 3.3f, 3.3f);
		n.genererCommande();
	}
	public float[] getScoreAIngredients() {
		return scoreAIngredients;
	}
	public void setScoreAIngredients(float[] scoreAIngredients) {
		this.scoreAIngredients = scoreAIngredients;
	}
	public void setScore() {
		//!---selon le temps de cuisson, le temps et le respect des ingrédients----!
	}

}

