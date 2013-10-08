<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Login.css"/>
<link rel="stylesheet" type="text/css" href="Commun.css"/>
<title>Page de connexion</title>
</head>
<body>
<header>
	<div id="logo">
		<img/>
	</div>
	<span id="menu_button">
		<input id="button_menu_left" type="button" value="Accueil" onclick="self.location.href='Accueil.jsp'"/>

		<input id="button_menu_right" type="button" value="Inscription" method="post" onclick="self.location.href = 'register.jsp'"/>
		
		
	</span>
	<div id="content">
		<div id="texte_co">
		<h1>Bienvenue sur le site Vave.</h1>
		</br>Vave est un site regroupant différent endroit où chaque utilisateur peut effectuer une évaluation sur un endroit</br>ou bien les consultés. Chacun est responsable des informations misent sur le site.
		</br>Pour vous y connecter,
		</br>veuillez renseignez les champs à droite pour effectuer votre connexion.
		
	
		</div>
		<%
            if (session.getAttribute("login") == null) {
        %>
		       <fieldset id="connex" >
		           <form action="Auth" method="post">
		               <label for="login">Nom utilisateur:</label>
		               <input type="text" name="login" id="login" required="true"><br>
		               <label for="password">Mot de passe:</label>
		               <input type="password" id="password" name="password" required="true"><br><br>
		               <input type="submit" value="Login " id="log">
		           </form>
		        </br>   
	            <a href="register.jsp">Pas encore inscrit ? C'est ici !</a>
		       </fieldset>
			            
		 
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