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
	
	Connection connection = null;
	public static String driverName = "com.mysql.jdbc.Driver"; //for MySql
	String serverName = "ginger.umd.edu"; // Use this server.
	String portNumber = "1521";
	String sid = "dbclass1";
	
	
	public static Airport getAirport(int id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			try{
			Class.forName(driverName);
			}catch(ClassNotFoundException e){
				System.out.println("ClassNotFoundException : "+e.getMessage());
			}
			con = DriverManager.getConnection(url, user, password);
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM article WHERE id=" + id);

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
			try{
				Class.forName(driverName);
				}catch(ClassNotFoundException e){
					System.out.println("ClassNotFoundException : "+e.getMessage());
				}
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM Airports");

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
	
	
	public static String RemoveAirport(int id){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			int delete = st.executeUpdate("DELETE * FROM Airports WHERE id=" + id);

			if (delete == 1) {
				String ret = "Airport has been removed";
				return ret;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String UpdateAirport(int id, String city1, String name1){
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("UPDATE Airports(city, name) VALUES (?, ?) WHERE id = "+id);
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
	
	public static String AddAirport(String city1, String name1){
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("INSERT INTO Airports(city, name) VALUES (?, ?)");
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
	
	public static String UpdateFlight(int id, int price, int dep_id, int dep_date, int dest_id, int dest_date){
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("UPDATE flights (dep_id, dep_date, dest_id, dest_date, price) VALUES (?, ?, ?, ?, ?) WHERE id = "+id);
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
	
	public static String AddFlight(int id, int price, int dep_id, int dep_date, int dest_id, int dest_date){
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
}
