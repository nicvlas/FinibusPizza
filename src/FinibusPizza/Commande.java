package FinibusPizza;

import java.util.HashMap;
import java.util.Iterator;


public class Commande {

	private Client unClient;
	private HashMap<Integer, Ingredients> lesIngredients;
	private Pate laPate;
	private int tempsPreparation;
	private boolean estReussite;
	private int tempsDePoseIngredient;
	

	/**
	 * Retourne les infos d'une pizza
	 */
	public String toString() {
		String phrase = "Pizza composée de : ";
		
		Iterator hmIterator = this.lesIngredients.entrySet().iterator();
		while (hmIterator.hasNext()) { 
            HashMap.Entry mapElement = (HashMap.Entry)hmIterator.next(); 
            Ingredients ingredient = (Ingredients) (mapElement.getValue());
            phrase+= " "+ingredient.getNom();
        }
		
		return phrase;
	}
	
	/**
	 * Constructeur d'une commande
	 * @param unClient
	 * @param lesIngredients
	 * @param laPate
	 * @param tempsPreparation
	 */
	public Commande(Client unClient, HashMap<Integer, Ingredients> lesIngredients, Pate laPate, int tempsPreparation) {
		this.unClient = unClient;
		this.lesIngredients = lesIngredients;
		this.laPate = laPate;
		this.tempsPreparation = tempsPreparation;
		this.estReussite = estReussite;
		this.tempsDePoseIngredient = 3; //3 secondes pour cliquer sur un ingrédient : TEST
	}
	
	/**
	 * Préparer la pizza : pétrir la pâte, sélectionner les ingrédients.
	 * Si elle est prête avant la fin du compteur, la fonction lance cuire()
	 * Si la pizza n'est pas prête après que le compteur soit fini, le client part et il y a perte d'argent
	 **/
	public void preparer() {
		
	}
	
	/**
	 * Cuit la pizza.
	 * Si la pizza n'est pas cuite avant la fin du temps imparti, on la donne au client contre moins d'argent et estReussite = false.
	 * Si la pizza est trop cuite, il faut la recommencer et estReussite = false.
	 */
	public void cuire() {
		
	}
	
	/**
	 * Sortir la pizza et la donner au client
	 */
	public void sortirDuFour() {
		
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Client c1 = new Client("Bernard", Difficulte.Facile, 3);
		Pate unePate = new Pate("pate", 1, 2, "lol");
		
		Ingredients fromage = new Ingredients("Fromage", 1.8f, 2.3f, "lol");
		Ingredients champignons = new Ingredients("Champignons", 1.8f, 2.3f, "lol");

		HashMap<Integer, Ingredients> ingredientsC1 = new HashMap<Integer, Ingredients>();
		ingredientsC1.put(1, fromage);
		ingredientsC1.put(2, champignons);
		
		Commande commande1 = new Commande(c1, ingredientsC1, unePate, 5);
		
		System.out.println(commande1.toString());

	}

}
