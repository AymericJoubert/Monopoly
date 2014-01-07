// OK

package monopoly.evenements;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import monopoly.jeu.Joueur;
/**
 * Classe permettant le tirage de carte pour le joueur, celle-ci implemente Evenement.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class TirerCarte implements Evenement{
	private Joueur j;
	private String type;
	public static int DernierLancer;


	/**
	 * Constructeur de la classe TirerCarte, celui-ci possede 2 parametres.
	 * @param j Joueur tirant les des
	 * @param type Savoir si la carte est une carte "chance" ou "caisse de communaute" (cc)
	 */
	public TirerCarte(Joueur j, String type){
		this.j = j;
		this.type = type;
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
		Carte c;
		if(type.equals("chance"))
			c = Carte.chance.get(r.nextInt(Carte.chance.size()));
		else
			c = Carte.cc.get(r.nextInt(Carte.chance.size()));
		JOptionPane.showMessageDialog(new JFrame(), cible().nom()+" - Chance : "+c.nom()+" : "+c.getEvenement());
		c.getEvenement().appliquerA(j);
	}
}
