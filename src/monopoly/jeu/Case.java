package monopoly.jeu ;
import monopoly.proprietes.Propriete ;
import monopoly.evenements.Evenement ;

/** Cette interface decrit une case du plateau de jeu du Monopoly */
public interface Case {
    /** Numero de la case */
    int numero() ;
    /** Donne la case associee au numero specifie */
    Case get(int numero) ;
    /** Intitule de la case */
    String nom() ;
    /** Titre de propriete associe � la case eventuellement
     * <code>null</code>)*/
    Propriete propriete() ;
    /** Evenement susceptible de se declencher �l'arrivee sur cette case (eventuellement <code>null</code>) */
    Evenement evenement() ;
}
