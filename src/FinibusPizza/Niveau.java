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
	//Temps total de partie(calculable + marge!)
	private int tempsPartie = 0; 
	//Trois temps, les trois désignant donc les trois étoiles obtenables (calculable !)
	private int[] scoreAuTemps;
	private float[] scoreATresorerie;
	private float score;
	//!-----Type à vérifier-------!
	//Tresorerie de début de partie(calculable + marge!)
	private float tresorerie = 0;
	//total clientèle
	private int nbClients;
	//Nb ingredients autorisé
	private int[] nbIngredients = new int[2];
	//marge de temps 
	private int margeTemps;
	//marge de tresorerie
	private float margeTresorerie;
	//tresorerie cours partie 
	private float tresorerietmp = 0;
	//Temps cours partie
	private int tempstmp = 0;
	public Niveau(String nom, Difficulte diff, int nb1TypeClient, int nb2TypeClient, int nb3TypeClient, float margeTresor, int margeTemps, int minIng, int maxIng) {
		this.nbClients = nb1TypeClient + nb2TypeClient + nb3TypeClient;
		if(margeTresor < 0 || margeTemps < 0f || nb1TypeClient < 0 || nb2TypeClient < 0 || nb3TypeClient < 0 || minIng <= 0 || this.nbClients == 0 || maxIng <= 0) {
			throw new IllegalArgumentException("Les valeurs numériques ne peuvent être négative !");
		} else if (minIng > maxIng) {
			throw new IllegalArgumentException("La valeur d'ingrédients minimum doit être inférieur à celle d'ingrédients maximal");
		}
		if(nom == null || difficulte == null) {
			throw new IllegalArgumentException("le nom et la difficulté ne peuvent être nuls");
		}
		this.nom = nom;
		this.difficulte=diff;
		this.setClients(nb1TypeClient, nb2TypeClient, nb3TypeClient);
		this.setnbIngredients(minIng, maxIng);
		//!--Calculer selon les commandes---!//
		this.margeTresorerie = margeTresor;
		this.margeTemps = margeTemps;
	}
	/**
	 * Modifier les nombres de clients par difficulté
	 * @param nbPremierTypeClient
	 * @param nbDeuxiemeTypeClient
	 * @param nbTroisiemeTypeClient
	 */
	public void setClients(int nbPremierTypeClient, int nbDeuxiemeTypeClient, int nbTroisiemeTypeClient) {
		clients.put(Difficulte.Facile, nbPremierTypeClient);
		clients.put(Difficulte.Normal, nbDeuxiemeTypeClient);
		clients.put(Difficulte.Karen, nbTroisiemeTypeClient);
	}
	public void setnbIngredients(int minIng, int maxIng) {
		this.nbIngredients[0]=minIng;
		this.nbIngredients[1]=maxIng;
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
	 * @parem marge 
	 */
	public void settresorerie(float marge, float tresor) {
		this.tresorerie = tresor * marge;
		this.setTresorerietmp(this.tresorerie); 
	}
	/**
	 * Permet de calculer la trésorerie du niveau, en y ajoutant une marge donnée dans la création du niveau
	 * @parem marge 
	 */
	public void settresorerie(float marge) {
		this.tresorerie *= marge;
		this.setTresorerietmp(this.tresorerie); 
	}
	/**
	 * Permet de calculer la trésorerie du niveau, en y ajoutant une marge donnée dans la création du niveau
	 * @param tresorerie
	 */
	public void settresoreriePetitaPetit(float tresorerie) {
		this.tresorerie += tresorerie;
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
		int score = this.margeTemps*(1/3);
		this.scoreAuTemps[0] = score;
	}
	/**
	 * Permet de paramétrer le score moyen
	 */
	public void setScoreAuTemps2() {
		int scoreAuTemps = this.margeTemps*(2/3);
		this.scoreAuTemps[1] = scoreAuTemps;
	}
	/**
	 * Permet de paramétrer le score le plus haut
	 */
	public void setScoreAuTemps3() {
		int scoreAuTemps = this.margeTemps;
		this.scoreAuTemps[2] = scoreAuTemps;
	}
	/**
	 * Permet de paramétrer les scores obtenables d'un coup
	 */
	public void setScoreALaTresorerie() {
		this.setScoreALaTresorerie1();
		this.setScoreALaTresorerie2();
		this.setScoreALaTresorerie3();
	}
	/**
	 * Permet de paramétrer le score le plus bas
	 */
	public void setScoreALaTresorerie1() {
		float score = this.margeTresorerie*(1/3);
		this.scoreATresorerie[0] = score;
	}
	/**
	 * Permet de paramétrer le score moyen
	 */
	public void setScoreALaTresorerie2() {
		float score = this.margeTresorerie*(2/3);
		this.scoreATresorerie[1] = score;
	}
	/**
	 * Permet de paramétrer le score le plus haut
	 */
	public void setScoreALaTresorerie3() {
		float score = this.margeTresorerie;
		this.scoreATresorerie[2] = score;
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
	 * @parem marge
	 */
	public void setTempsPartieDirectement(int temps, float marge) {
		this.tempsPartie = (int) (temps * marge);
		this.setTempstmp(this.tempsPartie);
	}
	/**
	 * Permet de calculer le temps de la partie
	 * @param temps
	 */
	public void setTempsPartieDirectement(int temps) {
		this.tempsPartie = temps;
		this.setTempstmp(this.tempsPartie);
	}
	/**
	 * Permet de calculer le temps de la partie
	 * @param marge
	 */
	public void setTempsPartiePetitaPetit(int temps) {
		this.tempsPartie += temps;
	}
	/**
	 * Permet de calculer le temps de la partie
	 * @param margeTemps
	 */
	public void setTempsPartie(float margeTemps) {
		this.tempsPartie *= margeTemps;
		this.setTempstmp(this.tempsPartie);
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
	/**
	 * Permet d'obtenir les paramètres des ingrédients, séparé par un /
	 * @param element
	 * @return liste des elements disponibles et utiles dans le string donnée
	 */
	public String[] elementsIngredients(String element) {
		String[] retour = element.split( "/" );
		if(retour.length != 3 || retour.length != 4) {
			throw new InternalError(element + " ne correspond pas à un String d'un fichier contenant des ingrédients traitables");
		}
		return retour;
	}
	/**
	 * Permet de génerer une Pate au hasard parmis celle présente parmis celle existante
	 * @return la pate choisie
	 */
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
	/**
	 * Permet de générer un client selon la difficulté choisir
	 * @param d, la difficulté choisie
	 * @return un client généré avec un nom et un prénom choisi aléatoirement parmis ceux de deux fichiers
	 */
	public Client generationClient(Difficulte d) {
		Random r3 = new Random();
        ArrayList<String> tmpN = new ArrayList<String>();
        ArrayList<String> tmpP = new ArrayList<String>();
	      try {
    	      //lire le fichier
    	      FileReader fileN = new FileReader("C:\\Users\\david\\git\\FinibusPizza\\src\\FinibusPizza\\textes\\noms");
    	      FileReader fileP = new FileReader("C:\\Users\\david\\git\\FinibusPizza\\src\\FinibusPizza\\textes\\prenoms");
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
	/**
	 * Permet de générer une base parmis celle d'un fichier
	 * @return la base générée
	 */
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
	/**
	 * Permet de génerer une hash map d'ingrédients, correspondant au contenu de la commande d'un client
	 * @param nbTypeIngredient, nombre de type d'ingrédients (viande ou fromage par exemple), un max et un min pour choisir aléatoirement entre les deux
	 * @param nbIngredient, il s'agît du nombre d'ingrédients pour chaque type, un max et un min pour choisir aléatoirement entre les deux
	 * @param base, il s'agît de la base choisie au préalable
	 * @return la hashmap avec la liste d'ingrédients et leurs nombre
	 */
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
	/**
	 * Permet de générer toutes les commandes en les répartissant aléatoirement dans une liste 
	 * @return si la tout s'est fait comme il faut
	 */
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
              this.settresoreriePetitaPetit(commande.getAchatCommande());
          }
        }
        this.settresorerie(this.margeTresorerie);
        this.setTempsPartie(this.margeTemps);
		return true;
	}
	public static void main(String[] args) {
		Niveau n = new Niveau("h", Difficulte.Karen, 2, 3, 4, 3, 3,10,15);
		n.genererCommande();
	}
	public void setScore() {
		//!---selon le temps de cuisson, le temps et le respect des ingrédients----!
		int scoreTresor;
		if(this.scoreATresorerie[0] <  getTresorerietmp() && getTresorerietmp() < this.scoreATresorerie[1]) {
			scoreTresor = 1;
		} else if (this.scoreATresorerie[1] <  getTresorerietmp() && getTresorerietmp() < this.scoreATresorerie[2]) {
			scoreTresor = 2;
		} else if(getTresorerietmp() > 0){
			scoreTresor = 0;
		} else {
			scoreTresor = 3;
		}
		int scoreTemps;
		if(this.scoreAuTemps[0] <  getTempstmp() && getTempstmp() < this.scoreAuTemps[1]) {
			scoreTemps = 1;
		} else if (this.scoreAuTemps[1] <  getTempstmp() && getTempstmp() < this.scoreAuTemps[2]) {
			scoreTemps = 2;
		} else if(getTempstmp() > 0){
			scoreTemps = 0;
		} else {
			scoreTemps = 3;
		}
		float score = (scoreTemps == 0 || scoreTresor ==0)?0:(scoreTemps+scoreTresor)/2;
		this.score = score;
	}
	public float getTresorerietmp() {
		return tresorerietmp;
	}
	public void setTresorerietmp(float tresorerietmp) {
		this.tresorerietmp = tresorerietmp;
	}
	public int getTempstmp() {
		return tempstmp;
	}
	public void setTempstmp(int tempstmp) {
		this.tempstmp = tempstmp;
	}

}

