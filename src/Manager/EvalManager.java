package Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.DatabaseHelper;
import Model.Evaluation;

public class EvalManager {

	private ArrayList<Evaluation> evaluations = null;

	public ArrayList<Evaluation> getEvaluations() {
		return evaluations;
	}
	public void setEvaluations(ArrayList<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public EvalManager() {
		this.evaluations = new ArrayList<Evaluation>();
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
		String query = "SELECT Id_Eva,Date_Eva,Note_Eva,Com_Cour_Eva,Com_Long_Eva,Autre_Eva,Id_Uti,Id_Eta FROM EVALUATION";
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
				
				this.evaluations.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.ConnectionClose();
		}
	}

}