package monopoly.evenements;

import monopoly.jeu.Joueur;

import java.util.*;

/** Classe qui decrit ce qu'est une carte */
public class Carte {
	private String nom;
	private Joueur j;
	private Evenement e;
	public static ArrayList<Carte> chance = new ArrayList<Carte>();
	public static ArrayList<Carte> cc = new ArrayList<Carte> ();
	/** Constructeur de la classe
	 * @param nom Nom de la carte
	 * @param e Evenement declenché par la carte
	 */
	public Carte(String nom, String type, Evenement e){
		this.nom = nom;
		this.e = e;
		this.j = null;
		if(type.equals("chance"))
			this.chance.add(this);
		else
			this.cc.add(this);		
	}

	public String nom(){
		return nom;
	}

	public Joueur conservePar(){
		return j;
	}
	
	public Evenement getEvenement(){
		return e;
	}
	public void donnerA(Joueur j){
		this.j = j;
		((ArrayList)j.cartes()).add(this);
	}
}