package monopoly.csv;

import monopoly.evenements.*;
import monopoly.proprietes.*;
/**
 * Classe modele des cases du monopoly.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Monopoly_Model {

	private int numero;
	private String nom;
	private String evenement;
	private String type_evenement;
	private String groupe;
	private int achat;
	private int cout_immobilier;
	String loyer;
	
	/**
	 * @param numero Numero de la case
	 * @param nom Nom de la case
	 * @param evenement Evenement de la case (Recette, carte, monopole ou prison)
	 * @param type_evenement Type d'evenement de la case (Si cette case est un terrain, une gare ou une compagnie)
	 * @param groupe Groupe appartenant la case (C'est en gros la couleur qui va être attribuee à la case)
	 * @param achat Prix pour achat de cette dite case.
	 * @param cout_immobilier Le coût immobilier de la case.
	 * @param loyer Le loyer de la case.
	 */
	public Monopoly_Model(int numero, String nom, String evenement, String type_evenement, String groupe, int achat, int cout_immobilier, String loyer) {
		this.numero=numero;
		this.nom=nom;
		this.setEvenement(evenement);
		this.type_evenement=type_evenement;
		this.setGroupe(groupe);
		this.achat=achat;
		this.cout_immobilier=cout_immobilier;
		this.loyer = loyer;
	}

	/**
	 * @return Numero de la case
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Attribue un numero à la case
	 * @param numero Numero de la case
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return Nom de la case
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Attribue un nom à la case
	 * @param nom Nom de la case
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return Evenement de la case
	 */
	public String getEvenement() {
		return evenement;
	}

	/**
	 * Attribue un evenement à la case
	 * @param evenement Evenement de la case
	 */
	public void setEvenement(String evenement) {
		this.evenement = evenement;
	}

	/**
	 * @return Type d'evenement de la case
	 */
	public String getType_evenement() {
		return type_evenement;
	}

	/**
	 * Attribue un type d'evenement à la case
	 * @param type_evenement Type d'evenement de la case
	 */
	public void setType_evenement(String type_evenement) {
		this.type_evenement = type_evenement;
	}

	/**
	 * @return Prix de l'achat de la case
	 */
	public int getAchat() {
		return achat;
	}

	/**
	 * Attribue un prix d'achat à la case
	 * @param achat Prix de l'achat de la case
	 */
	public void setAchat(int achat) {
		this.achat = achat;
	}

	/**
	 * @return Coût immobilier de la case
	 */
	public int getCout_immobilier() {
		return cout_immobilier;
	}

	/**
	 * Attribue un coût immobilier à la case
	 * @param cout_immobilier Coût immobilier de la case
	 */
	public void setCout_immobilier(int cout_immobilier) {
		this.cout_immobilier = cout_immobilier;
	}

	/**
	 * @return Groupe de la case
	 */
	public String getGroupe() {
		return groupe;
	}

	/**
	 * Attribue un groupe à la case
	 * @param groupe Groupe de la case
	 */
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	
	/**
	 * @return Loyer de la case
	 */
	public String getLoyer(){
		return loyer;
	}
	
	public String toString() {
		return getNumero()+";"+getNom()+";"+getEvenement()+";"+getType_evenement()+";"+getGroupe()+";"+getAchat()+";"+getCout_immobilier();
	}
}
