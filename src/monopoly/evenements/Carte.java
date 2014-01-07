package monopoly.evenements;

import monopoly.jeu.Joueur;

import java.util.*;

/**
 * Classe d�crivant le mod�le d'une Carte permettant � un joueur de poss�der des Cartes.
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
	 * @param e Ev�nement d�clench� par la carte
	 */
	public Carte(String nom, String type, Evenement e){
		this.nom = nom;
		this.e = e;
		this.j = null;
		//Si la carte est de type chance
		if(type.equals("chance"))
			this.chance.add(this);
		//Si la carte est de type caisse de communaut�e
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
	 * @return Joueur poss�dant la carte
	 */
	public Joueur conservePar(){
		return j;
	}
	
	/**
	 * @return Ev�nement r�alis� par la carte
	 */
	public Evenement getEvenement(){
		return e;
	}
	
	/**
	 * Permet � un joueur de recevoir la carte
	 * @param j Joueur r�ceptionnant la carte
	 */
	public void donnerA(Joueur j){
		this.j = j;
		((ArrayList)j.cartes()).add(this);
	}
}