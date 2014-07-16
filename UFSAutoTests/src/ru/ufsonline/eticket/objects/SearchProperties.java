package ru.ufsonline.eticket.objects;

import ru.ufsonline.eticket.utils.TestObject;

public class SearchProperties {
	
	private String departure;
	
	private String destination;
	
	private int departureDate;
	
	private String departureTime;
	
	private String adults;
	
	private String children;
	
	private String infants;
	
	public SearchProperties(TestObject searchProps) {
		this.departure = searchProps.getProperty("departure");
		this.destination = searchProps.getProperty("destination");
		this.departureDate = Integer.valueOf(searchProps.getProperty("departureDate"));
		this.departureTime = searchProps.getProperty("departureTime");
		this.adults = searchProps.getProperty("adults");
		this.children = searchProps.getProperty("children");
		this.infants = searchProps.getProperty("infants");
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(int departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getAdults() {
		return adults;
	}

	public void setAdults(String adults) {
		this.adults = adults;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getInfants() {
		return infants;
	}

	public void setInfants(String infants) {
		this.infants = infants;
	}
}