package dominio;

import java.sql.Date;

public class User
{
    int dni;
    String firstName;
    String lastName;
    String userName;
    String password;
    String cuil;
    Character genero;
    Nacionality nacionalidad;
    Date birthDate;
    String adress;
    City city;
    String email;
    boolean status;
    
    public User()
    {
        super();
    }
    public User(int dni, String firstName, String lastName, String userName, String password, String cuil,
            Character genero, Nacionality nacionalidad, Date birthDate, String adress, City city, String email,
            boolean status)
    {
        super();
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cuil = cuil;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.birthDate = birthDate;
        this.adress = adress;
        this.city = city;
        this.email = email;
        this.status = status;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "User [dni=" + dni + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
                + ", password=" + password + ", cuil=" + cuil + ", genero=" + genero + ", nacionalidad=" + nacionalidad
                + ", birthDate=" + birthDate + ", adress=" + adress + ", city=" + city + ", email=" + email
                + ", status=" + status + "]";
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adress == null) ? 0 : adress.hashCode());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((cuil == null) ? 0 : cuil.hashCode());
        result = prime * result + dni;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((genero == null) ? 0 : genero.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + (status ? 1231 : 1237);
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (adress == null)
        {
            if (other.adress != null)
                return false;
        }
        else if (!adress.equals(other.adress))
            return false;
        if (birthDate == null)
        {
            if (other.birthDate != null)
                return false;
        }
        else if (!birthDate.equals(other.birthDate))
            return false;
        if (city == null)
        {
            if (other.city != null)
                return false;
        }
        else if (!city.equals(other.city))
            return false;
        if (cuil == null)
        {
            if (other.cuil != null)
                return false;
        }
        else if (!cuil.equals(other.cuil))
            return false;
        if (dni != other.dni)
            return false;
        if (email == null)
        {
            if (other.email != null)
                return false;
        }
        else if (!email.equals(other.email))
            return false;
        if (firstName == null)
        {
            if (other.firstName != null)
                return false;
        }
        else if (!firstName.equals(other.firstName))
            return false;
        if (genero == null)
        {
            if (other.genero != null)
                return false;
        }
        else if (!genero.equals(other.genero))
            return false;
        if (lastName == null)
        {
            if (other.lastName != null)
                return false;
        }
        else if (!lastName.equals(other.lastName))
            return false;
        if (nacionalidad == null)
        {
            if (other.nacionalidad != null)
                return false;
        }
        else if (!nacionalidad.equals(other.nacionalidad))
            return false;
        if (password == null)
        {
            if (other.password != null)
                return false;
        }
        else if (!password.equals(other.password))
            return false;
        if (status != other.status)
            return false;
        if (userName == null)
        {
            if (other.userName != null)
                return false;
        }
        else if (!userName.equals(other.userName))
            return false;
        return true;
    }
    /**
     * @return the dni
     */
    public int getDni()
    {
        return dni;
    }
    /**
     * @param dni the dni to set
     */
    public void setDni(int dni)
    {
        this.dni = dni;
    }
    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    /**
     * @return the userName
     */
    public String getUserName()
    {
        return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    /**
     * @return the cuil
     */
    public String getCuil()
    {
        return cuil;
    }
    /**
     * @param cuil the cuil to set
     */
    public void setCuil(String cuil)
    {
        this.cuil = cuil;
    }
    /**
     * @return the genero
     */
    public Character getGenero()
    {
        return genero;
    }
    /**
     * @param genero the genero to set
     */
    public void setGenero(Character genero)
    {
        this.genero = genero;
    }
    /**
     * @return the nacionalidad
     */
    public Nacionality getNacionalidad()
    {
        return nacionalidad;
    }
    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(Nacionality nacionalidad)
    {
        this.nacionalidad = nacionalidad;
    }
    /**
     * @return the birthDate
     */
    public Date getBirthDate()
    {
        return birthDate;
    }
    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }
    /**
     * @return the adress
     */
    public String getAdress()
    {
        return adress;
    }
    /**
     * @param adress the adress to set
     */
    public void setAdress(String adress)
    {
        this.adress = adress;
    }
    /**
     * @return the city
     */
    public City getCity()
    {
        return city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(City city)
    {
        this.city = city;
    }
    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    /**
     * @return the status
     */
    public boolean isStatus()
    {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(boolean status)
    {
        this.status = status;
    }
}
