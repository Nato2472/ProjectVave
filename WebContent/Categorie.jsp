<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Manager.CategorieManager" %>
<%@page import="Model.Categorie" %>
<%@page import="Manager.EvalManager" %>
<%@page import="Model.Evaluation" %>
<%@page import="Manager.LieuManager" %>
<%@page import="Model.Lieu" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	String listcate = request.getParameter("listcate");
	String cateaff = request.getParameter("descrip");
	CategorieManager cmanager = new CategorieManager();
	EvalManager emanager = new EvalManager();
	Categorie cate = new Categorie();
	LieuManager lmanager = new LieuManager();
	Lieu eta = new Lieu();
	List<Evaluation> evalcate = new ArrayList<Evaluation>();

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
						<%if (listcate.equals("aff")){ %>
							<i>Cliquez sur une catégorie pour afficher ses informations</i>
						<% } else { %>
							<% cate = cmanager.GetCateByNom(cateaff);%>
							<b><u>Nom:</u></b>
							</br>
							<%= cate.getNom() %>
							</br></br>
							<b><u>Description:</u></b>
							</br>
							<%= cate.getDescription() %>
							</br></br>
							<a href="AddCate.jsp?Cate=<%= cate.getNom() %>"><i>Modifier la catégorie</i></a>
						<% } %>
				</section>
				
				<section id="eval_content">
					<% listcate = request.getParameter("listcate");
						if (listcate.equals("aff")){ 
							cmanager.GetListeCate();
							
							%> <h1> Liste des Catégorie</h1><button id="add_eval" onclick="self.location.href='AddCate.jsp?Cate=null'">Ajouter une catégorie</button></br><hr> <%
							for ( Categorie c : cmanager.getCategories()){ %>
								<a href='Categorie.jsp?listcate=cate&descrip=<%= c.getNom() %>'><li><%= c.getNom() %></li></a>
							<%}%>
						
					<% }else{ %>
						<% emanager.GetListEvalByCate(cate);%> 
							<h1>Liste des Evaluations</h1><a href="Categorie.jsp?listcate=aff"><i>Afficher toutes les catégories.</i></a><button id="add_eval">Ajouter une évaluation</button></br><hr> <%
							for(Evaluation e : emanager.getEvalcate()){
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
					<%} %>
				</section>
			</div>
			
		</header>
		<section>
			
		</section>
	</body>
</html>