package monopoly.evenements;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import monopoly.jeu.Cases;
import monopoly.jeu.Joueur;
import monopoly.proprietes.Propriete;

/**
 * Classe permettant l'achat de proprietes, celle-ci implemente Evenement.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Achat implements Evenement {
	private String nom;
	private Joueur j;
	private Propriete p;
	
	/** Constructeur de la classe Achat
	 * @param nom Nom de la carte
	 * @param j Joueur concerne par l'achat
	 * @param p Propriete concernee par l'achat
	 */
	public Achat(String nom, Joueur j, Propriete p){
		this.nom = nom;
		this.j = j;
		this.p = p;
	}

	/** 
	 * Retourne l'intitule de l'evenement 
	 */
	public String nom(){
		return nom;
	}

	/** 
	 * Retourne la cible de l'evenement
	 */
	public Joueur cible(){
		return j;
	}

	/** 
	 * Empile l'evenement dans la pile du joueur 
	 */
	public void appliquerA(Joueur j){
		this.j = j;
		j.chosesAFaire().add(this);
	}	

	/** 
	 * Permet l'achat d'une propriete par un joueur. Une fen�tre de dialogue s'ouvre permettant � l'utilisateur de choisir si oui ou non il souhaite acheter la propriete.
	 */
	public void executer(){
		//Si la case n'a pas de proprietaire
		if(p.proprietaire()==null){
			//Demande � l'utilisateur pour achat, ou non.
			int fifou = JOptionPane.showConfirmDialog(new JFrame(), cible().nom()+", voulez vous acheter "+this.nom+" ? ");
			if(fifou==0){
				cible().chosesAFaire().push(new Depenser(this.nom, cible(), p.prixAchat()));
				p.setProprietaire(cible());
			}
		}
		//Si le joueur passant sur la case n'est pas le proprietaire de la case
		else if(p.proprietaire() != cible()){
			//Alors on le previent qu'il vient de passer sur la case d'un autre joueur, et on lui indique la somme qu'il doit payer.
			JOptionPane.showMessageDialog(new JFrame(), cible().nom()+", vous devez donner : "+p.loyer()+" a "+p.proprietaire().nom());
			//On sort la somme � la cible de la case..
			cible().chosesAFaire().push(new Depenser(this.nom, cible(), p.loyer()));
			//Et on la donne au proprietaire de la case.
			p.proprietaire().chosesAFaire().push(new Recette(this.nom, p.proprietaire(), p.loyer()));
		}
	}
}
