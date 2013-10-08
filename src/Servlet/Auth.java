package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DatabaseHelper;
import Model.User;

/**
 * Servlet implementation class Auth
 */
@WebServlet("/Auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// je recup�re Login et Password entr� par l'utilisateur
		String login = request.getParameter("login");
		String password = request.getParameter("password");


		DatabaseHelper db = new DatabaseHelper();
		ResultSet rs = null;
	
		rs = db.QueryLogin(login, password);
		String rsLog = null;
		String rsPre = null;
		String rsNom = null;
		String rsPse = null;
		Date rsDate = null;
		
		try {
			if (rs.next()) {
				rsLog = rs.getObject(1).toString();
				rsPre = rs.getObject(2).toString();
				rsNom = rs.getObject(3).toString();
				rsPse = rs.getObject(4).toString();
				rsDate = rs.getDate(5);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.ConnectionClose();
		}

		// je cr�� une session
		if (rsLog != null) {
			HttpSession session = request.getSession();
			// Cr�ation du User et placement dans Session
			User u = new User(rsNom, rsPre, rsLog, rsPse, rsDate);
			
			session.setAttribute("login", rsLog);
			session.setAttribute("currentUser", u);
			
			getServletContext().getRequestDispatcher("/Accueil.jsp").forward(
					request, response);
		} else if (rsLog == null) {
			HttpSession session = request.getSession();
			session.setAttribute("error", "1");
			getServletContext().getRequestDispatcher("/Login.jsp").forward(
					request, response);
		}
	}
}
