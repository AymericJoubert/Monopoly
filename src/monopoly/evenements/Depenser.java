package monopoly.evenements;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case;
// OK

import java.util.Random;
/**
 * Classe representant une depense d'utilisateur, celle-ci est implantee avec Evenement.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Depenser implements Evenement {
	private String nom;
	private Joueur j;
	private int montant;

	/**
	 * Constructeur de la classe Depenser, contenant trois parametres.
	 * @param nom Nom de la depense
	 * @param j Joueur effectuant la depense
	 * @param montant Montant de la depense
	 */
	public Depenser(String nom, Joueur j, int montant){
		this.nom = nom;
		this.j = j;
		this.montant = montant;
	}

	/** 
	 * Retourne l'intitule de l'evenement 
	 */
	public String nom(){
		return nom;
	}

	/** 
	 * Retourne la cible de l'evenement
	 */
	public Joueur cible(){
		return j;
	}

	/** 
	 * Enleve du solde du joueur le montant
	 */
	public void executer(){
		cible().payer(montant);
	}
	
	/** 
	 * Empile l'evenement dans la pile du joueur 
	 */
	public void appliquerA(Joueur j){
		this.j = j;
		j.chosesAFaire().add(this);
	}	
}

