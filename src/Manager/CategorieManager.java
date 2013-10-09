package Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Categorie;
import Model.DatabaseHelper;
import Model.Lieu;

public class CategorieManager {

	private ArrayList<Categorie> categories;

	public ArrayList<Categorie> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<Categorie> categories) {
		this.categories = categories;
	}

	public CategorieManager() {
		this.categories = new ArrayList<Categorie>();
	}
	
	public void AddCate(Categorie c){
		String query = "INSERT INTO CATEGORIE (Nom_Cate, Descrip_Cate)"
				+" VALUES ('" + c.getNom() + "','" + c.getDescription() + "')";
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
		db.ConnectionClose();
	}
	public void ModifyCate(Categorie c){
		String query = "UPDATE CATEGORIE SET Nom_Cate = '" + c.getNom() + "', Descrip_Cate= '" + c.getDescription() + "' WHERE Id_Cate = " + c.getId();
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
		db.ConnectionClose();
	}
	public void DelCate(Categorie c){
		String query = "DELETE FROM CATEGORIE WHERE Id_Cate = " + c.getId();
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
		db.ConnectionClose();
	}	
	public void GetListeCate(){
		String query = "SELECT Id_Cate,Nom_Cate,Descrip_Cate FROM CATEGORIE";
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		
		rs = db.executeQuery(query);
		try {
			while(rs.next()){
				Categorie c = new Categorie();
				c.setId(rs.getInt(1));
				c.setNom(rs.getString(2));
				c.setDescription(rs.getString(3));
				
				this.categories.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.ConnectionClose();
		}
	}
	
	public Categorie GetCateByNom(String nom){
		String query = "SELECT Id_Cate,Nom_Cate,Descrip_Cate FROM CATEGORIE WHERE Nom_Cate ='" + nom + "'";
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		Categorie c = new Categorie();
		
		rs = db.executeQuery(query);
		try {
			if(rs.next()){
				c.setId(rs.getInt(1));
				c.setNom(rs.getString(2));
				c.setDescription(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.ConnectionClose();
		}
		
		return c;
	}
	
	public Categorie GetCateById(int id){
		String query = "SELECT Id_Cate,Nom_Cate,Descrip_Cate FROM CATEGORIE WHERE Id_Cate =" + id ;
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		Categorie c = new Categorie();
		
		rs = db.executeQuery(query);
		try {
			if(rs.next()){
				c.setId(rs.getInt(1));
				c.setNom(rs.getString(2));
				c.setDescription(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.ConnectionClose();
		}
		
		return c;
	}
	public int GetCateIdByNom(String nom){
		String query = "SELECT Id_Cate,Nom_Cate,Descrip_Cate FROM CATEGORIE WHERE Nom_Cate ='" + nom + "'";
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		Categorie c = new Categorie();
		
		rs = db.executeQuery(query);
		try {
			if(rs.next()){
				c.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.ConnectionClose();
		}
		
		return c.getId();
	}
	
	// Méthode permetant de vérifier l'existance d'une catégorie
	public boolean GetFreeForName(String nom){
		String query = "SELECT count(Nom_Cate) FROM CATEGORIE WHERE Nom_Cate ='" + nom + "'";
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		boolean val = true;
		
		rs = db.executeQuery(query);
		try {
			if(rs.next()){
				if (rs.getInt(1) >= 1){
					val = false;
				}else{
					val = true;
				}
			}else{
				val = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.ConnectionClose();
		}
		if (nom.equals("null")){
			val = false;
		}
		return val;
	}
	
	
	public boolean GetFree(Categorie c){
		boolean valid = false;
		this.GetListeCate();
		
		for(Categorie catego: this.categories){
			if (catego.equals(c)){
				valid = false;
				break;
			} else {
				valid = true;
			}
		}
		if(c.getNom().equals("null")){
			valid = false;
		}
		return valid;
	}
}
