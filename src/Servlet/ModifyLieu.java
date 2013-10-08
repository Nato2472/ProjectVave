package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manager.CategorieManager;
import Manager.LieuManager;
import Model.Lieu;

/**
 * Servlet implementation class ModifyLieu
 */
@WebServlet("/ModifyLieu")
public class ModifyLieu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyLieu() {
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
		Lieu l = new Lieu();
		LieuManager lmanager = new LieuManager();
		CategorieManager cmanager = new CategorieManager();
		HttpSession session = request.getSession();
		int i = cmanager.GetCateIdByNom(request.getParameter("Cate"));
		
		l.setNom((String) request.getParameter("NomLieu"));
		l.setAdresse((String) request.getParameter("Adr"));
		l.setCodepostal(Integer.parseInt(request.getParameter("CodePost")));
		l.setId_cate(i);
		l.setTelephone((String) request.getParameter("Tel"));
		l.setVille((String) request.getParameter("Ville"));
		//l.setId((Double) session.getAttribute("IdLieu"));
		l.setId(2.0);
		
		if ( lmanager.GetFree(l)){
			lmanager.ModifyLieu(l);
			request.getRequestDispatcher("/Lieu.jsp").forward(
					request, response);
		} else {
			session.setAttribute("AjoutLieu", "Error");
			request.getRequestDispatcher("/Categorie.jsp?listcate=aff").forward(
					request, response);
		}
	}

}
