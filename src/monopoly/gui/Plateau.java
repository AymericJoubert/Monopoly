package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import monopoly.csv.Carte_CSV;
import monopoly.csv.Monopoly_Model;
import monopoly.evenements.Evenement;
import monopoly.evenements.TireDes;
import monopoly.jeu.Case;
import monopoly.jeu.Joueur;
import monopoly.jeu.Player;

/** Cette classe est une interface graphique qui repr√©sente le plateau de jeu */
public class Plateau implements ActionListener{
	ArrayList<JPanel> cases = new ArrayList<JPanel>();
	JLabel des, joueurcourant, argent;
	String type, fichier;
	private JFrame fenetre;
	public static Plateau Game;
	Joueur joueurEnCours;
	int cptTour;
	int joue;
	JButton lancer;

	public Iterator<JPanel> cases_du_plateau() {
		Iterator<JPanel> ite = cases.iterator();
		return ite;
	}

	public Plateau(String type, String fichier){
		this.type = type;
		this.fichier = fichier;
		fenetre = new JFrame("Monopoly");
		Game=this;
		joueurEnCours = Player.joueurs.get(0);
		cptTour=0;
		joue=0;
		lancer = new JButton("Lancer les des !");
		lancer.addActionListener(this);
		init();
	}
	public void init(){
		fenetre.setResizable(true);
		fenetre.setExtendedState(fenetre.MAXIMIZED_BOTH);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel plate = new JPanel();
		plate.setLayout(new GridBagLayout());
		//Lecture du fichier de configuration du plateau
		try{
			Carte_CSV carte = new Carte_CSV();

			//Implementation dans le plateau
			ArrayList<Monopoly_Model> plateau = (ArrayList<Monopoly_Model>)carte.instanciation_liste_cartes(type, fichier);
			Monopoly_Model plat;

			GridBagConstraints gbc = new GridBagConstraints();
			//On positionne la case de d√©part du composant
			gbc.gridx = 0;
			gbc.gridy = 10;
			//Le nombre de case en hauteur et en largeur qu'une case occupe
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.weightx = 1;
			gbc.weighty = 1;
			int cpt=0;
			for(int i=0; i<11; i++){
				JPanel jj = new JPanel(new BorderLayout());
				JPanel nomRue = new JPanel(new GridBagLayout());
				nomRue.setBackground(new Color(204,229,218));
				jj.setBackground(new Color(204,229,218));
				jj.setBorder(BorderFactory.createLineBorder(Color.black));
				plat = plateau.get(cpt);
				jj.add(new JLabel(affiche(plat.getNom(), plat.getAchat())));
				JPanel couleur = new JPanel();
				if(plat.getType_evenement().equals("terrain")){
					couleur.setBackground(couleur(plat.getGroupe()));
					jj.add(couleur, BorderLayout.EAST);							
				}
				else{
					if(plat.getGroupe().equals("gares"))
						couleur.setBackground(Color.BLACK);
					else
						couleur.setBackground(couleur(plat.getGroupe()));
					jj.add(couleur, BorderLayout.EAST);	
				}
				nomRue.add(new JLabel(affiche(plat.getNom(), plat.getAchat())));
				jj.add(nomRue, BorderLayout.CENTER);
				String st = "<html>";
				for(Joueur j : Player.joueurs){
					if(j.position().numero() == cpt){
						st+=j.nom()+"<br/>";
					}
				}
				JLabel jo = new JLabel(st);
				jo.setForeground(Color.RED);
				jj.add(jo, BorderLayout.WEST);
				plate.add(jj, gbc);
				cases.add(jj);
				gbc.gridy-=1;
				cpt++;
			}

			gbc.gridx = 1;
			gbc.gridy = 0;
			for(int i=0; i<10; i++){
				JPanel jj = new JPanel(new BorderLayout());
				JPanel nomRue = new JPanel(new GridBagLayout());
				nomRue.setBackground(new Color(204,229,218));
				jj.setBackground(new Color(204,229,218));
				jj.setBorder(BorderFactory.createLineBorder(Color.black));
				plat = plateau.get(cpt);
				jj.add(new JLabel(affiche(plat.getNom(), plat.getAchat())));
				JPanel couleur = new JPanel();
				if(plat.getType_evenement().equals("terrain")){
					couleur.setBackground(couleur(plat.getGroupe()));
					jj.add(couleur, BorderLayout.SOUTH);							
				}
				else{
					if(plat.getGroupe().equals("gares"))
						couleur.setBackground(Color.BLACK);
					else
						couleur.setBackground(couleur(plat.getGroupe()));
					jj.add(couleur, BorderLayout.SOUTH);	
				}
				nomRue.add(new JLabel(affiche(plat.getNom(), plat.getAchat())));
				jj.add(nomRue, BorderLayout.CENTER);
				String st = "<html>";
				for(Joueur j : Player.joueurs){
					if(j.position().numero() == cpt){
						st+=j.nom()+"<br/>";
					}
				}
				JLabel jo = new JLabel(st);
				jo.setForeground(Color.RED);
				jj.add(jo, BorderLayout.NORTH);
				plate.add(jj, gbc);
				cases.add(jj);
				gbc.gridx+=1;
				cpt++;
			}
			gbc.gridx = 10;
			gbc.gridy = 1;
			for(int i=0; i<10; i++){
				JPanel jj = new JPanel(new BorderLayout());
				JPanel nomRue = new JPanel(new GridBagLayout());
				nomRue.setBackground(new Color(204,229,218));
				jj.setBackground(new Color(204,229,218));
				jj.setBorder(BorderFactory.createLineBorder(Color.black));
				plat = plateau.get(cpt);
				jj.add(new JLabel(affiche(plat.getNom(), plat.getAchat())));
				JPanel couleur = new JPanel();
				if(plat.getType_evenement().equals("terrain")){
					couleur.setBackground(couleur(plat.getGroupe()));
					jj.add(couleur, BorderLayout.WEST);							
				}
				else{
					if(plat.getGroupe().equals("gares"))
						couleur.setBackground(Color.BLACK);
					else
						couleur.setBackground(couleur(plat.getGroupe()));
					jj.add(couleur, BorderLayout.WEST);	
				}
				nomRue.add(new JLabel(affiche(plat.getNom(), plat.getAchat())));
				jj.add(nomRue, BorderLayout.CENTER);
				String st = "<html>";
				for(Joueur j : Player.joueurs){
					if(j.position().numero() == cpt){
						st+=j.nom()+"<br/>";
					}
				}
				JLabel jo = new JLabel(st);
				jo.setForeground(Color.RED);
				jj.add(jo, BorderLayout.EAST);
				plate.add(jj, gbc);
				cases.add(jj);
				gbc.gridy+=1;
				cpt++;
			}
			gbc.gridx = 9;
			gbc.gridy = 10;
			for(int i=0; i<9; i++){
				JPanel jj = new JPanel(new BorderLayout());
				JPanel nomRue = new JPanel(new GridBagLayout());
				nomRue.setBackground(new Color(204,229,218));
				jj.setBackground(new Color(204,229,218));
				jj.setBorder(BorderFactory.createLineBorder(Color.black));
				plat = plateau.get(cpt);
				jj.add(new JLabel(affiche(plat.getNom(), plat.getAchat())));
				JPanel couleur = new JPanel();
				if(plat.getType_evenement().equals("terrain")){
					couleur.setBackground(couleur(plat.getGroupe()));
					jj.add(couleur, BorderLayout.NORTH);							
				}
				else{
					if(plat.getGroupe().equals("gares"))
						couleur.setBackground(Color.BLACK);
					else
						couleur.setBackground(couleur(plat.getGroupe()));
					jj.add(couleur, BorderLayout.NORTH);	
				}
				nomRue.add(new JLabel(affiche(plat.getNom(), plat.getAchat())));
				jj.add(nomRue, BorderLayout.CENTER);
				String st = "<html>";
				for(Joueur j : Player.joueurs){
					if(j.position().numero() == cpt){
						st+=j.nom()+"<br/>";
					}
				}
				JLabel jo = new JLabel(st);
				jo.setForeground(Color.RED);
				jj.add(jo, BorderLayout.SOUTH);
				plate.add(jj, gbc);
				cases.add(jj);
				gbc.gridx-=1;
				cpt++;
			}
			//Interieur de la fenetre
			gbc.gridx=1;
			gbc.gridy=1;
			gbc.gridheight = 9;
			gbc.gridwidth = 9;
			JPanel interieur = new JPanel(new BorderLayout());
			JPanel infosJeu = new JPanel(new GridLayout(1,3));
			JPanel deco = new JPanel(new GridLayout(1,3));
			deco.setBackground(new Color(204,229,218));
			ImageIcon monopoly = new ImageIcon(new ImageIcon("monopoly.jpg").getImage().getScaledInstance(230, 300, Image.SCALE_DEFAULT));
			deco.add(new JLabel(new ImageIcon("chance.gif")));
			deco.add(new JLabel(monopoly));
			deco.add(new JLabel(new ImageIcon("communaute.gif")));				
			this.argent = new JLabel("Argent : "+joueurEnCours.especes());
			this.des = new JLabel("DËs : 0");
			JPanel infoUser = new JPanel(new GridLayout(1,1));
			this.joueurcourant = new JLabel("Joueur courant : "+joueurEnCours.nom());
			des.setHorizontalAlignment(JTextField.RIGHT);
			infosJeu.add(argent);
			infosJeu.add(lancer);
			infosJeu.add(des);
			infoUser.add(joueurcourant);
			interieur.add(infoUser, BorderLayout.NORTH);
			interieur.add(deco, BorderLayout.CENTER);
			interieur.add(infosJeu, BorderLayout.SOUTH);
			plate.add(interieur, gbc);
		}catch(Exception e){
			e.printStackTrace();
		}
		fenetre.setContentPane(plate);
		fenetre.setVisible(true);
	}

