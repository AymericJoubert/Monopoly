package monopoly.csv;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
/**
 * Classe permettant la recuperation des fichiers CSV dans des Lists.
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Carte_CSV {

	/**
	 * Permet la creation d'une liste (Carte_Model ou Monopoly_Model) afin de recuperer chaque valeur des differents fichiers CSV.
	 * @param type Soit "monopoly" si on souhaite retrouver une liste du fichier "monopoly.csv", soit "cartes" si on souhaite retrouver une liste du fichier "cartes.csv".
	 * @param chemin Chemin vers le fichier "monopoly.csv" ou "cartes.csv"
	 * @return La liste demandee, avec chaque element separe par un ";" du fichier CSV.
	 */
	public List instanciation_liste_cartes(String type, String chemin) {
		List liste=null;
		try {
			//On utilise CSVReader (Sous Licence Apache 2.0) afin de simplifier la tâche.
			CSVReader csv = new CSVReader(new InputStreamReader(new FileInputStream(chemin), "UTF-8"), ';');;
			String[] next_line=null;		
			try {
				next_line=csv.readNext();
				//Si le type du csv est de type "cartes"
				if(type=="cartes") {
					//On cree une liste de "cartes"
					liste = new ArrayList<Carte_Model>();
					//Tant qu'il y a une valeur disponible dans le fichier CSV...
					while((next_line = csv.readNext()) != null) {

						//On recupere les differentes informations.
						int numero = Integer.parseInt(next_line[0]);
						String nom = next_line[1];
						String intitule = next_line[2];
						String evenement = next_line[3];
						String parametres = next_line[4];		
						//Et on ajoute l'element à la liste
						liste.add(new Carte_Model(numero, nom, intitule, evenement, parametres));
					}
				}
				//Si le type du csv est de type "monopoly"
				if(type=="monopoly") {
					//On cree une liste de cases monopoly
					liste = new ArrayList<Monopoly_Model>();
					//Tant qu'il y a une valeur disponible dans le fichier CSV...
					while((next_line = csv.readNext()) != null) {
						
						//On recupere les differentes informations, en evitant les problemes dûs aux "cast" et au valeurs vides.
						String loyer = null;
						int numero = Integer.parseInt(next_line[0]);
						String nom = next_line[1];
						String evenement = next_line[2];
						String type_evenement = next_line[3];
						String groupe = next_line[4];
						int achat, cout_immobilier;
						System.out.print(next_line[5]);
						if(next_line[5].equals("")) {
							achat=0;
						} else {
							achat=Integer.parseInt(next_line[5]);
						}
						if(next_line[6].equals("")) {
							cout_immobilier = 0;
						} else {
							cout_immobilier = Integer.parseInt(next_line[6]);
							loyer = next_line[7];
						}
						if(next_line[4].equals("gares"))
							loyer = next_line[7];
						//Puis on ajoute la case à la liste
						liste.add(new Monopoly_Model(numero, nom, evenement, type_evenement, groupe, achat, cout_immobilier, loyer));
					}
				}
				
				//Differentes erreurs possibles.
			} catch (IOException e) {
				System.out.println("Probleme de lecture");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Probleme d'acces au CSV");
		} catch (Exception e){
			System.out.println("Probleme d'encodage des caracteres.");		
		}
		return liste;
	}
}
