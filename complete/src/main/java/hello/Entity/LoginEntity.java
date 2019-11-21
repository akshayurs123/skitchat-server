package hello.Entity;

import javax.persistence.Entity;


public class LoginEntity {
 
    private  String username;
    private  String password;
    
    
    public LoginEntity(){}
 

	public String getUsername() {
		return username;
	}

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
