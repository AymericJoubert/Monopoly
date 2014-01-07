package monopoly.csv;
/**
 * Classe mod�le des Cartes.
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
	 * Constructeur de la classe Carte_Model, permettant l'impl�mentation des diff�rentes cartes du jeu.
	 * @param numero Num�ro de la carte
	 * @param groupe Groupe de la carte (Soit Banque soit CC (Caisse de communaut�))
	 * @param intitule Intitul� de la carte (Nom de celle-ci)
	 * @param evenement Ev�nement que fait ressortir la carte.
	 * @param parametres Une valeur, selon l'�v�nement, ou une chance ou m�me, un aller en prison.
	 */
	public Carte_Model(int numero, String groupe, String intitule, String evenement, String parametres) {
		this.numero=numero;
		this.groupe=groupe;
		this.intitule=intitule;
		this.evenement=evenement;
		this.parametres=parametres;
	}

	/**
	 * @return Num�ro de la carte.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Attribuer un num�ro � la carte.
	 * @param numero Num�ro de la carte.
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
	 * Attribuer un groupe � la carte.
	 * @param groupe Groupe de la carte.
	 */
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	/**
	 * @return Intitul� de la carte.
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * Attribuer un intitul� � la carte.
	 * @param intitule Intitul� de la carte.
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * @return Ev�nement de la carte.
	 */
	public String getEvenement() {
		return evenement;
	}

	/**
	 * Attribuer un �v�nement � la carte.
	 * @param evenement Ev�nement de la carte.
	 */
	public void setEvenement(String evenement) {
		this.evenement = evenement;
	}

	/**
	 * @return Param�tre(s) de la carte.
	 */
	public String getParametres() {
		return parametres;
	}

	/**
	 * Attribuer un/des param�tre(s) � la carte.
	 * @param parametres Param�tre(s) de la carte.
	 */
	public void setParametres(String parametres) {
		this.parametres = parametres;
	}
	
	public String toString() {
		return getNumero()+";"+getGroupe()+";"+getIntitule()+";"+getEvenement()+";"+getParametres();
	}
}
