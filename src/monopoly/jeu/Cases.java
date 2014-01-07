package monopoly.jeu ;
import monopoly.proprietes.Propriete ;
import monopoly.evenements.Evenement ;
import java.util.*;

/**
 * Classe representant une Case, celle-ci est implantee avec Case.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Cases implements Case {
    private int numero;
    public static HashMap<Integer, Case> cases = new HashMap<Integer, Case>();
    private String nom;
    private Propriete propriete;
    private Evenement evenement;

    /**
     * Constructeur de la classe Cases, celui-ci prend 4 parametres
     * @param numero Numero de la case
     * @param nom Nom de la case
     * @param propriete Propriete situee sur cette dite case
     * @param evenement Evenement que produit cette dite case
     */
    public Cases(int numero, String nom, Propriete propriete, Evenement evenement){
    	this.numero = numero;
    	this.nom = nom;
    	this.propriete = propriete;
    	this.evenement = evenement;
    	//On place la case dans la liste des cases
    	cases.put(numero, (Case) this);
    }

    /** 
     * Retourne le numero de la case  
     */
    public int numero(){
    	return numero;
    }

    /** Retoure la case dont le numero est passe en parametre 
     *  @param numero Numero de la case voulue
     */
    public Case get(int numero){
    	int num = numero%40;
    	if(num==0) num++;
    	return cases.get(num);
    }

    /** 
     * Retourne le nom de la case
     */
    public String nom(){
    	return nom;
    }
    
    /** 
     * Retourne la propriete associee à la case 
     */
    public Propriete propriete(){
    	return propriete;
    }

    /** 
     * Retourne l'evenement associe à la case 
     */ 
    public Evenement evenement(){
    	return evenement;
    }

}