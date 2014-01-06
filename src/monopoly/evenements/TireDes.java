// OK

package monopoly.evenements ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.*;
import java.util.Random;

/** Classe représentant le lancer de dès par l'utilisateur  */
public class TireDes implements Evenement {
	private Joueur j;
	public static int DernierLancer = 0;
	public static int nbCases;

	/** Constructeur de la classe */
	public TireDes(Joueur j){
		this.j = j;
	}

	/** Retourne l'intitulé de l'évenement */
	public String nom(){
		return "Tire les dès !";
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

	/** Deplace le personnage sur la case suivante selon le nombre donne par les des */
	public void executer(){
		Random r = new Random();
		this.nbCases = r.nextInt(11)+2;
		/*if(!j.enPrison())
			if(nbCases == 12){
				j.chosesAFaire().push(new Deplacement(j, null));
				j.chosesAFaire().push(new TireDes(j));
			}
			else{
				j.chosesAFaire().push(new Deplacement(j, null));
			}
		else if(nbCases == 12){
			this.nbCases+= r.nextInt(11)+2;
			j.chosesAFaire().push(new Deplacement(j, Cases.cases.get((j.position().numero()+nbCases)%40)));
		}
		else*/
			j.chosesAFaire().push(new Deplacement(j, Cases.cases.get((j.position().numero()+nbCases)%40)));


		TireDes.DernierLancer = nbCases;
	}
}