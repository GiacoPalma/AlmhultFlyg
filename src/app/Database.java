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


	public static String current_user = "";

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
			rs = st.executeQuery("SELECT * FROM airports WHERE id=" + id);

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
	
	public static Flight getFlight(int id) {
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
			rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM `routes` JOIN `flights` ON flights.route1_id = routes.id WHERE flights.id =" + id);

			if (rs.next()) {
				Route route1 = new Route();
				route1.depature_airport_id = rs.getInt("dep_id");
				route1.depature_date = rs.getString("dep_date");
				route1.destination_airport_id = rs.getInt("dest_id");
				route1.destination_date = rs.getString("dest_date");
				route1.price = rs.getInt("price");
				route1.airplane = rs.getInt("airplane");
				route1.distance = rs.getInt("distance");
		
				Flight flight = new Flight(route1);
				flight.id = rs.getInt("flight_id");
				
				
				return flight;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static List<Flight> getAvailableFlights(int dep_id, int dest_id, String dep_date) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Flight> ret = new ArrayList<Flight>();
		
		try {
			try {
				Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				System.out
						.println("ClassNotFoundException : " + e.getMessage());
			}
			con = DriverManager.getConnection(url, user, password);

			st = con.createStatement();
			if(dep_id > 0 && dest_id > 0 && dep_date !=null){
			rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id=" + dep_id + " AND dest_id="+dest_id+" AND dep_date LIKE '"+dep_date+"%'");
			} else if(dep_id == 0 && dest_id > 0 && dep_date != null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dest_id="+dest_id+" AND dep_date LIKE '"+dep_date+"%'");
			} else if(dep_id > 0 && dest_id == 0 && dep_date != null){
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="+dep_id+" AND dep_date LIKE '"+dep_date+"%'");
			} else if(dep_id > 0 && dest_id > 0 && dep_date == null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id=" + dep_id + " AND dest_id="+dest_id);
			} else if(dep_id > 0 && dest_id > 0 && dep_date == null){
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id=" + dep_id + " AND dest_id="+dest_id);
			} else if (dep_id == 0 && dest_id == 0 && dep_date != null){
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_date LIKE '"+dep_date+"%'");
				System.out.println(rs);
			} else if (dep_id > 0 && dest_id == 0 && dep_date == null){
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="+dep_id);
			} else if (dep_id == 0 && dest_id > 0 && dep_date == null){
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dest_id="+dest_id);
			} else if (dep_id == 0 && dest_id == 0 && dep_date == null){
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id");
			}
			
			while (rs.next()) {
				Route route1 = new Route();
				route1.id = rs.getInt("id");
				route1.depature_airport_id = rs.getInt("dep_id");
				route1.depature_date = rs.getString("dep_date");
				route1.destination_airport_id = rs.getInt("dest_id");
				route1.destination_date = rs.getString("dest_date");
				route1.price = rs.getInt("price");
				route1.distance = rs.getInt("distance");
				route1.airplane = rs.getInt("airplane");
				
				Flight flight = new Flight(route1);
				flight.id = rs.getInt("flight_id");
				ret.add(flight);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
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
			rs = st.executeQuery("SELECT flights.id AS flight_id, A.name AS departure, B.name AS destination, routes.* FROM routes LEFT JOIN airports AS A ON A.id = routes.dep_id LEFT JOIN airports AS B ON B.id = routes.dest_id LEFT JOIN flights on flights.route1_id = routes.id");

			while (rs.next()) {
				Route route1 = new Route();
				route1.id = rs.getInt("id");
				route1.depature_airport_id = rs.getInt("dep_id");
				route1.depature_date = rs.getString("dep_date");
				route1.destination_airport_id = rs.getInt("dest_id");
				route1.destination_date = rs.getString("dest_date");
				route1.price = rs.getInt("price");
				route1.airplane = rs.getInt("airplane");
				route1.distance = rs.getInt("distance");
				Airport airport = new Airport();
				route1.airport = airport;
				route1.airport.setName(rs.getString("departure"));
				Airport airportDest = new Airport();
				route1.dest_airport = airportDest;
				route1.dest_airport.setName(rs.getString("destination"));
				Flight flight = new Flight(route1);
				flight.id = rs.getInt("flight_id");

				

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
	
	public static boolean AddBooking(Flight flight, User bookinguser) {
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("INSERT INTO bookings(flight_id, user_id) VALUES (?, ?)");
			st.setInt(1, flight.id);
			st.setInt(2, bookinguser.id);
			
			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Att skapa bokningen misslyckades.");
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (Boolean) null;
	}

	public static boolean UpdateRoute(Route route) {
		Connection con = null;
		java.sql.PreparedStatement st = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("UPDATE routes SET dep_id=?, dep_date=?, dest_id=?, dest_date=?, price=?, airplane = 1 WHERE id=?");
			st.setInt(1, route.getDepature_airport_id());
			st.setString(2, route.getDepature_date());
			st.setInt(3, route.getDestination_airport_id());
			st.setString(4, route.getDestination_date());
			st.setInt(5, route.getPrice());
			st.setInt(6, route.getId());
			st.executeUpdate();
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

	public static boolean AddRoute(int price, int dep_id, String dep_date,
			int dest_id, String dest_date, int distance) {
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("INSERT INTO routes (dep_id, dep_date, dest_id, dest_date, price, airplane, distance) VALUES (?, ?, ?, ?, ?, ?, ?)");
			st.setNString(1, Integer.toString(dep_id));
			st.setNString(2, dep_date);
			st.setNString(3, Integer.toString(dest_id));
			st.setNString(4, dest_date);
			st.setNString(5, Integer.toString(price));
			st.setInt(6, 1);
			st.setInt(7, distance);
			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Att skapa rutten misslyckades.");
			} else {
				return true;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (Boolean) null;
	}
	
	public static String RemoveRoute(int id){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			st.executeUpdate("DELETE FROM routes WHERE id=" + id);

			String ret = "Route has been removed";
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

	public static boolean addFlight(int route1_id, int route2_id) {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			String SQL_INSERT = "INSERT INTO flights (route1_id, route2_id) VALUES (?, ?)";
			st = (PreparedStatement) con.prepareStatement(SQL_INSERT);
			st.setInt(1, route1_id);
			st.setInt(2, route2_id);
			
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
