package monopoly.csv;
/**
 * Classe modele des Cartes.
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
	 * Constructeur de la classe Carte_Model, permettant l'implementation des differentes cartes du jeu.
	 * @param numero Numero de la carte
	 * @param groupe Groupe de la carte (Soit Banque soit CC (Caisse de communaute))
	 * @param intitule Intitule de la carte (Nom de celle-ci)
	 * @param evenement Evenement que fait ressortir la carte.
	 * @param parametres Une valeur, selon l'evenement, ou une chance ou même, un aller en prison.
	 */
	public Carte_Model(int numero, String groupe, String intitule, String evenement, String parametres) {
		this.numero=numero;
		this.groupe=groupe;
		this.intitule=intitule;
		this.evenement=evenement;
		this.parametres=parametres;
	}

	/**
	 * @return Numero de la carte.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Attribuer un numero à la carte.
	 * @param numero Numero de la carte.
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
	 * @return Intitule de la carte.
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * Attribuer un intitule à la carte.
	 * @param intitule Intitule de la carte.
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * @return Evenement de la carte.
	 */
	public String getEvenement() {
		return evenement;
	}

	/**
	 * Attribuer un evenement à la carte.
	 * @param evenement Evenement de la carte.
	 */
	public void setEvenement(String evenement) {
		this.evenement = evenement;
	}

	/**
	 * @return Parametre(s) de la carte.
	 */
	public String getParametres() {
		return parametres;
	}

	/**
	 * Attribuer un/des parametre(s) à la carte.
	 * @param parametres Parametre(s) de la carte.
	 */
	public void setParametres(String parametres) {
		this.parametres = parametres;
	}
	
	public String toString() {
		return getNumero()+";"+getGroupe()+";"+getIntitule()+";"+getEvenement()+";"+getParametres();
	}
}
