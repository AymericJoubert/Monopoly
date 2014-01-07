package monopoly.evenements;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case;
// OK

import java.util.Random;
/**
 * Classe représentant une dépense d'utilisateur, celle-ci est implantée avec Evenement.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Depenser implements Evenement {
	private String nom;
	private Joueur j;
	private int montant;

	/**
	 * Constructeur de la classe Depenser, contenant trois paramètres.
	 * @param nom Nom de la dépense
	 * @param j Joueur effectuant la dépense
	 * @param montant Montant de la dépense
	 */
	public Depenser(String nom, Joueur j, int montant){
		this.nom = nom;
		this.j = j;
		this.montant = montant;
	}

	/** 
	 * Retourne l'intitulé de l'évenement 
	 */
	public String nom(){
		return nom;
	}

	/** 
	 * Retourne la cible de l'évenement
	 */
	public Joueur cible(){
		return j;
	}

	/** 
	 * Enlève du solde du joueur le montant
	 */
	public void executer(){
		cible().payer(montant);
	}
	
	/** 
	 * Empile l'évenement dans la pile du joueur 
	 */
	public void appliquerA(Joueur j){
		this.j = j;
		j.chosesAFaire().add(this);
	}	
}

