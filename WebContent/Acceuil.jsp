<%@page import="java.util.Date"%>
<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Model.User" %>
<%
	// serveur IP: 217.128.202.143
	//User u = (User) request.getAttribute("User");
	User u = new User("nom", "prenom", "login", "mdp", new Date());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Acceuil.css"/>
		<title>Acceuil</title>
	</head>
	<body>
		<header>
			<div id="logo">
				<img/>
			</div>
			<span id="menu_button">
				<button id="button_cate">Catégorie</button>
				<button id="button_lieu">Lieu</button>
				<button id="button_deco">Deconnexion</button>
				
			</span>
			<div id="content">
				<section id="user_content">
					<b><u>Information:</u></b>
					</br></br>
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
					</br></br>
					<a><i>Editer mes informations.</i></a>
				</section>
			</div>
			
		</header>
		<section>
			
		</section>
		
	</body>
</html>