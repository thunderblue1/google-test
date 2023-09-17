package com.gcu.test.models;


import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;


@Table("employee")
public class EmployeeModel
{
	@Column("employee_id")
	@Id
	private Long id;
	
	@Column("first_name")
    @NotNull(message="First Name is a required field")
    @Size(min=1, max=32, message="First Name must be between 1 and 32 characters")
    private String firstName;

	@Column("last_name")
    @NotNull(message="Last name is a required field")
    @Size(min=1, max=32, message="Last Name must be between 1 and 32 characters")
    private String lastName;

	@Column("email")
    @NotNull(message="Email is a required field")
    @Size(min=1, max=32, message="Email must be between 1 and 32 characters")
    private String email;

	@Column("phone")
    @NotNull(message="Phone Number is a required field")
    @Size(min=10, max=10, message="Phone must be 10 digits")
    private String phone;

	@Column("position")
    @NotNull(message="Position is a required field")
    @Size(min=1, max=32, message="Position must be between 1 and 32 characters")
    private String position;
	
	@Column("bio")
	private String bio;
	
    @Column("photo")
    @Size(min=0, max=254, message="Password must be between 0 and 254 characters")
    private String photo;
	
	@Column("username")
    @NotNull(message="Username is a required field")
    @Size(min=1, max=32, message="Username must be between 1 and 32 characters")
    private String username;

	@Column("pass")
    @NotNull(message="Password is a required field")
    @Size(min=1, max=32, message="Password must be between 1 and 32 characters")
    private String pass;
    
	public EmployeeModel() {
		super();
		this.id=null;
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.phone = "";
		this.position = "";
		this.bio = "";
		this.photo = "";
		this.username = "";
		this.pass = "";
	}
	public EmployeeModel(
			Long id,
			@NotNull(message = "First Name is a required field") @Size(min = 1, max = 32, message = "First Name must be between 1 and 32 characters") String firstName,
			@NotNull(message = "Last name is a required field") @Size(min = 1, max = 32, message = "Last Name must be between 1 and 32 characters") String lastName,
			@NotNull(message = "Email is a required field") @Size(min = 1, max = 32, message = "Email must be between 1 and 32 characters") String email,
			@NotNull(message = "Phone Number is a required field") @Size(min = 10, max = 10, message = "Phone must be 10 digits") String phone,
			@NotNull(message = "Email is a required field") @Size(min = 1, max = 32, message = "Position must be between 1 and 32 characters") String position,
			String bio,
			String photo,
			@NotNull(message = "Username is a required field") @Size(min = 1, max = 32, message = "Username must be between 1 and 32 characters") String username,
			@NotNull(message = "Password is a required field") @Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters") String pass) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.position = position;
		this.bio = bio;
		this.photo = photo;
		this.username = username;
		this.pass = pass;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String password) {
		this.pass = password;
	}
}