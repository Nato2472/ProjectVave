package Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Categorie;
import Model.DatabaseHelper;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("test");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("test");
		} finally{
			db.ConnectionClose();
		}
		
		return c;
	}
}
