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
	//Difficulte du niveau, � choisir selon l'enum�ratuon difficult�
	private Difficulte difficulte; 
	//Liste contenant au maximum les trois types de clients possibles en tant que cl� enfin de savoir le nombre de clients par type
	private HashMap<Difficulte, Integer> clients = new HashMap<Difficulte, Integer>();
	//liste contenant des commandes � g�n�rer
	private ArrayList<Commande> commandes = new ArrayList<Commande>();
	//!------A modifier type selon comment g�rer le temps Valsior ------!
	//Temps total de partie(calculable + marge!)
	private float tempsPartie; 
	//!-----Possible que l'on doive le faire selon l'argent -----!
	//Trois temps, les trois d�signant donc les trois �toiles obtenables (calculable !)
	private float[] scoreAuTemps;
	//!-----Type � v�rifier-------!
	//Tresorerie de d�but de partie(calculable + marge!)
	private float tresorerie;
	//total client�le
	private int nbClients;
	//Nb ingredients autoris�
	private int[] nbIngredients = new int[2];
	public Niveau(String nom, Difficulte difficulte, int nbPremierTypeClient, int nbDeuxiemeTypeClient, int nbTroisiemeTypeClient, float margeTresorerie, float margeTemps, int minIng, int maxIng) {
		this.nbClients = nbPremierTypeClient + nbDeuxiemeTypeClient + nbTroisiemeTypeClient;
		if(margeTresorerie < 0 || margeTemps < 0f || nbPremierTypeClient < 0 || nbDeuxiemeTypeClient < 0 || nbTroisiemeTypeClient < 0 || minIng <= 0 || this.nbClients == 0 || maxIng <= 0) {
			throw new IllegalArgumentException("Les valeurs num�riques ne peuvent �tre n�gative !");
		} else if (minIng > maxIng) {
			throw new IllegalArgumentException("La valeur d'ingr�dients minimum doit �tre inf�rieur � celle d'ingr�dients maximal");
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
	 * Permet de calculer la tr�sorerie du niveau, en y ajoutant une marge donn�e dans la cr�ation du niveau
	 * @param tresorerie
	 * @parem marge 
	 */
	public void settresorerie(float marge) {
		//!---Calcul � faire selon les ingr�dients voulus par les clients et donc leurs prix !----
		float tresorerie = 0;
		this.tresorerie = tresorerie * marge;
	}
	/**
	 * Permet d'obtenir les r�glages du score, calcul� selon le temps total de jeu
	 * @return un tableau de float contenant les trois temps correspondant aux trois �toiles de score obtenables
	 */
	public float[] getScoreAuTemps() {
		return scoreAuTemps;
	}
	/**
	 * Permet de param�trer les scores obtenables d'un coup
	 */
	public void setScoreAuTemps() {
		this.setScoreAuTemps1();
		this.setScoreAuTemps2();
		this.setScoreAuTemps3();
	}
	/**
	 * Permet de param�trer le score le plus bas
	 */
	public void setScoreAuTemps1() {
		//------! Calcul a faire, totalement faux ----- !
		float scoreAuTemps = 0;
		this.scoreAuTemps[0] = scoreAuTemps;
	}
	/**
	 * Permet de param�trer le score moyen
	 */
	public void setScoreAuTemps2() {
		//------! Calcul a faire, totalement faux ----- !
		float scoreAuTemps = getTempsPartie()*(1/3);
		this.scoreAuTemps[1] = scoreAuTemps;
	}
	/**
	 * Permet de param�trer le score le plus haut
	 */
	public void setScoreAuTemps3() {
		//------! Calcul a faire, totalement faux ----- !
		float scoreAuTemps = getTempsPartie()*(2/3);
		this.scoreAuTemps[2] = scoreAuTemps;
	}
	
	/**
	 * Permet d'obtenir la liste HashMap des clients, en clef le type et en donn�es, le nombre
	 * @return Une HashMap
	 */
	public HashMap<Difficulte, Integer> getClients() {
		return clients;
	}
	/**
	 * Permet d'obtenir le temps de la partie 
	 * @return le temps de la partie autoris�e et max
	 */
	public float getTempsPartie() {
		return tempsPartie;
	}
	/**
	 * Permet de calculer le temps de la partie
	 * @param marge
	 */
	public void setTempsPartie(float marge) {
		//!---Calcul � faire selon le temps imparti des clients !----
		float tempsPartie = 0;
		this.tempsPartie = tempsPartie * marge;
		setScoreAuTemps();
	}
	/**
	 * Permet d'obtenir la difficult� du niveau 
	 * @return la difficult�
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
			throw new InternalError(element + " ne correspond pas � un String d'un fichier contenant des ingr�dients traitables");
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
	      //!---Modifier dernier valeur--!
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
	public boolean genererCommande() {
        Iterator iterator = clients.entrySet().iterator();
        //Parcours HashMapClients
        while (iterator.hasNext()) {
          Map.Entry mapentry = (Map.Entry) iterator.next();
          //Distribution des commandes de mani�re al�atoire dans commandes
          for(int i1=(int) mapentry.getValue(); i1>0;i1--) {
        	  Random r = new Random();
              int place = r.nextInt(this.nbClients);
              //Verification si la place est libre
              while(this.commandes.get(place) != null) {
            	  place = r.nextInt(this.nbClients);
              }
             //generation commande selon client obtenu
             //!--- A faire quand le type d'ingr�dients sera inclu dans clients ----!

              //generation client
              //!---- A surveiller ---- !
              Client c = generationClient((Difficulte)mapentry.getKey());
              
              //Generation nbIngredients total dans la pizza, en oubliant la pate et la base !
              Random r1 = new Random();
              int nbIngredientsTotal = r.nextInt((this.nbIngredients[1] - this.nbIngredients[0]) + 1) + this.nbIngredients[0];          
              
              //Generation Pates
              Pate pate = generationPate();
    	      
              //Generation Base 
              Ingredients base = generationBase();
      	      
              //Gestion reste ingr�dients 
      	      
      	      //calcul temps preparation
              
      	      //creation commande
              Commande commande = new Commande(c, null, pate, 0);
              //Client unClient, HashMap<Integer, Ingredients> lesIngredients, Pate laPate, int tempsPreparation
              
              this.commandes.add(place, commande);
              System.out.println(place);
          }
        }
		return true;
	}
	public static void main(String[] args) {
		Niveau n = new Niveau("h", Difficulte.Karen, 2, 3, 4, 3.3f, 3.3f,10,15);
		n.genererCommande();
	}
	public void setScore() {
		//!---selon le temps de cuisson, le temps et le respect des ingr�dients----!
	}

}

