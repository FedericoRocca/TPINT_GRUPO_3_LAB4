package dominio;
import java.util.ArrayList;
import java.util.Date;

public class User {
	
    public Province getProvince()
    {
        return province;
    }

    public void setProvince(Province province)
    {
        this.province = province;
    }

    private String dni;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String cuil;
	private String gender; /*era de tipo Character*/
	private String nacionality;
	private Nationality nation;
	private Date birthDate;
	private String address;
	private String city;
	private String email;
	private ArrayList<Phone> phones;
	private Role rol;
	private boolean status;
	private Province province;

	public User(String dni, String firstName, String lastName, String userName, String password, String cuil,
            String gender, String nacionality, Nationality nation, Date birthDate, String address, String city,
            String email, ArrayList<Phone> phones, Role rol, boolean status, Province province)
    {
        super();
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cuil = cuil;
        this.gender = gender;
        this.nacionality = nacionality;
        this.nation = nation;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phones = phones;
        this.rol = rol;
        this.status = status;
        this.province = province;
    }

    //Constructors
	public User() {
		super();
		phones = new ArrayList<Phone>();
	}
	
	public User(String dni, String firstName, String lastName, String userName, String password, String cuil,
			String gender, String nacionality, Date birthDate, String address, String city, String email, ArrayList<Phone> phones, boolean status, Role rol) {
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
		this.phones = phones;
		this.status = status;
		this.rol = rol;
	}
	
	public User(String dni, String firstName, String lastName, String userName, String password, String cuil,
            String gender, String nacionality, Date birthDate, String address, String city, String email, ArrayList<Phone> phones, boolean status) {
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
        this.phones = phones;
        this.status = status;
    }

	   public User(String dni, String firstName, String lastName, String userName, String password, String cuil,
            String gender, Nationality nation, Date birthDate, String address, String city, String email, boolean status) {
        super();
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cuil = cuil;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.email = email;
        this.status = status;
        this.nation = nation;
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
	    
	    public User(String dni, String firstName, String lastName, String userName, String password, String cuil,
                String gender, Nationality nation, Date birthDate, String address, String city, String email, boolean status, Role rol) {
            super();
            this.dni = dni;
            this.firstName = firstName;
            this.lastName = lastName;
            this.userName = userName;
            this.password = password;
            this.cuil = cuil;
            this.gender = gender;
            this.nation = nation;
            this.birthDate = birthDate;
            this.address = address;
            this.city = city;
            this.email = email;
            this.status = status;
            this.rol = rol;
        }
	    
	    public Nationality getNation()
	    {
	        return nation;
	    }

	    public void setNation(Nationality nation)
	    {
	        this.nation = nation;
	    }

	    public ArrayList<Phone> getPhones()
	    {
	        return phones;
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

	public ArrayList<Phone> getPhone() {
		return phones;
	}

	public void setPhones(ArrayList<Phone> phones) {
		this.phones = phones;
	}
	
	public void addPhone(Phone newPhone) {
	    this.phones.add(newPhone);
    }

	@Override
	public String toString() {
		return "User [dni=" + dni + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", cuil=" + cuil + ", gender=" + gender + ", nacionality=" + nacionality
				+ ", birthDate=" + birthDate + ", address=" + address + ", city=" + city + ", email=" + email
				+ ", phone=" + phones + ", status=" + status + "]";
	}

}
