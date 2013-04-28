package app;

public class Airport {
	// Sam was here
	public int id;
	public String name;
	public String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void Print() {
		System.out.println(name);
		System.out.println(city);
	}

}
