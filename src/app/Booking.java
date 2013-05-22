package app;

public class Booking {
	public int id;
	public int flightId;
	public int userId;
	public Airport depAirport;
	public Airport destAirport;
	public Route route;
	public User user;
	public int confirmed;

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public Airport getDepAirport() {
		return depAirport;
	}

	public void setDepAirport(Airport depAirport) {
		this.depAirport = depAirport;
	}

	public Airport getDestAirport() {
		return destAirport;
	}

	public void setDestAirport(Airport destAirport) {
		this.destAirport = destAirport;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean checkAvailability(int max, int booked) {
		if (max > booked) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getAvailability(int max, int booked) {
		return max-booked;
	}

}
