package monopoly.evenements ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.*;
import java.util.Random;

/** Classe représentant le lancer de dès par l'utilisateur  */
public class Emprisonnement implements Evenement {
	private String nom;
	private Joueur j;
	private int tours;
	
	/** Constructeur de la classe */
	public Emprisonnement(Joueur j){
		this.nom = "Emprisonnement";
		this.j = j;
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
		j.chosesAFaire().add(this);
	}

	/** Deplace le personnage sur la case suivante selon le nombre donne par les des */
	public void executer(){
		/*((Player)j).vider();
		tours++;
		j.placerSur(Cases.cases.get(20));
		j.emprisonner();
		tours++;
		if(tours < 3)
			j.chosesAFaire().push(new TireDes())
			j.chosesAFaire().push(this);*/
	}
}