package com.gcu.test.models;

import javax.validation.constraints.Size;

public class LoginModel
{
    @Size(min=1, max=32, message="Username must be between 1 and 32 characters")
    private String username;
    
    @Size(min=1, max=32, message="Password must be between 1 and 32 characters")
    private String password;

    public LoginModel() {
		super();
		this.username = "";
		this.password = "";
	}

	public LoginModel(@Size(min = 1, max = 32, message = "Username must be between 1 and 32 characters") String username,
					  @Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters")String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() { return username; }
	
	public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}