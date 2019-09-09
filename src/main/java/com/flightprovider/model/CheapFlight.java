package com.flightprovider.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheapFlight {
	private String route;
	private Long departure;
	private Long arrival;
	
	public String getRoute() {
		return route;
	}
	
	public void setRoute(String route) {
		this.route = route;
	}
	
	public Long getDeparture() {
		return departure;
	}
	
	public void setDeparture(Long departure) {
		this.departure = departure;
	}
	
	public Long getArrival() {
		return arrival;
	}
	
	public void setArrival(Long arrival) {
		this.arrival = arrival;
	}
	
	
}
