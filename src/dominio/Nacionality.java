package dominio;

public class Nacionality {
	
	private int id;
	private String description;
	
	//Constructors
	public Nacionality() {
		
	}
	

	public Nacionality(int id, String description) {
		super();
		this.id = id;
		this.description = description;
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

	//To String
	@Override
	public String toString() {
		return "Nacionality [id=" + id + ", description=" + description + "]";
	}
	
	//
}
