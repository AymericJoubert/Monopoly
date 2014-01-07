package monopoly.jeu;

import monopoly.evenements.Evenement;
import monopoly.proprietes.Propriete;

public class Case_Model implements Case {
	
	private String nom;
	private int numero;
	private Propriete propriete;
	private Evenement evenement;
	
	public Case_Model(String nom, int numero, Propriete propriete, Evenement evenement) {
		this.nom=nom;
		this.numero=numero;
		this.propriete=propriete;
		this.evenement=evenement;
	}
	
	
	@Override
	public int numero() {
		return numero;
	}

	@Override
	public Case get(int numero) {
		return null;
	}

	@Override
	public String nom() {
		return nom;
	}

	@Override
	public Propriete propriete() {
		return propriete;
	}

	@Override
	public Evenement evenement() {
		return evenement;
	}

}
