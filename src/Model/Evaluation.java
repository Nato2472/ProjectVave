package Model;

import java.util.Date;

public class Evaluation {

	private double id = 0;
	private Date dateEval = null;
	private String nomEval = null;
	private float note = 0;
	private String comCourt = null;
	private String comLong = null;
	private String autreEva = null;
	private double id_uti = 0;
	private double id_eta = 0;
	private double id_Cate = 0;
	
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public Date getDateEval() {
		return dateEval;
	}
	public void setDateEval(Date dateEval) {
		this.dateEval = dateEval;
	}
	public float getNote() {
		return note;
	}
	public void setNote(float note) {
		this.note = note;
	}
	public String getComCourt() {
		return comCourt;
	}
	public void setComCourt(String comCourt) {
		this.comCourt = comCourt;
	}
	public String getComLong() {
		return comLong;
	}
	public void setComLong(String comLong) {
		this.comLong = comLong;
	}
	public String getAutreEva() {
		return autreEva;
	}
	public void setAutreEva(String autreEva) {
		this.autreEva = autreEva;
	}
	public double getId_uti() {
		return id_uti;
	}
	public void setId_uti(double id_uti) {
		this.id_uti = id_uti;
	}
	public double getId_eta() {
		return id_eta;
	}
	public void setId_eta(double id_eta) {
		this.id_eta = id_eta;
	}
	public String getNomEval() {
		return nomEval;
	}
	public void setNomEval(String nomEval) {
		this.nomEval = nomEval;
	}
	public double getId_Cate() {
		return id_Cate;
	}
	public void setId_Cate(double id_Cate) {
		this.id_Cate = id_Cate;
	}
	
	public Evaluation() {
		super();
	}
	public Evaluation(String nomEval, float note, String comCourt,
			String comLong, String autreEva) {
		super();
		this.nomEval = nomEval;
		this.note = note;
		this.comCourt = comCourt;
		this.comLong = comLong;
		this.autreEva = autreEva;
	}
	
	
}
