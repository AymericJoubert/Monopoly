// OK

package monopoly.evenements;

import monopoly.gui.Plateau;
import monopoly.jeu.Case;
import monopoly.jeu.Cases;
import monopoly.jeu.Joueur;

public class DeplacementRelatif implements Evenement{
	private String nom;
	private Joueur j;
	private int nbCases;

	/** Constructeur de la classe */
	public DeplacementRelatif(Joueur j){
		this.nom = "Deplacement Relatif";
		this.j = j;
	}

	/** Retourne le nom de l'événement */
	public String nom(){
		return nom;
	}

	/** Le joueur qui subit l'événement */
	public Joueur cible(){
		return j;
	}

	/** Empile l'evenement dans la pile du joueur */
	public void appliquerA(Joueur j){
		this.j = j;
		j.chosesAFaire().add(this);
	}	

	/** Execute l'evenement */
	public void executer(){
		Case c = Cases.cases.get((cible().position().numero()+TireDes.DernierLancer)%40);
		cible().placerSur(c);
		if((c.evenement()!=null)){
			Evenement e = c.evenement();
			e.appliquerA(cible());
		}

	}
}
