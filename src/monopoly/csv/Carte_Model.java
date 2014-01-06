package monopoly.csv;

public class Carte_Model {

	private int numero;
	private String groupe;
	private String intitule;
	private String evenement;
	private String parametres;
	
	public Carte_Model(int numero, String groupe, String intitule, String evenement, String parametres) {
		this.numero=numero;
		this.groupe=groupe;
		this.intitule=intitule;
		this.evenement=evenement;
		this.parametres=parametres;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getEvenement() {
		return evenement;
	}

	public void setEvenement(String evenement) {
		this.evenement = evenement;
	}

	public String getParametres() {
		return parametres;
	}

	public void setParametres(String parametres) {
		this.parametres = parametres;
	}
	
	public String toString() {
		return getNumero()+";"+getGroupe()+";"+getIntitule()+";"+getEvenement()+";"+getParametres();
	}
}
