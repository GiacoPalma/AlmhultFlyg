package app;

public class Flight {
	public int id;
	public Route route1;
	public Route route2;
	public Flight(Route route){
		this.route1 = route;
	}
	
	public boolean checkRoute2(){
		boolean tru = false;
		if (route2 != null){
			tru = true;
		}else if(route2 == null){
			tru = false;
		}
		return tru;
	}
	
}
