package monopoly.jeu ;
import monopoly.proprietes.Propriete ;
import monopoly.evenements.Evenement ;
import java.util.*;

/**
 * Classe représentant une Case, celle-ci est implantée avec Case.
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
     * Constructeur de la classe Cases, celui-ci prend 4 paramètres
     * @param numero Numéro de la case
     * @param nom Nom de la case
     * @param propriete Propriété située sur cette dite case
     * @param evenement Evénement que produit cette dite case
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
     * Retourne le numéro de la case  
     */
    public int numero(){
    	return numero;
    }

    /** Retoure la case dont le numero est passé en paramètre 
     *  @param numero Numéro de la case voulue
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
     * Retourne la propriété associée à la case 
     */
    public Propriete propriete(){
    	return propriete;
    }

    /** 
     * Retourne l'événement associé à la case 
     */ 
    public Evenement evenement(){
    	return evenement;
    }

}