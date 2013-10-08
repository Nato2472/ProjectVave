package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.SessionConfig;

import Manager.EvalManager;
import Model.DatabaseHelper;
import Model.Evaluation;
import Model.User;

/**
 * Servlet implementation class AddEvalServlet
 */
@WebServlet("/AddEvalServlet")
public class AddEvalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEvalServlet() {
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

		String nameEval = request.getParameter("nameEval");
		String noteEval = request.getParameter("noteEval");
		String comCourtEval = request.getParameter("comCourtEval");
		String comLongEval = request.getParameter("comLongEval");
		String autreComEval = request.getParameter("autreComEval");
		String comboBoxLieu = request.getParameter("comboBoxLieu");
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("currentUser");
		
		
		float note = -1;
		Double idLieu = null;

		if ((nameEval.length() > 1) & (noteEval.length() > 0) & (comCourtEval.length() > 1) & (comLongEval.length() > 1) ) {
			
			try {
				idLieu = Double.parseDouble(comboBoxLieu); // String to Double
				note = Float.parseFloat(noteEval); // String to Float
			} catch (NumberFormatException n) {
				n.printStackTrace();
				System.err.println("idUser = " + u.getId() + " et note = " + note); // Verification des Parses
				response.sendRedirect("AddEval.jsp?err=note");
				return;
			}
			
			if ((note <= 5) & (note >= 0)) 
			{
				Evaluation eval = new Evaluation(nameEval, note, comCourtEval, comLongEval, autreComEval);
				EvalManager Emanager = new EvalManager();
				
				
				DatabaseHelper db = new DatabaseHelper();
				ResultSet rs = null;
				String query = "SELECT Id_Cate FROM ETABLISSEMENT WHERE Id_Eta=" + idLieu;
				rs = db.executeQuery(query);
				Double idCat = null;
				try {
					while(rs.next()){
						idCat = rs.getDouble(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return;
				}
				
				
				eval.setId_uti(u.getId());
				eval.setId_eta(idLieu);
				eval.setId_cate(idCat);
				Emanager.AddEval(eval);
				response.sendRedirect("AddEval.jsp?err=ok");
				return;
			} else {
				response.sendRedirect("AddEval.jsp?err=note");
				return;
			}
			
			
		} else {
			// Erreur tout les champs ne sont pas remplis
			response.sendRedirect("AddEval.jsp?err=champs");
		}
		
		
	}

}
