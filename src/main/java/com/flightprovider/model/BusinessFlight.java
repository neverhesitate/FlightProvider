package com.flightprovider.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessFlight {
	private String departure;
	private String arrival;
	private Long departureTime;
	private Long arrivalTime;
	
	public String getDeparture() {
		return departure;
	}
	
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	
	public String getArrival() {
		return arrival;
	}
	
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	
	public Long getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(Long departureTime) {
		this.departureTime = departureTime;
	}
	
	public Long getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(Long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
}
