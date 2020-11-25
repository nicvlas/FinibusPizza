package FinibusPizza;

import java.util.Random;

public class Client {
	
	private String nomClient;
	private Difficulte typeClient;
	private int tempsClient;
	private float pourboire;
	private int[] nbTypeIngredients;// Tableau min, max nombre type d'ingr�dients
	private String[] lesNomsClient;
	private float margeTemps;
	
	//Faire un fichier avec margeTemps, pourboire initial, min et max nombre type d'ingr�dients
	
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
		this.lesNomsClient = new String[] {"Michel","Bernard","Christophe","Alexandre","Jean","Camille","Cécile","Elise","Bernadette","Manon"};
		this.typeClient = typeClient;
		Random rd = new Random();
		switch(this.typeClient){
			case Facile:{
				//Définir le nom du client
				this.nomClient = this.lesNomsClient[(rd.nextInt(9 - 0) + 0)];
				//Nombre max de type d'ingrédients (Valeur test sans compter la pâte et la base)
				this.nbTypeIngredients[0] = 2;
				this.nbTypeIngredients[1] = 3;
				break;}
			case Normal:{
				//Définir le nom du client
				this.nomClient = this.lesNomsClient[(rd.nextInt(9 - 0) + 0)];
				//Nombre max de type d'ingrédients (Valeur test sans compter la pâte et la base)
				this.nbTypeIngredients[0]=3;
				this.nbTypeIngredients[1] = 4;
				break;}
			case Karen:{
				//Définir le nom du client
				this.nomClient = "Karen";
				//Nombre max de type d'ingrédients (Valeur test sans compter la pâte et la base)
				this.nbTypeIngredients[0] = 4;
				this.nbTypeIngredients[1] = 5;
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
				//Nombre max de type d'ingrédients (Valeur test sans compter la pâte et la base)
				this.nbTypeIngredients[0] = 2;
				this.nbTypeIngredients[1] = 3;
				break;}
			case Normal:{
				//Nombre max de type d'ingrédients (Valeur test sans compter la pâte et la base)
				this.nbTypeIngredients[0]=3;
				this.nbTypeIngredients[1] = 4;
				break;}
			case Karen:{
				//Nombre max de type d'ingrédients (Valeur test sans compter la pâte et la base)
				this.nbTypeIngredients[0] = 4;
				this.nbTypeIngredients[1] = 5;
				break;}
		}
		
	}
	
	/**
	 * Permet de déterminer le pourboire du client
	 * @param tempsPreparation : le temps de préparation de la commande
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
	}

}
 
