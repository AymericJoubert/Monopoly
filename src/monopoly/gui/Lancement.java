package monopoly.gui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import monopoly.csv.Carte_CSV;
import monopoly.csv.Carte_Model;
import monopoly.csv.Monopoly_Model;
import monopoly.evenements.Achat;
import monopoly.evenements.Carte;
import monopoly.evenements.Depenser;
import monopoly.evenements.Emprisonnement;
import monopoly.evenements.Evenement;
import monopoly.evenements.Recette;
import monopoly.evenements.TirerCarte;
import monopoly.jeu.Cases;
import monopoly.jeu.Joueur;
import monopoly.jeu.Player;
import monopoly.proprietes.Compagnie;
import monopoly.proprietes.Gare;
import monopoly.proprietes.Groupes;
import monopoly.proprietes.Propriete;
import monopoly.proprietes.Terrain;
/**
 * Classe Lancement qui etend JFrame et implemente ActionListener
 * @author Aymeric Joubert / Axel Delerue
 *
 */
public class Lancement extends JFrame implements ActionListener {
	
	String mono;
	String cartesMono;
	private JLabel liste_joueurs = new JLabel();
	private JTextField nomJoueur = new JTextField();
	private JButton ajoutJoueur = new JButton("Ajouter");
	private JButton jouer = new JButton("Jouer");
	private int cpt=0;
	private Plateau p;

