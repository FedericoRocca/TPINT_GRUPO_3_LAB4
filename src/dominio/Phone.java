package dominio;

public class Phone {
	
	private int number;
	private String description;
	
	//Constructors
	public Phone(){
		
	}
	
	public Phone(int number, String description) {
		super();
		this.number = number;
		this.description = description;
	}
		
	//Getters & Setters
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
		return "Phone [number=" + number + ", description=" + description + "]";
	}
	
	
}
