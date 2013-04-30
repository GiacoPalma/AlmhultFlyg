package app;

public class User {
	public int id;
	public String email;
	public String phonenumber;
	public String first_name;
	public String last_name;
	public String password;
	public int admin_status;
	
	
	public String getName(){
		
		return first_name+" "+last_name;
	
	}
}
