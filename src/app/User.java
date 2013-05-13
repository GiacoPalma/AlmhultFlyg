package app;

public class User {
	public int id;
	public String email;
	public String phonenumber;
	public String first_name;
	public String last_name;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getAdmin_status() {
		return admin_status;
	}


	public void setAdmin_status(int admin_status) {
		this.admin_status = admin_status;
	}


	public String password;
	public int admin_status;
	
	
	public String getName(){
		
		return first_name+" "+last_name;
	
	}
}
