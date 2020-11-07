package FinibusPizza;

import java.util.HashMap;
import java.util.Iterator;


public class Commande {

	private Client unClient;
	private HashMap<Integer, Ingredients> lesIngredients;
	private Pate laPate;
	private int tempsPreparation;
	private boolean estReussite;
	
	public Client getUnClient() {
		return unClient;
	}


	public HashMap<Integer, Ingredients> getLesIngredients() {
		return lesIngredients;
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

	
	public String toString() {
		return "";
	}
	
	public void preparer() {
		//lancer compteur = tempsPréparation
		//si la pizza n'est pas prête après que le compteur soit fini : le client part : perte d'argent
		//si elle est prête avant la fin du compteur, lancer cuire()
	}
	
	public void cuire() {
		//si on fait pas sortirDuFour() avant que le compteur >= temps de cuisson → estRéussite = false → RECOMMENCER preparer()
		
		//si on fait sortirDuFour() avant que le compteur <= tempsPréparation → est réussite = false → donner au client mais moins d'argent sera perçu
	}
	
	public void sortirDuFour() {
		//sortir la pizza et la donner au client
	}

	
	public Commande(Pate laPate, int tempsPreparation) {
		this.lesIngredients = new HashMap<>();
		this.laPate = laPate;
		this.tempsPreparation = tempsPreparation;
		this.estReussite = false; //pour l'instant, on considère que la pizza n'est pas réussite car elle n'est même pas faite
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pate unePate = new Pate("pate", 1, 2, "lol", 3);
		
		Commande commande1 = new Commande(unePate, 3);
		Ingredients fromage = new Ingredients("Fromage", 1.8, 2.3, "lol");
		Ingredients champignons = new Ingredients("Champignons", 1.8, 2.3, "lol");

		commande1.lesIngredients.put(1, fromage);
		commande1.lesIngredients.put(2, champignons);
		
		Iterator hmIterator = commande1.lesIngredients.entrySet().iterator();
		while (hmIterator.hasNext()) { 
            HashMap.Entry mapElement = (HashMap.Entry)hmIterator.next(); 
            Ingredients ingredient = (Ingredients) (mapElement.getValue());
            System.out.println(ingredient.getNom()); 
        }

	}

}
