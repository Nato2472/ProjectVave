package Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Categorie;
import Model.DatabaseHelper;
import Model.Evaluation;
import Model.Lieu;

public class EvalManager {

	private ArrayList<Evaluation> evaluations = null;
	private ArrayList<Evaluation> evalcate = null;
	private ArrayList<Evaluation> evallast = null;
	private ArrayList<Evaluation> evallieu = null;
	
	public ArrayList<Evaluation> getEvaluations() {
		return evaluations;
	}
	public void setEvaluations(ArrayList<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
	public ArrayList<Evaluation> getEvalcate() {
		return evalcate;
	}
	public void setEvalcate(ArrayList<Evaluation> evalcate) {
		this.evalcate = evalcate;
	}
	public ArrayList<Evaluation> getEvallast() {
		return evallast;
	}
	public void setEvallast(ArrayList<Evaluation> evallast) {
		this.evallast = evallast;
	}
	public ArrayList<Evaluation> getEvallieu() {
		return evallieu;
	}
	public void setEvallieu(ArrayList<Evaluation> evallieu) {
		this.evallieu = evallieu;
	}
	
	public EvalManager() {
		this.evaluations = new ArrayList<Evaluation>();
		this.evalcate = new ArrayList<Evaluation>();
		this.evallast = new ArrayList<Evaluation>();
		this.evallieu = new ArrayList<Evaluation>();
	}
	
	public void AddEval(Evaluation e, int id_user, int id_eta){
		String query = "INSERT INTO EVALUATION (Date_Eva,Note_Eva,Com_Cour_Eva,Com_Long_Eva,Autre_Eva,Id_Uti,Id_Eta)"
				+" VALUES ('" + e.getDateEval() + "','" + e.getNote() + "','" + e.getComCourt() + "','" + e.getComLong() + "','"
				+ e.getAutreEva() + "','" + id_user + "','" + id_eta + "')";
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
		db.ConnectionClose();
	}
	public void DelEval(Evaluation e){
		String query = "DELETE FROM EVALUATION WHERE Id_Eva = " + e.getId();
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
		db.ConnectionClose();
	}	
	public void GetListeEval(){
		String query = "SELECT Id_Eva,Date_Eva,Note_Eva,Com_Cour_Eva,Com_Long_Eva,Autre_Eva,Id_Uti,Id_Eta,Id_Cate,Nom_Eva FROM EVALUATION";
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		
		rs = db.executeQuery(query);
		try {
			while(rs.next()){
				Evaluation e = new Evaluation();
				e.setId(rs.getDouble(1));
				e.setDateEval(rs.getDate(2));
				e.setNote(rs.getFloat(3));
				e.setComCourt(rs.getString(4));
				e.setComLong(rs.getString(5));
				e.setAutreEva(rs.getString(6));
				e.setId_uti(rs.getInt(7));
				e.setId_eta(rs.getInt(8));
				e.setId_cate(rs.getInt(9));
				e.setNom(rs.getString(10));
				
				this.evaluations.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.ConnectionClose();
		}
	}
	public void GetListEvalByCate(Categorie c){
		String query = "SELECT Id_Eva,Date_Eva,Note_Eva,Com_Cour_Eva,Com_Long_Eva,Autre_Eva,Id_Uti,Id_Eta,Nom_Eva FROM EVALUATION WHERE Id_Cate = " + c.getId();
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		
		rs = db.executeQuery(query);
		try {
			while(rs.next()){
				Evaluation e = new Evaluation();
				e.setId(rs.getDouble(1));
				e.setDateEval(rs.getDate(2));
				e.setNote(rs.getFloat(3));
				e.setComCourt(rs.getString(4));
				e.setComLong(rs.getString(5));
				e.setAutreEva(rs.getString(6));
				e.setId_uti(rs.getInt(7));
				e.setId_eta(rs.getInt(8));
				e.setNom(rs.getString(9));
				e.setId_cate(c.getId());
				
				this.evalcate.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.ConnectionClose();
		}
	}
	public void GetListEvalByLieu(Lieu l){
		String query = "SELECT Id_Eva,Date_Eva,Note_Eva,Com_Cour_Eva,Com_Long_Eva,Autre_Eva,Id_Uti,Id_Eta,Nom_Eva FROM EVALUATION WHERE Id_Eta = " + l.getId();
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		
		rs = db.executeQuery(query);
		try {
			while(rs.next()){
				Evaluation e = new Evaluation();
				e.setId(rs.getDouble(1));
				e.setDateEval(rs.getDate(2));
				e.setNote(rs.getFloat(3));
				e.setComCourt(rs.getString(4));
				e.setComLong(rs.getString(5));
				e.setAutreEva(rs.getString(6));
				e.setId_uti(rs.getInt(7));
				e.setId_eta(rs.getInt(8));
				e.setNom(rs.getString(9));
				e.setId_cate(l.getId());
				
				this.evallieu.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.ConnectionClose();
		}
	}
	public void GetEvalLast(){
		String query = "SELECT TOP 5 Id_Eva,Date_Eva,Note_Eva,Com_Cour_Eva,Com_Long_Eva,Autre_Eva,Id_Uti,Id_Eta,Id_Cate,Nom_Eva FROM EVALUATION ORDER BY Id_Eva DESC";
		ResultSet rs = null;
		DatabaseHelper db = new DatabaseHelper();
		
		evallast.clear();
		rs = db.executeQuery(query);
		try {
			while(rs.next()){
				Evaluation e = new Evaluation();
				e.setId(rs.getDouble(1));
				e.setDateEval(rs.getDate(2));
				e.setNote(rs.getFloat(3));
				e.setComCourt(rs.getString(4));
				e.setComLong(rs.getString(5));
				e.setAutreEva(rs.getString(6));
				e.setId_uti(rs.getInt(7));
				e.setId_eta(rs.getInt(8));
				e.setId_cate(rs.getInt(9));
				e.setNom(rs.getString(10));
				
				this.evallast.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.ConnectionClose();
		}
	}
}