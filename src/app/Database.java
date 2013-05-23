package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Airplane getAirplane(int id) {
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
			rs = st.executeQuery("SELECT * FROM airplanes WHERE id=" + id);

			if (rs.next()) {
				Airplane airplane = new Airplane();
				airplane.setId(rs.getInt("id"));
				airplane.setModel(rs.getString("model"));
				airplane.setSeatsTotal(rs.getInt("seats_total"));

				return airplane;
			}
			//st.close();
			//con.close();
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
			rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM `routes` JOIN `flights` ON flights.route1_id = routes.id WHERE flights.id ="
					+ id);

			if (rs.next()) {
				Route route1 = new Route();
				route1.depature_airport_id = rs.getInt("dep_id");
				route1.depature_date = rs.getString("dep_date");
				route1.destination_airport_id = rs.getInt("dest_id");
				route1.destination_date = rs.getString("dest_date");
				route1.price = rs.getInt("price");
				route1.airplane = rs.getInt("airplane");
				route1.distance = rs.getInt("distance");
				route1.id = rs.getInt("id");

				Flight flight = new Flight(route1);
				flight.id = rs.getInt("flight_id");

				return flight;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Route getRoute(int id) {
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
			rs = st.executeQuery("SELECT  A.name AS departure, B.name AS destination, routes.* FROM routes LEFT JOIN airports AS A ON A.id = routes.dep_id LEFT JOIN airports AS B ON B.id = routes.dest_id WHERE routes.id ="
					+ id);

			if (rs.next()) {
				Route route1 = new Route();
				route1.depature_airport_id = rs.getInt("dep_id");
				route1.depature_date = rs.getString("dep_date");
				route1.destination_airport_id = rs.getInt("dest_id");
				route1.destination_date = rs.getString("dest_date");
				route1.price = rs.getInt("price");
				route1.airplane = rs.getInt("airplane");
				route1.distance = rs.getInt("distance");
				route1.id = rs.getInt("id");

				return route1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<Flight> getAvailableFlights(int dep_id, int dest_id,
			String dep_date) {
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
			if (dep_id > 0 && dest_id > 0 && dep_date != null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="
						+ dep_id
						+ " AND dest_id="
						+ dest_id
						+ " AND dep_date LIKE '" + dep_date + "%'");
			} else if (dep_id == 0 && dest_id > 0 && dep_date != null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dest_id="
						+ dest_id + " AND dep_date LIKE '" + dep_date + "%'");
			} else if (dep_id > 0 && dest_id == 0 && dep_date != null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="
						+ dep_id + " AND dep_date LIKE '" + dep_date + "%'");
			} else if (dep_id > 0 && dest_id > 0 && dep_date == null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="
						+ dep_id + " AND dest_id=" + dest_id);
			} else if (dep_id > 0 && dest_id > 0 && dep_date == null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="
						+ dep_id + " AND dest_id=" + dest_id);
			} else if (dep_id == 0 && dest_id == 0 && dep_date != null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_date LIKE '"
						+ dep_date + "%'");
				System.out.println(rs);
			} else if (dep_id > 0 && dest_id == 0 && dep_date == null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="
						+ dep_id);
			} else if (dep_id == 0 && dest_id > 0 && dep_date == null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dest_id="
						+ dest_id);
			} else if (dep_id == 0 && dest_id == 0 && dep_date == null) {
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
				route1.seats_booked = rs.getInt("seats_booked");

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

	public static List<Booking> getAllBookings(User userb) {

		List<Booking> ret = new ArrayList<Booking>();
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
			rs = st.executeQuery("SELECT bookings.*, routes.*, dep.name as dep_name, dest.name as dest_name FROM bookings LEFT JOIN routes ON bookings.flight_id=routes.id JOIN airports AS dep ON routes.dep_id=dep.id JOIN airports AS dest ON routes.dest_id=dest.id where bookings.user_id="
					+ userb.id);

			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getInt("id"));
				booking.setFlightId(rs.getInt("flight_id"));
				booking.setUserId(rs.getInt("user_id"));
				Airport depAirport = new Airport();
				booking.depAirport = depAirport;
				Airport destAirport = new Airport();
				booking.destAirport = destAirport;
				booking.depAirport.setName(rs.getString("dep_name"));
				booking.destAirport.setName(rs.getString("dest_name"));
				Route route = new Route();
				booking.route = route;
				booking.route.setId(rs.getInt("routes.id"));
				booking.route.setDepature_date(rs.getString("dep_date"));
				booking.route.setDestination_date(rs.getString("dest_date"));
				booking.route.price = rs.getInt("price");

				ret.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static List<Booking> getAllBookingsByBooking(Route route) {

		List<Booking> ret = new ArrayList<Booking>();
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
			rs = st.executeQuery("SELECT bookings.*, users.*, routes.*, dep.name as dep_name, dest.name as dest_name FROM bookings LEFT JOIN users ON bookings.user_id=users.id LEFT JOIN routes on bookings.flight_id=routes.id JOIN airports AS dep ON routes.dep_id=dep.id JOIN airports AS dest ON routes.dest_id=dest.id where bookings.flight_id="
					+ route.getId());

			while (rs.next()) {
				Booking retBooking = new Booking();
				User user = new User();
				retBooking.setUser(user);
				retBooking.user.setFirst_name(rs.getString("first_name"));
				retBooking.user.setLast_name(rs.getString("last_name"));
				retBooking.user.setEmail(rs.getString("email"));
				retBooking.setId(rs.getInt("id"));
				ret.add(retBooking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static List<Booking> getAllUserBookings() {

		List<Booking> ret = new ArrayList<Booking>();
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
			rs = st.executeQuery("SELECT bookings.*, bookings.id as book_id, routes.*, users.*, dep.name as dep_name, dest.name as dest_name FROM bookings LEFT JOIN routes ON bookings.flight_id=routes.id LEFT JOIN airports AS dep ON routes.dep_id=dep.id JOIN airports AS dest ON routes.dest_id=dest.id JOIN users ON bookings.user_id=users.id where bookings.confirmed=0");

			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getInt("book_id"));
				booking.setFlightId(rs.getInt("flight_id"));
				booking.setUserId(rs.getInt("user_id"));
				Airport depAirport = new Airport();
				booking.depAirport = depAirport;
				Airport destAirport = new Airport();
				booking.destAirport = destAirport;
				booking.depAirport.setName(rs.getString("dep_name"));
				booking.destAirport.setName(rs.getString("dest_name"));
				booking.setConfirmed(rs.getInt("confirmed"));
				Route route = new Route();
				booking.route = route;
				booking.route.setDepature_date(rs.getString("dep_date"));
				booking.route.setDestination_date(rs.getString("dest_date"));
				booking.route.price = rs.getInt("price");
				User user = new User();
				booking.user = user;
				booking.user.setId(rs.getInt("user_id"));
				booking.user.setFirst_name(rs.getString("first_name"));
				booking.user.setLast_name(rs.getString("last_name"));
				booking.user.setEmail(rs.getString("email"));

				ret.add(booking);
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
//
	public static List<Route> getAllRoutes() {
		List<Route> ret = new ArrayList<Route>();
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
			rs = st.executeQuery("SELECT  A.name AS departure, B.name AS destination, routes.* FROM routes LEFT JOIN airports AS A ON A.id = routes.dep_id LEFT JOIN airports AS B ON B.id = routes.dest_id ORDER BY  routes.id");

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
				route1.seats_booked = rs.getInt("seats_booked");

				ret.add(route1);
			}
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}


	public static List<Route> getAllFlightID(){
		List<Route> ret = new ArrayList<Route>();
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
			rs = st.executeQuery("SELECT flights.id AS flight_id, flights.route1_id, flights.route2_id, routes.id AS route_id, routes.dep_id, routes.dest_id, a_dep.name AS departure, a_dest.name AS middle, a_mid.name AS destination FROM routes INNER JOIN flights ON flights.route1_id = routes.id INNER JOIN routes AS r_mid ON r_mid.id = flights.route2_id INNER JOIN airports AS a_dep ON a_dep.id = routes.dep_id INNER JOIN airports AS a_mid ON a_mid.id = routes.dest_id INNER JOIN airports AS a_dest ON a_dest.id = r_mid.dest_id WHERE flights.route2_id !=0 ORDER BY  `flights`.`id` ASC");
			//rs = st.executeQuery("SELECT id, route1_id, route2_id FROM flights");
			while (rs.next()) {
				Route route1 = new Route();
				route1.id = rs.getInt("route_id");
				route1.route1_id = rs.getInt("route1_id");
				route1.route2_id = rs.getInt("route2_id");
				route1.flight_id = rs.getInt("flight_id");
				Airport airport = new Airport();
				route1.airport = airport;
				route1.airport.setName(rs.getString("departure"));
				Airport airportDest = new Airport();
				route1.dest_airport = airportDest;
				route1.dest_airport.setName(rs.getString("destination"));
				route1.middle = rs.getString("middle");
				ret.add(route1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	
	public static List<User> getAllUsers() {

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
		java.sql.PreparedStatement st2 = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("INSERT INTO bookings(flight_id, user_id) VALUES (?, ?)");
			st.setInt(1, flight.id);
			st.setInt(2, bookinguser.id);
			st2 = con
					.prepareStatement("UPDATE routes SET seats_booked = seats_booked + 1 WHERE id=?");
			st2.setInt(1, flight.id);
			st2.executeUpdate();

			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Att skapa bokningen misslyckades.");
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (Boolean) null;
	}

	public static boolean UpdateRoute(Route route, int id) {
		Connection con = null;
		java.sql.PreparedStatement st = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("UPDATE routes SET dep_id=?, dep_date=?, dest_id=?, dest_date=?, price=?, airplane=?, distance=? WHERE id="
					+ id);
			st.setInt(1, route.getDepature_airport_id());
			st.setString(2, route.getDepature_date());
			st.setInt(3, route.getDestination_airport_id());
			st.setString(4, route.getDestination_date());
			st.setInt(5, route.getPrice());
			st.setInt(6, route.airplane);
			st.setInt(7, route.distance);
			st.executeUpdate();
			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {

				throw new SQLException("Att uppdatera rutten misslyckades.");

			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (Boolean) null;
	}

	public static boolean MakeConfirmed(int id) {
		Connection con = null;
		java.sql.PreparedStatement st = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("UPDATE bookings SET confirmed=1 WHERE id=?");
			st.setInt(1, id);

			st.executeUpdate();
			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {

				throw new SQLException("Att uppdatera rutten misslyckades.");

			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (Boolean) null;
	}

	public static boolean AddRoute(int dep_id, String dep_date, int dest_id,
			String dest_date, int price, int airplane, int distance) {
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
			st.setInt(6, airplane);
			st.setInt(7, distance);

			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Att skapa rutten misslyckades.");
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (Boolean) null;
	}

	public static String RemoveRoute(int id) {
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

	public static boolean deleteUserFromRoute(int userDelete) {
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("DELETE FROM bookings WHERE id=?");
			st.setInt(1, userDelete);

			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Att ta bort bokningen misslyckades");
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (Boolean) null;
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

	public static String UpdateFlight(int id, int route1_id, int route2_id) {
		Connection con = null;
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.prepareStatement("UPDATE flights SET route1_id = ?, route2_id = ? WHERE id = "
					+ id);
			st.setInt(1, route1_id);
			st.setInt(2, route2_id);
			st.executeUpdate();
			String ret = "Flight has been updated";
			return ret;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static int addFlight(int route1_id, int route2_id) {

		Connection con = null;
		PreparedStatement st = null;
		int ret = 0;
		

		try {
			con = DriverManager.getConnection(url, user, password);
			String SQL_INSERT = "INSERT INTO flights (route1_id, route2_id) VALUES (?, ?)";
			st = (PreparedStatement) con.prepareStatement(SQL_INSERT);
			st.setInt(1, route1_id);
			st.setInt(2, route2_id);
			

			int affectedRows = st.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Att skapa flygningen misslyckades.");
			} else {
				Long ret1 = st.getLastInsertID();
				Integer i = ret1 != null ? ret1.intValue() : null;
				ret = i;
				return ret;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static String registerUser(String email, String firstName,
			String lastName, String phonenumber, int adminStatus,
			String passWord) {
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

	public static User loginUser(String email, String passWord) {
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
			rs = st.executeQuery("SELECT * FROM users WHERE email='" + email
					+ "' AND password='" + passWord.toString() + "'");

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

	public static String UpdateUser(int id, String email, String firstName,
			String lastName, String phonenumber, int adminStatus) {
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

	public static String RemoveUser(int id) {
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

	public static boolean RemoveBooking(int id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			int affectedRows = st
					.executeUpdate("DELETE FROM bookings WHERE id=" + id);

			if (affectedRows == 0) {
				throw new SQLException("N�gonting gick fel. F�rs�k igen");
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (Boolean) null;
	}

	public static List<Airplane> getAllAirplanes() {
		List<Airplane> ret = new ArrayList<Airplane>();
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
			rs = st.executeQuery("SELECT * FROM airplanes");

			while (rs.next()) {
				Airplane airplane = new Airplane();
				airplane.id = rs.getInt("id");
				airplane.fuel_per_km = rs.getInt("fuel_per_km");
				airplane.model = rs.getString("model");
				airplane.seats_total = rs.getInt("seats_total");
				airplane.travel_speed = rs.getInt("travel_speed");
				ret.add(airplane);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;

	}
	
	public static List<Flight> getAvailableFlights2(int dep_id, int dest_id, String dep_date){
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
			List<Route> routes = getAllRoutes();
			List<Route> routesfromdep_id = new ArrayList<Route>();
			List<Route> routestodest_id = new ArrayList<Route>();
			con.close();
			
			for(int i = 0;i<routes.size();i++){
				String date = routes.get(i).depature_date.substring(0, 10);
					if(dep_id == routes.get(i).depature_airport_id){
						System.out.println("hejloop2");
						routesfromdep_id.add(routes.get(i));	
					}
					if(dest_id == routes.get(i).destination_airport_id){
						System.out.println("hejloop3");
						routestodest_id.add(routes.get(i));
					}
					if(dest_id <= 0 && dep_id <=0 && dep_date.equals(date)){
						Route route1 = createRouteInstance(routes.get(i));
						Flight flight = new Flight(route1);
						ret.add(flight);
					}
			}
			for(int i = 0;i<routesfromdep_id.size();i++){
				
				
				String date = routesfromdep_id.get(i).depature_date.substring(0, 10);
				
				System.out.println("hejloop4 "+date);
				System.out.println("heejloop5 "+dep_date);
				if(dest_id == routesfromdep_id.get(i).destination_airport_id && dep_date == null){
					Route route1 = createRouteInstance(routesfromdep_id.get(i));
					Flight flight = new Flight(route1);
					ret.add(flight);
				}else if(dest_id <= 0 && dep_date == null){
					Route route1 = createRouteInstance(routesfromdep_id.get(i));
					Flight flight = new Flight(route1);
					ret.add(flight);
				}else if(dest_id == routesfromdep_id.get(i).destination_airport_id && dep_date.equals(date)){
					System.out.println("heejloop5"+dep_date);
					Route route1 = createRouteInstance(routesfromdep_id.get(i));
					Flight flight = new Flight(route1);
					ret.add(flight);
				}
			}
			if(routestodest_id.size() > 0 && dep_id == 0){
				for(int i = 0;i<routestodest_id.size();i++){
					Route route1 = createRouteInstance(routestodest_id.get(i));
					Flight flight = new Flight(route1);
					ret.add(flight);
				}
			}
			if(routesfromdep_id.size() > 0 && routestodest_id.size() > 0){
				for(int i = 0;i<routesfromdep_id.size();i++){
					System.out.println("hejloopd");
					for(int y = 0; y <routestodest_id.size();y++){
						String dest_date_route1 = routesfromdep_id.get(i).destination_date.substring(0, 10);
						String dep_date_route2 = routestodest_id.get(y).depature_date.substring(0, 10);
						System.out.println(routesfromdep_id.get(i).destination_airport_id+"<dep dest>"+routestodest_id.get(y).depature_airport_id);
						if(routesfromdep_id.get(i).destination_airport_id == routestodest_id.get(y).depature_airport_id && dest_date_route1.equals(dep_date_route2)){
							
							Route route1 = new Route();
							route1.id = routesfromdep_id.get(i).id;
							route1.depature_airport_id = routesfromdep_id.get(i).depature_airport_id;
							route1.depature_date = routesfromdep_id.get(i).depature_date;
							route1.destination_airport_id = routesfromdep_id.get(i).destination_airport_id;
							route1.destination_date = routesfromdep_id.get(i).destination_date;
							route1.price = routesfromdep_id.get(i).price;
							route1.distance = routesfromdep_id.get(i).distance;
							route1.airplane = routesfromdep_id.get(i).airplane;
							route1.seats_booked = routesfromdep_id.get(i).seats_booked;
							
							Route route2 = new Route();
							route2.id = routestodest_id.get(y).id;
							route2.depature_airport_id = routestodest_id.get(y).depature_airport_id;
							route2.depature_date = routestodest_id.get(y).depature_date;
							route2.destination_airport_id = routestodest_id.get(y).destination_airport_id;
							route2.destination_date = routestodest_id.get(y).destination_date;
							route2.price = routestodest_id.get(y).price;
							route2.distance = routestodest_id.get(y).distance;
							route2.airplane = routestodest_id.get(y).airplane;
							route2.seats_booked = routestodest_id.get(y).seats_booked;

							Flight flight = new Flight(route1);
							flight.route2 = route2;
							int flightID = checkFlightId(flight.route1.id, flight.route2.id);
							if(flightID > 0){
								flight.id = flightID;
							}else{
								flight.id = addFlight(flight.route1.id, flight.route2.id);
							}
							ret.add(flight);
						}
					}
				}
			}
			
			
			/*if (dep_id > 0 && dest_id > 0 && dep_date != null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="
						+ dep_id
						+ " AND dest_id="
						+ dest_id
						+ " AND dep_date LIKE '" + dep_date + "%'");
			} else if (dep_id == 0 && dest_id > 0 && dep_date != null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dest_id="
						+ dest_id + " AND dep_date LIKE '" + dep_date + "%'");
			} else if (dep_id > 0 && dest_id == 0 && dep_date != null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="
						+ dep_id + " AND dep_date LIKE '" + dep_date + "%'");
			} else if (dep_id > 0 && dest_id > 0 && dep_date == null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="
						+ dep_id + " AND dest_id=" + dest_id);
			} else if (dep_id > 0 && dest_id > 0 && dep_date == null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="
						+ dep_id + " AND dest_id=" + dest_id);
			} else if (dep_id == 0 && dest_id == 0 && dep_date != null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_date LIKE '"
						+ dep_date + "%'");
				System.out.println(rs);
			} else if (dep_id > 0 && dest_id == 0 && dep_date == null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dep_id="
						+ dep_id);
			} else if (dep_id == 0 && dest_id > 0 && dep_date == null) {
				rs = st.executeQuery("SELECT flights.id AS flight_id, routes.* FROM routes JOIN flights ON flights.route1_id = routes.id WHERE dest_id="
						+ dest_id);
			} else if (dep_id == 0 && dest_id == 0 && dep_date == null) {
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
				route1.seats_booked = rs.getInt("seats_booked");

				Flight flight = new Flight(route1);
				flight.id = rs.getInt("flight_id");
				ret.add(flight);

			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	public static int checkFlightId(int route1ID, int route2ID){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int flight_id = 0;

		try {
			try {
				Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				System.out
						.println("ClassNotFoundException : " + e.getMessage());
			}
			con = DriverManager.getConnection(url, user, password);

			st = con.createStatement();
			rs = st.executeQuery("SELECT  * FROM flights WHERE route1_id ="+route1ID+" AND route2_id ="+route2ID);
			if (rs.next()) {
				flight_id = rs.getInt("id");
				
				

				return flight_id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flight_id;
	}
	public static Route createRouteInstance(Route route){
		Route route1 = new Route();
		route1.id = route.id;
		route1.depature_airport_id = route.depature_airport_id;
		route1.depature_date = route.depature_date;
		route1.destination_airport_id = route.destination_airport_id;
		route1.destination_date = route.destination_date;
		route1.price = route.price;
		route1.distance = route.distance;
		route1.airplane = route.airplane;
		route1.seats_booked = route.seats_booked;
		
		return route1;
	}
}
