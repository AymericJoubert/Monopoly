package monopoly.jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import monopoly.evenements.Carte;
import monopoly.evenements.Evenement;
import monopoly.evenements.Emprisonnement;
import monopoly.proprietes.Propriete;

/** Classe représentant le joueur */
public class Player implements Joueur {
	private int numero;
	private String nom;
	private boolean enPrison;
	private boolean elimine;
	private int especes;
	private Case position;
	public static LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
	public static ArrayList<Propriete> proprietes = new ArrayList<Propriete>();
	public static ArrayList<Carte> cartes = new ArrayList<Carte>();
	public static Stack<Evenement> evenements = new Stack<Evenement>();
	public static int nbJoueurs = 0;

	/** Constructeur du joueur */
	public Player(int numero, String nom, int especes, Case position) {
		this.numero = numero;
		this.nom = nom;
		enPrison = false;
		this.especes = especes;
		this.position = position;
		joueurs.add(this);
		nbJoueurs++;
	}

	/** Retourne le numero du joueur */
	public int numero(){
		return numero;
	}

	/** Retourne le nom du joueur */
	public String nom(){
		return nom;
	}

	/** @return vrai si le joueur est en prison, faux sinon */
	public boolean enPrison(){
		return enPrison;
	}

	/** Met le joueur en prison */
	public void emprisonner(){
		enPrison=true;
		Emprisonnement.PRISON.put(this,0);
	}
	
	/** Met le joueur en prison */
	public void liberer(){
		enPrison=false;
		Emprisonnement.PRISON.remove(this);
	}

	/** @return vrai si le joueur est eliminé, faux sinon */
	public boolean elimine(){
		return elimine;
	}

	/** Elimine le joueur de la partie, termine la partie s'il ne reste plus qu'un joueur */
	public void eliminer(){
		elimine = true;
		int cpt=0;
		Joueur jou = null;
		for(Joueur j : Player.joueurs){
			if(!j.elimine()){
				cpt++;
				jou = j;
			}
		}
		if(cpt==1){
			JOptionPane.showMessageDialog(new JFrame(), jou.nom()+"- Vous avez gagné ! Félicitations !");
		}
	}

	/** Retourne le montant de l'argent possédé par le joueur */
	public int especes(){
		return especes;
	}

	/** Deduit le montant de la somme possédée par le joueur, s'il a assez  */
	public boolean payer(int somme){
		if(especes < somme)
			return false;
		else
			especes-=somme;
		return true;	
	}

	/** Verse la somme passée en paramètre au joueur */
	public void verser(int somme){
		especes+=somme;
	}

	/** Retourne la case actuelle du joueur */
	public Case position(){
		return position;
	}

	/** Place le joueur sur une case passée en paramètre */
	public void placerSur(Case c){
		position = c;
	}

	/** Retourne la liste des adversaires du joueur */
	public List<Joueur> adversaires(){
		List<Joueur> jou = new ArrayList<Joueur>();
		for(Joueur j : joueurs){
			if(!this.equals(j) && !j.elimine()){
				jou.add(j);
			}
		}
		return jou;
	}

	/** Renvoi la liste des proprietes que l'utilisateur possede */
	public List<Propriete> titres(){
		return null;
	}

	/** Renvoi la liste des cartes (chance ou compagnie) possédées par le joueur */ 
	public List<Evenement> cartes(){
		ArrayList<Evenement> ev = new ArrayList<Evenement>();
		for(Carte ca : cartes){
			ev.add(ca.getEvenement());
		}
		return (List<Evenement>)ev;
	}

	/** Renvoi la liste des evenement en attente dans la pile du joueur */
	public Stack<Evenement> chosesAFaire(){
		return evenements;
	}

	/** Vide la pile d'evenements */
	public void vider(){
		evenements.clear();
	}
	
	public static Iterator<Joueur> getIterator_Joueurs() {
		return joueurs.iterator();
	}
	
	public void setCase(Case c){
		this.position = c;
	}
}