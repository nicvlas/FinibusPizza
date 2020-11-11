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
	 * Constructeur de la p�te
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
	 * P�trissage de la p�te : si l'utilisateur rel�che le bouton "p�trir" trop t�t ou trop tard apr�s tempsP�trissage, la p�te ne sera
	 * bien mont�e et estR�ussite = 0
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
