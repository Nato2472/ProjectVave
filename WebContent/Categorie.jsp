<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Manager.CategorieManager" %>
<%@page import="Model.Categorie" %>
<%
	String listcate = request.getParameter("listcate");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="Categorie.css"/>
	<title>Catégorie</title>
</head>
	<body>
		<header>
			<div id="logo">
				<img/>
			</div>
			<span id="menu_button">
				<input id="button_cate" type="button" value="Accueil" onclick="self.location.href='Accueil.jsp'"/>
				<input id="button_lieu" type="button" value="Lieu" onclick="self.location.href='Error.html'"/>
				<% if(session.getAttribute("login") == null){ %>
					<input id="button_deco" type="button" value="Connexion" onclick="self.location.href='Login.jsp'"/>
					<input id="button_ins" type="button" value="Inscription" onclick="self.location.href = 'register.jsp'"/>
				<% } else{ %>
					<input id="button_deco" type="submit" value="Déconnexion" onclick="self.location.href = 'Logout'"/>
					<div id="msg_co">
						Bonjour <%= session.getAttribute("login") %>
					</div>
				<% } %>
			</span>
			<div id="content">
				<section id="cate_content">
					<b><u>Information:</u></b>
					</br></br>
					
					</br></br>
					<a href="Categorie.jsp"><i>Editer la catégorie.</i></a>
				</section>
				
				<section id="eval_content">
					<% listcate = request.getParameter("listcate");
						if (listcate.equals("aff")){ 
							CategorieManager cmanager = new CategorieManager();
							cmanager.GetListeCate();
							
							for ( Categorie c : cmanager.getCategories()){ %>
								<a href='Categorie.jsp?listcate=cate&descrip=<%= c.getNom() %>'><li><%= c.getNom() %></li></a>
							<%}%>
						
					<% }else{ %>
					
					<%} %>
				</section>
			</div>
			
		</header>
		<section>
			
		</section>
	</body>
</html>