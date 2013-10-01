package Model;

public class Categorie {

	private int id =0;
	private String nom = null;
	private String description = null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Categorie() {
		super();
	}
	public Categorie(int id, String nom, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
	}
	
}
