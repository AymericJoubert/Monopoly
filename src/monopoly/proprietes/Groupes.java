package monopoly.proprietes;

import monopoly.jeu.Joueur;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


/**
 * Classe qui contient les propriétées par groupe immobillier
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

	public Groupes(String nom, int cout){
		this.nom = nom;
		this.cout = cout;
		groupes.put(nom,this);
	}
	/**
	 * methode de recuperation le nom du groupe
	 * @return un string comportant le nom du groupe
	 */
	public String nom(){
		return nom;
	}

	/**
	 * retourne le cout d'achat d une maison ou d'un hotel pour le groupe immobilier
	 * @return un int correspondant au cout d'une construction
	 */
	public int coutImmo(){
		return cout;
	}

	/**
	 * methode qui renvoie les proprietees contenues dans le groupe immobilier
	 * @return une list comportant les proprietees qui compose le groupe immobilier  
	 */
	public List<Propriete> composition(){
		return proprietes;
	}

	/**
	 * Recherche l'existance du groupe dont le nom est passe en paramtre
	 * @param nom Nom du groupe recherche
	 * @return le groupe recherche ou null si il existe pas
	 */
	public Groupe get(String nom){
		return groupes.get(nom);
	}

	/**
	 * Methode qui teste si le groupe appartient a un seul proprietaire 
	 * @return Renvoie un booleen, egale a true si le proprietaire est le meme pour toutes les
	 * proprietees du groupe
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
	 * @param p Propriete à ajouter au groupe
	 */
	public void add(Propriete p){
		proprietes.add(p);
	}

	/**
	 * Retourne les principaux caracteriques du Groupe
	 * @return Une chaine de caracteres qui definit le groupe
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