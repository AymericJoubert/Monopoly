package monopoly.csv;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Carte_CSV {


	

	public List instanciation_liste_cartes(String type, String chemin) {
		List liste=null;
		try {
			CSVReader csv = new CSVReader(new InputStreamReader(new FileInputStream(chemin), "UTF-8"), ';');;
			String[] next_line=null;		
			try {
				next_line=csv.readNext();
				if(type=="cartes") {
					liste = new ArrayList<Carte_Model>();
					while((next_line = csv.readNext()) != null) {

						int numero = Integer.parseInt(next_line[0]);
						String nom = next_line[1];
						String intitule = next_line[2];
						String evenement = next_line[3];
						String parametres = next_line[4];					
						liste.add(new Carte_Model(numero, nom, intitule, evenement, parametres));
					}
				}
				
				if(type=="monopoly") {
					liste = new ArrayList<Monopoly_Model>();
					while((next_line = csv.readNext()) != null) {
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
						liste.add(new Monopoly_Model(numero, nom, evenement, type_evenement, groupe, achat, cout_immobilier, loyer));
					}
				}
				
			} catch (IOException e) {
				System.out.println("Problème de lecture");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Problème d'accès au CSV");
		} catch (Exception e){
			System.out.println("Probl�me d'encodage des caract�res.")	;		
		}
		return liste;
	}

/*	public void instanciation_liste_partie_joueurs() {
		try {
			CSVReader csv = new CSVReader(new FileReader(chemin_partie_joueurs), ';');;
			String[] next_line=null;		
			try {
				next_line=csv.readNext();
				while((next_line = csv.readNext()) != null) {
					int numero = Integer.parseInt(next_line[0]);
					String nom = next_line[1];
					String intitule = next_line[2];
					String evenement = next_line[3];
					String parametres = next_line[4];
					liste.add(new Carte_Model(numero, nom, intitule, evenement, parametres));
				}
			} catch (IOException e) {
				System.out.println("Problème de lecture");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Problème d'accès au CSV");
		}
	}*/
	
	
	
	
	
	
	
	/*public static void main(String[] args) {
		Carte_CSV test = new Carte_CSV();
		List<Carte_Model> list = test.instanciation_liste_cartes("monopoly","./config/monopoly.csv");
		Iterator<Carte_Model> ite = list.iterator();
		while(ite.hasNext()) {
			System.out.println(ite.next());
		}
		
	}*/

}
