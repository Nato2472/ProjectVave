<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Login.css"/>
<title>Page de connexion</title>
</head>
<body>
<header>
	<div id="logo">
		<img/>
	</div>
	<span id="menu_button">
		<input id="button_cate" type="button" value="Catégorie" onclick="self.location.href='Categorie.jsp'"/>
		<input id="button_lieu" type="button" value="Lieu" onclick="self.location.href='index.html'"/>

		<input id="button_ins" type="button" value="Inscription" method="post" onclick="self.location.href = 'register.jsp'"/>
		
		
	</span>
	<div id="content">

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
			            
	            <a href="register.jsp">Pas encore inscrit ? C'est ici !</a>
		 
		       <% if (session.getAttribute("error") == "1") { %>
		           	<FONT color="red">Mauvais identifiant ou mot de passe !</FONT>
		   		<%
	            }
           	} else {
             	request.getRequestDispatcher("Acceuil.jsp");
            }%>
	</div>
</body>
</html>