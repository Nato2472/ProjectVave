<%@page import="java.util.Date"%>
<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Model.User" %>
<%@page import="Servlet.Logout" %>

<%
	// serveur IP: 217.128.202.143
	// keloud@osys.fr
	
	User u = null;
	if (session.getAttribute("currentUser") != null) {
		u = (User) session.getAttribute("currentUser");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, target-densitydpi=device-dpi"/>
		<link rel="stylesheet" type="text/css" href="Accueil.css"/>
		<title>Accueil</title>
	</head>
	<body>
	<div id="bloc_page">
		<header>
			<div id="logo">
				<img/>
			</div>
			<span id="menu_button">
				<input id="button_cate" type="button" value="Catégorie" onclick="self.location.href='Categorie.jsp'"/>
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
			</header>
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
					<% } %>
				</section>
				
				<section id="eval_content">
				bla bla bla categorie bla bla bla
				texte texte encore du texte.
				Test test this is a test.</br>
				One two one two.
				</br><iframe src="MapBing.html" style="height:420px; width:410px"></iframe>
				
				</section>
			</div>		
		
		<section>
			
		</section>	
		</div>
	</body>
</html>