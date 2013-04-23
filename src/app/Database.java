package app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Database {
	
	

	public static String url = "jdbc:mysql://193.17.218.178:3306/161957-airport";
	public static String user = "161957_ur42152";
	public static String password = "Vaxjoairport1000";
	
	
	Connection connection = null;
	public static String driverName = "com.mysql.jdbc.Driver"; //for MySql
	String serverName = "ginger.umd.edu"; // Use this server.
	String portNumber = "1521";
	String sid = "dbclass1";
	
	
	static Airport getAirport(int id) {
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

				ret.add(airport);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}
	
	
	static String RemoveAirport(int id){
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
	
	static String AddAirport(String city1, String name1){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			int add = st.executeUpdate("INSERT INTO Airports " + "VALUES ('', "+city1+", "+name1+")");

			if (add == 1) {
				String ret = "Airport has been added";
				return ret;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
