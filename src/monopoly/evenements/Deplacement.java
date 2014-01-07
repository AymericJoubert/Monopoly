// OK

package monopoly.evenements;

import monopoly.gui.Plateau;
import monopoly.jeu.Case;
import monopoly.jeu.Cases;
import monopoly.jeu.Joueur;
/**
 * Classe représentant le déplacement d'un utilisateur, celle-ci est implantée avec Evenement.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Deplacement implements Evenement{
	private String nom;
	private Joueur j;
	private int nbCases;
	public Case cCible;

	/**
	 * Constructeur de la classe Deplacement
	 * @param j Joueur se déplaçant
	 */
	public Deplacement(Joueur j){
		this.nom = "Déplacement";
		this.j = j;
		this.cCible = cCible;
	}
	
	/** 
	 * Retourne le nom de l'événement (Déplacement)
	 */
	public String nom(){
		return nom;
	}

	/** 
	 * Le joueur qui subit le déplacement 
	 */
	public Joueur cible(){
		return j;
	}
	
	/** 
	 * Empile le déplacement dans la pile du joueur 
	 */
	public void appliquerA(Joueur j){
		this.j = j;
		j.chosesAFaire().add(this);
	}	
	
	/** 
	 * Exécute le déplacement 
	 */
	public void executer(){
		//Si la cible est n'est pas renseignée
		if(cCible == null){
			Case c = Cases.cases.get(cible().position().numero()+TireDes.DernierLancer);
			cible().placerSur(c);
			if(!(c.evenement()==null))
				cible().chosesAFaire().add(c.evenement());			
		}
		else{
			cible().placerSur(cCible);
			Plateau.Game.make();
			if(cCible.evenement()!=null){
				Evenement e = cCible.evenement();
				e.appliquerA(cible());
			}
		}

	}
}
