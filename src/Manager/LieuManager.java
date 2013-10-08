package Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.LMUL;

import Model.Categorie;
import Model.DatabaseHelper;
import Model.Lieu;

public class LieuManager {
	
	private ArrayList<Lieu> lieux = null;

	public ArrayList<Lieu> getLieux() {
		return lieux;
	}
	public void setLieux(ArrayList<Lieu> lieux) {
		this.lieux = lieux;
	}

	public LieuManager() {
		this.lieux = new ArrayList<Lieu>();
	}
	
	public void AddLieu(Lieu l){
		String query = "INSERT INTO ETABLISSEMENT (Nom_Eta,Adr_Eta,CP_Eta,Ville_Eta,Num_Tel_Eta,Id_Cate)"
				+" VALUES ('" + l.getNom() + "','"  + l.getAdresse() + "','" + l.getCodepostal() + "','" + l.getVille() + "','" + l.getTelephone() + "','"
				+ l.getId_cate() + "')";
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
		db.ConnectionClose();
	}
	public void ModifyLieu(Lieu l){
		String query = "UPDATE ETABLISSEMENT SET Nom_Eta = '" + l.getNom() + "', Adr_Eta = '" + l.getAdresse()
				+ "',CP_Eta = '" + l.getCodepostal() + "',Ville_Eta = '" + l.getVille() + "',Num_Tel_Eta = '" 
				+ l.getTelephone() +"',Id_Cate = " + l.getId_cate() + " WHERE Id_Eta =" + l.getId();
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
		db.ConnectionClose();
	}	
	public void DelLieu(Lieu l){
		String query = "DELETE FROM ETABLISSEMENT WHERE Id_Eta = " + l.getId();
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
		db.ConnectionClose();
	}	
	public void GetListeLieu(){
 		String query = "SELECT Id_Eta,Nom_Eta,Adr_Eta,CP_Eta,Ville_Eta,Num_Tel_Eta,Id_Cate FROM ETABLISSEMENT";
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		
		rs = db.executeQuery(query);
		try {
			while(rs.next()){
				Lieu l = new Lieu();
				l.setId(rs.getDouble(1));
				l.setNom(rs.getString(2));
				l.setAdresse(rs.getString(3));
				l.setCodepostal(rs.getInt(4));
				l.setVille(rs.getString(5));
				l.setTelephone(rs.getString(6));
				l.setId_cate(rs.getInt(7));
				
				
				this.lieux.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.ConnectionClose();
		}
	}
	
	public Lieu GetLieuById(double id){
		String query = "SELECT Id_Eta,Adr_Eta,CP_Eta,Ville_Eta,Num_Tel_Eta,Id_Cate,Nom_Eta FROM ETABLISSEMENT WHERE Id_Eta = " + id;
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		Lieu l = new Lieu();
		
		rs = db.executeQuery(query);
		try {
			if(rs.next()){
				l.setId(rs.getDouble(1));
				l.setAdresse(rs.getString(2));
				l.setCodepostal(rs.getInt(3));
				l.setVille(rs.getString(4));
				l.setTelephone(rs.getString(5));
				l.setId_cate(rs.getInt(6));
				l.setNom(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.ConnectionClose();
		}
		return l;
	}
	public Lieu GetLieuByNom(String nom){
		String query = "SELECT Id_Eta,Adr_Eta,CP_Eta,Ville_Eta,Num_Tel_Eta,Id_Cate,Nom_Eta FROM ETABLISSEMENT WHERE Nom_Eta = '" + nom + "'";
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		Lieu l = new Lieu();
		
		rs = db.executeQuery(query);
		try {
			if(rs.next()){
				l.setId(rs.getDouble(1));
				l.setAdresse(rs.getString(2));
				l.setCodepostal(rs.getInt(3));
				l.setVille(rs.getString(4));
				l.setTelephone(rs.getString(5));
				l.setId_cate(rs.getInt(6));
				l.setNom(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.ConnectionClose();
		}
		return l;
	}
	public boolean GetFree(Lieu l){
		boolean valid = false;
		this.GetListeLieu();
		
		for(Lieu lieu: this.lieux){
			if (lieu.equals(l)){
				valid = false;
				break;
			} else {
				valid = true;
			}
		}
		if(l.getNom().equals("null")){
			valid = false;
		}
		return valid;
	}
}
