package app;

public class Booking {
	public int id;
	public int flightId;
	public int userId;
	public Airport depAirport;
	public Airport destAirport;
	public Route route;

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

}
