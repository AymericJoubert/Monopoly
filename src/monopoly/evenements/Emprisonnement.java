package monopoly.evenements ;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import monopoly.gui.Plateau;
import monopoly.jeu.Cases;
import monopoly.jeu.Joueur;
import monopoly.jeu.Player;

/**
 * Classe permettant la possibilite d'amener un joueur en prison s'il passe par la case "Aller en prison", celle-ci implemente Evenement.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Emprisonnement implements Evenement {
	private String nom;
	private Joueur j;
	public static HashMap<Joueur, Integer> PRISON = new HashMap<Joueur, Integer>();

	/**
	 * Constructeur de la classe Emprisonnement
	 * @param j Le joueur vise.
	 */
	public Emprisonnement(Joueur j){
		this.nom = "Emprisonnement";
		this.j = j;
	}

	/** Retourne l'intitule de l'evenement */
	public String nom(){
		return nom;
	}

	/** Retourne la cible de l'evenement */
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
		((Player)cible()).vider();
		JOptionPane.showMessageDialog(new JFrame(), "Vous allez en prison !");
		cible().placerSur(Cases.cases.get(10));
		cible().emprisonner();
		Plateau.Game.make();
	}
}