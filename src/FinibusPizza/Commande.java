package FinibusPizza;

import java.util.HashMap;
import java.util.Iterator;


public class Commande {

	private static final float Iterator = 0;
	private Client unClient;
	private HashMap<Ingredients, Integer> lesIngredients;
	private Pate laPate;
	private int tempsPreparation;
	private boolean estReussite;
	private int tempsDePoseIngredient;
	private float AchatCommande;
	private float VenteCommande;
	
	public float getTempsDePoseIngredient() {
		return tempsDePoseIngredient;
	}

	public void setTempsDePoseIngredient(int tempsDePoseIngredient) {
		this.tempsDePoseIngredient = tempsDePoseIngredient;
	}

	public int getTempsPreparation() {
		return tempsPreparation;
	}

	public void setTempsPreparation(int tempsPreparation) {
		this.tempsPreparation = tempsPreparation;
	}

	public float getAchatCommande() {
		return AchatCommande;
	}

	public void setAchatCommande(float achatCommande) {
		AchatCommande = achatCommande;
	}

	public float getVenteCommande() {
		return VenteCommande;
	}

	public void setVenteCommande(float venteCommande) {
		VenteCommande = venteCommande;
	}

	
	/**
	 * Constructeur d'une commande
	 * @param unClient
	 * @param ingredientsC1
	 * @param laPate
	 * @param nbTempsPrepCommande
	 */
	public Commande(Client unClient, HashMap<Ingredients, Integer> ingredientsC1, Pate laPaten) {
		if(unClient == null || ingredientsC1 == null || laPaten == null) {
			throw new IllegalArgumentException("Erreur dans la création d'une commande");
		}
		this.unClient = unClient;
		this.lesIngredients = ingredientsC1;
		this.laPate = laPate;
		this.estReussite = estReussite;
		this.setTempsDePoseIngredient(3); //3 secondes pour cliquer sur un ingrédient : TEST
		
		//calcul temps préparation ((temps pose * nb ingrédients) + temps petrissage + temps cuisson)*marge
		this.tempsPreparation = ((this.lesIngredients.size()*this.tempsDePoseIngredient)+laPaten.getTempsPetrissage());
		
		
		//calcul achat commande : prixAchat de chaque ingrédient
		Iterator hmIterator = this.lesIngredients.entrySet().iterator();
		while (hmIterator.hasNext()) { 
            HashMap.Entry mapElement = (HashMap.Entry)hmIterator.next(); 
            Ingredients ingredient = (Ingredients) (mapElement.getKey());
            this.AchatCommande += ingredient.getPrixAchat();
        }
		
		//calcul vente commande : prix vente de chaque ingrédient
		Iterator hmIterator2 = this.lesIngredients.entrySet().iterator();
		while (hmIterator2.hasNext()) { 
            HashMap.Entry mapElement = (HashMap.Entry)hmIterator2.next(); 
            Ingredients ingredient = (Ingredients) (mapElement.getKey());
            this.VenteCommande += ingredient.getPrixVente();
        }
	}
	
	/**
	 * Retourne les infos d'une pizza
	 */
	public String toString() {
		String phrase = "Pizza composée de :";
		
		Iterator hmIterator = this.lesIngredients.entrySet().iterator();
		while (hmIterator.hasNext()) { 
            HashMap.Entry mapElement = (HashMap.Entry)hmIterator.next(); 
            Ingredients ingredient = (Ingredients) (mapElement.getKey());
            phrase+= " "+ingredient.getNom();
        }
		
		phrase += "\nCoût de fabrication : "+this.AchatCommande+"\nProfit :"+(this.VenteCommande-this.AchatCommande);
		
		return phrase;
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
		
		Client c1 = new Client("Bernard", Difficulte.Facile);
		Pate unePate = new Pate("pate", 1, 2, "lol");
		
		Ingredients fromage = new Ingredients("Fromage", 1.8f, 2.3f, "lol");
		Ingredients champignons = new Ingredients("Champignons", 1.8f, 2.3f, "lol");

		HashMap<Ingredients,Integer> ingredientsC1 = new HashMap<Ingredients,Integer>();
		ingredientsC1.put(fromage, 15);
		ingredientsC1.put(champignons, 15);
		
		Commande commande1 = new Commande(c1, ingredientsC1, unePate);
		
		System.out.println(commande1.toString());

	}
}
