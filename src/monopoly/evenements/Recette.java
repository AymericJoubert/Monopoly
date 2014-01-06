// OK

package monopoly.evenements;

import monopoly.jeu.Joueur;

public class Recette implements Evenement{
	private String nom;
	private Joueur j;
	private int montant;
	/** Constructeur de la classe */
	public Recette(String nom, Joueur j, int montant){
		this.nom = nom;
		this.j = j;
		this.montant = montant;
	}

	/** Retourne l'intitulé de l'évenement */
	public String nom(){
		return nom;
	}

	/** Retourne la cible de l'évenement */
	public Joueur cible(){
		return j;
	}
	
	/** Empile l'evenement dans la pile du joueur */
	public void appliquerA(Joueur j){
		this.j = j;
		j.chosesAFaire().add(this);
	}	
	

	/** Verse le montant offert en passant sur la case depart au joueur */
	public void executer(){
		j.verser(montant);
	}
}
