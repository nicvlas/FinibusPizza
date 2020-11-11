package FinibusPizza;

public class Pate extends Ingredients{

	private boolean estReussite; //0 = non, 1 = oui
	private int tempsPetrissage; //random int
	
	public boolean isEstReussite() {
		return estReussite;
	}


	public void setEstReussite(boolean estReussite) {
		this.estReussite = estReussite;
	}


	public int getTempsPetrissage() {
		return tempsPetrissage;
	}


	public void setTempsPetrissage(int tempsPetrissage) {
		this.tempsPetrissage = tempsPetrissage;
	}
	
	/**
	 * Constructeur de la pâte
	 * @param nom
	 * @param prixAchat
	 * @param prixVente
	 * @param cheminDaccesImage
	 * @param estReussite
	 * @param tempsPetrissage
	 */
	public Pate(String nom, double prixAchat, double prixVente, String cheminDaccesImage) {
		super(nom, prixAchat, prixVente, cheminDaccesImage);
		this.estReussite = false; //false car elle n'a pas encore �t� p�trie
		this.tempsPetrissage = 5;
	}

	/**
	 * Petrissage de la pâte : si l'utilisateur relâche le bouton "pétrir" trop tôt ou trop tard après tempsPétrissage, la pâte ne sera
	 * bien montée et estRéussite = 0
	 */
	public void petrir() {
	}
	
	public boolean finirPetrissage() {
		return true;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pate unePate = new Pate("pate", 1, 2, "lol");
		unePate.petrir();
		
	}

}
