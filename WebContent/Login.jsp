<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de connexion</title>
</head>
<body>

		<%
            if (session.getAttribute("login") == null) {
        %>
	            <fieldset>
	                <form action="Auth" method="post">
	                    <label for="login">Nom utilisateur:</label>
	                    <input type="text" name="login" id="login" required="true"><br>
	                    <label for="password">Mot de passe:</label>
	                    <input type="password" id="password" name="password" required="true"><br><br>
	                    <input type="submit" value="Login ">
	                </form>
	            </fieldset>
	            
	            <a href="Register.jsp">Pas encore inscrit ? C'est ici !</a>
 
	            <% if (session.getAttribute("error") == "1") {
	            	%><FONT color="red">Mauvais identifiant ou mot de passe !</FONT><%
	            }
           	} else {%>
             	<%
             	request.getRequestDispatcher("Acceuil.jsp"); %> 
            <%}%>

</body>
</html>