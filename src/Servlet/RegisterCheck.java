package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		// /////////////////////////////////////////////
		// /////// Vérification IDs dans BDD ///////////
		// /////////////////////////////////////////////
		String url = "jdbc:jtds:sqlserver://217.128.202.143:1433/Vave";
		String BDDuser = "sa";
		String BDDpassword = "Mobile2013";

		Connection connexion = null;
		Statement stmt = null;
		ResultSet rs = null;

	}

}
