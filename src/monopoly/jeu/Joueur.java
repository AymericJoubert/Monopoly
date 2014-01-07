package monopoly.jeu ;

import java.util.List ;
import java.util.Stack ;

import monopoly.proprietes.Propriete ;
import monopoly.evenements.Evenement ;

/** Cette interface decrit les fonctionnalites que doit presenter une
 * classe representant un joueur de Monopoly */
public interface Joueur {
    /** Le numero du joueur */
    int numero() ;
    /** Le nom du joueur */
    String nom() ;
    /** Indique si le joueur est emprisonne */
    boolean enPrison() ;
    /** Emprisonne le joueur */
    void emprisonner() ;
    /** Libere le joueur */
    void liberer() ;
    /** Indique si le joueur est elimine */
    boolean elimine() ;
    /** Élimine le joueur */
    void eliminer() ;
    /** Liquidites possedees par le joueur */
    int especes() ;
    /** Impose au joueur le paiement de la somme specifiee
     * @return true si le joueur a pu payer, false sinon
     */
    boolean payer(int somme) ;
    /** Verse au joueur la somme specifier */
    void verser(int somme) ;
    /** Donne la case sur laquelle le joueur est place */
    Case position() ;
    /** Place le joueur sur la case specifiee */
    void placerSur(Case c) ;
    /** Donne la liste des autres joueurs encore en lice (tous sauf
     * <code>this</code> et les elimines)*/
    List<Joueur> adversaires() ;
    /** Titres de proprietes possedes par le joueur */
    List<Propriete> titres() ;
    /** Cartes conservees par le joueur */
    List<Evenement> cartes() ;
    /** La pile d'evenements que le joueur doit subir pendant son tour
     * de jeu : si la pile est vide, le joueur a termine son tour ; sinon il doit
     * depiler un evenement pour l'executer. Il peut arriver qu'un evenement manipule
     * cette pile (par exemple "Aller en prison" termine le tour du joueur même s'il lui
     * restait theoriquement des choses à faire) */
    Stack<Evenement> chosesAFaire() ;
}
