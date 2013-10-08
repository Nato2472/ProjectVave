package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

	/*
	 * Attributs
	 */
	private String url;
	private String id;
	private String mdp;
	private Connection connexion = null;
	/*
	 * constructeur
	 */
	public DatabaseHelper() {
		super();
		url = "jdbc:jtds:sqlserver://217.128.202.143:1433/Vave";
		id = "sa";
		mdp = "Mobile2013";
	}

	/*
	 * Methods
	 */
	/**
	 * Créé la connection à la base de donnée
	 */
	public void ConnectionOpen() {
		try {
			// chargement du driver de connection à SQLSERVER
			Class.forName("net.sourceforge.jtds.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("Impossible de trouver le driver!");
		}

		connexion = null;

		try {
			connexion = DriverManager.getConnection(url, id, mdp);
			System.out.println("Connexion établie");

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
		
	/**
	 * Ferme la connection à la base de donnée.
	 */
	public void ConnectionClose() {
		if (connexion != null) {
			try {
				connexion.close();
				connexion = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Exécute une query et renvoit un resultset
	 */
	public ResultSet executeQuery(String query){
		ResultSet rs = null;
		Statement stm = null;
		
		this.ConnectionOpen();
		
		try {
			stm = connexion.createStatement();
			rs = stm.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;
	}
	
	/**
	 * Exécute un update
	 */
	public void executeUpdate(String query){
		Statement stm = null;
		
		this.ConnectionOpen();
		
		try {
			stm = connexion.createStatement();
			stm.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.ConnectionClose();
		}
	}
	
	/**
	 * Update avec Prepared Statement pour Evaluation
	 */
	public boolean UpdateEval(Evaluation ev){
		this.ConnectionOpen();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = connexion.prepareStatement("INSERT INTO EVALUATION (Date_Eva,Nom_Eva,Note_Eva,Com_Cour_Eva,Com_Long_Eva,Autre_Eva,Id_Uti,Id_Eta,Id_Cate) VALUES ((?),(?),(?),(?),(?),(?),(?),(?),(?))");
			// Creation de la date actuelle, et conversion pour la mettre dans la BDD
			java.util.Date now = new java.util.Date();
			java.sql.Date NOW = new java.sql.Date(now.getTime());
			
			pstmt.setDate(1, NOW); // date actuelle
			pstmt.setObject(2, ev.getNom());
			pstmt.setObject(3, ev.getNote());
			pstmt.setObject(4, ev.getComCourt());
			pstmt.setObject(5, ev.getComLong());
			pstmt.setObject(6, ev.getAutreEva());
			pstmt.setObject(7, ev.getId_uti());
			pstmt.setObject(8, ev.getId_eta());
			pstmt.setObject(9, ev.getId_cate());
			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erreur dans UpdateEval");
			return false;
		} finally{
			this.ConnectionClose();
		}
	}
	
	public ResultSet QueryLogin(String login, String password){
		this.ConnectionOpen();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = connexion.prepareStatement("SELECT Login_Uti, Prenom_Uti, Nom_Uti, Pseudo_Uti, Date_Inscrip_Uti ,Id_Uti FROM UTILISATEUR WHERE Login_Uti = ? AND Mdp_Uti= ?;");
			pstmt.setObject(1, login);
			pstmt.setObject(2, password);
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erreur dans QueryLogin");
		}
		return rs;
	}
	
	public ResultSet CheckIfExistLogin(String login, String pseudo){
		this.ConnectionOpen();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = connexion.prepareStatement("SELECT count(Login_Uti) FROM UTILISATEUR WHERE Login_Uti = ? AND Pseudo_Uti = ?");
			pstmt.setObject(1, login);
			pstmt.setObject(2, pseudo);
			
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erreur dans QueryLogin");
		}
		return rs;
	}
	
	public ResultSet QueryRegister(String login, String firstname, String lastname,String pseudo, String pass1){
		this.ConnectionOpen();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = connexion.prepareStatement("INSERT INTO UTILISATEUR (Login_Uti, Prenom_Uti, Nom_Uti, Pseudo_Uti, Mdp_Uti, Date_Inscrip_Uti) VALUES (?, ?, ?, ?, ?, ?)");
			pstmt.setObject(1, login);
			pstmt.setObject(2, firstname);
			pstmt.setObject(3, lastname);
			pstmt.setObject(4, pseudo);
			pstmt.setObject(5, pass1);
			// Creation de la date actuelle, et conversion pour la mettre dans la BDD
			java.util.Date now = new java.util.Date();
			java.sql.Date NOW = new java.sql.Date(now.getTime());
			pstmt.setDate(6, NOW); // date actuelle

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erreur dans QueryLogin");
		}
		return rs;
	}
}
