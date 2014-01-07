package monopoly.evenements;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case;
// OK

import java.util.Random;
/**
 * Classe repr�sentant une d�pense d'utilisateur, celle-ci est implant�e avec Evenement.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Depenser implements Evenement {
	private String nom;
	private Joueur j;
	private int montant;

	/**
	 * Constructeur de la classe Depenser, contenant trois param�tres.
	 * @param nom Nom de la d�pense
	 * @param j Joueur effectuant la d�pense
	 * @param montant Montant de la d�pense
	 */
	public Depenser(String nom, Joueur j, int montant){
		this.nom = nom;
		this.j = j;
		this.montant = montant;
	}

	/** 
	 * Retourne l'intitul� de l'�venement 
	 */
	public String nom(){
		return nom;
	}

	/** 
	 * Retourne la cible de l'�venement
	 */
	public Joueur cible(){
		return j;
	}

	/** 
	 * Enl�ve du solde du joueur le montant
	 */
	public void executer(){
		cible().payer(montant);
	}
	
	/** 
	 * Empile l'�venement dans la pile du joueur 
	 */
	public void appliquerA(Joueur j){
		this.j = j;
		j.chosesAFaire().add(this);
	}	
}

