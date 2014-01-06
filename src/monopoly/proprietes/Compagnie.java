package monopoly.proprietes ;

import monopoly.evenements.TireDes;
import monopoly.jeu.Case;

/** Cette interface décrit les fonctionnalités associées à tout titre
 * d'une compagnie */
public class Compagnie extends Monopole{
	
	private int loyer;
	
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