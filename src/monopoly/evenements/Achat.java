package monopoly.evenements;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import monopoly.jeu.Cases;
import monopoly.jeu.Joueur;
import monopoly.proprietes.Propriete;

public class Achat implements Evenement {
	private String nom;
	private Joueur j;
	private Propriete p;
	
	/** Constructeur de la classe
	 * @param nom Nom de la carte
	 * @param j Joueur concerne par l'achat
	 */
	public Achat(String nom, Joueur j, Propriete p){
		this.nom = nom;
		this.j = j;
		this.p = p;
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
		if(p.proprietaire()==null){
			int fifou = JOptionPane.showConfirmDialog(new JFrame(), j.nom()+", voulez vous acheter :"+this.nom+" ? ");
			if(fifou==0){
				j.chosesAFaire().push(new Depenser(this.nom, j, p.prixAchat()));
				p.setProprietaire(j);
			}
		}
		else if(p.proprietaire() != j){
			JOptionPane.showMessageDialog(new JFrame(), j.nom()+", vous devez donner : "+p.loyer()+" a "+p.proprietaire().nom());
			j.chosesAFaire().push(new Depenser(this.nom, j, p.loyer()));
			p.proprietaire().chosesAFaire().push(new Recette(this.nom, p.proprietaire(), p.loyer()));
		}
	}
}
