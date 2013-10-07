<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Model.User" %>
    <%@page import="java.util.ArrayList"%>
    <%@page import="Manager.CategorieManager"%>
    <%@page import="Model.Categorie" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout d'une évaluation</title>
<script>
var lieux = [];

lieux['0'] = ["Paris", "Londres"];
lieux['1'] = ["Tokyo", "Moscou", "Berlin"];
lieux['0'][3] = "Madrid";
function Remplir(value)
{
	var cbtofill = document.forms["formulaire"]["comboBoxLieu"];
	
	cbtofill.length = 1;
	cbtofill.selectedIndex = 0;
	
	var nlieu = lieux[value];
	
	for(each in nlieu)
	{
		var nOption = document.createElement('option'); 
		var nData = document.createTextNode(nlieu[each]); 
		nOption.setAttribute('value',nlieu[each]); 
		nOption.appendChild(nData); 
		cbtofill.appendChild(nOption); 
	}
	
}
</script>
</head>
<body>
<%
CategorieManager cm = new CategorieManager();
cm.GetListeCate();
ArrayList<Categorie> ListeCat = cm.getLieux();

if (request.getParameter("err") != null) {
	String desErr = request.getParameter("err");
	if (desErr.equals("champs")) { %>
		<script>alert('Les champs ne sont pas tous remplis');</script><%
	}  else if(desErr.equals("note")) { %>
		<script>alert('La note n\'est pas valide');</script><% 
	} else {
		%><script>alert('Vous cherchez les problèmes !');</script><%
	}
}
%>




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
	               <input type="text" id="comLongEval" name="comLongEval" required="true" value=<%=request.getParameter("comLongEval")%>><br>
	               
	               <label for="autreComEval">Un dernier détail ?</label>
	               <input type="text" name="autreConEval" id="autreComEval" value=<%=request.getParameter("autreComEval")%>><br>
	               
	               <label for="lieuEval">Lieu correspondant a votre évaluation.</label>
	               <select name="comboBoxCat" id="comboBoxCat" size="1" onChange="Remplir(this.value);"> 
	               <% for (int i = 0; i < ListeCat.size() ; i++) { %>
					<option value="<%=ListeCat.get(i)%>"><%=ListeCat.get(i) %></option> 
					<% } %>
					</select>
	               <select name="comboBoxLieu" id="comboBoxLieu" size="1"> 
					</select>
	               
	               
	               <% //if (session.getAttribute("currentUser") != null) { idUser = u.getId();%>
	               <input type="hidden" name="idUser" id="idUser" value=15><br>
	               <% //} %>
	               <input type="submit" value="Ajouter">
	           </form>
	           
	           
			<% } else { %>


		           <form name="formulaire" action="AddEvalServlet" method="post">
		               <label for="nameEval">Titre pour votre évaluation:</label>
		               <input type="text" name="nameEval" id="nameEval" required="true"><br>
		               
		               <label for="noteEval">Note (de 1 à 5):</label>
		               <input type="text" id="noteEval" name="noteEval" required="true"><br>
		               
		               <label for="comCourtEval">Commentaire court (résumé):</label>
		               <input type="text" name="comCourtEval" id="comCourtEval" required="true"><br>
		               
		               <label for="comLongEval">Commentaire détaillé:</label>
		               <input type="text" id="comLongEval" name="comLongEval" required="true"><br>
		               
		               <label for="autreComEval">Un dernier détail ?</label>
		               <input type="text" name="autreConEval" id="autreComEval"><br>
		               
		               <label for="lieuEval">Lieu correspondant a votre évaluation.</label>
		               <select name="comboBoxCat" id="comboBoxCat" size="1" onChange="Remplir(this.value);"> 
		               <% for (int i = 0; i < ListeCat.size(); i++) {
		               %>
						<option value="<%=ListeCat.get(i)%>"><%=ListeCat.get(i) %></option> 
						<% } %>
						</select>
		               <select name="comboBoxLieu" id="comboBoxLieu" size="1"> 
						</select>
		               
		               
		               <% //if (session.getAttribute("currentUser") != null) { idUser = u.getId();%>
		               <input type="hidden" name="idUser" id="idUser" value=15><br>
		               <% //} %>
		               <input type="submit" value="Ajouter">
		           </form>
		           
		           <% } %>
		       </fieldset>
</body>
</html>