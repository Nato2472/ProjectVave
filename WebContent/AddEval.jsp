<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Model.User" %>
    <%@page import="java.util.ArrayList"%>
    <%@page import="Manager.CategorieManager"%>
    <%@page import="Model.Categorie" %>
    <%@page import="Manager.LieuManager"%>
    <%@page import="Model.Lieu" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
User u = null;
Double idUser;
if (session.getAttribute("currentUser") != null) {
	u = (User) session.getAttribute("currentUser");
}
%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="AddCate.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Commun.css"/>
		<link rel="stylesheet" type="text/css" href="AddCate.css"/>
<title>Ajout d'une évaluation</title>

</head>
<body>
	<header>
		<div id="logo">
			<img/>
		</div>
		<span id="menu_button">
			<input id="button_menu_left" type="button" value="Accueil" onclick="self.location.href='Accueil.jsp'"/>
			<input id="button_menu_left" type="button" value="Catégorie" onclick="self.location.href='Categorie.jsp?listcate=aff'"/>
			<input id="button_menu_left" type="button" value="Lieu" onclick="self.location.href='Lieu.jsp?listlieu=aff'"/>
			<% if(session.getAttribute("login") == null){ %>
				<input id="button_menu_right" type="button" value="Connexion" onclick="self.location.href='Login.jsp'"/>
				<input id="button_menu_right" type="button" value="Inscription" onclick="self.location.href='register.jsp'"/>
			<% } else{ %>
				<input id="button_menu_right" type="submit" value="Déconnexion" onclick="self.location.href='Logout.java'"/>
				<div id="msg_co">
					Bonjour <%= session.getAttribute("login") %>
				</div>
			<% }%>
		</span>
		<div id="content">
			<section id="info_content">
					<h1>Ajout d'une Evaluation</h1>
					Pour ajouter une nouvelle evaluation, veuillez remplir les champs.
					Veillez à renseigner comme il se doit les champs.
			</section>
			
			<section id="main_content">
				<% LieuManager lm = new LieuManager();
				lm.GetListeLieu();
				ArrayList<Lieu> ListeLieu = lm.getLieux();
				
				
				if (request.getParameter("err") != null) {
					String desErr = request.getParameter("err");
					if (desErr.equals("champs")) { %>
						<script>alert('Les champs ne sont pas tous remplis');</script><%
					}  else if(desErr.equals("note")) { %>
						<script>alert('La note n\'est pas valide');</script><% 
					}  else if(desErr.equals("ok")) { %>
					<script>alert('Evaluation ajouté !');</script><% 
				}	 else { %>
						<script>alert('Vous cherchez les problèmes !');</script><%
					}
				}
				%>
				
				
				
<% if (session.getAttribute("currentUser") != null) {%>
				
				<fieldset>
					<% if (request.getParameter("nameEval") != null) { %>
				
					<form name="formulaire" action="AddEvalServlet" method="post">
		               <label for="nameEval">Titre pour votre évaluation:</label>
		               <input type="text" name="nameEval" id="nameEval" required="true" value=<%=request.getParameter("nameEval")%>><br>
		               
		               <label for="noteEval">Note (de 1 à 5):</label>
		               <input type="text" id="noteEval" name="noteEval" required="true" value=<%=request.getParameter("noteEval")%>><br>
		               
		               <label for="comCourtEval">Commentaire court (résumé):</label>
		               <input type="text" name="comCourtEval" id="comCourtEval" required="true" value=<%=request.getParameter("comCourtEval")%>><br>
		               
		               <label for="comLongEval">Commentaire détaillé:</label>
		               <input type="text" id="comLongEval" name="comLongEval" required="true" size=100 value=<%=request.getParameter("comLongEval")%>><br>
		               
		               <label for="autreComEval">Un dernier détail ?</label>
		               <input type="text" name="autreConEval" id="autreComEval" value=<%=request.getParameter("autreComEval")%>><br>
		               
		               <label for="lieuEval">Lieu correspondant a votre évaluation.</label>
		               <select name="comboBoxLieu" id="comboBoxLieu" size="1" onChange="Remplir(this.value);"> 
		               <% for (int i = 0; i < ListeLieu.size() ; i++) { %>
						<option value="<%=ListeLieu.get(i).getId()%>"><%=ListeLieu.get(i).getNom() %></option> 
						<% } %>
						</select><input id="button_lieu" type="button" value="AddLieu" onclick="self.location.href='AddLieu.jsp?Lieu=null'"/>
		               
		               
		               <% //if (session.getAttribute("currentUser") != null) { idUser = u.getId();%>
		               <input type="hidden" name="idUser" id="idUser" value=15><br>
		               <% //} %>
		               <input type="submit" value="Ajouter">
		           </form>
		           
		           
				<% } else { %>


<fieldset>
		           <form name="formulaire" action="AddEvalServlet" method="post">
		               <label for="nameEval">Titre pour votre évaluation:</label>
		               <input type="text" name="nameEval" id="nameEval" required="true"><br>
		               
		               <label for="noteEval">Note (de 1 à 5):</label>
		               <input type="text" id="noteEval" name="noteEval" required="true"><br>
		               
		               <label for="comCourtEval">Commentaire court (résumé):</label><br>
		               <textarea rows="2" cols="40" name="comCourtEval" id="comCourtEval" required="true"></textarea><br>
		               
		               <label for="comLongEval">Commentaire détaillé:</label><br>
		               <textarea rows="4" cols="50" id="comLongEval" name="comLongEval" required="true"></textarea><br>
		               <label for="autreComEval">Un dernier détail ?</label>
		               <input type="text" name="autreComEval" id="autreComEval"><br>
		               
		               <label for="lieuEval">Lieu correspondant a votre évaluation.</label>
		               <select name="comboBoxLieu" id="comboBoxLieu" size="1"> 
		               <% for (int i = 0; i < ListeLieu.size(); i++) { %>
						<option value="<%=ListeLieu.get(i).getId()%>"><%=ListeLieu.get(i).getNom() %></option> 
						<% } %>
						</select><input id="button_lieu" type="button" value="Ajouter un lieu (si non existant)" onclick="self.location.href='AddLieu.jsp?Lieu=null'"/>
		               
		               <br><br><input type="submit" value="Ajouter">
		           </form>
	       </fieldset>
       </section>
	</div>
<% } else { 
	response.sendRedirect("Login.jsp");
}
%>

		       
		       
</body>
</html>