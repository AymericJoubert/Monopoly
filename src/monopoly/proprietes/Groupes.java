package monopoly.proprietes;

import monopoly.jeu.Joueur;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe Groupes qui contient les propri�t�s par groupe de proprit�t�s et qui �tend Groupe
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Groupes implements Groupe{

	// la map de tous les groupes immobiliers
	public static HashMap<String,Groupe> groupes = new HashMap<String,Groupe>();
	// le nom du groupe immobilier
	private String nom;
	// le cout de construction des maisons et hotels sur les proprietées du groupe
	private int cout;
	// Les proprietées appartenant au groupe immobilier
	private ArrayList<Propriete> proprietes = new ArrayList<Propriete>();

	/**
	 * Constructeur de la classe Groupes prenant en compte 2 param�tres
	 * @param nom Nom du groupe des propri�t�s
	 * @param cout Co�t du groupe
	 */
	public Groupes(String nom, int cout){
		this.nom = nom;
		this.cout = cout;
		groupes.put(nom,this);
	}
	
	/**
	 * Nom du groupe
	 * @return Nom du groupe
	 */
	public String nom(){
		return nom;
	}

	/**
	 * Retourne le co�t d'achat d une maison ou d'un h�tel pour le groupe immobilier
	 * @return Co�t d'une construction
	 */
	public int coutImmo(){
		return cout;
	}

	/**
	 * M�thode qui renvoie les propri�t�s contenues dans le groupe immobilier
	 * @return Une liste comportant les propri�t�s qui composent le groupe immobilier  
	 */
	public List<Propriete> composition(){
		return proprietes;
	}

	/**
	 * Recherche l'existance du groupe dont le nom est pass� en param�tre
	 * @param nom Nom du groupe recherch�
	 * @return Le groupe recherche ou null si il n'existe pas
	 */
	public Groupe get(String nom){
		return groupes.get(nom);
	}

	/**
	 * Methode qui teste si le groupe appartient � un seul propri�taire 
	 * @return Renvoie un bool�en, true si le propri�taire est le m�me pour toutes les propri�t�s du groupe sinon false
	 */
	public boolean proprietaireUnique(){
		boolean result = true;
		Joueur proprietaire, tmp;
		proprietaire = proprietes.get(0).proprietaire(); 
		for (Propriete p : proprietes){
			tmp = p.proprietaire();
			if(tmp != proprietaire || tmp == null)
				result = false;
			proprietaire = tmp;
		}
		return result;
	}

	/**
	 * Ajoute une propri�t� au groupe
	 * @param p Propri�t� � ajouter au groupe
	 */
	public void add(Propriete p){
		proprietes.add(p);
	}

	/**
	 * Retourne les principales caract�riques du Groupe
	 * @return Une cha�ne de caract�res qui d�finit le groupe
	 */
	public String toString(){
		String result = "";
		for(Propriete p : proprietes)
			result += p.nom() +",";
		/* enleve la derniere virgule */
		result = result.substring(0,result.length() -1);
		return "nom du groupe : " + nom + " cout immobilier : " + cout + " propriétés du groupe : " + result;
	}
}