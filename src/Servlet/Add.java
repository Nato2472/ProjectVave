package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Categorie;
import Manager.CategorieManager;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
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

		Categorie c = new Categorie();
		CategorieManager cmanager = new CategorieManager();
		
		c.setDescription((String) request.getParameter("descrip"));
		c.setNom((String) request.getParameter("NomCate"));
		
		if ( cmanager.GetFreeForName(c.getNom())){
			cmanager.AddCate(c);
			request.getRequestDispatcher("/Categorie.jsp?listcate=aff").forward(
					request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("AjoutCate", "Error");
			request.getRequestDispatcher("/AddCate.jsp?Cate=null").forward(
					request, response);
		}
		
	}

}
