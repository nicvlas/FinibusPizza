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
	public Pate(String nom, double prixAchat, double prixVente, String cheminDaccesImage,
			int tempsPetrissage) {
		super(nom, prixAchat, prixVente, cheminDaccesImage);
		this.estReussite = false; //false car elle n'a pas encore été pétrie
		this.tempsPetrissage = tempsPetrissage;
	}

	/**
	 * Pétrissage de la pâte : si l'utilisateur relâche le bouton "pétrir" trop tôt ou trop tard après tempsPétrissage, la pâte ne sera
	 * bien montée et estRéussite = 0
	 */
	public void petrir() {
		//on doit la pétrir pendant tempsPetrissage temps. Si pas assez ou trop : échec
		//et donc perte d'argent
		//recommencer tant que estRéussite = true
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
