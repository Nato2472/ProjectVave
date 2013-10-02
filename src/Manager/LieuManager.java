package Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	@SuppressWarnings("unused")
	public LieuManager() {
		ArrayList<Lieu> lieux = new ArrayList<Lieu>();
	}
	
	public void AddLieu(Lieu l){
		String query = "INSERT INTO ETABLISSEMENT (Adr_Eta,CP_Eta,Ville_Eta,Num_Tel_Eta,Id_Cate)"
				+" VALUES ('" + l.getAdresse() + "','" + l.getCodepostal() + "','" + l.getVille() + "','" + l.getTelephone() + "','"
				+ l.getId_cate() + "')";
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
	}	
	public void DelLieu(Lieu l){
		String query = "DELETE FROM ETABLISSEMENT WHERE Id_Eta = " + l.getId();
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
	}	
	public void GetListeLieu(){
		String query = "SELECT Id_Eta,Adr_Eta,CP_Eta,Ville_Eta,Num_Tel_Eta,Id_Cate FROM ETABLISSEMENT";
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		
		rs = db.executeQuery(query);
		try {
			while(rs.next()){
				Lieu l = new Lieu();
				l.setId(rs.getDouble(1));
				l.setAdresse(rs.getString(2));
				l.setCodepostal(rs.getInt(3));
				l.setVille(rs.getString(4));
				l.setTelephone(rs.getString(5));
				l.setId_cate(rs.getInt(6));
				
				
				this.lieux.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
