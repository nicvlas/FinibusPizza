package modele;


public class Pate extends Ingredients{

	private boolean estReussite; //0 = non, 1 = oui
	private int tempsPetrissage; //random int
	
	public boolean isEstReussite() {
		return estReussite;
	}

	public int getTempsPetrissage() {
		return tempsPetrissage;
	}
	
	public void setEstReussite(boolean _estReussite) {
		this.estReussite = _estReussite;
	}

	/**
	 * Constructeur de la p�te
	 * @param nom
	 * @param prixAchat
	 * @param prixVente
	 */
	public Pate(String nom, float prixAchat, float prixVente) {
		super(nom, prixAchat, prixVente);
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
		Pate unePate = new Pate("pate", 1, 2);
		unePate.petrir();
		
	}

}
