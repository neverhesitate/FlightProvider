package com.flightprovider.service;

import java.util.List;
import com.flightprovider.model.BusinessFlight;
import com.flightprovider.model.CheapFlight;

public interface FlightProviderService {
	//
	public List<CheapFlight> getAllCheapFlights();
	public List<BusinessFlight> getAllBusinessFlights();

}
