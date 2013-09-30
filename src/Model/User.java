package Model;

import java.util.Date;

public class User {

	private String nom;
	private String prenom;
	private String pseudo;
	private String login;
	private String mdp;
	private Date date;

	/*
	 * Getteur & Setteur
	 */
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/*
	 * Constructeur
	 */

	public User(String nom, String prenom, String login, String mdp, Date date) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
		this.date = date;
	}

	public User() {
		super();
	}

	/*
	 * Methode
	 */
}
