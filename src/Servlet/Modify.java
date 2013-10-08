package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import Manager.CategorieManager;
import Model.Categorie;

/**
 * Servlet implementation class Modify
 */
@WebServlet("/Modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
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
		// TODO Auto-generated method stub
		Categorie c = new Categorie();
		CategorieManager cmanager = new CategorieManager();
		HttpSession session = request.getSession();
		
		c.setDescription(request.getParameter("descrip"));
		c.setNom(request.getParameter("NomCate"));
		c.setId((Integer) session.getAttribute("IdCate"));
		
		if ( cmanager.GetFree(c)){
			cmanager.ModifyCate(c);
			request.getRequestDispatcher("/Categorie.jsp?listcate=aff").forward(
					request, response);
		} else {
			session.setAttribute("AjoutCate", "Error");
			request.getRequestDispatcher("/Categorie.jsp?listcate=aff").forward(
					request, response);
		}
	}

}
