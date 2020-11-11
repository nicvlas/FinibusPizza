package FinibusPizza;

import java.util.Random;

public class Client {
	private String nomClient;
	private Integer typeClient;
	private Integer tempsClient;
	private Float marge;
	private Float pourboire;
	
	private String[] lesNomsClient;
	
	public Integer getTypeClient() {
		return typeClient;
	}

	public Integer getTempsClient() {
		return tempsClient;
	}

	public Float getMarge() {
		return marge;
	}

	public Float getPourboire() {
		return pourboire;
	}
	
	/**
	 * Constructeur du client
	 * @param typeClient : type de client (simple, normal, difficile, Karen)
	 */
	public Client(int typeClient){
		this.lesNomsClient = new String[] {"Michel","Bernard","Christophe","Alexandre","Jean","Camille","Cécile","Elise","Bernadette","Manon"};
		this.typeClient = typeClient;
		Random rd = new Random();
		switch(this.typeClient){
			case 0:{
				//Définir la marge du client simple
				this.marge = 1.3f;
				//Définir le nom du client
				this.nomClient = this.lesNomsClient[(rd.nextInt(9 - 0) + 0)];
				break;}
			case 1:{
				//Définir la marge du client normal
				this.marge = 1.2f;
				//Définir le nom du client
				this.nomClient = this.lesNomsClient[(rd.nextInt(9 - 0) + 0)];
				break;}
			case 2:{
				//Définir la marge du client difficile
				this.marge = 1.1f;
				//Définir le nom du client
				this.nomClient = this.lesNomsClient[(rd.nextInt(9 - 0) + 0)];
				break;}
			case 3:{
				//Définir la marge du client Karen
				this.marge = 1f;
				this.nomClient = "Karen";
				break;}
		}
		this.tempsClient = 0;
		this.pourboire = 0f;
	}
	
	/**
	 * Constructeur du client
	 * @param typeClient : type de client (simple, normal, difficile, Karen)
	 */
	public Client(String nom, int typeClient){
		this.typeClient = typeClient;
		switch(this.typeClient){
			case 0:{
				//Définir la marge du client simple
				this.marge = 1.3f;
				break;}
			case 1:{
				//Définir la marge du client normal
				this.marge = 1.2f;
				break;}
			case 2:{
				//Définir la marge du client difficile
				this.marge = 1.1f;
				break;}
			case 3:{
				//Définir la marge du client Karen
				this.marge = 1f;
				this.nomClient = "Karen";
				break;}
		}
		this.tempsClient = 0;
		this.pourboire = 0f;
		this.nomClient = nom;
	}
	
	/**
	 * Permet de déterminer le pourboire du client
	 * @param tempsPreparation : le temps de préparation de la commande
	 * @return pourboire : le pourboire du client
	 */
	public Float pourboire(Integer tempsPreparation){
		int tempsFinal = this.tempsClient - tempsPreparation;
		// Vérifie si le joueur a servi la pizza dans les temps
		//Traitement de la note et du pourboire
		if(tempsFinal >= 0){
			this.pourboire += 2;
			if(tempsFinal > 2)
			{
				this.pourboire += 2;
				if(tempsFinal > 4) {
					this.pourboire += 2;
					if(tempsFinal > 6) {
						this.pourboire += 2;
						if(tempsFinal > 8) {
							this.pourboire += 2;
						}
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
		Client c1 = new Client(0);
		Client c2 = new Client(1);
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		c1.pourboire(30);
		System.out.println(c1.toString());
		System.out.println(c2.toString());
	}

}
