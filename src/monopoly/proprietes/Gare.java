package monopoly.proprietes ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;

/**
 * Classe Gare avec toutes les informations qu'une gare a besoin et qui étend Monopole
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Gare extends Monopole{
	private int loyer;
	private Joueur proprietaire;

	/**
	 * Constructeur de la classe Gare, possédant 6 paramètres
     * @param num Numéro de la gare
     * @param position Position de la gare sur une Case
     * @param nom Nom de la gare
     * @param prix Prix que coûte la gare
     * @param groupe Groupe qui couvre la gare
	 * @param loyer Valeur du loyer de la gare
	 */
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