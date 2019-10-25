package hello.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SoulEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  long id;
    private  String soulId;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String username;
    private  String password;
    private  String location;
    private  String device;
    private  String fu1;
    private  String fu2;
    private  String fu3;
    
    public SoulEntity(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSoulId() {
		return soulId;
	}

	public void setSoulId(String soulId) {
		this.soulId = soulId;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getFu1() {
		return fu1;
	}

	public void setFu1(String fu1) {
		this.fu1 = fu1;
	}

	public String getFu2() {
		return fu2;
	}

	public void setFu2(String fu2) {
		this.fu2 = fu2;
	}

	public String getFu3() {
		return fu3;
	}

	public void setFu3(String fu3) {
		this.fu3 = fu3;
	}

 
    
    
    
}
