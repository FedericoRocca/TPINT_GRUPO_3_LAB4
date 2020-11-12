package dominio;

public class Phone {
	
	private int number;
	private String description;
	private User user;
	private boolean status;
	
	//Constructors
	public Phone(){
		
	}
	
	public Phone(int number, String description, User user, boolean status) {
		super();
		this.number = number;
		this.description = description;
		this.user = user;
		this.status = status;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	//To String
	@Override
	public String toString() {
		return "Phone [number=" + number + ", description=" + description + ", user=" + user + ", status=" + status
				+ "]";
	}
	
	
}
