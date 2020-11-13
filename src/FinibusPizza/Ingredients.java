package FinibusPizza;

public class Ingredients {

	private String nom;
	private float prixAchat;
	private float prixVente;
	private String cheminDaccesImage;
	private int stock;
	

	public String getNom() {
		return nom;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public double getPrixVente() {
		return prixVente;
	}
	
	public String getCheminDaccesImage() {
		return cheminDaccesImage;
	}
	
	/**
	 * Permet d'acheter l'ingrédient, en contrepartie d'argent
	 */
	public void acheter(int qte) {
		//ajouter au stock du jeu la valeur en paramètre
		//enlever à la trésorerie du jeu qte*this.getPrixAchat()
		//if trésorerie <= 0 : GAME OVER
		
	}

	/**
	 * Retourne les informations de l'ingrédient
	 * 
	 */
	public String toString() {
		return "Nom ingrédient : "+this.getNom()+".\nPrix d'achat : "+
								Math.round(this.getPrixAchat())+".\nPrix de vente : "+this.getPrixVente()+"\nEn stock : "+this.stock;
	}
	
	/**
	 * Constructeur d'un ingrédient
	 * @param nom : nom de l'ingrédient
	 * @param prixAchat : prix d'achat
	 * @param prixVente : prix de vente
	 * @param cheminDaccesImage : chemin d'accès pour l'image
	 */
	public Ingredients(String nom, float prixAchat, float prixVente, String cheminDaccesImage) {
		this.nom = nom;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.cheminDaccesImage = cheminDaccesImage;
		this.stock = 0; //à vérifier
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ingredients fromage = new Ingredients("Fromage", 1.88f, 2.30f, "lol");
		fromage.stock = 1;
		System.out.println(fromage.toString());	
				
	}



}
