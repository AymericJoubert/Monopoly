package monopoly.proprietes;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;
import javax.swing.*;
import java.awt.*;
/**
 * Classe Terrain avec toutes les informations qu'un terrain a besoin et qui étend Monopole
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Terrain implements Propriete{
	private int num;
	private Case position;
	private String nom;
	private boolean hypotheque;
	private int prix;
	private Groupe groupe;
	private Joueur proprietaire;
	private int niveauImmobilier;
	private int coutImmobilier;
	private int [] loyer;

	/**
	 * Constructeur de la classe Terrain prenant en compte 7 paramètres
	 * @param num Numéro du terrain
	 * @param position Position du terrain (Case)
	 * @param nom Nom du terrain
	 * @param prix Prix du terrain
	 * @param groupe Groupe auquel appartient le terrain
	 * @param coutImmobilier Coût immobilier du terrain
	 * @param loyer Loyers du terrain
	 */
	public Terrain(int num, Case position, String nom, int prix, Groupe groupe, int coutImmobilier, int [] loyer){
		this.num = num;
		this.position = position;
		this.nom = nom;
		this.prix = prix;
		this.groupe = groupe;
		this.coutImmobilier = coutImmobilier;
		this.loyer = loyer;
		hypotheque=false;
		niveauImmobilier=0;
		proprietaire = null;
	}
	
	/**
	 *  @return La case du plateau de jeu associé à ce titre de propriété
     */
	public Case position(){
		return position;
	}

	/** 
	 * @return Nom du terrain 
	 */
	public String nom(){
		return nom;
	}

	/** 
	 * @return True si la propriété est hypothèquable, False sinon
	 */
	public boolean hypotheque(){
		return hypotheque;
	}

	/** 
	 * Hypothèque la propriété (en procurant ainsi des liquidités au propriétaire pour une valeur moitié du prix d'achat)
	 */
	public void hypothequer(){
		proprietaire.verser(prix/2);
		hypotheque=true;
	}

	/** 
	 * Lève l'hypothèque (si le joueur possède les liquidités suffisantes soit la valeur hypothècaire + 10%)
	 * @return True si l'hypothèque est levée, False sinon */
	public boolean deshypothequer(){
		if(proprietaire.payer((int)((prix/2)*1.1))){
			hypotheque=false;
			return true;
		}
		return false;
	}

	/** 
	 * @return Prix d'achat 
	 */
	public int prixAchat(){
		return prix;
	}

	/** 
	 * @return Le groupe auquel est rattaché la propriété
	 */
	public Groupe groupe(){
		return groupe;
	}

	/** 
	 * @return True si la propriété est constructible, False sinon 
	 */
	public boolean constructible(){
		boolean construct=true;
		//Si le groupe est possédé par un et un seul joueur
		if(groupe.proprietaireUnique()){
			for(Propriete p : groupe.composition()){
				if((p.hypotheque()) || (p.niveauImmobilier() < niveauImmobilier))
					construct=false;
			}
		}
		return construct;
	}

	/** Construit un bâtiment sur cette propriété si c'est possible (cf. règles de constructibilité et liquidités du joueur).
	 * @return True si la construction a pu être réalisée, False sinon 
	 */
	public boolean construire(){
		if(constructible() && proprietaire.payer(coutImmobilier)){
			niveauImmobilier++;
			return true;
		}
		else
			return false;
	}

	/**
 	 * Détruit un bâtiment sur cette propriété si c'est possible (et reverse alors au joueur la moitié du prix d'achat des bâtiments) 
	 * @return True si un bâtiment a été détruit, False sinon */
	public boolean detruire(){
		if(niveauImmobilier > 0){
			niveauImmobilier--;
			proprietaire.verser(coutImmobilier/2);
			return true;
		}
		else{
			return false;
		}
	}

	/** 
	 * @return Propriétaire du titre (éventuellement <code>null</code>)
	 */
	public Joueur proprietaire(){
		return proprietaire;
	}

	/** 
	 * @return Montant du loyer à percevoir 
	 */
	public int loyer(){
		return loyer[niveauImmobilier];
	}

	/** 
	 * @return Niveau des constructions (0 = terrain nu, 1 à 4 = nombre de maisons, 5 = hôtel) 
	 */
	public int niveauImmobilier(){
		return niveauImmobilier();
	}
	
	/**
	 * Attribuer un terrain à une case.
	 * @param c Case où la position doit être attribuée
	 */
	public void setCase(Case c){
		this.position = c;
	}
	
	public void setProprietaire(Joueur j){
		proprietaire = j;
	}
}