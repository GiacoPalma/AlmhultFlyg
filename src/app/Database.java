package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class Database {
	
	public static String url = "jdbc:mysql://localhost:3306/161957-airport";
	public static String user = "root";
	public static String password = "";
<<<<<<< HEAD
=======
<<<<<<< HEAD
<<<<<<< HEAD
	
	public static String current_user = "";
=======
>>>>>>> f9eac770b3b74cafbdb0c0548a70613b214badfa
=======
		
=======
>>>>>>> 04386234443dc5b63b8f5b502bfb138e1c7ccfe2
>>>>>>> 92b2eb9511abb8136f96c7bf779434216aaf3683

>>>>>>> f9eac770b3b74cafbdb0c0548a70613b214badfa
	Connection connection = null;
	public static String driverName = "com.mysql.jdbc.Driver"; // for MySql
	String serverName = "ginger.umd.edu"; // Use this server.
	String portNumber = "1521";
	String sid = "dbclass1";

	public static Airport getAirport(int id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;		

		try {
			try {
				Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				System.out
						.println("ClassNotFoundException : " + e.getMessage());
			}
			con = DriverManager.getConnection(url, user, password);

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM Airports WHERE id=" + id);

			if (rs.next()) {
				Airport airport = new Airport();
				airport.name = rs.getString("name");
				airport.city = rs.getString("city");

				return airport;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<Airport> getAllAirports() {

		List<Airport> ret = new ArrayList<Airport>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				System.out
						.println("ClassNotFoundException : " + e.getMessage());
			}
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery(" SELECT * FROM airports ORDER BY city");

			while (rs.next()) {
				Airport airport = new Airport();
				airport.city = rs.getString("city");
				airport.name = rs.getString("name");
				airport.id = rs.getInt("id");

				ret.add(airport);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}
	
	public static List<Flight> getAllFlights() {

		List<Flight> ret = new ArrayList<Flight>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				System.out
						.println("ClassNotFoundException : " + e.getMessage());
			}
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT A.name AS departure, B.name AS destination, flights.* FROM flights LEFT JOIN airports AS A ON A.id = flights.dep_id LEFT JOIN airports AS B ON B.id = flights.dest_id");

			while (rs.next()) {
				Flight flight = new Flight();
				flight.setId(rs.getInt("id"));
				flight.setDepature_airport_id(rs.getInt("dep_id"));
				flight.setDestination_airport_id(rs.getInt("dest_id"));
				flight.setDepature_date(rs.getString("dep_date"));
				flight.setDestination_date(rs.getString("dest_date"));
				flight.setPrice(rs.getInt("price"));
				Airport airport = new Airport();
				flight.setAirport(airport);
				flight.airport.setName(rs.getString("departure"));
				Airport airportDest = new Airport();
				flight.setDest_airport(airportDest);
				flight.dest_airport.setName(rs.getString("destination"));

				

				ret.add(flight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}
	
	public static List<User> getAllUsers(){
		
		List<User> ret = new ArrayList<User>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				System.out
						.println("ClassNotFoundException : " + e.getMessage());
			}
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM users ORDER BY email");

			while (rs.next()) {
				User user1 = new User();
				user1.id = rs.getInt("id");
				user1.email = rs.getString("email");
				user1.first_name = rs.getString("first_name");
				user1.last_name = rs.getString("last_name");
				user1.admin_status = rs.getInt("admin_status");
				user1.phonenumber = rs.getString("phonenumber");
				ret.add(user1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
		
		
	}

	public static String RemoveAirport(int id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			int delete = st
					.executeUpdate("DELETE FROM airports WHERE id=" + id);

			if (delete == 1) {
				String ret = "Airport has been removed";
				return ret;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String UpdateAirport(int id, String city1, String name1) {
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("UPDATE airports SET city = ?, name = ? WHERE id = "
					+ id);
			st.setString(1, city1);
			st.setString(2, name1);
			st.executeUpdate();
			String ret = "Airport has been updated";
			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String AddAirport(String city1, String name1) {
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("INSERT INTO airports(city, name) VALUES (?, ?)");
			st.setString(1, city1);
			st.setString(2, name1);
			st.executeUpdate();
			String ret = "Airport has been added";
			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String UpdateFlight(int id, int price, int dep_id,
			int dep_date, int dest_id, int dest_date) {
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("UPDATE flights (dep_id, dep_date, dest_id, dest_date, price) VALUES (?, ?, ?, ?, ?) WHERE id = "
					+ id);
			st.setNString(1, Integer.toString(dep_id));
			st.setNString(2, Integer.toString(dep_date));
			st.setNString(3, Integer.toString(dest_id));
			st.setNString(4, Integer.toString(dest_date));
			st.setNString(5, Integer.toString(price));
			st.executeUpdate();
			String ret = "Flight has been updated";
			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String AddFlight(int id, int price, int dep_id, int dep_date,
			int dest_id, int dest_date) {
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("INSERT INTO flights (dep_id, dep_date, dest_id, dest_date, price) VALUES (?, ?, ?, ?, ?)");
			st.setNString(1, Integer.toString(dep_id));
			st.setNString(2, Integer.toString(dep_date));
			st.setNString(3, Integer.toString(dest_id));
			st.setNString(4, Integer.toString(dest_date));
			st.setNString(5, Integer.toString(price));
			st.executeUpdate();
			String ret = "Flight has been added";
			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String RemoveFlight(int id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			st.executeUpdate("DELETE FROM flights WHERE id=" + id);

			String ret = "Flight has been removed";
			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean createFlight(Flight flight) {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			String SQL_INSERT = "INSERT INTO flights (dep_id, dep_date, dest_id, dest_date, price) VALUES (?, ?, ?, ?, ?)";
			st = (PreparedStatement) con.prepareStatement(SQL_INSERT);
			st.setInt(1, flight.getDepature_airport_id());
			st.setString(2, flight.getDepature_date());
			st.setInt(3, flight.getDestination_airport_id());
			st.setString(4, flight.getDestination_date());
			st.setInt(5, flight.getPrice());
			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Att skapa flygningen misslyckades.");
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (Boolean) null;
	}
	
	public static String registerUser(String email, String firstName, String lastName, String phonenumber, int adminStatus, String passWord){
		Connection con = null;
		PreparedStatement st = null;
		String ret = "";

		try {
			con = DriverManager.getConnection(url, user, password);
			String SQL_INSERT = "INSERT INTO users (email, phonenumber, first_name, last_name, admin_status, password) VALUES (?, ?, ?, ?, ?, ?)";
			st = (PreparedStatement) con.prepareStatement(SQL_INSERT);
			st.setString(1, email);
			st.setString(2, phonenumber);
			st.setString(3, firstName);
			st.setString(4, lastName);
			st.setInt(5, adminStatus);
			st.setString(6, passWord);
			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Registreringen misslyckades, v�nligen f�rs�k igen senare.");
			} else {
				ret = "Registreringen lyckades!";
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return ret;
		
	
	}
	
	public static User loginUser(String email, String passWord){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			try {
				Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				System.out
						.println("ClassNotFoundException : " + e.getMessage());
			}
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM users WHERE email='" + email + "' AND password='" + passWord.toString()+"'");

			if (rs.next()) {
				User user = new User();
				user.id = rs.getInt("id");
				user.email = rs.getString("email");
				user.phonenumber = rs.getString("phonenumber");
				user.first_name = rs.getString("first_name");
				user.last_name = rs.getString("last_name");
				user.admin_status = rs.getInt("admin_status");
				
				current_user = rs.getString("email");
				
				return user;
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	
	}
	
	public static String UpdateUser(int id, String email, String firstName, String lastName, String phonenumber, int adminStatus){
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("UPDATE users SET email = ?, phonenumber = ?, first_name = ?, last_name = ?, admin_status = ? WHERE id = "
					+ id);
			st.setString(1, email);
			st.setString(2, phonenumber);
			st.setString(3, firstName);
			st.setString(4, lastName);
			st.setInt(5, adminStatus);
			st.executeUpdate();
			String ret = "User has been updated";
			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static String RemoveUser(int id){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			st.executeUpdate("DELETE FROM users WHERE id=" + id);

			String ret = "User has been removed";
			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
