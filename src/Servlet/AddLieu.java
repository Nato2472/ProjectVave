package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.security.jca.GetInstance.Instance;
import Manager.CategorieManager;
import Manager.LieuManager;
import Model.Lieu;

/**
 * Servlet implementation class AddLieu
 */
@WebServlet("/AddLieu")
public class AddLieu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLieu() {
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
		LieuManager lmanager = new LieuManager();
		CategorieManager cmanager = new CategorieManager();
		HttpSession session = request.getSession();
		Lieu l = new Lieu();
		boolean valid = true;
	
		l.setNom((String) request.getParameter("NomLieu"));
		l.setAdresse((String) request.getParameter("Adr"));
		try{
			l.setCodepostal(Integer.parseInt(request.getParameter("CodePost")));
		}catch (Exception e){
			session.setAttribute("AjoutCP", "Error");
			valid = false;
		}
		
		l.setId_cate(cmanager.GetCateIdByNom(request.getParameter("Cate")));
		l.setTelephone((String) request.getParameter("Tel"));
		l.setVille((String) request.getParameter("Ville"));
		
		if ( lmanager.GetFree(l) & valid){
			lmanager.AddLieu(l);
			request.getRequestDispatcher("/Lieu.jsp?listlieu=aff").forward(
					request, response);
		} else {
			session.setAttribute("AjoutLieu", "Error");
			request.getRequestDispatcher("/AddLieu.jsp?Lieu=null").forward(
					request, response);
		}
	}

}
