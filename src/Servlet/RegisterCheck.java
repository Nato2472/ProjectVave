package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

import Model.DatabaseHelper;

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

		String ValGet = "&nom=" + lastname + "&prenom=" + firstname + "&pseudo=" + pseudo + "&login=" + login + "";
		
		if(lastname.length() > 2 & firstname .length() > 2 & pseudo.length() > 2 & login.length() > 2) {
			

			if (pass1.equals(pass2)) {
	
				DatabaseHelper db = new DatabaseHelper();
				ResultSet rs = null;
				
				
				try {
					rs = db.CheckIfExistLogin(login, pseudo);
					String StrRes = null;
					if (rs.next()) {
						StrRes = rs.getObject(1).toString();
					}

					
					if ( StrRes.equals("0")) { // Si il y a aucune correspondance en BDD, j'inscris l'utilisateur.
						
						db.QueryRegister(login, firstname, lastname, pseudo, pass1);
						
						// Inscription reussi
						response.sendRedirect("register.jsp?reg=ok");
	
					} else {
						response.sendRedirect("register.jsp?err=user" + ValGet);
					}
	
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					db.ConnectionClose();
				}
				
				
			} else {
				// les mots de passe de ne sont pas identiques.
				response.sendRedirect("register.jsp?err=pass" + ValGet);
			}
		} else {
			response.sendRedirect("register.jsp?err=3car" + ValGet);
		}
	}
}


