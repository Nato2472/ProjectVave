<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@ page import="Model.Lieu" %>
<%@ page import="Manager.LieuManager" %>
<%@ page import="Model.Categorie" %>
<%@ page import="Manager.CategorieManager" %>
<%@ page import="Servlet.AddLieu" %>

<%
	LieuManager lmanager = new LieuManager();
	CategorieManager cmanager = new CategorieManager();
	
	cmanager.GetListeCate();
	lmanager.GetListeLieu();
	String ajout = "Error";
	if( ajout.equals(session.getAttribute("AjoutLieu"))){ %>
	<script type="text/javascript">
	alert("Vous avez entr� un nom de cat�gorie d�j� existant! Veuillez recommencer!");
	</script> <%
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Commun.css"/>
		<link rel="stylesheet" type="text/css" href="AddLieu.css"/>
<title>Edition d'un lieu</title>
</head>
<body>
	<header>
		<div id="logo">
			<img/>
		</div>
		<span id="menu_button">
			<input id="button_menu_left" type="button" value="Accueil" onclick="self.location.href='Accueil.jsp'"/>
			<input id="button_menu_left" type="button" value="Cat�gorie" onclick="self.location.href='Categorie.jsp?listcate=aff'"/>
			<input id="button_menu_left" type="button" value="Lieu" onclick="self.location.href='Lieu.jsp?listlieu=aff'"/>
			<% if(session.getAttribute("login") == null){ %>
				<input id="button_menu_right" type="button" value="Connexion" onclick="self.location.href='Login.jsp'"/>
				<input id="button_menu_right" type="button" value="Inscription" onclick="self.location.href='register.jsp'"/>
			<% } else{ %>
				<input id="button_menu_right" type="submit" value="D�connexion" onclick="self.location.href='Logout.java'"/>
				<div id="msg_co">
					Bonjour <%= session.getAttribute("login") %>, &nbsp;
				</div>
			<% }%>
		</span>
		<div id="content">
			<section id="info_content">
				<% if (request.getParameter("Lieu").equals("null")){ %>
					<h1>Ajout d'un lieu</h1>
					Pour ajouter un nouveau lieu, veuillez remplir les champs.
					Dans le cas o� le lieu existerait d�j�, un message d'alerte s'affichera.
				<% }else{ %>
					<h1>Modification d'un lieu</h1>
					Pour modifier une cat�gorie, veuillez editer les champs.
					Si le nom de la cat�gorie est modifi� et qu'une autre cat�gorie porte le m�me nom,
					un message d'alerte s'afficera.
				<%} %>
			</section>
			
			<section id="main_content">
				<% if (request.getParameter("Lieu").equals("null")){ %>
					<fieldset id="field">
						<form method="post" action="AddLieu">
							<label id="textlieu" for="NomLieu">Nom du lieu:</label>
			                <input type="text" name="NomLieu" id="NomLieu" required="true">
			                <br><br>
			                <label id="textlieu" for="Adr">Adresse:</label>
			                <input type="text" name="Adr" id="Adr" required="true">
			                <br><br>
			                <label id="textlieu" for="CodePost">Code Postal:</label>
			                <input type="text" name="CodePost" id="CodePost" required="true">
			                <br><br>
			                <label id="textlieu" for="Ville">Ville:</label>
			                <input type="text" name="Ville" id="Ville" required="true">
			                <br><br>
			                <label id="textlieu" for="Tel">Telephone:</label>
			                <input type="text" name="Tel" id="Tel" required="true">
			                <br><br>
			                <label id="textlieu" for="Cate">Cat�gorie:</label>
			                <select nom="Cate" id="Cate" required="true" >
			                <% for(Categorie c : cmanager.getCategories()){%>
			                	<option value="<%= c.getNom() %>" selected="selected"><%= c.getNom() %></option>
			                <% } %>
			                </select>
			                <br><br>
			                <input type="submit" value="Ajouter" id="add">
						</form>
					</fieldset>
				<% }else{ 
					Lieu l = new Lieu();
					l = lmanager.GetLieuByNom((String) request.getParameter("Lieu"));
					session.setAttribute("IdLieu", l.getId());%>
					<fieldset >
						<form method="post" action="ModifyLieu">
							<label for="NomLieu">Nom du lieu:</label>
			                <input type="text" name="NomLieu" id="NomLieu" required="true" value="<%= l.getNom() %>">
			                <br>
			                <label for="Adr">Adresse:</label>
			                <input type="text" name="Adr" id="Adr" required="true" value="<%= l.getAdresse() %>">
			                <br>
			                <label for="CodePost">Code Postal:</label>
			                <input type="text" name="CodePost" id="CodePost" required="true" value="<%= l.getCodepostal() %>">
			                <br>
			                <label for="Ville">Ville:</label>
			                <input type="text" name="Ville" id="Ville" required="true" value="<%= l.getVille() %>">
			                <br>
			                <label for="Tel">Telephone:</label>
			                <input type="text" name="Tel" id="Tel" required="true" value="<%= l.getTelephone() %>">
			                <br>
			                <label for="Cate">Cat�gorie:</label>
			                <select name="Cate" id="Cate" required="true" >
			                <% for(Categorie c : cmanager.getCategories()){
			                		if ( c.getId() == l.getId_cate()){%>
			                			<option value="<%=c.getNom() %>" selected="selected"><%= c.getNom() %></option>
			                		<% } else { %>
			                			<option value="<%=c.getNom() %>"><%= c.getNom() %></option>
			                		<% } %>
			                <% } %>
			                </select>
			                <br><br>
			                <input type="submit" value="Modifier">
						</form>
					</fieldset>
				<%} %>
			</section>
		</div>		
	</header>
</body>
</html>