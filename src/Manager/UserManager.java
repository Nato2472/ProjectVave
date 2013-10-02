package Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.DatabaseHelper;
import Model.User;

public class UserManager {

	private ArrayList<User> users = null;

	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	@SuppressWarnings("unused")
	public UserManager() {
		ArrayList<User> evaluations = new ArrayList<User>();
	}
	
	public void AddUser(User u){
		String query = "INSERT INTO UTILISATEUR (Login_Uti, Prenom_Uti, Nom_Uti, Pseudo_Uti, Mdp_Uti, Date_Inscrip_Uti)"
				+" VALUES ('" + u.getLogin() + "','" + u.getPrenom() + "','" + u.getNom() + "','" + u.getPseudo() + "','"
				+ u.getMdp() + "','" + u.getDate() + "')";
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
	}
	public void DelUser(User u){
		String query = "DELETE FROM UTILISATEUR WHERE Id_Uti = " + u.getId();
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
	}	
	public void GetListeUser(){
		String query = "SELECT  Id_Uti,Login_Uti, Prenom_Uti, Nom_Uti, Pseudo_Uti, Mdp_Uti, Date_Inscrip_UtiFROM EVALUATION";
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		
		rs = db.executeQuery(query);
		try {
			while(rs.next()){
				User u = new User();
				u.setId(rs.getDouble(1));
				u.setLogin(rs.getString(2));
				u.setPrenom(rs.getString(3));
				u.setNom(rs.getString(4));
				u.setPseudo(rs.getString(5));
				u.setMdp(rs.getString(6));
				u.setDate(rs.getDate(7));
				
				this.users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}