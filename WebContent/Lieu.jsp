<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Manager.CategorieManager" %>
<%@page import="Model.Categorie" %>
<%@page import="Manager.EvalManager" %>
<%@page import="Model.Evaluation" %>
<%@page import="Manager.LieuManager" %>
<%@page import="Model.Lieu" %>
<%@page import="Manager.UserManager" %>
<%@page import="Model.User" %>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	String listlieu = request.getParameter("listlieu");
	String lieuaff = request.getParameter("descrip");
	CategorieManager cmanager = new CategorieManager();
	EvalManager emanager = new EvalManager();
	Lieu lieu = new Lieu();
	LieuManager lmanager = new LieuManager();
	Lieu eta = new Lieu();
	List<Evaluation> evalcate = new ArrayList<Evaluation>();

	String ajout = "Error";
	if( ajout.equals(session.getAttribute("AjoutLieu")) & ajout.equals(session.getAttribute("AjoutCP")) == false){ %>
		<script type="text/javascript">
			alert("Vous avez entré un nom de catégorie déjà existant! Veuillez recommencer!");
		</script> 
		<%
		ajout = null;
		session.setAttribute("AjoutLieu", "Valid");
	}
	if( ajout.equals(session.getAttribute("AjoutLieu")) & ajout.equals(session.getAttribute("AjoutCP"))){ %>
	<script type="text/javascript">
		alert("Vous avez entré un code postal non valide!");
	</script> <%
	ajout = null;
	session.setAttribute("AjoutLieu", "Valid");
	session.setAttribute("AjoutCP", "Valid" );
}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Commun.css"/>
		<link rel="stylesheet" type="text/css" href="Eval.css"/>
	<title>Lieu</title>
</head>
	<body>
		<header>
			<div id="logo">
				<img/>
			</div>
			<span id="menu_button">
				<input id="button_menu_left" type="button" value="Accueil" onclick="self.location.href='Accueil.jsp'"/>
				<input id="button_menu_left" type="button" value="Catégorie" onclick="self.location.href='Categorie.jsp?listcate=aff'"/>
				<% if(session.getAttribute("login") == null){ %>
					<input id="button_menu_right" type="button" value="Connexion" onclick="self.location.href='Login.jsp'"/>
					<input id="button_menu_right" type="button" value="Inscription" onclick="self.location.href = 'register.jsp'"/>
				<% } else{ %>
					<input id="button_menu_right" type="submit" value="Déconnexion" onclick="self.location.href = 'Logout'"/>
					<div id="msg_co">
						Bonjour <%= session.getAttribute("login") %>
					</div>
				<% } %>
			</span>
			<div id="content">
				<section id="info_content">
					<b><u>Information:</u></b>
					</br></br>
						<%if (listlieu.equals("aff")){ %>
						
							<i>Cliquez sur unlieu pour afficher ses informations</i>
							
						<% } else { %>
						
							<!-- l'affichage du descriptif d'un lieu -->
							<% lieu = lmanager.GetLieuByNom(lieuaff);%>
							
							<b><%= lieu.getNom() %></b>
							<br><br>
							Adresse:<br><%= lieu.getAdresse() %> <%= lieu.getCodepostal() %> <%= lieu.getVille() %>
							<br><br>
							Tel: <%= lieu.getTelephone() %>
							<br><br>
							<% Categorie cat = new Categorie();
								cat = cmanager.GetCateById(lieu.getId_cate());%>
							Catégorie: <%= cat.getNom() %>
							<br><br>
							
							<!--  fin de l'affichage du descriptif du lieu -->
							<!--  Redirection vers la page de modification du lieu -->
							<% if(session.getAttribute("login") != null){ %>
								<a href="AddLieu.jsp?Lieu=<%= lieu.getNom() %>"><i>Modifier le lieu</i></a>
							<%} %>
						<% } %>
				</section>
				
				<section id="main_content">
					<% listlieu = request.getParameter("listlieu");
						// Affichage de la liste des lieux
						if (listlieu.equals("aff")){ 
							lmanager.GetListeLieu();
							
							%> <h1> Liste des Lieux</h1>
								
								<% if(session.getAttribute("login") != null){ %>
								<button id="add_eval" onclick="self.location.href='AddLieu.jsp?Lieu=null'">Ajouter un lieu</button></br><% } %><hr> <%
							for ( Lieu l : lmanager.getLieux()){
								Categorie catego = new Categorie();
								catego = cmanager.GetCateById(l.getId_cate()); %>
								<a href='Lieu.jsp?listlieu=lieu&descrip=<%= l.getNom() %>'><li><%= l.getNom() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Catégorie: <%= catego.getNom() %></li></a>
							<%}%>
						
					<% }else{ %>
						<% emanager.GetListEvalByLieu(lieu);%> 
							<h1>Liste des Evaluations</h1><a href="Lieu.jsp?listlieu=aff"><i>Afficher touts les lieux.</i></a>
								<% if(session.getAttribute("login") != null){ %>
								<button id="add_eval" onclick="self.location.href='AddEval.jsp'">Ajouter une évaluation</button></br><%} %><hr> <%
							for(Evaluation e : emanager.getEvallieu()){
								eta = lmanager.GetLieuById(e.getId_eta());%>
							<div id="eval">
								<div id="eta">
									<u><b>Etablissement:</b></u></br>
									<%= eta.getNom() %></br> <%= eta.getAdresse() %></br> <%= eta.getCodepostal() %> <%= eta.getVille() %> </br>
									<u>Tel:</u> <%= eta.getTelephone() %> </br></br>
								</div>
								<div id="user_eval">
									<u><b>Evaluateur:</b></u>
									<% User u = new User();
										UserManager umanager = new UserManager();
										u = umanager.GetUserById(e.getId_uti());%>
										<%= u.getPseudo() %>
								</div>
								<div id="carac_eval">
									<u><b>Evaluation:</b></u>
									</br>
									<u>Titre:</u> <%= e.getNom() %>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Note:<%= e.getNote() %>/5   &nbsp;&nbsp;&nbsp; <%= e.getDateEval() %>
									</br>
									<u>Objet:</u> <%= e.getComCourt() %></br>
									<u>Commentaire:</u></br>
									<%= e.getComLong() %></br>
								</div>
 							</div>
 							</br></br>
						<% } %>
					<%} %>
				</section>
			</div>
			
		</header>
		<section>
			
		</section>
	</body>
</html>