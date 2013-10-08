<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Model.User" %>
    <%@page import="java.util.ArrayList"%>
    <%@page import="Manager.CategorieManager"%>
    <%@page import="Model.Categorie" %>
    <%@page import="Manager.LieuManager"%>
    <%@page import="Model.Lieu" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="AddCate.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout d'une �valuation</title>

</head>
<body>
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
	<script>alert('Evaluation ajout� !');</script><% 
}	 else { %>
		<script>alert('Vous cherchez les probl�mes !');</script><%
	}
}
%>



<% if (session.getAttribute("currentUser") != null) {%>
		               		
		  
<fieldset>
		           <form name="formulaire" action="AddEvalServlet" method="post">
		               <label for="nameEval">Titre pour votre �valuation:</label>
		               <input type="text" name="nameEval" id="nameEval" required="true"><br>
		               
		               <label for="noteEval">Note (de 1 � 5):</label>
		               <input type="text" id="noteEval" name="noteEval" required="true"><br>
		               
		               <label for="comCourtEval">Commentaire court (r�sum�):</label><br>
		               <textarea rows="2" cols="40" name="comCourtEval" id="comCourtEval" required="true"></textarea><br>
		               
		               <label for="comLongEval">Commentaire d�taill�:</label><br>
		               <textarea rows="4" cols="50" id="comLongEval" name="comLongEval" required="true"></textarea><br>
		               <label for="autreComEval">Un dernier d�tail ?</label>
		               <input type="text" name="autreComEval" id="autreComEval"><br>
		               
		               <label for="lieuEval">Lieu correspondant a votre �valuation.</label>
		               <select name="comboBoxLieu" id="comboBoxLieu" size="1"> 
		               <% for (int i = 0; i < ListeLieu.size(); i++) { %>
						<option value="<%=ListeLieu.get(i).getId()%>"><%=ListeLieu.get(i).getNom() %></option> 
						<% } %>
						</select><input id="button_lieu" type="button" value="Ajouter un lieu (si non existant)" onclick="self.location.href='AddLieu.jsp?Lieu=null'"/>
		               
		               <br><br><input type="submit" value="Ajouter">
		           </form>
		       </fieldset>
<% } else { 
	response.sendRedirect("Login.jsp");
}
%>

		       
		       
</body>
</html>