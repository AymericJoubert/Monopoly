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
/**
 * Classe Plateau, l'interface graphique du Monopoly, celle-ci implémente ActionListener
 * @author Aymeric Joubert / Axel Delerue
 *
 */

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

	/**
	 * Instanciation de la classe Plateau, celle-ci ayant deux paramètres.
	 * La classe Plateau correspond au plateau graphique du jeu.
	 * @param type Soit dans ce cas précis "monopoly", cela permet d'indiquer que nous utiliserons le fichier "monopoly.csv".
	 * @param fichier Soit dans ce cas précis, le chemin d'accès au fichier "monopoly.csv" (De base "config/monopoly.csv").
	 */
	public Plateau(String type, String fichier){
		this.type = type;
		this.fichier = fichier;
		fenetre = new JFrame("Monopoly");
		Game=this;
		//Le joueur en cours est le premier de la liste
		joueurEnCours = Player.joueurs.get(0);
		cptTour=0;
		joue=0;
		lancer = new JButton("Lancer les des !");
		lancer.addActionListener(this);
		make();
	}
	
	/**
	 * Permet la récupération d'un Itérator parcourant les cases du plateau.
	 * @return Itérator des JPanels contenant les cases du plateau.
	 */
	public Iterator<JPanel> cases_du_plateau() {
		Iterator<JPanel> ite = cases.iterator();
		return ite;
	}


	/**
	 * Permet la création des différents JPanels, ceux-ci étant placés dans une GridBag.
	 * Cette méthode permet de créer les objets visuels.
	 */
	public void make(){
		fenetre.setResizable(true);
		fenetre.setExtendedState(fenetre.MAXIMIZED_BOTH);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel plate = new JPanel();
		//On définit en Layout le GridBagLayout
		plate.setLayout(new GridBagLayout());
		//Lecture du fichier de configuration du plateau
		try{
			Carte_CSV carte = new Carte_CSV();

			//Implémentation dans le plateau
			ArrayList<Monopoly_Model> plateau = (ArrayList<Monopoly_Model>)carte.instanciation_liste_cartes(type, fichier);
			Monopoly_Model plat;

			GridBagConstraints gbc = new GridBagConstraints();
			//On positionne la case de départ du composant
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
			
			//Permet de stocker les différents JPanel dans un global (celui-ci)
			JPanel interieur = new JPanel(new BorderLayout());
			
			//Permet d'afficher les différents éléments de jeu pour chaque personne, comme l'argent, le 
			JPanel infosJeu = new JPanel(new GridLayout(1,3));
			
			//Permet d'indiquer quel est le joueur courant
			JPanel infoUser = new JPanel(new GridLayout(1,1));
			
			//Permet le placement des différentes images au centre du plateau
			JPanel deco = new JPanel(new GridLayout(1,3));
			deco.setBackground(new Color(204,229,218));
			
			//Image principale, au centre du plateau de jeu.
			ImageIcon monopoly = new ImageIcon(new ImageIcon("monopoly.jpg").getImage().getScaledInstance(230, 300, Image.SCALE_DEFAULT));
		
			//On ajoute les différentes images, "chance", "monopoly" et "communaute"
			deco.add(new JLabel(new ImageIcon("chance.gif")));
			deco.add(new JLabel(monopoly));
			deco.add(new JLabel(new ImageIcon("communaute.gif")));	
			
			//Argent que l'utilisateur courant possède
			this.argent = new JLabel("Argent : "+joueurEnCours.especes());
			
			//La valeur des dès que l'utilisateur aura tiré sera affiché dans ce JLabel
			this.des = new JLabel("Dès : 0");
			
			//On affiche aussi le joueur courant, au dessus du milieu du plateau (En gros, au dessus des trois images)
			this.joueurcourant = new JLabel("Joueur courant : "+joueurEnCours.nom());
			des.setHorizontalAlignment(JTextField.RIGHT);
			
			//On ajoute tout pour ensuite l'attribuer au plateau
			infosJeu.add(argent);
			infosJeu.add(lancer);
			infosJeu.add(des);
			infoUser.add(joueurcourant);
			
			//On place tout à l'"intérieur".
			interieur.add(infoUser, BorderLayout.NORTH);
			interieur.add(deco, BorderLayout.CENTER);
			interieur.add(infosJeu, BorderLayout.SOUTH);
			
			//On place le tout dans le plateau.
			plate.add(interieur, gbc);
		}catch(Exception e){
			e.printStackTrace();
		}
		//On peut afficher la fenêtre avec le plateau de jeu créé.
		fenetre.setContentPane(plate);
		fenetre.setVisible(true);
	}

	/**
	 * Permet d'affecté une valeur au JLabel où est stocké la valeur de dès que l'utilisateur vient de tirer.
	 * @param valeur La valeur des dès
	 */
	public void attribuerDes(int valeur) {
		this.des.setText("Dès : "+valeur);
		make();
	}

	/**
	 * Permet de mettre la valeur des différentes case en HTML et donc de profiter du saut à la ligne, celui-ci n'étant pas possible avec "\n" dans le JLabel.
	 * La case peut déborder de texte si cette méthode n'est pas affectée pour la création de chaque case. 
	 * @param p Quelle est la chaine de caractère a "caster" en html ?
	 * @param p2 Au bout de combien de mots se fait la coupure ?
	 * @return
	 */
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

	
	/**
	 * Permet de récupérer un objet de type Color en mettant en paramètre le nom d'une couleur.
	 * Cela permet d'attribuer la bonne couleur à chaque case du plateau.
	 * @param p Correspond à la couleur voulue (Celle-ci étant donnée dans une chaine de caractère)
	 * @return La couleur voulue par l'utilisateur via le paramètre
	 */
	public Color couleur(String p){
		//Si la couleur est "jaune"
		if( p.equals("jaune"))
			return Color.YELLOW;
		else
			//Si la couleur est "bleu roi"
			if (p.equals("bleu roi"))
				return Color.BLUE;
			else
				//Si la couleur est "vert"
				if(p.equals("vert"))
					return Color.GREEN;
				else
					//Si la couleur est "bleu ciel"
					if(p.equals("bleu ciel"))
						return Color.CYAN;
					else
						//Si la couleur est "mauve"
						if(p.equals("mauve"))
							return Color.PINK;
						else
							//Si la couleur est "violet"
							if(p.equals("violet"))
								return new Color(238,55,119);
							else
								//Si la couleur est "orange"
								if(p.equals("orange"))
									return Color.ORANGE;
								else
									//Si la couleur est "rouge"
									if(p.equals("rouge"))
										return Color.RED;		
		//Sinon, on retourne une couleur de case basique.
		return new Color(204,229,218);
	}

	/**
	 * Permet l'exécution du "jeu" à proprement parler.
	 */
	public void jeu(){
		int cptJoueurs;
		boolean tourr = true;

		//Si le joueur n'est pas éliminé que son tour continue
		if(!joueurEnCours.elimine() && tourr==true){
			//On fait jouer le joueur en cours
			jouer(joueurEnCours);					
		}
		tourr=false;
		cptJoueurs=0;
		//Pour chaque joueurs de la liste des joueurs
		for (Joueur j : Player.joueurs){
			//Si le joueur n'est pas éliminé, on incrémente le compteur.
			if (!j.elimine()){
				cptJoueurs++;
			}
		}
		//Si il y a plus d'un joueur
		if(cptJoueurs > 1)
			tourr=true;
		joue++;
		joueurEnCours = Player.joueurs.get(joue%Player.nbJoueurs);
		cptTour++;
		make();

	}

	/**
	 * Exécute la pile d'événements pour l'utilisateur, tout en lui rajoutant évidemment un tirage de dès afin de lui permettre d'avancer.
	 * @param j Le joueur courant généralement (Méthode appelé via jeu()), sinon le joueur qu'il faut faire jouer.
	 */
	public void jouer(Joueur j){
		j.chosesAFaire().push(((Evenement)new TireDes(j)));
		//Tant que la liste des choses à faire du joueur n'est pas vide.
		while (!j.chosesAFaire().isEmpty()){
			//On exécute la pile de choses à faire.
			j.chosesAFaire().pop().executer();
			//On affiche le dernier lancé de l'utilisateur.
			this.des.setText(((Integer)TireDes.DernierLancer).toString());
		}
	}
	
	public void actionPerformed(ActionEvent e){
		jeu();
	}
}