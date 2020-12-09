package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Client {
	
	private String nomClient;
	private Difficulte typeClient;
	private int tempsClient;
	private int pourboire = 0;
	private int[] nbTypeIngredients;// Tableau min, max nombre type d'ingrÃ¯Â¿Â½dients
	private String[] lesNomsClient;
	private float margeTemps = 0f;
	
	private static ArrayList<String[]> tmpC;
	private static Difficulte difficulteFichier;
	
	//Faire un fichier avec margeTemps, pourboire initial, min et max nombre type d'ingrÃ¯Â¿Â½dients
	
	public Difficulte getTypeClient() {
		return typeClient;
	}

	public int getTempsClient() {
		return tempsClient;
	}

	public float getPourboire() {
		return pourboire;
	}
	
	public void setTempsClient(int tempsClient) {
		this.tempsClient = tempsClient;
	}
	
	public int[] getnbTypeIngredients() {
		return nbTypeIngredients;
	}
	
	public float getMargeTemps() {
		return margeTemps;
	}

	public void setMargeTemps(float margeTemps) {
		this.margeTemps = margeTemps;
	}
	
	/**
	 * Constructeur du client
	 * @param typeClient : type de client (simple, normal, difficile, Karen)
	 */
	public Client(Difficulte typeClient){
		this.lesNomsClient = new String[] {"Michel","Bernard","Christophe","Alexandre","Jean","Camille","CÃƒÂ©cile","Elise","Bernadette","Manon"};
		this.typeClient = typeClient;
		this.nbTypeIngredients = new int[2];
		this.nbTypeIngredients[0] = 0;
		this.nbTypeIngredients[0] = 0;
		this.tempsClient = 0;
		parametreClient();
		try {
			for(String[] t : tmpC) {
				if(t[0].equals("Facile"))
					difficulteFichier = Difficulte.Facile;
				else if(t[0].equals("Normal"))
					difficulteFichier = Difficulte.Normal;
				else
					difficulteFichier = Difficulte.Karen;
				if(typeClient == difficulteFichier) {
					this.margeTemps = Float.parseFloat(t[1]);
					this.pourboire = Integer.parseInt(t[2]);
					this.nbTypeIngredients[0] = Integer.parseInt(t[3]);
					this.nbTypeIngredients[1] = Integer.parseInt(t[4]);
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Random rd = new Random();
		this.nomClient = this.lesNomsClient[(rd.nextInt(9 - 0) + 0)];
	}
	
	/**
	 * Constructeur du client
	 * @param typeClient : type de client (simple, normal, difficile, Karen)
	 */
	public Client(String nom, Difficulte typeClient){
		this.typeClient = typeClient;
		this.nomClient = nom;
		this.nbTypeIngredients = new int[2];
		this.nbTypeIngredients[0] = 0;
		this.nbTypeIngredients[0] = 0;
		this.tempsClient = 0;
		parametreClient();
		try {
			for(String[] t : tmpC) {
				if(t[0].equals("Facile"))
					difficulteFichier = Difficulte.Facile;
				else if(t[0].equals("Normal"))
					difficulteFichier = Difficulte.Normal;
				else
					difficulteFichier = Difficulte.Karen;
				if(typeClient == difficulteFichier) {
					this.margeTemps = Float.parseFloat(t[1]);
					this.pourboire = Integer.parseInt(t[2]);
					this.nbTypeIngredients[0] = Integer.parseInt(t[3]);
					this.nbTypeIngredients[1] = Integer.parseInt(t[4]);
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/*
	 * Lecture des paramÃ¨tres des clients
	 */
	private void parametreClient() {
		try {
			tmpC = new ArrayList<String[]>();
			//Lecture du fichier
			FileReader file = new FileReader(getClass().getResource("../textes/clients.txt").getFile());
            BufferedReader buffer = new BufferedReader(file);
            String tmpC2 = buffer.readLine();
            // parcourir le fichier
            while (tmpC2 != null) {
                tmpC.add(elementsClients(tmpC2));
                tmpC2 = buffer.readLine();
            }
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private String[] elementsClients(String element) {
		String[] retour = element.split( "/" );
        if(retour.length != 5) {
            throw new InternalError(element + " ne correspond pas à un String d'un fichier contenant des clients traitables");
        }
        return retour;
	}
	
	
	/**
	 * Retourne les informations du client
	 */
	public String toString(){
		return this.nomClient+" "+this.typeClient+" "+this.tempsClient+" "+margeTemps+" "+this.pourboire+" "+nbTypeIngredients[0]+" "+nbTypeIngredients[1];
	}
	
	public static void main(String[] args) {
		Client c1 = new Client(Difficulte.Facile);
		Client c2 = new Client("Karen1",Difficulte.Karen);
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		
	}
}
