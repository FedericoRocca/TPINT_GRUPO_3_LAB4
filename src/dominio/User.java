package entidades;
import java.util.Date;

public class User {
	
	private String dni;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String cuil;
	private String gender; /*era de tipo Character*/
	private String nacionality;
	private Date birthDate;
	private String address;
	private String email;
	private boolean Status;

	//Constructors
	public User() {
	
	}
	
	public User(String dni, String firstName, String lastName, String userName, String password, String cuil,
			String gender, String nacionality, Date birthDate, String address, String email, boolean status) {
		super();
		this.dni = dni;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.cuil = cuil;
		this.gender = gender;
		this.nacionality = nacionality;
		this.birthDate = birthDate;
		this.address = address;
		this.email = email;
		Status = status;
	}

	//Getters & Setters
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNacionality() {
		return nacionality;
	}
	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}

	
	//To String
	@Override
	public String toString() {
		return "User [dni=" + dni + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", cuil=" + cuil + ", gender=" + gender + ", nacionality=" + nacionality
				+ ", birthDate=" + birthDate + ", address=" + address + ", email=" + email + ", Status=" + Status + "]";
	}
	
		
}
