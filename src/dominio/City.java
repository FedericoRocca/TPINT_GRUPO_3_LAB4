package dominio;

public class City {
	
	private int id;
	private String description;
	private int postalCode;
	private Province province;
	
	//Constructors
	public City() {
		
	}

	public City(int id, String description, int postalCode, Province province) {
		super();
		this.id = id;
		this.description = description;
		this.postalCode = postalCode;
		this.province = province;
	}

	//Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}

	
	//To String
	@Override
	public String toString() {
		return "City [id=" + id + ", description=" + description + ", postalCode=" + postalCode + ", province="
				+ province + "]";
	}
	

}
