package modele;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Commande {

	private static final float Iterator = 0;
	private Client unClient;
	private HashMap<Ingredients, Integer> lesIngredients;
	private Pate laPate;
	private int tempsPreparation;
	private boolean estReussite;
	private float AchatCommande;
	private float VenteCommande;
	private int tempsCuisson;
	private float marge = 1.5F;
	
	public Client getUnClient() {
		return unClient;
	}
	
	public Pate getLaPate() {
		return laPate;
	}

	public int getTempsPreparation() {
		return tempsPreparation;
	}

	public boolean isEstReussite() {
		return estReussite;
	}

	public float getAchatCommande() {
		return AchatCommande;
	}

	public float getVenteCommande() {
		return VenteCommande;
	}

	public int getTempsCuisson() {
		return tempsCuisson;
	}
	
	public HashMap<Ingredients, Integer> getLesIngredients() {
		return lesIngredients;
	}


	/**
	 * Constructeur d'une commande, qui n'est pas considérée comme réussite au moment de sa création
	 * 
	 * @param unClient
	 * @param ingredientsC1
	 * @param laPaten
	 */
	public Commande(Client unClient, HashMap<Ingredients, Integer> ingredientsC1, Pate laPaten) {
		if (unClient == null || ingredientsC1 == null || laPaten == null) {
			throw new IllegalArgumentException("Erreur dans la création d'une commande");
		}
		this.unClient = unClient;
		this.lesIngredients = ingredientsC1;
		this.laPate = laPaten;
		this.estReussite = false;
		
		//aléatoire temps cuisson
		Random r = new Random();
		int low = 5;
		int high = 8;
		this.tempsCuisson = r.nextInt(high-low) + low;

		// calcul temps préparation total : temps de pose des ingrédients + temps de pétrissage + temps de cuisson
		Iterator hmIterator1 = this.lesIngredients.entrySet().iterator();
		while (hmIterator1.hasNext()) {
			HashMap.Entry mapElement = (HashMap.Entry) hmIterator1.next();
			Ingredients ingredient = (Ingredients) (mapElement.getKey());
			this.tempsPreparation += ingredient.getTempsDePoseIngredient();
		}
		this.tempsPreparation += (this.laPate.getTempsPetrissage() + this.getTempsCuisson())*1.25;
		

		// calcul achat et vente commande
		Iterator hmIterator2 = this.lesIngredients.entrySet().iterator();
        while (hmIterator2.hasNext()) {
            HashMap.Entry mapElement = (HashMap.Entry) hmIterator2.next();
            Ingredients ingredient = (Ingredients) (mapElement.getKey());
            this.AchatCommande += ingredient.getPrixAchat() * (int)mapElement.getValue();
            this.VenteCommande += ingredient.getPrixVente() * (int)mapElement.getValue();
        }

		this.VenteCommande = this.VenteCommande;
	}

	/**
	 * Retourne les infos d'une pizza
	 */
	public String toString() {
		String phrase = "Pizza composée de :";

		Iterator hmIterator = this.lesIngredients.entrySet().iterator();
		while (hmIterator.hasNext()) {
			HashMap.Entry mapElement = (HashMap.Entry) hmIterator.next();
			Ingredients ingredient = (Ingredients) (mapElement.getKey());
			phrase += " " + ingredient.getNom();
		}

		phrase += "\nCoût de fabrication : " + this.AchatCommande + "\nProfit : "
				+ this.VenteCommande;

		return phrase;
	}

	/**
	 * Préparer la pizza : pétrir la pâte, sélectionner les ingrédients. Si elle est
	 * prête avant la fin du compteur, la fonction lance cuire() Si la pizza n'est
	 * pas prête après que le compteur soit fini, le client part et il y a perte
	 * d'argent
	 **/
	public void preparer() {

	}

	/**
	 * Cuit la pizza. Si la pizza n'est pas cuite avant la fin du temps imparti, on
	 * la donne au client contre moins d'argent et estReussite = false. Si la pizza
	 * est trop cuite, il faut la recommencer et estReussite = false.
	 */
	public Integer[] cuire() {
		float tempsCuissonAutorise = this.marge * this.tempsCuisson;
		
        Integer[] tabMarge = new Integer[2];
        
        tabMarge[0] = this.tempsCuisson;
        
        tabMarge[1] = (int) tempsCuissonAutorise;
        
        return tabMarge;
	}

	/**
	 * Sortir la pizza et la donner au client
	 */
	public void sortirDuFour() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Client c1 = new Client("Bernard", Difficulte.Facile);
		Pate unePate = new Pate("pate", 1, 2);

		Ingredients fromage = new Ingredients("Fromage", 1.8f, 2.3f);
		Ingredients champignons = new Ingredients("Champignons", 1.8f, 2.3f);

		HashMap<Ingredients, Integer> ingredientsC1 = new HashMap<Ingredients, Integer>();
		ingredientsC1.put(fromage, 15);
		ingredientsC1.put(champignons, 15);

		Commande commande1 = new Commande(c1, ingredientsC1, unePate);


	}


}
