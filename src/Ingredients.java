
public class Ingredients {

	private String nom;
	private float prixAchat;
	private float prixVente;
	private String cheminDaccesImage;
	private int stock;
	private int tempsDePoseIngredient;
	

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
	
	public int getTempsDePoseIngredient() {
		return tempsDePoseIngredient;
	}

	
	/**
	 * Permet d'acheter l'ingr�dient, en contrepartie d'argent
	 */
	public void acheter(int qte) {
		//ajouter au stock du jeu la valeur en param�tre
		//enlever � la tr�sorerie du jeu qte*this.getPrixAchat()
		//if tr�sorerie <= 0 : GAME OVER
		
	}

	/**
	 * Retourne les informations de l'ingr�dient
	 * 
	 */
	public String toString() {
		return "Nom ingr�dient : "+this.getNom()+".\nPrix d'achat : "+ Math.round(this.getPrixAchat())+".\nPrix de vente : "+this.getPrixVente()+"\nEn stock : "+this.stock;
	}
	
	/**
	 * Constructeur d'un ingr�dient, qui ajoute un stock et un temps de pose
	 * @param nom : nom de l'ingr�dient
	 * @param prixAchat : prix d'achat
	 * @param prixVente : prix de vente
	 * @param cheminDaccesImage : chemin d'acc�s pour l'image
	 */
	public Ingredients(String nom, float prixAchat, float prixVente, String cheminDaccesImage) {
		this.nom = nom;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.cheminDaccesImage = cheminDaccesImage;
		this.stock = 6; //� v�rifier
		this.tempsDePoseIngredient = 3;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ingredients fromage = new Ingredients("Fromage", 1.88f, 2.30f, "lol");
		System.out.println(fromage.toString());	
				
	}



}
