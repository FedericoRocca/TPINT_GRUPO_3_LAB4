package dominio;

public class Role {
	private int id;
	private String nameRole;
	private boolean status;

	//Constructors
	public Role() {
		
	}
	
	public Role(int id, String nameRole, boolean status) {
		super();
		this.id = id;
		this.nameRole = nameRole;
		this.status = status;
	}
	
	//Getters & Setters	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
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
		return "Role [id=" + id + ", nameRole=" + nameRole + ", status=" + status + "]";
	}
		
}
