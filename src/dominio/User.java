package dominio;
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
	private String city;
	private String email;
	private Phone phone;
	private Role rol;
	private boolean status;

	//Constructors
	public User() {
		super();
	}
	
	public User(String dni, String firstName, String lastName, String userName, String password, String cuil,
			String gender, String nacionality, Date birthDate, String address, String city, String email, Phone phone, boolean status, Role rol) {
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
		this.city = city;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.rol = rol;
	}
	
	public User(String dni, String firstName, String lastName, String userName, String password, String cuil,
            String gender, String nacionality, Date birthDate, String address, String city, String email, Phone phone, boolean status) {
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
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

	   public User(String dni, String firstName, String lastName, String userName, String password, String cuil,
            String gender, String nacionality, Date birthDate, String address, String city, String email, boolean status) {
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
        this.city = city;
        this.email = email;
        this.status = status;
    }

	    public User(String dni, String firstName, String lastName, String userName, String password, String cuil,
	            String gender, String nacionality, Date birthDate, String address, String city, String email, boolean status, Role rol) {
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
	        this.city = city;
	        this.email = email;
	        this.status = status;
	        this.rol = rol;
	    }

    /**
     * @return the rol
     */
    public Role getRol()
    {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Role rol)
    {
        this.rol = rol;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [dni=" + dni + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", cuil=" + cuil + ", gender=" + gender + ", nacionality=" + nacionality
				+ ", birthDate=" + birthDate + ", address=" + address + ", city=" + city + ", email=" + email
				+ ", phone=" + phone + ", status=" + status + "]";
	}

}