	/**
	 * La methode Lancement permet l'execution du jeu
	 * @param mono Fichier CSV permettant l'instanciation des cases du Monopoly
	 * @param cartesMono Fichier CSV permettant l'instanciation des cartes du Monopoly
	 */
	public Lancement(String mono, String cartesMono){
		super("Monopoly");
		this.mono = mono;
		this.cartesMono = cartesMono;
		JPanel menu = new JPanel(new GridLayout(4,1));
		menu.add(liste_joueurs);
		menu.add(nomJoueur);
		menu.add(ajoutJoueur);
		menu.add(jouer);
		this.setSize(300,300);
		this.setLocation(300,200);
		this.setContentPane(menu);
		this.setVisible(true);
		ajoutJoueur.addActionListener(this);
		jouer.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Permet la creation du plateau et donc du visuel pour l'utilisateur
	 */
	public void creerGui(){
		this.p = new Plateau("monopoly", mono);
		//this.p.joueurcourant.setText("Joueur courant : "+Player.joueurs.get(0).nom());
	}

	/**
	 * Permet la creation des cartes pour le jeu, celui-ci prenant en compte cartesMono, le parametre determinant l'emplacement du fichier cartes.csv
	 */
	public void creerCartes(){
		Carte_CSV carte = new Carte_CSV();
		ArrayList<Carte_Model> cartes = (ArrayList<Carte_Model>)carte.instanciation_liste_cartes("cartes", cartesMono);
		int cpt=0;
		for(Carte_Model carteCourante : cartes){
			//Si la carte est une recette
			if(carteCourante.getEvenement().equals("recette")){
				Recette r = new Recette(carteCourante.getIntitule(), null, Integer.parseInt(carteCourante.getParametres()));
				Carte c = new Carte(carteCourante.getIntitule(), carteCourante.getGroupe(), (Evenement)r);
			}
			// Si la carte est une depense
			else if(carteCourante.getEvenement().equals("depense")){
				Depenser d = new Depenser(carteCourante.getIntitule(), null, Integer.parseInt(carteCourante.getParametres()));
			}
			// Si la carte est un choix
			else if(carteCourante.getEvenement().equals("choix")){

			}
			// Si la carte est une carte prison
			else if(carteCourante.getEvenement().equals("prison")){
				Emprisonnement e = new Emprisonnement(null);
			}
			// Si la carte est une carte bonus
			else if(carteCourante.getEvenement().equals("bonus")){

			}
			// Si la carte est une carte de frais immobilies
			else if(carteCourante.getEvenement().equals("frais immo")){

			}
			// Si la carte est une carte "aller à x cases" ou "revenir de x cases"
			else if(carteCourante.getEvenement().equals("aller") || carteCourante.getEvenement().equals("revenir")){

			}
			cpt++;
		}

	}

	/**
	 * Permet la creation des groupes, celle-ci repose sur le fichier "monopoly.csv", donne lui aussi en parametre lors de l'instanciation (mono)
	 */
	public void creerGroupes()
	{
		Carte_CSV carte = new Carte_CSV();
		ArrayList<Monopoly_Model> list = (ArrayList<Monopoly_Model>)carte.instanciation_liste_cartes("monopoly", mono);
		for (Monopoly_Model mono : list)
		{
			String groupe = mono.getGroupe();
			if(Groupes.groupes.get(groupe) == null){
				new Groupes(groupe, mono.getCout_immobilier());
			}
		}
	}

	/**
	 * Permet la creation des cases, celle-ci repose egalement sur le fichier "monopoly.csv" 
	 */
	public void creerCases(){
		Carte_CSV carte = new Carte_CSV();
		int cpt=0; int cpt2;
		Cases c;
		//Implementation dans le plateau
		ArrayList<Monopoly_Model> plateau = (ArrayList<Monopoly_Model>)carte.instanciation_liste_cartes("monopoly", mono);
		//On parcourt chaque case du plateau
		for(Monopoly_Model cas : plateau){
			//Si la case est une case "terrain"
			if(cas.getType_evenement().equals("terrain")){
				int[] loyer = new int[6];
				cpt2=0;
				for(String s : cas.getLoyer().split(",")){
					loyer[cpt2] = Integer.parseInt(s);
					cpt2++;
				}
				//On cree un nouveau terrain
				Terrain t = new Terrain(cpt, null, cas.getNom(), cas.getAchat(), Groupes.groupes.get(cas.getGroupe()), cas.getCout_immobilier(), loyer);
				c = new Cases(cpt, cas.getNom(), (Propriete) t, (Evenement) new Achat(cas.getNom(), null, t));
				t.setCase(c);
			}
			else{
				//Si la case est une case de type "gares"
				if(cas.getGroupe().equals("gares")){
					//On cree une nouvelle gare
					Gare g = new Gare(cpt, null, cas.getNom(), cas.getAchat(), Groupes.groupes.get("gares"), 2500);
					c = new Cases(cpt, cas.getNom(), (Propriete) g, (Evenement) new Achat(cas.getNom(), null, g));
					g.setCase(c);
				}
				else{
					//Si la case est une case de type "compagnies"
					if(cas.getGroupe().equals("compagnies")){
						//On cree une nouvelle compagnie
						Compagnie co = new Compagnie(cpt, null, cas.getNom(), cas.getAchat(), Groupes.groupes.get("compagnies"));
						c = new Cases(cpt, cas.getNom(), (Propriete) co, (Evenement) new Achat(cas.getNom(), null, co));
						co.setCase(c);
					}
					else{
						//Si la case est une case de type "Prison"
						if(cas.getNom().equals("Allez en prison")){
							c = new Cases(cpt, cas.getNom(), null, (Evenement) new Emprisonnement(null));
						}
						else{
							//Si la case est une case de type "Chance"
							if(cas.getNom().equals("Chance")){
								c = new Cases(cpt, cas.getNom(), null, (Evenement) new TirerCarte(null, "chance"));
							}
							else{
								//Si la case est une case de type "Caisse de communaute"
								if(cas.getNom().equals("Caisse de communaute")){
									c = new Cases(cpt, cas.getNom(), null, (Evenement) new TirerCarte(null, "communaute"));
								}
								else{
									//Si la case est une case de type "Impôts sur le revenu"
									if(cas.getNom().equals("Impôts sur le revenu")){
										c = new Cases(cpt, cas.getNom(), null, (Evenement) new Depenser("Revenu", null, Integer.parseInt(cas.getEvenement().split(",")[1])));
									}
									else{
										//Si la case est une case de type "Compagnie de distribution d'electricite"
										if(cas.getNom().equals("Compagnie de distribution d'electricite")){
											c = new Cases(cpt, cas.getNom(), null, (Evenement) new Depenser("Electicite", null, Integer.parseInt(cas.getEvenement().split(",")[1])));
										}
										else{
											//Si la case n'a pas de type, on la creee sans Evenement
											c = new Cases(cpt, cas.getNom(), null, null);
										}
									}
								}
							}
						}
					}
				}

			}
			cpt++;
		}
	}

	/**
	 * Methode main, instanciant Lancement et permettant du coup l'execution du jeu
	 * @param args Le premier argument est le chemin du fichier "monopoly.csv" (config/monopoly.csv), le deuxieme le chemin du fichier "cartes.csv" (config/cartes.csv) 
	 */
	public static void main(String [] args){
		Lancement c = new Lancement(args[0], args[1]);
	}

	
	public void actionPerformed(ActionEvent e){
		cpt++;
		
		//Si l'evenement recupere est de nom "Ajouter"
		if(e.getActionCommand().equals("Ajouter")){
			if(!this.nomJoueur.getText().equals("")) {
				
				//Ajouter un joueur à la liste des joueurs (static)
				new Player(cpt, nomJoueur.getText(), 150000, null);
				Iterator<Joueur> ite_liste_joueurs = Player.getIterator_Joueurs();
				
				//On redefinie le fond de la liste des joueurs en noir, si jamais il y a eu une pression sur Jouer sans joueurs dans la liste
				this.liste_joueurs.setForeground(Color.BLACK);
				this.liste_joueurs.setHorizontalAlignment(SwingConstants.CENTER);
				
				//On cree la liste des joueurs visible pour l'utilisateur
				String liste_des_joueurs_string = "<html>";
				while(ite_liste_joueurs.hasNext()) {
					liste_des_joueurs_string+=ite_liste_joueurs.next().nom()+"<br>";
				}
				liste_des_joueurs_string+="</html>";
				
				//Et on montre la liste des joueurs à l'utilisateur
				liste_joueurs.setText(liste_des_joueurs_string);
				//Le nom du joueur est remis à 0, afin de permettre la rentree d'un nouvel utilisateur
				nomJoueur.setText("");
				
				//Des joueurs sont presents, nous pouvons jouer (Activation du bouton)
				this.jouer.setEnabled(true);
			}
		}
		else{
			//Si la liste des joueurs n'est pas vide
			if(!Player.joueurs.isEmpty()) {
				this.setVisible(false);
				//On cree les groupes
				creerGroupes();
				//On cree les cases
				creerCases();
				//On cree les cartes
				creerCartes();
				
				//On place les joueurs sur la case depart
				for(Joueur p : Player.joueurs){
					p.placerSur(Cases.cases.get(0));
				}
				//Et on cree l'interface
				creerGui();
				
				//Si la liste est vide ..
			} else {
				//On met le fond de la liste des joueurs en rouge
				this.liste_joueurs.setForeground(Color.RED);
				//Tout en le centrant
				this.liste_joueurs.setHorizontalAlignment(SwingConstants.CENTER);
				//Et on fait apparaître le message d'erreur
				this.liste_joueurs.setText("Joueur(s) OBLIGATOIRE");
				//La validation est donc impossible avant l'ajout d'un joueur
				this.jouer.setEnabled(false);
			}
		}
	}
}
