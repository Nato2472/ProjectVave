package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.SessionConfig;

import Manager.EvalManager;
import Model.Evaluation;

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
		String idUserTemp = request.getParameter("idUser");
		String comboBoxCat = request.getParameter("comboBoxCat");
		String comboBoxLieu = request.getParameter("comboBoxLieu");
		
		System.err.println("comboBoxCat = " + comboBoxCat);
		System.err.println("comboBoxLieu = " + comboBoxLieu);
		
		Double idUser = 15.0;
		float note = -1;

		if ((nameEval.length() > 1) & (noteEval.length() > 0) & (comCourtEval.length() > 1) & (comLongEval.length() > 1) ) {
			try {
				idUser = Double.parseDouble(idUserTemp); // String to Double
				note = Float.parseFloat(noteEval); // String to Float
				
				
				
				
			} catch (NumberFormatException n) {
				n.printStackTrace();
				System.err.println("idUser = " + idUser + " et note = " + note); // Verification des Parses
				response.sendRedirect("AddEval.jsp?err=note");
				return;
			}
			
			Evaluation eval = new Evaluation(nameEval, note, comCourtEval, comLongEval, autreComEval);
			EvalManager EM = new EvalManager();
			
			eval.setId_uti(idUser);
			eval.setId_eta(1); // Obtenir Id_Etablissement -> Modifier le Form
			eval.setId_Cate(1); // Obtenir Id_Categorie -> Modifier le Form
			EM.AddEval(eval);
			
		} else {
			// Erreur tout les champs ne sont pas remplis
			response.sendRedirect("AddEval.jsp?err=champs");
		}
		
		
	}

}
