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
	private int tempsPartie; 
	//!-----Possible que l'on doive le faire selon l'argent -----!
	//Trois temps, les trois désignant donc les trois étoiles obtenables (calculable !)
	private int[] scoreAuTemps;
	//!-----Type à vérifier-------!
	//Tresorerie de début de partie(calculable + marge!)
	private float tresorerie;
	//total clientèle
	private int nbClients;
	//Nb ingredients autorisé
	private int[] nbIngredients = new int[2];
	//marge de temps 
	private int margeTemps;
	public Niveau(String nom, Difficulte difficulte, int nbPremierTypeClient, int nbDeuxiemeTypeClient, int nbTroisiemeTypeClient, float margeTresorerie, int margeTemps, int minIng, int maxIng) {
		this.nbClients = nbPremierTypeClient + nbDeuxiemeTypeClient + nbTroisiemeTypeClient;
		if(margeTresorerie < 0 || margeTemps < 0f || nbPremierTypeClient < 0 || nbDeuxiemeTypeClient < 0 || nbTroisiemeTypeClient < 0 || minIng <= 0 || this.nbClients == 0 || maxIng <= 0) {
			throw new IllegalArgumentException("Les valeurs numériques ne peuvent être négative !");
		} else if (minIng > maxIng) {
			throw new IllegalArgumentException("La valeur d'ingrédients minimum doit être inférieur à celle d'ingrédients maximal");
		}
		if(nom == null || difficulte == null) {
			throw new IllegalArgumentException("le nom et la difficulté ne peuvent être nuls");
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
		this.margeTemps = margeTemps;
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
	public void settresorerie(int marge) {
		//!---Calcul à faire selon les ingrédients voulus par les clients et donc leurs prix !----
		float tresorerie = 0;
		this.tresorerie = tresorerie * marge;
	}
	/**
	 * Permet d'obtenir les réglages du score, calculé selon le temps total de jeu
	 * @return un tableau de float contenant les trois temps correspondant aux trois étoiles de score obtenables
	 */
	public int[] getScoreAuTemps() {
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
		int scoreAuTemps = 0;
		this.scoreAuTemps[0] = scoreAuTemps;
	}
	/**
	 * Permet de paramétrer le score moyen
	 */
	public void setScoreAuTemps2() {
		//------! Calcul a faire, totalement faux ----- !
		int scoreAuTemps = getTempsPartie()*(1/3);
		this.scoreAuTemps[1] = scoreAuTemps;
	}
	/**
	 * Permet de paramétrer le score le plus haut
	 */
	public void setScoreAuTemps3() {
		//------! Calcul a faire, totalement faux ----- !
		int scoreAuTemps = getTempsPartie()*(2/3);
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
	public int getTempsPartie() {
		return tempsPartie;
	}
	/**
	 * Permet de calculer le temps de la partie
	 * @param temps
	 */
	public void setTempsPartieDirectement(int temps) {
		this.tempsPartie = temps;
	}
	/**
	 * Permet de calculer le temps de la partie
	 * @param marge
	 */
	public void setTempsPartiePetitaPetit(int temps) {
		//!---Calcul à faire selon le temps imparti des clients !----
		this.tempsPartie += temps;
	}
	/**
	 * Permet de calculer le temps de la partie
	 * @param marge
	 */
	public void setTempsPartie(int marge) {
		this.tempsPartie *= marge;
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
	public String[] elementsIngredients(String element) {
		String[] retour = element.split( "/" );
		if(retour.length != 3 || retour.length != 4) {
			throw new InternalError(element + " ne correspond pas à un String d'un fichier contenant des ingrédients traitables");
		}
		return retour;
	}
	public Pate generationPate() {
		Random r3 = new Random();
        ArrayList<String> tmpP = new ArrayList<String>();
	      try {
    	      //lire le fichier
    	      FileReader fileP = new FileReader("C:\\Users\\david\\git\\FinibusPizza\\src\\FinibusPizza\\textes\\pates");
    	      BufferedReader bufferP = new BufferedReader(fileP);
    	      tmpP.add(bufferP.readLine());
    	      // parcourir le fichier
    	      while(tmpP.get(tmpP.size()-1) != null) {
    	    	  tmpP.add(bufferP.readLine());
    	          } 
    	   }
    	   catch (IOException e) {
    	      e.printStackTrace();
    	   }
	      
	      int nbPates = r3.nextInt((tmpP.size()) + 1);
	      String pateTmp = tmpP.get(nbPates-1);
	      String[] pateTmp1 = elementsIngredients(pateTmp);
	      return new Pate(pateTmp1[0], Float.valueOf(pateTmp1[1]), Float.valueOf(pateTmp1[2]), "yes");
	}
	public Client generationClient(Difficulte d) {
		Random r3 = new Random();
        ArrayList<String> tmpN = new ArrayList<String>();
        ArrayList<String> tmpP = new ArrayList<String>();
	      try {
    	      //lire le fichier
    	      FileReader fileN = new FileReader("C:\\Users\\david\\git\\FinibusPizza\\src\\FinibusPizza\\textes\\noms");
    	      FileReader fileP = new FileReader("C:\\Users\\david\\git\\FinibusPizza\\src\\FinibusPizza\\textes\\noms");
    	      BufferedReader bufferN = new BufferedReader(fileN);
    	      BufferedReader bufferP = new BufferedReader(fileP);
    	      tmpN.add(bufferN.readLine());
    	      tmpP.add(bufferP.readLine());
    	      // parcourir le fichier
    	      while(tmpP.get(tmpP.size()-1) != null) {
    	    	  tmpP.add(bufferP.readLine());
    	      } 
    	      while(tmpN.get(tmpN.size()-1) != null) {
    	    	  tmpN.add(bufferN.readLine());
    	      } 
    	   }
    	   catch (IOException e) {
    	      e.printStackTrace();
    	   }
	      int nbN = r3.nextInt((tmpN.size()) + 1);
	      int nbP = r3.nextInt((tmpP.size()) + 1);
	      String nomPrenom;
	      if(d != Difficulte.Karen) {
	    	  nomPrenom = tmpP.get(nbP-1) + " " + tmpN.get(nbN-1) ;
	      } else {
	    	  nomPrenom = "Karen " +  tmpN.get(nbN-1);
	      }
	      return new Client(nomPrenom,d);
	      
	}
	public Ingredients generationBase() {
		Random r2 = new Random();
        ArrayList<String> tmpB = new ArrayList<String>();
	      try {
    	      //lire le fichier
    	      FileReader fileB = new FileReader("C:\\Users\\david\\git\\FinibusPizza\\src\\FinibusPizza\\textes\\bases");
    	      BufferedReader bufferB = new BufferedReader(fileB);
    	      tmpB.add(bufferB.readLine());
    	      // parcourir le fichier
    	      while(tmpB.get(tmpB.size()-1) != null) {
    	          	tmpB.add(bufferB.readLine());
    	          } 
    	   }
    	   catch (IOException e) {
    	      e.printStackTrace();
    	   }
	      int nbBases = r2.nextInt((tmpB.size()) + 1);
	      String baseTmp = tmpB.get(nbBases-1);
	      String[] baseTmp1 = elementsIngredients(baseTmp);
	      return new Ingredients(baseTmp1[0], Float.valueOf(baseTmp1[1]), Float.valueOf(baseTmp1[2]), "yes");
	}
	public HashMap<Ingredients, Integer> generationListeIngredients(int[] nbTypeIngredient, int[] nbIngredient, Ingredients base){
		HashMap<Ingredients, Integer> ingredientsList = new HashMap<Ingredients, Integer>();
		Random r2 = new Random();
		ArrayList<String> tmpI = new ArrayList<String>();
		ingredientsList.put(base, 1);
		try {
  	      //lire le fichier
  	      FileReader fileI = new FileReader("C:\\Users\\david\\git\\FinibusPizza\\src\\FinibusPizza\\textes\\ingredients");
  	      BufferedReader bufferI = new BufferedReader(fileI);
  	      tmpI.add(bufferI.readLine());
  	      // parcourir le fichier
  	      while(tmpI.get(tmpI.size()-1) != null) {
  	    	  tmpI.add(bufferI.readLine());
  	      } 
  	   }
  	   catch (IOException e) {
  	      e.printStackTrace();
  	   }
		String catchTmp = " ";
		int nbTypeIng = r2.nextInt(nbTypeIngredient[1]-nbTypeIngredient[0]+1)+nbTypeIngredient[0];
		int nbIng;
		String ingTmp;
		int nbIngExistant;
		String[] ingTmp1;
		Ingredients ing;
		for(int i = nbTypeIng; i>0 ; i--) {
			nbIng = r2.nextInt(nbIngredient[1]-nbIngredient[0]+1)+nbIngredient[0];
			nbIngExistant = r2.nextInt((tmpI.size()) + 1);
			ingTmp = tmpI.get(nbIngExistant-1);
			while(ingTmp == catchTmp) {
				nbIngExistant = r2.nextInt((tmpI.size()) + 1);
				ingTmp = tmpI.get(nbIngExistant-1);
			}
			catchTmp = ingTmp;
			ingTmp1 = elementsIngredients(ingTmp);
			ing = new Ingredients(ingTmp1[0], Float.valueOf(ingTmp1[1]), Float.valueOf(ingTmp1[2]), "yes");
			ingredientsList.put(ing, nbIng);
		}
	   return ingredientsList;
	}
	public boolean genererCommande() {
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

              //generation client
              //!---- A surveiller ---- !
              Client c = generationClient((Difficulte)mapentry.getKey());
            		  
              //Generation Pates
              Pate pate = generationPate();
    	      
              //Generation Base 
              Ingredients base = generationBase();
      	      
              //Gestion reste ingrédients 
              HashMap<Ingredients, Integer> listeIng = generationListeIngredients(c.getnbTypeIngredients(), this.nbIngredients, base);
              
              Commande commande = new Commande(c, listeIng, pate);
              
              this.commandes.add(place, commande);
              this.setTempsPartiePetitaPetit(commande.getTempsPreparation());
          }
        }
        this.setTempsPartie(this.margeTemps);
		return true;
	}
	public static void main(String[] args) {
		Niveau n = new Niveau("h", Difficulte.Karen, 2, 3, 4, 3, 3,10,15);
		n.genererCommande();
	}
	public void setScore() {
		//!---selon le temps de cuisson, le temps et le respect des ingrédients----!
	}

}

