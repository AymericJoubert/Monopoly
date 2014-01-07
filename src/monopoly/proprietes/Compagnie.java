package monopoly.proprietes ;

import monopoly.evenements.TireDes;
import monopoly.jeu.Case;

/**
 * Classe Compagnie avec toutes les informations qu'une compagnie à besoin et qui étend Monopole
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Compagnie extends Monopole{
	
	private int loyer;
	
    /**
     * Constructeur de la classe Compagnie, possédant 5 paramètres
     * @param num Numéro de la compagnie
     * @param position Position de la compagnie sur une Case
     * @param nom Nom de la compagnie
     * @param prix Prix que coûte la compagnie
     * @param groupe Groupe qui couvre la compagnie
     */
    public Compagnie(int num, Case position, String nom, int prix, Groupe groupe){
    	super(num, position, nom, prix, groupe);
    }

    public int loyer(){
		return TireDes.DernierLancer;
    }

    public int niveauImmobilier(){
	if(super.proprietaire()!=null){
	    int cpt=0;
	    for(Propriete p : super.groupe().composition()){
		if (p.proprietaire() == super.proprietaire())
		    cpt++;
	    } 
	    return cpt;
	}
	return 1;
    }
} 