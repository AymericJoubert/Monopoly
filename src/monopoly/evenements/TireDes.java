// OK

package monopoly.evenements ;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import monopoly.jeu.Cases;
import monopoly.jeu.Joueur;

/**
 * Classe permettant de tirer les des, celle-ci impl�mente Evenement.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class TireDes implements Evenement {
	private Joueur j;
	public static int DernierLancer = 0;
	public static int nbCases = 0;

	/** 
	  * Constructeur de la classe TireDes
	  * @param j Joueur tirant les des
	  */
	public TireDes(Joueur j){
		this.j = j;
	}

	/** Retourne l'intitul� de l'�venement */
	public String nom(){
		return "Tire les des !";
	}

	/** Retourne la cible de l'�venement */
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
		nbCases = r.nextInt(11)+2;
		//Si le joueur n'est pas en prison
		if(!j.enPrison()){
			//Si le joueur a depasse la case depart (case>39) alors il recoit 20000
			if((j.position().numero()+nbCases)>39){
				JOptionPane.showMessageDialog(new JFrame(), "Vous �tes pass� par la case Depart, vous recevez 20000 F");
				j.chosesAFaire().push(new Recette("Depart", j, 20000));
			}
			TireDes.DernierLancer = nbCases;
			j.chosesAFaire().push(new DeplacementRelatif(j));
			//Si le joueur fait 12
			if(TireDes.DernierLancer == 12){
				JOptionPane.showMessageDialog(new JFrame(), "Vous avez fait un 12 ! Vous pouvez rejouer.");
				j.chosesAFaire().push(new TireDes(j));
			}
		}
		//Sinon, le joueur doit faire 12 ou tirer les des deux fois pour sorir de prison
		else{
			TireDes.DernierLancer = nbCases;
			Emprisonnement.PRISON.put(cible(), Emprisonnement.PRISON.get(cible()) + 1);
			if(DernierLancer == 12 || Emprisonnement.PRISON.get(cible()) > 2){
				cible().liberer();
				JOptionPane.showMessageDialog(new JFrame(), "Vous sortez de prison.");
				cible().chosesAFaire().push(new DeplacementRelatif(cible()));
			}
		}
	}
}
