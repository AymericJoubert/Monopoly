package monopoly.proprietes ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;

/** Cette interface décrit les fonctionnalités associées à tout titre
 * de monopole */
public abstract class Monopole implements Propriete {
    private int num, prix;
    private String nom;
    private Case position;
    private boolean hypotheque;
    private Groupe groupe;
    private Joueur proprietaire;

    public Monopole(int num, Case position, String nom, int prix, Groupe groupe){
	this.num = num;
	this.nom = nom;
	this.prix = prix;
	this.position = position;
	this.groupe = groupe;
	hypotheque = false;
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
	return false;
    }

    /** Construit un bâtiment sur cette propriété si c'est possible
     * (cf. règles de constructibilité et liquidités du joueur).
     * @return true si la construction a pu être réalisée, false
     * sinon */
    public boolean construire(){
	return false;
    }

    /** Détruit un bâtiment sur cette propriété si c'est possible (et
     * reverse alors au joueur la moitié du prix d'achat des
     * bâtiments) 
     * @return true si un bâtiment a été détruit, false sinon */
    public boolean detruire(){
	return false;
    }

    /** Propriétaire du titre (éventuellement <code>null</code>) */
    public Joueur proprietaire(){
	return proprietaire;
    }

    /** Montant du loyer à percevoir */
    public abstract int loyer();

    /** Niveau des constructions (0 = terrain nu, 1 à 4 = nb de
     * maisons, 5 = hôtel)  */
    public abstract int niveauImmobilier();
    
    public void setProprietaire(Joueur j){
	proprietaire = j;
    }
    
	public void setCase(Case c){
		this.position = c;
	}
}
