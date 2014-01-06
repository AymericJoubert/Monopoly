// OK

package monopoly.evenements;

import monopoly.gui.Plateau;
import monopoly.jeu.Case;
import monopoly.jeu.Cases;
import monopoly.jeu.Joueur;

public class Deplacement implements Evenement{
	private String nom;
	private Joueur j;
	private int nbCases;
	public Case cCible;

	/** Constructeur de la classe */
	public Deplacement(Joueur j, Case cCible){
		this.nom = "Déplacement";
		this.j = j;
		this.cCible = cCible;
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
		if(cCible == null){
			Case c = Cases.cases.get(j.position().numero()+TireDes.DernierLancer);
			j.placerSur(c);
			if(!(c.evenement()==null))
				j.chosesAFaire().add(c.evenement());			
		}
		else{
			j.placerSur(cCible);
			Plateau.Game.init();
			if(!(cCible.evenement()==null)){
				Evenement e = cCible.evenement();
				e.appliquerA(j);
			}
		}

	}
}
