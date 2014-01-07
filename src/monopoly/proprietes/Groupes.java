package monopoly.proprietes;

import monopoly.jeu.Joueur;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe Groupes qui contient les propriétés par groupe de propritétés et qui étend Groupe
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Groupes implements Groupe{

	// la map de tous les groupes immobiliers
	public static HashMap<String,Groupe> groupes = new HashMap<String,Groupe>();
	// le nom du groupe immobilier
	private String nom;
	// le cout de construction des maisons et hotels sur les proprietÃ©es du groupe
	private int cout;
	// Les proprietÃ©es appartenant au groupe immobilier
	private ArrayList<Propriete> proprietes = new ArrayList<Propriete>();

	/**
	 * Constructeur de la classe Groupes prenant en compte 2 paramètres
	 * @param nom Nom du groupe des propriétés
	 * @param cout Coût du groupe
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
	 * Retourne le coût d'achat d une maison ou d'un hôtel pour le groupe immobilier
	 * @return Coût d'une construction
	 */
	public int coutImmo(){
		return cout;
	}

	/**
	 * Méthode qui renvoie les propriétés contenues dans le groupe immobilier
	 * @return Une liste comportant les propriétés qui composent le groupe immobilier  
	 */
	public List<Propriete> composition(){
		return proprietes;
	}

	/**
	 * Recherche l'existance du groupe dont le nom est passé en paramètre
	 * @param nom Nom du groupe recherché
	 * @return Le groupe recherche ou null si il n'existe pas
	 */
	public Groupe get(String nom){
		return groupes.get(nom);
	}

	/**
	 * Methode qui teste si le groupe appartient à un seul propriétaire 
	 * @return Renvoie un booléen, true si le propriétaire est le même pour toutes les propriétés du groupe sinon false
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
	 * Ajoute une propriété au groupe
	 * @param p Propriété à ajouter au groupe
	 */
	public void add(Propriete p){
		proprietes.add(p);
	}

	/**
	 * Retourne les principales caractériques du Groupe
	 * @return Une chaîne de caractères qui définit le groupe
	 */
	public String toString(){
		String result = "";
		for(Propriete p : proprietes)
			result += p.nom() +",";
		/* enleve la derniere virgule */
		result = result.substring(0,result.length() -1);
		return "nom du groupe : " + nom + " cout immobilier : " + cout + " propriÃ©tÃ©s du groupe : " + result;
	}
}