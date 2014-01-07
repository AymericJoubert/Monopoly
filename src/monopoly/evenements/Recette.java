// OK

package monopoly.evenements;

import monopoly.jeu.Joueur;
/**
 * Classe permettant le versement d'argent aux joueurs; celle-ci implemente Evenement.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Recette implements Evenement{
	private String nom;
	private Joueur j;
	private int montant;

	/**
	 * Constructeur de la classe Recette
	 * @param nom Nom de la recette
	 * @param j Joueur prenant la recette
	 * @param montant Montant de cette recette
	 */
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
	

	/** Verse le montant offert au joueur */
	public void executer(){
		cible().verser(montant);
	}
}
