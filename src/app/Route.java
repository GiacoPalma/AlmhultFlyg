package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Route {
	public int id;
	public Integer depature_airport_id;
	public String depature_date;
	public Integer destination_airport_id;
	public String destination_date;
	public Integer price;
	public Integer distance;
	public Integer airplane_id;
	public List<String> errorMessages = new ArrayList<String>();
	public Airport airport;
	public Airport dest_airport;
	public Airplane airplane;

	public Airport getDest_airport() {
		return dest_airport;
	}

	public void setDest_airport(Airport dest_airport) {
		this.dest_airport = dest_airport;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public boolean validate() {
		boolean departureCheck = this.checkDeparture();
		boolean destinationCheck = this.checkDestination();
		if (departureCheck && destinationCheck) {
			this.checkDepartureAndDestination();
		}
		boolean departureDateCheck = this.checkDepartureDate();
		boolean destinationDateCheck = this.checkDestinationDate();
		if (departureDateCheck && destinationDateCheck) {
			this.checkDestinationBeforeDeparture();
		}
		this.checkPrice();

		if (errorMessages.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkDepartureAndDestination() {
		if (this.getDepature_airport_id() == this.getDestination_airport_id()) {
			errorMessages
					.add("Destinationsort kan inte vara samma som avreseort");
			return false;
		} else {
			return true;
		}
	}

	public boolean checkPrice() {
		if (this.price == null) {
			errorMessages.add("Du m�ste fylla i ett pris");
			return false;
		} else {
			return true;
		}
	}

	public boolean checkDeparture() {
		if (this.depature_airport_id == null || this.depature_airport_id <= 0) {
			errorMessages.add("Du m�ste v�lja avreseort");
			return false;
		} else {
			return true;
		}
	}

	public boolean checkDestination() {
		if (this.destination_airport_id == null
				|| this.destination_airport_id <= 0) {
			errorMessages.add("Du m�ste v�lja destination");
			return false;
		} else {
			return true;
		}
	}

	public boolean checkDepartureDate() {
		if (this.depature_date == null || this.depature_date == "") {
			errorMessages.add("Du m�ste v�lja ett datum f�r avresa");
			return false;
		} else {
			return true;
		}
	}

	public boolean checkDestinationDate() {
		if (this.destination_date == null) {
			errorMessages.add("Du m�ste v�lja ett datum f�r destination");
			return false;
		} else {
			return true;
		}
	}

	public boolean checkDestinationBeforeDeparture() {
		if (this.getFormattedDepature_date().compareTo(
				this.getFormattedDestination_date()) > 0) {
			errorMessages
					.add("Destinationsdatum kan inte vara innan avresedatum");
			return false;
		} else {
			return true;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDepature_airport_id() {
		return depature_airport_id;
	}

	public void setDepature_airport_id(int depature_airport_id) {
		this.depature_airport_id = depature_airport_id;
	}

	public Date getFormattedDepature_date() {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(depature_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public Date getFormattedDestination_date() {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(destination_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public String getDepature_date() {
		return depature_date;
	}

	public void setDepature_date(String depature_date) {
		this.depature_date = depature_date;
	}

	public int getDestination_airport_id() {
		return destination_airport_id;
	}

	public void setDestination_airport_id(int destination_airport_id) {
		this.destination_airport_id = destination_airport_id;
	}

	public String getDestination_date() {
		return destination_date;
	}

	public void setDestination_date(String destination_date) {
		this.destination_date = destination_date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int distance, int fuel_per_km, int seatstotal){//int price) {
		//this.price = price;
		int fuel = distance * fuel_per_km;
		this.price = 250 + (fuel / seatstotal) + 280;
	}

	public int getDuration() {
		int calc1 = Integer.parseInt(depature_date);
		int calc2 = Integer.parseInt(destination_date);
		int duration = calc1 - calc2;
		return duration;
	}
	
	/*public int getDistance() {
		return distance;
	}*/
	
}