	public void attribuerDes(int valeur) {
		this.des.setText("DËs : "+valeur);
		fenetre.revalidate();
	}

	public String affiche(String p, int p2){
		String ret = "<html><body><center> ";
		int cpt=0;
		String [] a= p.split(" ");
		for(String f : a){
			if(cpt%2!=0) ret+=" "+f+" <br/> ";
			else ret += " "+f+" ";
			cpt++;
		}
		if(p2!=0)
			ret+=" <br/> "+((Integer)p2).toString()+" F";
		return ret;
	}

	public void actionPerformed(ActionEvent e){
		jeu();
		this.cases.get(5).add(new JLabel("FIFOU"), BorderLayout.NORTH);
		fenetre.revalidate();
	}

	public Color couleur(String p){
		if( p.equals("jaune"))
			return Color.YELLOW;
		else
			if (p.equals("bleu roi"))
				return Color.BLUE;
			else
				if(p.equals("vert"))
					return Color.GREEN;
				else
					if(p.equals("bleu ciel"))
						return Color.CYAN;
					else
						if(p.equals("mauve"))
							return Color.PINK;
						else
							if(p.equals("violet"))
								return new Color(238,55,119);
							else
								if(p.equals("orange"))
									return Color.ORANGE;
								else
									if(p.equals("rouge"))
										return Color.RED;										
		return new Color(204,229,218);
	}

	public void jeu(){
		int cptJoueurs;
		boolean tourr = true;

		if(!joueurEnCours.elimine() && tourr==true){
			jouer(joueurEnCours);					
		}
		tourr=false;
		cptJoueurs=0;
		for (Joueur j : Player.joueurs){
			if (!j.elimine()){
				cptJoueurs++;
			}
		}
		if(cptJoueurs > 1)
			tourr=true;
		joue++;
		joueurEnCours = Player.joueurs.get(joue%Player.nbJoueurs);
		cptTour++;
		init();

	}

	public void jouer(Joueur j){
		j.chosesAFaire().push(((Evenement)new TireDes(j)));
		while (!j.chosesAFaire().isEmpty())
		{
			j.chosesAFaire().pop().executer();
			this.des.setText(((Integer)TireDes.DernierLancer).toString());
		}
	}
}