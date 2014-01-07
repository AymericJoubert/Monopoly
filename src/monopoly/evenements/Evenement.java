package monopoly.evenements ;

import monopoly.jeu.Joueur ;
import monopoly.jeu.Case ;

/** Cette interface decrit les fonctionnalites associees aux
 * evenements du jeu */
public interface Evenement {
    /** Intitule de l'evenement (i.e. en principe de la case associee
     * ou de la carte) */
    String nom() ;
    /** Le joueur qui subit l'evenement */
    Joueur cible() ;    
    /** Execute l'evenement */
    void executer() ;
    /** Applique l'evenement a un joueur */
    void appliquerA(Joueur j);
}
