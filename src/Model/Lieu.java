package Model;

public class Lieu {

	private double id = 0;
	private String nom = null;
	private int codepostal =0;
	private String ville = null;
	private String adresse = null;
	private String telephone = null;
	private int id_cate = 0;
	
	
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getCodepostal() {
		return codepostal;
	}
	public void setCodepostal(int codepostal) {
		this.codepostal = codepostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getId_cate() {
		return id_cate;
	}
	public void setId_cate(int id_cate) {
		this.id_cate = id_cate;
	}
	
	public Lieu() {
		super();
	}
	public Lieu(double id, String nom, int codepostal, String ville,
			String adresse, String telephone, int id_cate) {
		super();
		this.id = id;
		this.nom = nom;
		this.codepostal = codepostal;
		this.ville = ville;
		this.adresse = adresse;
		this.telephone = telephone;
		this.id_cate = id_cate;
	}
}
