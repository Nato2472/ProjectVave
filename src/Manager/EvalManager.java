package Manager;

import java.sql.PreparedStatement;
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

	@SuppressWarnings("unused")
	public EvalManager() {
		ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();
	}
	
	public void AddEval(Evaluation e){
		// Prepared Statement obligatoire donc (malheureusement) objet Evaluation
		// envoyé dans DatabaseManager
		boolean verif = false;
		
		DatabaseHelper db = new DatabaseHelper();
		verif = db.UpdateEval(e);
		if (verif) {
			System.out.println("Ajout évaluation reussi !");
		} else {
			System.out.println("Echec ajout évaluation !");
		}
	}
	
	
	public void DelEval(Evaluation e){
		String query = "DELETE FROM EVALUATION WHERE Id_Eva = " + e.getId();
		
		DatabaseHelper db = new DatabaseHelper();
		db.executeUpdate(query);
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
		}
	}

}