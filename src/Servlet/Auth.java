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
		
		// je recupère Login et Password entré par l'utilisateur
		String login = request.getParameter("login");
		String password = request.getParameter("password");


		// /////// Vérification IDs dans BDD ///////////
		String url = "jdbc:jtds:sqlserver://217.128.202.143:1433/Vave";
		//String url = "jdbc:jtds:sqlserver://localhost:1433/Vave";
		String BDDuser = "sa";
		String BDDpassword = "Mobile2013";

		Connection connexion = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");

			connexion = DriverManager.getConnection(url, BDDuser, BDDpassword);

			pstmt = connexion.prepareStatement("SELECT Login_Uti, Prenom_Uti, Nom_Uti, Pseudo_Uti, Date_Inscrip_Uti FROM UTILISATEUR WHERE Login_Uti = ? AND Mdp_Uti= ?;");
			pstmt.setObject(1, login);
			pstmt.setObject(2, password);
			
			rs = pstmt.executeQuery();
			String rsLog = null;
			String rsPre = null;
			String rsNom = null;
			String rsPse = null;
			Date rsDate = null;
			if (rs.next()) {
				rsLog = rs.getObject(1).toString();
				rsPre = rs.getObject(2).toString();
				rsNom = rs.getObject(3).toString();
				rsPse = rs.getObject(4).toString();
				rsDate = rs.getDate(5);
			}

			// je créé une session
			if (rsLog != null) {
				HttpSession session = request.getSession();
				// Création du User et placement dans Session
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

		} catch (ClassNotFoundException ex) {
			System.err.println("Impossible de trouver le driver");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}
}
