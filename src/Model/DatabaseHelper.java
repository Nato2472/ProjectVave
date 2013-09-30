package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

	/*
	 * Attributs
	 */
	private String url;
	private String id;
	private String mdp;

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
	public void Connection() {
		try {
			// chargement du driver de connection à SQLSERVER
			Class.forName("net.sourceforge.jtds.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("Impossible de trouver le driver!");
			System.exit(-1);
		}

		Connection connexion = null;

		try {
			connexion = DriverManager.getConnection(url, id, mdp);
			System.out.println("Connexion établie");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
