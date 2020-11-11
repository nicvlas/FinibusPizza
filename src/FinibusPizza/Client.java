package FinibusPizza;

public class Client {
	
	private Integer typeClient;
	private Integer tempsClient;
	private Float notation;
	private Float pourboire;
	
	public Integer getTypeClient() {
		return typeClient;
	}

	public Integer getTempsClient() {
		return tempsClient;
	}

	public Float getNotation() {
		return notation;
	}

	public Float getPourboire() {
		return pourboire;
	}
	
	/**
	 * Constructeur du client
	 * @param typeClient : type de client (simple, normal, difficile, Karen)
	 */
	public Client(int typeClient){
		this.typeClient = typeClient;
		switch(this.typeClient){
			case 0:{
				//Définir le temps du client simple en seconde
				this.tempsClient = 40;
				break;}
			case 1:{
				//Définir le temps du client normal en seconde
				this.tempsClient = 35;
				break;}
			case 2:{
				//Définir le temps du client difficile en seconde
				this.tempsClient = 30;
				break;}
			case 3:{
				//Définir le temps du client Karen en seconde
				this.tempsClient = 25;
				break;}
		}
		this.notation = 0f;
		this.pourboire = 0f;
	}
	
	/**
	 * Permet la notation du client
	 * @param aReussi : Si le joueur a réussi la commande
	 * @param tempsPreparation : le temps de préparation de la commande
	 * @return note : la note du client
	 */
	public Float noter(Integer tempsPreparation){
		boolean aReussi = false;
		Float note = 0f;
		int tempsFinal = this.tempsClient - tempsPreparation;
		// Vérifie si le joueur a servi la pizza dans les temps
		if(tempsFinal >= 0)
			aReussi = true;
		//Traitement de la note et du pourboire
		if(aReussi){
			note = 1f;
			this.pourboire += 2;
			if(tempsFinal > 2)
			{
				note++;
				this.pourboire += 2;
				if(tempsFinal > 4) {
					note++;
					this.pourboire += 2;
					if(tempsFinal > 6) {
						note++;
						this.pourboire += 2;
						if(tempsFinal > 8) {
							note++;
							this.pourboire += 2;
						}
					}
				}
			}
		}
		this.notation = note;
		return note;
	}
	
	/**
	 * Retourne les informations du client
	 */
	public String toString(){
		return "Type : "+this.typeClient+" Temps d'attente du client : "+this.tempsClient+" Notation : "+this.notation+" pourboire : "+this.pourboire;
	}
	
	public static void main(String[] args) {
		Client c1 = new Client(0);
		System.out.println(c1.toString());
		c1.noter(30);
		System.out.println(c1.toString());
	}

}
