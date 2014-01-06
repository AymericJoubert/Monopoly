package monopoly.listenner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import monopoly.evenements.*;
import monopoly.gui.Plateau;
import monopoly.jeu.Player;

public class Partie implements ActionListener {

	private int perso_courant=0;
	Plateau p;
	
	public Partie(Plateau p) {
		this.p=p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Lancer !")){
			TireDes tire = new TireDes(Player.joueurs.get(perso_courant));
			tire.executer();
			this.p.attribuerDes(TireDes.nbCases);
			
			
			/*tire.executer();
			Iterator<JPanel> ite = p.cases_du_plateau();
			while(ite.hasNext()) {
		          JComponent compo = (JComponent)ite.next().getComponent(0);            
		            if (compo.getClass().equals(JLabel.class)) {
		                      System.out.println(((JLabel)compo).getText());               
		            }
			}*/
			
		}
		
	}

}
