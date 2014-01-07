package monopoly.jeu ;
import monopoly.proprietes.Propriete ;
import monopoly.evenements.Evenement ;

/** Cette interface d�crit une case du plateau de jeu du Monopoly */
public interface Case {
    /** Num�ro de la case */
    int numero() ;
    /** Donne la case associ�e au num�ro sp�cifi� */
    Case get(int numero) ;
    /** Intitulé de la case */
    String nom() ;
    /** Titre de propri�t� associ� � la case �ventuellement
     * <code>null</code>)*/
    Propriete propriete() ;
    /** Ev�nement susceptible de se d�clencher �l'arriv�e sur cette case (�ventuellement <code>null</code>) */
    Evenement evenement() ;
}
