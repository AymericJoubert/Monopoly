package monopoly.proprietes;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;
import javax.swing.*;
import java.awt.*;
/**
 * Classe Terrain avec toutes les informations qu'un terrain a besoin et qui �tend Monopole
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
	 * Constructeur de la classe Terrain prenant en compte 7 param�tres
	 * @param num Num�ro du terrain
	 * @param position Position du terrain (Case)
	 * @param nom Nom du terrain
	 * @param prix Prix du terrain
	 * @param groupe Groupe auquel appartient le terrain
	 * @param coutImmobilier Co�t immobilier du terrain
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
	 *  @return La case du plateau de jeu associ� � ce titre de propri�t�
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
	 * @return True si la propri�t� est hypoth�quable, False sinon
	 */
	public boolean hypotheque(){
		return hypotheque;
	}

	/** 
	 * Hypoth�que la propri�t� (en procurant ainsi des liquidit�s au propri�taire pour une valeur moiti� du prix d'achat)
	 */
	public void hypothequer(){
		proprietaire.verser(prix/2);
		hypotheque=true;
	}

	/** 
	 * L�ve l'hypoth�que (si le joueur poss�de les liquidit�s suffisantes soit la valeur hypoth�caire + 10%)
	 * @return True si l'hypoth�que est lev�e, False sinon */
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
	 * @return Le groupe auquel est rattach� la propri�t�
	 */
	public Groupe groupe(){
		return groupe;
	}

	/** 
	 * @return True si la propri�t� est constructible, False sinon 
	 */
	public boolean constructible(){
		boolean construct=true;
		//Si le groupe est poss�d� par un et un seul joueur
		if(groupe.proprietaireUnique()){
			for(Propriete p : groupe.composition()){
				if((p.hypotheque()) || (p.niveauImmobilier() < niveauImmobilier))
					construct=false;
			}
		}
		return construct;
	}

	/** Construit un b�timent sur cette propri�t� si c'est possible (cf. r�gles de constructibilit� et liquidit�s du joueur).
	 * @return True si la construction a pu �tre r�alis�e, False sinon 
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
 	 * D�truit un b�timent sur cette propri�t� si c'est possible (et reverse alors au joueur la moiti� du prix d'achat des b�timents) 
	 * @return True si un b�timent a �t� d�truit, False sinon */
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
	 * @return Propri�taire du titre (�ventuellement <code>null</code>)
	 */
	public Joueur proprietaire(){
		return proprietaire;
	}

	/** 
	 * @return Montant du loyer � percevoir 
	 */
	public int loyer(){
		return loyer[niveauImmobilier];
	}

	/** 
	 * @return Niveau des constructions (0 = terrain nu, 1 � 4 = nombre de maisons, 5 = h�tel) 
	 */
	public int niveauImmobilier(){
		return niveauImmobilier();
	}
	
	/**
	 * Attribuer un terrain � une case.
	 * @param c Case o� la position doit �tre attribu�e
	 */
	public void setCase(Case c){
		this.position = c;
	}
	
	public void setProprietaire(Joueur j){
		proprietaire = j;
	}
}