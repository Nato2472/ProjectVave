<%@page import="java.util.Date"%>
<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Model.User" %>
<%@page import="Servlet.Logout" %>
<%@page import="Manager.CategorieManager" %>
<%@page import="Model.Categorie" %>
<%@page import="Manager.EvalManager" %>
<%@page import="Model.Evaluation" %>
<%@page import="Manager.LieuManager" %>
<%@page import="Model.Lieu" %>

<%
	// serveur IP: 217.128.202.143
	// keloud@osys.fr
	CategorieManager cmanager = new CategorieManager();
	EvalManager emanager = new EvalManager();
	Categorie cate = new Categorie();
	LieuManager lmanager = new LieuManager();
	Lieu eta = new Lieu();
	User u = null;
	if (session.getAttribute("currentUser") != null) {
		u = (User) session.getAttribute("currentUser");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Accueil.css"/>
		<title>Accueil</title>
	</head>
	<body>
		<header>
			<div id="logo">
				<img/>
			</div>
			<span id="menu_button">
				<input id="button_cate" type="button" value="Catégorie" onclick="self.location.href='Categorie.jsp?listcate=aff'"/>
				<input id="button_lieu" type="button" value="Lieu" onclick="self.location.href='Error.html'"/>
				<% if(session.getAttribute("login") == null){ %>
					<input id="button_deco" type="button" value="Connexion" onclick="self.location.href='Login.jsp'"/>
					<input id="button_ins" type="button" value="Inscription" onclick="self.location.href = 'register.jsp'"/>
				<% } else{ %>
					<input id="button_deco" type="submit" value="Déconnexion" onclick="self.location.href = 'Logout.java'"/>
					<div id="msg_co">
						Bonjour <%= session.getAttribute("login") %>
					</div>
				<% }%>
			</span>
			<div id="content">
				<section id="user_content">
					<b><u>Information:</u></b>
					</br></br>
					<% if (session.getAttribute("currentUser") != null) { %>
					<u>Nom Prénom:</u>
					</br>
					<%= u.getPrenom() %> <%= u.getNom() %>
					</br></br>
					<u>Date d'inscription:</u>
					</br>
					<%= u.getDate() %>
					</br></br>
					<u>Pseudo:</u>
					</br>
					<%= u.getPseudo() %>
					</br></br>
					<u>Login:</u>
					</br>
					<%= u.getLogin() %>
					<% } else {%>
					<i><a href="Login.jsp">Veuillez vous connecter pour pouvoir voir vos informations utilisateurs</a></i>
					<% } %>
				</section>
				
				<section id="eval_content">
						<% emanager.GetEvalLast();%>
						<h1>Dernières Evaluations</h1>
						<% if(session.getAttribute("login") != null){ %>
						<button id="add_eval" onclick="self.location.href='AddCate.jsp?Cate=null'">Ajouter une évaluation</button></br>
						<% } %>
						<hr>	
						<% for(Evaluation e : emanager.getEvallast()){
							eta = lmanager.GetLieuById(e.getId_eta());%>
							<div id="eval">
								<u><b>Etablissement:</b></u></br>
								<%= eta.getNom() %></br> <%= eta.getAdresse() %></br> <%= eta.getCodepostal() %> <%= eta.getVille() %> </br>
								<u>Tel:</u> <%= eta.getTelephone() %> </br></br>
								<u><b>Evaluation:</b></u>
								</br>
								<u>Titre:</u> <%= e.getNom() %>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Note:<%= e.getNote() %>/5   &nbsp;&nbsp;&nbsp; <%= e.getDateEval() %>
								</br>
								<u>Objet:</u> <%= e.getComCourt() %></br>
								<u>Commentaire:</u></br>
								<%= e.getComLong() %></br>
								
							</div>
							</br></br>
						<% } %>
				</section>
			</div>		
		</header>
	</body>
</html>