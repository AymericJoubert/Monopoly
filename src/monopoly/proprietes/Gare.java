package monopoly.proprietes ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;

/** Cette interface décrit les fonctionnalités associées à tout titre
 * d'une gare */
public class Gare extends Monopole{
	private int loyer;
	private Joueur proprietaire;

	public Gare(int num, Case position, String nom, int prix, Groupe groupe, int loyer){
		super(num, position, nom, prix, groupe);
		this.loyer = loyer;
	}

	public int loyer(){
		return niveauImmobilier()*loyer;
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