package Model;

import java.sql.Connection;
import java.sql.DriverManager;
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
	 * Cr�� la connection � la base de donn�e
	 */
	public void ConnectionOpen() {
		try {
			// chargement du driver de connection � SQLSERVER
			Class.forName("net.sourceforge.jtds.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("Impossible de trouver le driver!");
		}

		connexion = null;

		try {
			connexion = DriverManager.getConnection(url, id, mdp);
			System.out.println("Connexion �tablie");

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
		
	/**
	 * Ferme la connection � la base de donn�e.
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
	 * Ex�cute une query et renvoit un resultset
	 */
	public ResultSet executeQuery(String query){
		ResultSet rs = null;
		Statement stm = null;
		
		this.ConnectionOpen();
		
		try {
			stm = connexion.createStatement();
			rs = stm.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
	}
	
	/**
	 * Ex�cute un update
	 */
	public void executeUpdate(String query){
		Statement stm = null;
		
		this.ConnectionOpen();
		
		try {
			stm = connexion.createStatement();
			stm.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.ConnectionClose();
		}
	}
}
