package monopoly.jeu ;
import monopoly.proprietes.Propriete ;
import monopoly.evenements.Evenement ;
import java.util.*;

/** Classe decrivant la classe qui représente une case */
public class Cases implements Case {
    private int numero;
    public static HashMap<Integer, Case> cases = new HashMap<Integer, Case>();
    private String nom;
    private Propriete propriete;
    private Evenement evenement;

    /** Constructeur de la classe */
    public Cases(int numero, String nom, Propriete propriete, Evenement evenement){
    	this.numero = numero;
    	this.nom = nom;
    	this.propriete = propriete;
    	this.evenement = evenement;
    	cases.put(numero, (Case) this);
    }

    /** Retourne le numéro de la case  */
    public int numero(){
    	return numero;
    }

    /** Retoure la case dont le numero est passé en paramètre */
    public Case get(int numero){
    	int num = numero%40;
    	if(num==0) num++;
    	return cases.get(num);
    }

    /** Retourne le nom de la case */
    public String nom(){
    	return nom;
    }
    
    /** Retourne la propriete associée a la case */
    public Propriete propriete(){
    	return propriete;
    }

    /** Retourne l'événement associé a la case */ 
    public Evenement evenement(){
    	return evenement;
    }

}