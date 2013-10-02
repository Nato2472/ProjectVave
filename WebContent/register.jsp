<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
String GETnom = "";
String GETprenom = "";
String GETpseudo = "";
String GETlogin = "";


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'inscription</title>
</head>
<body>
<%
System.out.println("err = " + request.getParameter("err"));
	if (request.getParameter("err") != null) {
		String desErr = request.getParameter("err");
		if (desErr.equals("pass")) {
			%><script>alert('Les mots de passe ne sont pas identiques');</script><%
		} else if (desErr.equals("user")) {
			%><script>alert('Cet utilisateur existe déjà.');</script><%
		} else if (desErr.equals("err")) {
			%><script>alert('Une erreur empèche l\'inscription.');</script><%
		} else if (desErr.equals("3car")) {
			%><script>alert('Chaque champs doit au moins contenir 3 caractères.');</script><%
		} else {
			%><script>alert('Vous cherchez les problèmes !');</script><%
		}
		
		GETnom = request.getParameter("nom");
		GETprenom = request.getParameter("prenom");
		GETpseudo = request.getParameter("pseudo");
		GETlogin = request.getParameter("login");	
	}
	
	if (request.getParameter("reg") != null) {
		%><FONT color="green">Vous êtes correctement inscrit !</FONT>
		<a href="Login.jsp">Cliquer ici pour vous connecter !</a><%
	}
%>



	<fieldset>
              <form action="RegisterCheck" method="post">
                  <label for="nom">Votre nom :</label>
                  <input type="text" name="nom" id="nom" value="<%=GETnom %>" required="true"><br>
                  <label for="prenom">Votre prénom :</label>
                  <input type="text" name="prenom" id="prenom" value="<%=GETprenom %>"required="true"><br>
                  
                  <label for="pseudo" title="Le pseudo sera visible par tout utilisateur du site.">Votre pseudo :</label>
                  <input type="text" name="pseudo" id="pseudo" value="<%=GETpseudo %>"required="true"><br>
                 
                  <label for="login" title="Le login est utilisé pour la connexion au site, il est strictement personnel.">Votre login :</label>
                  <input type="text" name="login" id="login" value="<%=GETlogin %>"required="true"><br>
                  
                  <label for="password">Mot de passe:</label>
                  <input type="password" id="password" name="password" required="true"><br>
                   <label for="password2">Retapez le mot de passe:</label>
                  <input type="password" id="password2" name="password2" required="true"><br>
                  
                  <br>
                  <input type="submit" value="S'incrire">
              </form>
          </fieldset>
</body>
</html>