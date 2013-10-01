<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'inscription</title>
</head>
<body>
	<fieldset>
              <form action="RegisterCheck" method="post">
                  <label for="nom">Votre nom :</label>
                  <input type="text" name="nom" id="nom" required="true"><br>
                  <label for="prenom">Votre prénom :</label>
                  <input type="text" name="prenom" id="prenom" required="true"><br>
                  
                  <label for="pseudo" title="Le pseudo sera visible par tout utilisateur du site.">Votre pseudo :</label>
                  <input type="text" name="pseudo" id="pseudo" required="true"><br>
                 
                  <label for="login" title="Le login est utilisé pour la connexion au site, il est strictement personnel.">Votre login :</label>
                  <input type="text" name="login" id="login" required="true"><br>
                  
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