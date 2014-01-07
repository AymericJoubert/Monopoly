// OK

package monopoly.evenements;

import monopoly.gui.Plateau;
import monopoly.jeu.Case;
import monopoly.jeu.Cases;
import monopoly.jeu.Joueur;
/**
 * Classe repr�sentant le d�placement d'un utilisateur, celle-ci est implant�e avec Evenement.
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
	 * @param j Joueur se d�pla�ant
	 */
	public Deplacement(Joueur j){
		this.nom = "D�placement";
		this.j = j;
		this.cCible = cCible;
	}
	
	/** 
	 * Retourne le nom de l'�v�nement (D�placement)
	 */
	public String nom(){
		return nom;
	}

	/** 
	 * Le joueur qui subit le d�placement 
	 */
	public Joueur cible(){
		return j;
	}
	
	/** 
	 * Empile le d�placement dans la pile du joueur 
	 */
	public void appliquerA(Joueur j){
		this.j = j;
		j.chosesAFaire().add(this);
	}	
	
	/** 
	 * Ex�cute le d�placement 
	 */
	public void executer(){
		//Si la cible est n'est pas renseign�e
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
