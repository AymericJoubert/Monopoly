package monopoly.evenements;

import monopoly.jeu.Joueur;

import java.util.*;

/**
 * Classe decrivant le modele d'une Carte permettant à un joueur de posseder des Cartes.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Carte {
	private String nom;
	private Joueur j;
	private Evenement e;
	public static ArrayList<Carte> chance = new ArrayList<Carte>();
	public static ArrayList<Carte> cc = new ArrayList<Carte> ();
	
	/** Constructeur de la classe Carte
	 * @param nom Nom de la carte
	 * @param e Evenement declenche par la carte
	 */
	public Carte(String nom, String type, Evenement e){
		this.nom = nom;
		this.e = e;
		this.j = null;
		//Si la carte est de type chance
		if(type.equals("chance"))
			this.chance.add(this);
		//Si la carte est de type caisse de communautee
		else
			this.cc.add(this);		
	}

	/**
	 * @return Nom de la carte
	 */
	public String nom(){
		return nom;
	}

	/**
	 * @return Joueur possedant la carte
	 */
	public Joueur conservePar(){
		return j;
	}
	
	/**
	 * @return Evenement realise par la carte
	 */
	public Evenement getEvenement(){
		return e;
	}
	
	/**
	 * Permet à un joueur de recevoir la carte
	 * @param j Joueur receptionnant la carte
	 */
	public void donnerA(Joueur j){
		this.j = j;
		((ArrayList)j.cartes()).add(this);
	}
}