package monopoly.proprietes;

import monopoly.jeu.Joueur;
import monopoly.jeu.Case;
import javax.swing.*;
import java.awt.*;

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

	/** La case du plateau de jeu associée à ce titre de propriété */
	public Case position(){
		return position;
	}

	/** Nom de la propriété (le même que la case en principe) */
	public String nom(){
		return nom;
	}

	/** Indique si la propriété est hypothéquée */
	public boolean hypotheque(){
		return hypotheque;
	}

	/** Hypothèque la propriété (en procurant ainsi des liquidités au
	 * propriétaire pour une valeur moitié du prix d'achat) */
	public void hypothequer(){
		proprietaire.verser(prix/2);
		hypotheque=true;
	}

	/** Lève l'hypothèque (si le joueur possède les liquidités
	 * suffisantes soit la valeur hypothécaire + 10%)
	 * @return true si l'hypothèque est levée, false sinon */
	public boolean deshypothequer(){
		if(proprietaire.payer((int)((prix/2)*1.1))){
			hypotheque=false;
			return true;
		}
		return false;
	}

	/** Prix d'achat */
	public int prixAchat(){
		return prix;
	}

	/** Le groupe auquel est rattachée la propriété */
	public Groupe groupe(){
		return groupe;
	}

	/** Indique si la propriété est constructible */
	public boolean constructible(){
		boolean construct=true;
		if(groupe.proprietaireUnique()){
			for(Propriete p : groupe.composition()){
				if((p.hypotheque()) || (p.niveauImmobilier() < niveauImmobilier))
					construct=false;
			}
		}
		return construct;
	}

	/** Construit un bâtiment sur cette propriété si c'est possible
	 * (cf. règles de constructibilité et liquidités du joueur).
	 * @return true si la construction a pu être réalisée, false
	 * sinon */
	public boolean construire(){
		if(constructible() && proprietaire.payer(coutImmobilier)){
			niveauImmobilier++;
			return true;
		}
		else
			return false;
	}


	/** Détruit un bâtiment sur cette propriété si c'est possible (et
	 * reverse alors au joueur la moitié du prix d'achat des
	 * bâtiments) 
	 * @return true si un bâtiment a été détruit, false sinon */
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


	/** Propriétaire du titre (éventuellement <code>null</code>) */
	public Joueur proprietaire(){
		return proprietaire;
	}


	/** Montant du loyer à percevoir */
	public int loyer(){
		return loyer[niveauImmobilier];
	}

	/** Niveau des constructions (0 = terrain nu, 1 à 4 = nb de
	 * maisons, 5 = hôtel)  */
	public int niveauImmobilier(){
		return niveauImmobilier();
	}

	public void setProprietaire(Joueur j){
		proprietaire = j;
	}
	
	public void setCase(Case c){
		this.position = c;
	}
}