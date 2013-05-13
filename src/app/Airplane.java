package app;

public class Airplane {
	public int id;
	public String model;
	public int seats_total;
	public int fuel_per_km;
	public int travel_speed;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getModel(){
		return model;
	}
	
	public void setModel(String model){
		this.model = model;
	}
	
	public int getSeatsTotal(){
		return seats_total;
	}
	
	public void setSeatsTotal(int seats_total){
		this.seats_total = seats_total;
	}
	
	public int getFuel_per_km(){
		return fuel_per_km;
	}
	
	public void setFuel_per_km(int fuel_per_km){
		this.fuel_per_km = fuel_per_km;
	}
	
	public int getTravel_speed(){
		return travel_speed;
	}
	
	public void setTravel_speed(int travel_speed){
		this.travel_speed = travel_speed;
	}
}
