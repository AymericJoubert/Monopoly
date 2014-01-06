package monopoly.csv;

import monopoly.evenements.*;
import monopoly.proprietes.*;

public class Monopoly_Model {

	private int numero;
	private String nom;
	private String evenement;
	private String type_evenement;
	private String groupe;
	private int achat;
	private int cout_immobilier;
	String loyer;
	
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

	public String getLoyer(){
		return loyer;
	}
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getType_evenement() {
		return type_evenement;
	}

	public void setType_evenement(String type_evenement) {
		this.type_evenement = type_evenement;
	}



	public int getAchat() {
		return achat;
	}

	public void setAchat(int achat) {
		this.achat = achat;
	}

	public int getCout_immobilier() {
		return cout_immobilier;
	}

	public void setCout_immobilier(int cout_immobilier) {
		this.cout_immobilier = cout_immobilier;
	}

	public String getEvenement() {
		return evenement;
	}

	public void setEvenement(String evenement) {
		this.evenement = evenement;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	
	public String toString() {
		return getNumero()+";"+getNom()+";"+getEvenement()+";"+getType_evenement()+";"+getGroupe()+";"+getAchat()+";"+getCout_immobilier();
	}
}
