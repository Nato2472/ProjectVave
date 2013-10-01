package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterCheck
 */
@WebServlet("/RegisterCheck")
public class RegisterCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterCheck() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// je recupère Login et Password entré par l'utilisateur
		String lastname = request.getParameter("nom");
		String firstname = request.getParameter("prenom");
		String pseudo = request.getParameter("pseudo");
		String login = request.getParameter("login");
		String pass1 = request.getParameter("password");
		String pass2 = request.getParameter("password2");

		if (pass1 == pass2) {

		}

		///////////////////////////////////////////////
		///////// Vérification IDs dans BDD ///////////
		///////////////////////////////////////////////
		String url = "jdbc:jtds:sqlserver://217.128.202.143:1433/Vave";
		String BDDuser = "sa";
		String BDDpassword = "Mobile2013";

		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");

			connexion = DriverManager.getConnection(url, BDDuser, BDDpassword);

		} catch (ClassNotFoundException ex) {
			System.err.println("Impossible de trouver le driver");
			System.exit(-1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			stmt = connexion.createStatement();
			rs = stmt.executeQuery("SELECT count(Login_Uti) FROM UTILISATEUR WHERE Login_Uti = '" + login + "' AND Pse_Uti ='" + pseudo + "';");
			String rsLog = null;
			if (rs.next()) {
				rsLog = rs.getObject(1).toString();
			}

			System.out.println("Login depuis la BDD : " + rsLog);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
