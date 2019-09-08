package com.flightprovider.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CheapFlightInfoResponse {
	@JsonProperty("data")
	private List<CheapFlight> cheapFlights;

	public List<CheapFlight> getCheapFlights() {
		return cheapFlights;
	}

	public void setCheapFlights(List<CheapFlight> cheapFlights) {
		this.cheapFlights = cheapFlights;
	}

}
