<%@page import="java.io.PrintWriter"%>
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Model.Categorie" %>
<%@ page import="Manager.CategorieManager" %>
<%@ page import="Servlet.Add" %>

<%
	CategorieManager cmanager = new CategorieManager();

	cmanager.GetListeCate();
	String ajout = "Error";
	if( ajout.equals(session.getAttribute("AjoutCate"))){ %>
	<script type="text/javascript">
	alert("Vous avez entré un nom de catégorie déjà existant! Veuillez recommencer!");
	</script> <%
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Commun.css"/>
		<link rel="stylesheet" type="text/css" href="AddCate.css"/>
<title>Edition d'une catégorie</title>
</head>
	<body>
		<header>
			<div id="logo">
				<img/>
			</div>
			<span id="menu_button">
				<input id="button_menu_left" type="button" value="Accueil" onclick="self.location.href='Accueil.jsp'"/>
				<input id="button_menu_left" type="button" value="Catégorie" onclick="self.location.href='Categorie.jsp?listcate=aff'"/>
				<input id="button_menu_left" type="button" value="Lieu" onclick="self.location.href='Error.html'"/>
				<% if(session.getAttribute("login") == null){ %>
					<input id="button_menu_right" type="button" value="Connexion" onclick="self.location.href='Login.jsp'"/>
					<input id="button_menu_right" type="button" value="Inscription" onclick="self.location.href = 'register.jsp'"/>
				<% } else{ %>
					<input id="button_menu_right" type="submit" value="Déconnexion" onclick="self.location.href = 'Logout.java'"/>
					<div id="msg_co">
						Bonjour <%= session.getAttribute("login") %>, &nbsp;
					</div>
				<% }%>
			</span>
			<div id="content">
				<section id="info_content">
					<% if (request.getParameter("Cate").equals("null")){ %>
						<h1>Ajout d'une catégorie</h1>
						Pour ajouter une nouvelle catégorie, veuillez remplir les champs.
						Dans le cas où la catégorie existerait déjà, un message d'alerte s'affichera.
					<% }else{ %>
						<h1>Modification d'une catégorie</h1>
						Pour modifier une catégorie, veuillez editer les champs.
						Si le nom de la catégorie est modifié et qu'une autre catégorie porte le même nom,
						un message d'alerte s'affichera.
					<%} %>
				</section>
				
				<section id="main_content">
					<% if (request.getParameter("Cate").equals("null")){ %>
						<fieldset >
							<form method="post" action="Add">
								<label id="Nom" for="NomCate">Nom de la catégorie:</label>
				                <input type="text" name="NomCate" id="NomCate" required="true">
				                <br><br>
				                <label id="Descrip" for="descrip">Description:</label>
				                </br>
				                <textarea id="descrip" name="descrip" required="true" ></textarea>
				                <br><br>
				                <input type="submit" value="Ajouter" id="add">
							</form>
						</fieldset>
						
					<% }else{ 
						Categorie c = new Categorie();
						c = cmanager.GetCateByNom((String) request.getParameter("Cate"));
						session.setAttribute("IdCate", c.getId());%>
						<fieldset >
							<form method="post" action="Modify">
								<label for="NomCate">Nom de la catégorie:</label>
				                <input type="text" name="NomCate" id="NomCate" required="true" value="<%= c.getNom() %>">
				                <br>
				                <label for="descrip">Description:</label>
				                </br>
				                <textarea id="descrip" name="descrip" required="true"><%= c.getDescription() %></textarea>
				                <br><br>
				                <input type="submit" value="Modifier ">
							</form>
						</fieldset>
					<%} %>
				</section>
			</div>		
		</header>
	</body>
</html>