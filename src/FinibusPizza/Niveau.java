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
	private float scoreCuisson;
	private float scorePizzaIng;
	//!-----Type à vérifier-------!
	//Tresorerie de début de partie(calculable + marge!)
	private float tresorerie = 0;
	//total clientèle
	private int nbClients;
	//Nb ingredients autorisé
	private int[] nbIngredients = new int[2];
	//marge de temps, calculable ! Pour score
	private int margeTemps = 0;
	//marge de tresorerie
	private float margeTresorerie;
	//tresorerie cours partie 
	private float tresorerietmp = 0;
	//Temps cours partie
	private int tempstmp = 0;
	//Nombre de guichet ouvert, maximum 3 
	private int nbPersonneComptoir;
	public Niveau(String nom, Difficulte diff, int nb1TypeClient, int nb2TypeClient, int nb3TypeClient, float margeTresor, int minIng, int maxIng) {
		this.nbClients = nb1TypeClient + nb2TypeClient + nb3TypeClient;
		if(margeTresor < 0 || nb1TypeClient < 0 || nb2TypeClient < 0 || nb3TypeClient < 0 || minIng <= 0 || this.nbClients == 0 || maxIng <= 0) {
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
		switch(diff) {
		case Karen:
			this.nbPersonneComptoir=3;
			break;
		case Normal:
			this.nbPersonneComptoir=2;
			break;
		default:
			this.nbPersonneComptoir=1;
				
		}
			
	}
	public ArrayList<Commande> getCommandes() {
		return commandes;
	}
	public int getTempsTmp() {
		return this.tempstmp;
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
	/**
	 * Set ingrédients
	 * @param minIng
	 * @param maxIng
	 */
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
	 * Permet d'obtenir les paramètres des niveaux, séparé par un /
	 * @param element
	 * @return liste des elements disponibles et utiles dans le string donnée
	 */
	public String[] elementsNiveau(String element) {
		String[] retour = element.split( "/" );
		if(retour.length != 8) {
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
    	      FileReader fileN = new FileReader(getClass().getResource("./textes/noms").getFile());
    	      FileReader fileP = new FileReader(getClass().getResource("./textes/prenoms").getFile());
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
    	      FileReader fileB = new FileReader(getClass().getResource("./textes/bases").getFile());
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
  	      FileReader fileI = new FileReader(getClass().getResource("./textes/ingredients").getFile());
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
              //!--Attente code client --!//
              this.setTempsPartiePetitaPetit((int) (commande.getTempsPreparation()*c.getMargeTemps()));
              this.margeTemps+=(int) (commande.getTempsPreparation()*c.getMargeTemps()) - commande.getTempsPreparation(); 
              this.settresoreriePetitaPetit(commande.getAchatCommande());
          }
        }
        this.settresorerie(this.margeTresorerie);
		return true;
	}
	/**Calcul le score de cuisson à partir de la fonction cuire() de Commande
	 * 
	 * @param score
	 */
	public void setScoreCuisson(float score) {
		this.scoreCuisson = score;
	}
	/**
	 * Permet de calculer le score de la pizza à l'unité. A utiliser à chaque fois qu'une pizza est donnée à un client
	 * @param pizza
	 * @param commande
	 */
	public void setScorePizzaIng(HashMap<Ingredients, Integer> pizza, Commande commande) {
        HashMap<Ingredients, Integer> pizzaDemandee = commande.getLesIngredients();
        Iterator it = pizzaDemandee.entrySet().iterator();
        int score = 3;
        score -= (pizzaDemandee.size() == pizza.size())?0:1;
        int sortieBoucle = 0;
        while (it.hasNext() || sortieBoucle ==1) {
            Map.Entry m = (Map.Entry) it.next();
            if(pizza.containsKey(m.getKey())) {
            	score-=(pizza.get(m.getKey()) ==  m.getValue())?0:1;
            	sortieBoucle++;
            }
        }
        this.scorePizzaIng+=score;
	}
	/**
	 * Permet d'obtenir le score total !
	 */
	public void setScore() {
		//!---selon le temps de cuisson, le temps et le respect des ingrédients----!
		int scoreTresor;
		this.scorePizzaIng = this.scorePizzaIng/this.commandes.size();
		if(this.scoreATresorerie[0] <  getTresorerietmp() && getTresorerietmp() < this.scoreATresorerie[1]) {
			scoreTresor = 2;
		} else if (this.scoreATresorerie[1] <  getTresorerietmp() && getTresorerietmp() < this.scoreATresorerie[2]) {
			scoreTresor = 3;
		} else if(getTresorerietmp() > 0){
			scoreTresor = 1;
		} else {
			scoreTresor = 3;
		}
		int scoreTemps;
		if(this.scoreAuTemps[0] <  getTempstmp() && getTempstmp() < this.scoreAuTemps[1]) {
			scoreTemps = 2;
		} else if (this.scoreAuTemps[1] <  getTempstmp() && getTempstmp() < this.scoreAuTemps[2]) {
			scoreTemps = 3;
		} else if(getTempstmp() > 0){
			scoreTemps = 1;
		} else {
			scoreTemps = 3;
		}
		float score = (scoreTemps == 0 || scoreTresor ==0 || scoreCuisson == 0 || scorePizzaIng ==0)?0:(scoreTemps+scoreTresor+scoreCuisson+scorePizzaIng)/3;
		this.score = score;
	}
	/**
	 * Permet d'obtenir le montant de trésorerie encore disponible
	 * @return de l'argent
	 */
	public float getTresorerietmp() {
		return tresorerietmp;
	}
	/**
	 * Permet de mettre à jour la trésorerie
	 * @param tresorerietmp
	 */
	public void setTresorerietmp(float tresorerietmp) {
		this.tresorerietmp = tresorerietmp;
	}
	/**
	 * Get temps en cours
	 * @return le temps non écoulé de partie
	 */
	public int getTempstmp() {
		return tempstmp;
	}
	/**
	 * setTempsEnCours
	 * @param tempstmp
	 */
	public void setTempstmp(int tempstmp) {
		this.tempstmp = tempstmp;
	}
	/**
	 * Permet de retirer de l'argent à chaque achat d'ingrédients
	 * @param ing
	 * @param nb
	 * @return si l'on peut continuer de jouer
	 */
	public boolean retraitArgentIng(Ingredients ing, int nb) {
		this.tresorerietmp-=ing.getPrixAchat()*nb;
		if(this.tresorerietmp <= 0 ) {
			this.defaite("Tresorerie vide");
			return false;
		}
		return true;
		
	}
	/**
	 * Permet de retirer de l'argent à l'achat de la pate
	 * @param ing
	 * @return si l'on peut continuer de jouer
	 */
	public boolean retraitArgentIng(Pate ing) {
		this.tresorerietmp-=ing.getPrixAchat();
		if(this.tresorerietmp <= 0 ) {
			this.defaite("Tresorerie vide");
			return false;
		}
		return true;
		
	}
	/**
	 * Permet de vendre la pizza et de recolter l'argent de la commande initiale
	 * @param commande
	 * @param pizza
	 */
	public void ventePizza(Commande commande, Commande pizza) {
		this.setScorePizzaIng(pizza.getLesIngredients(), commande);
		int pourboire = (this.scorePizzaIng == 3 || this.scoreCuisson == 3)?(int)commande.getUnClient().getPourboire():0;
		this.tresorerietmp+=(commande.getVenteCommande() < pizza.getVenteCommande())?commande.getVenteCommande() + pourboire :pizza.getVenteCommande() + pourboire;
		this.setScorePizzaIng(pizza.getLesIngredients(), commande);
	}
	//!--A gerer avec l'interface graphique ---!!!
	/**
	 * Permet de gérer les défaites et d'afficher leurs raisons
	 * @param raison
	 */
	public void defaite(String raison) {
		System.out.println("Perdu : " + raison);
	}
	public void partie() {
		/*ArrayList<String> tmp = new ArrayList<String>();
		try {
	  	      //lire le fichier
	  	      FileReader file = new FileReader(getClass().getResource("./textes/niveaux").getFile());
	  	      BufferedReader buffer = new BufferedReader(file);
	  	      tmp.add(buffer.readLine());
	  	      // parcourir le fichier
	  	      while(tmp.get(tmp.size()-1) != null) {
	  	    	tmp.add(buffer.readLine());
	  	      } 
	  	   }
	  	   catch (IOException e) {
	  	      e.printStackTrace();
	  	   }
		int tmpNb = 0;
		String[] donneesNiv = new String[8];
		while(elementsNiveau(tmp.get(tmpNb))[0] != nomNiveau && tmp.size() != tmpNb) {
			if(elementsNiveau(tmp.get(tmpNb))[0] == nomNiveau) {
				donneesNiv = elementsNiveau(tmp.get(tmpNb));
			}
			tmpNb++;
		}
		if(tmp.size() == tmpNb) {
			throw new InternalError("Fichier ne contenant pas ce niveau !");
		}
		Difficulte dur;
		switch(donneesNiv[1]) {
			case "Karen":
				dur = Difficulte.Karen;
				break;
			case "Normal":
				dur = Difficulte.Normal;
				break;
			default:
				dur = Difficulte.Facile;
		}
		Niveau niveauEnCours = new Niveau(donneesNiv[0],dur,Integer.parseInt(donneesNiv[2]),Integer.parseInt(donneesNiv[3]),Integer.parseInt(donneesNiv[4]),Float.parseFloat(donneesNiv[5]),Integer.parseInt(donneesNiv[6]),Integer.parseInt(donneesNiv[7]));*/
		ArrayList<Commande> commandes = this.getCommandes();
		
		
	}

}

