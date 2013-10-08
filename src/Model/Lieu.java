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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + codepostal;
		result = prime * result + id_cate;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lieu other = (Lieu) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (codepostal != other.codepostal)
			return false;
		if (id_cate != other.id_cate)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
		}
	
}
