package monopoly.csv;
/**
 * Classe modèle des Cartes.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Carte_Model {

	private int numero;
	private String groupe;
	private String intitule;
	private String evenement;
	private String parametres;
	
	/**
	 * Constructeur de la classe Carte_Model, permettant l'implémentation des différentes cartes du jeu.
	 * @param numero Numéro de la carte
	 * @param groupe Groupe de la carte (Soit Banque soit CC (Caisse de communauté))
	 * @param intitule Intitulé de la carte (Nom de celle-ci)
	 * @param evenement Evénement que fait ressortir la carte.
	 * @param parametres Une valeur, selon l'événement, ou une chance ou même, un aller en prison.
	 */
	public Carte_Model(int numero, String groupe, String intitule, String evenement, String parametres) {
		this.numero=numero;
		this.groupe=groupe;
		this.intitule=intitule;
		this.evenement=evenement;
		this.parametres=parametres;
	}

	/**
	 * @return Numéro de la carte.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Attribuer un numéro à la carte.
	 * @param numero Numéro de la carte.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return Groupe de la carte.
	 */
	public String getGroupe() {
		return groupe;
	}

	/**
	 * Attribuer un groupe à la carte.
	 * @param groupe Groupe de la carte.
	 */
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	/**
	 * @return Intitulé de la carte.
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * Attribuer un intitulé à la carte.
	 * @param intitule Intitulé de la carte.
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * @return Evénement de la carte.
	 */
	public String getEvenement() {
		return evenement;
	}

	/**
	 * Attribuer un événement à la carte.
	 * @param evenement Evénement de la carte.
	 */
	public void setEvenement(String evenement) {
		this.evenement = evenement;
	}

	/**
	 * @return Paramètre(s) de la carte.
	 */
	public String getParametres() {
		return parametres;
	}

	/**
	 * Attribuer un/des paramètre(s) à la carte.
	 * @param parametres Paramètre(s) de la carte.
	 */
	public void setParametres(String parametres) {
		this.parametres = parametres;
	}
	
	public String toString() {
		return getNumero()+";"+getGroupe()+";"+getIntitule()+";"+getEvenement()+";"+getParametres();
	}
}
