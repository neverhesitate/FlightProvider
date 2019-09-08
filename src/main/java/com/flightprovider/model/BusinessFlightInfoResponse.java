package com.flightprovider.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessFlightInfoResponse {
	
	@JsonProperty("data")
	private List<BusinessFlight> businessFlights;

	public List<BusinessFlight> getBusinessFlights() {
		return businessFlights;
	}

	public void setBusinessFlights(List<BusinessFlight> businessFlights) {
		this.businessFlights = businessFlights;
	}

}
