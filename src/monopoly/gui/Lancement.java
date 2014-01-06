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

public class Lancement extends JFrame implements ActionListener {
	String mono;
	String cartesMono;
	private JLabel liste_joueurs = new JLabel();
	private JTextField nomJoueur = new JTextField();
	private JButton ajoutJoueur = new JButton("Ajouter");
	private JButton jouer = new JButton("Jouer");
	private int cpt=0;
	private Plateau p;

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

	public void actionPerformed(ActionEvent e){
		cpt++;
		if(e.getActionCommand().equals("Ajouter")){
			if(!this.nomJoueur.getText().equals("")) {
				new Player(cpt, nomJoueur.getText(), 150000, null);
				Iterator<Joueur> ite_liste_joueurs = Player.getIterator_Joueurs();
				this.liste_joueurs.setForeground(Color.BLACK);
				this.liste_joueurs.setHorizontalAlignment(SwingConstants.CENTER);
				String liste_des_joueurs_string = "<html>";
				while(ite_liste_joueurs.hasNext()) {
					liste_des_joueurs_string+=ite_liste_joueurs.next().nom()+"<br>";
				}
				liste_des_joueurs_string+="</html>";
				liste_joueurs.setText(liste_des_joueurs_string);
				nomJoueur.setText("");
				this.jouer.setEnabled(true);

			} else {

			}

		}
		else{
			if(!Player.joueurs.isEmpty()) {
				this.setVisible(false);
				creerGroupes();
				creerCases();
				creerCartes();
				for(Joueur p : Player.joueurs){
					p.placerSur(Cases.cases.get(0));
				}
				creerGui();
			} else {
				this.liste_joueurs.setForeground(Color.RED);
				this.liste_joueurs.setHorizontalAlignment(SwingConstants.CENTER);
				this.liste_joueurs.setText("Joueur(s) OBLIGATOIRE");
				this.jouer.setEnabled(false);
			}
		}
	}


	public void creerGui(){
		this.p = new Plateau("monopoly", mono);
		//this.p.joueurcourant.setText("Joueur courant : "+Player.joueurs.get(0).nom());
	}

	public void creerCartes(){
		Carte_CSV carte = new Carte_CSV();
		ArrayList<Carte_Model> cartes = (ArrayList<Carte_Model>)carte.instanciation_liste_cartes("cartes", cartesMono);
		int cpt=0;
		for(Carte_Model carteCourante : cartes){
			if(carteCourante.getEvenement().equals("recette")){
				Recette r = new Recette(carteCourante.getIntitule(), null, Integer.parseInt(carteCourante.getParametres()));
				Carte c = new Carte(carteCourante.getIntitule(), carteCourante.getGroupe(), (Evenement)r);
			}
			else if(carteCourante.getEvenement().equals("depense")){
				Depenser d = new Depenser(carteCourante.getIntitule(), null, Integer.parseInt(carteCourante.getParametres()));
			}
			else if(carteCourante.getEvenement().equals("choix")){

			}
			else if(carteCourante.getEvenement().equals("prison")){
				Emprisonnement e = new Emprisonnement(null);
			}
			else if(carteCourante.getEvenement().equals("bonus")){

			}
			else if(carteCourante.getEvenement().equals("frais immo")){

			}
			else if(carteCourante.getEvenement().equals("aller") || carteCourante.getEvenement().equals("revenir")){

			}
			cpt++;
		}

	}

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

	public void creerCases(){
		Carte_CSV carte = new Carte_CSV();
		int cpt=0; int cpt2;
		Cases c;
		//Implementation dans le plateau
		ArrayList<Monopoly_Model> plateau = (ArrayList<Monopoly_Model>)carte.instanciation_liste_cartes("monopoly", mono);
		for(Monopoly_Model cas : plateau){

			if(cas.getType_evenement().equals("terrain")){
				int[] loyer = new int[6];
				cpt2=0;
				for(String s : cas.getLoyer().split(",")){
					loyer[cpt2] = Integer.parseInt(s);
					cpt2++;
				}
				Terrain t = new Terrain(cpt, null, cas.getNom(), cas.getAchat(), Groupes.groupes.get(cas.getGroupe()), cas.getCout_immobilier(), loyer);
				c = new Cases(cpt, cas.getNom(), (Propriete) t, (Evenement) new Achat(cas.getNom(), null, t));
				t.setCase(c);
			}
			else{
				if(cas.getGroupe().equals("gares")){
					Gare g = new Gare(cpt, null, cas.getNom(), cas.getAchat(), Groupes.groupes.get("gares"), 2500);
					c = new Cases(cpt, cas.getNom(), (Propriete) g, (Evenement) new Achat(cas.getNom(), null, g));
					g.setCase(c);
				}
				else{
					if(cas.getGroupe().equals("compagnies")){
						Compagnie co = new Compagnie(cpt, null, cas.getNom(), cas.getAchat(), Groupes.groupes.get("compagnies"));
						c = new Cases(cpt, cas.getNom(), (Propriete) co, (Evenement) new Achat(cas.getNom(), null, co));
						co.setCase(c);
					}
					else{
						if(cas.getNom().equals("Allez en prison")){
							c = new Cases(cpt, cas.getNom(), null, (Evenement) new Emprisonnement(null));
						}
						else{
							if(cas.getNom().equals("Chance")){
								c = new Cases(cpt, cas.getNom(), null, (Evenement) new TirerCarte(null, "chance"));
							}
							else{
								if(cas.getNom().equals("Caisse de communauté")){
									c = new Cases(cpt, cas.getNom(), null, (Evenement) new TirerCarte(null, "communaute"));
								}
								else{
									if(cas.getNom().equals("Impôts sur le revenu")){
										c = new Cases(cpt, cas.getNom(), null, (Evenement) new Depenser("Revenu", null, Integer.parseInt(cas.getEvenement().split(",")[1])));
									}
									else{
										if(cas.getNom().equals("Compagnie de distribution d'éléctricité")){
											c = new Cases(cpt, cas.getNom(), null, (Evenement) new Depenser("Electicite", null, Integer.parseInt(cas.getEvenement().split(",")[1])));
										}
										else{
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

	public static void main(String [] args){
		Lancement c = new Lancement(args[0], args[1]);
	}
}
