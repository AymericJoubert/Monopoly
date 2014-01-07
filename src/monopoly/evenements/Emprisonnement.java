package monopoly.evenements ;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import monopoly.gui.Plateau;
import monopoly.jeu.Cases;
import monopoly.jeu.Joueur;
import monopoly.jeu.Player;

/** Classe représentant le lancer de dès par l'utilisateur  */
public class Emprisonnement implements Evenement {
	private String nom;
	private Joueur j;
	public static HashMap<Joueur, Integer> PRISON = new HashMap<Joueur, Integer>();
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