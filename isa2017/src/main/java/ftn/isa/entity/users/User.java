package ftn.isa.entity.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="USER")
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable {
	
	private static final long serialVersionUID = 2626562778387146532L;

	@Id
	@Column(name="USER_ID")
	@GeneratedValue
	private long id;
	
	@NotNull
	@Email(message = "Email must be a well-formed address")
	@Column(name="USER_EMAIL",unique=true,nullable=false)
	private String email;
	
	@Pattern(regexp="^[A-Z][a-z A-Z]*")
	@NotNull
	@Column(name="USER_NAME",unique=false,nullable=false)
	private String userName;
	
	@Pattern(regexp="^[A-Z][a-z A-Z]*")
	@Column(name="CITY")
	private String city;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="PHONE")
	private String phone;
	
	public long getId() {
		return id;
	}

	@Pattern(regexp="^[A-Z][a-z A-Z]*")
	@NotNull
	@Column(name="USER_SURNAME",unique=false,nullable=false)
	private String surname;
	
	@Pattern(regexp="\\w*")
	@NotNull
	@Column(name="USER_PASS",unique=false,nullable=false)
	private String password;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="mm.dd.yyyy") 
	@Column(name="USER_DATE")
	private Date dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public User(String name, String surname, String email, String password, Date dateOfBirth) {
		this.userName = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}

	public User() {
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	
	public String getUserName() {
		return userName;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public void setId(long id){
		this.id=id;
	}
	
}
