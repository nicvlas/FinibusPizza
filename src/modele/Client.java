package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Client {
	
	private String nomClient;
	private Difficulte typeClient;
	private int tempsClient;
	private float pourboire;
	private int[] nbTypeIngredients;// Tableau min, max nombre type d'ingrï¿½dients
	private String[] lesNomsClient;
	private float margeTemps;
	
	//Faire un fichier avec margeTemps, pourboire initial, min et max nombre type d'ingrï¿½dients
	
	public Difficulte getTypeClient() {
		return typeClient;
	}

	public int getTempsClient() {
		return tempsClient;
	}

	public float getPourboire() {
		return pourboire;
	}
	
	public int[] getnbTypeIngredients() {
		return nbTypeIngredients;
	}

	/**
	 * Constructeur du client
	 * @param typeClient : type de client (simple, normal, difficile, Karen)
	 */
	public Client(Difficulte typeClient){
		this.lesNomsClient = new String[] {"Michel","Bernard","Christophe","Alexandre","Jean","Camille","CÃ©cile","Elise","Bernadette","Manon"};
		this.typeClient = typeClient;
		this.nbTypeIngredients = new int[2];
		Random rd = new Random();
		switch(this.typeClient){
			case Facile:{
				//DÃ©finir le nom du client
				this.nomClient = this.lesNomsClient[(rd.nextInt(9 - 0) + 0)];
				//Nombre max de type d'ingrÃ©dients (Valeur test sans compter la pÃ¢te et la base)
				this.nbTypeIngredients[0] = 2;
				this.nbTypeIngredients[1] = 3;
				this.margeTemps=1.3f;
				break;}
			case Normal:{
				//DÃ©finir le nom du client
				this.nomClient = this.lesNomsClient[(rd.nextInt(9 - 0) + 0)];
				//Nombre max de type d'ingrÃ©dients (Valeur test sans compter la pÃ¢te et la base)
				this.nbTypeIngredients[0]=3;
				this.nbTypeIngredients[1] = 4;
				this.margeTemps=1.2f;
				break;}
			case Karen:{
				//DÃ©finir le nom du client
				this.nomClient = "Karen";
				//Nombre max de type d'ingrÃ©dients (Valeur test sans compter la pÃ¢te et la base)
				this.nbTypeIngredients[0] = 4;
				this.nbTypeIngredients[1] = 5;
				this.margeTemps=1.1f;
				break;}
		}
		this.tempsClient = 0;
		this.pourboire = 0f;
	}
	
	/**
	 * Constructeur du client
	 * @param typeClient : type de client (simple, normal, difficile, Karen)
	 */
	public Client(String nom, Difficulte typeClient){
		this.typeClient = typeClient;
		this.tempsClient = 0;
		this.pourboire = 0f;
		this.nomClient = nom;
		switch(this.typeClient){
			case Facile:{
				//Nombre max de type d'ingrÃ©dients (Valeur test sans compter la pÃ¢te et la base)
				this.nbTypeIngredients[0] = 2;
				this.nbTypeIngredients[1] = 3;
				this.margeTemps=1.3f;
				break;}
			case Normal:{
				//Nombre max de type d'ingrÃ©dients (Valeur test sans compter la pÃ¢te et la base)
				this.nbTypeIngredients[0]=3;
				this.nbTypeIngredients[1] = 4;
				this.margeTemps=1.2f;
				break;}
			case Karen:{
				//Nombre max de type d'ingrÃ©dients (Valeur test sans compter la pÃ¢te et la base)
				this.nbTypeIngredients[0] = 4;
				this.nbTypeIngredients[1] = 5;
				this.margeTemps=1.1f;
				break;}
		}
	}
	
	/*
	 * Lecture des paramètres des clients
	 */
	public void parametreClient() {
		ArrayList<String[]> tmpC = new ArrayList<String[]>();
		try {
			FileReader file = new FileReader(getClass().getResource("../textes/clients.txt").getFile());
			//FileReader file = new FileReader(new File(getClass().getResource("../textes/clients.txt").toURI()));
			BufferedReader buffer = new BufferedReader(file);
			String tmpC1 = buffer.readLine();
			
			while (tmpC1 != null) {
                tmpC.add(elementsClients(tmpC1));
                tmpC1 = buffer.readLine();
            }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < tmpC.size(); i++) {
			System.out.println(tmpC.get(i)+" | ");
		}
	}
	
	public String[] elementsClients(String element) {
		String[] retour = element.split( "/" );
		/*if(retour.length != 3 || retour.length != 4) {
			throw new InternalError(element + " ne correspond pas à un String d'un fichier contenant des clients traitables");
		}*/
		return retour;
	}
	
	
	/**
	 * Permet de dÃ©terminer le pourboire du client
	 * @param tempsPreparation : le temps de prÃ©paration de la commande
	 * @return pourboire : le pourboire du client
	 */
	public float pourboire(Integer tempsPreparation){
		int tempsFinal = this.tempsClient - tempsPreparation;
		//Traitement du pourboire
		if(tempsFinal >= 0){
			this.pourboire += 3;
			if(tempsFinal > 5){
				this.pourboire += 3;
				if(tempsFinal > 10) {
					this.pourboire += 3;
					if(tempsFinal > 15) {
						this.pourboire += 3;
						}
					}
				}
			}
		return this.pourboire;
	}
	
	/**
	 * Retourne les informations du client
	 */
	public String toString(){
		return "Nom : "+this.nomClient+" Type : "+this.typeClient+" Temps d'attente du client : "+this.tempsClient+" pourboire : "+this.pourboire;
	}
	
	public static void main(String[] args) {
		Client c1 = new Client(Difficulte.Facile);
		Client c2 = new Client(Difficulte.Karen);
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		c1.pourboire(30);
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println("_____");
		c1.parametreClient();
	}

	public float getMargeTemps() {
		return margeTemps;
	}

	public void setMargeTemps(float margeTemps) {
		this.margeTemps = margeTemps;
	}

}
 
