package com.flightprovider.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.flightprovider.model.BusinessFlight;
import com.flightprovider.model.BusinessFlightInfoResponse;
import com.flightprovider.model.CheapFlight;
import com.flightprovider.model.CheapFlightInfoResponse;

@Service("flightProviderService")
public class FlightProviderServiceImpl implements FlightProviderService{
	//TODO
	private static final String cheapFlightsURL = "https://tokigames-challenge.herokuapp.com/api/flights/cheap";
	private static final String businessFlightsURL = "https://tokigames-challenge.herokuapp.com/api/flights/business";

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<CheapFlight> getAllCheapFlights() {
		ResponseEntity<CheapFlightInfoResponse> responseEntity = restTemplate.exchange(cheapFlightsURL, HttpMethod.GET, null, new ParameterizedTypeReference<CheapFlightInfoResponse>() {
		});
		List<CheapFlight> pojoObjList = ((CheapFlightInfoResponse)responseEntity.getBody()).getCheapFlights();
		return pojoObjList;
	}

	@Override
	public List<BusinessFlight> getAllBusinessFlights() {
		ResponseEntity<BusinessFlightInfoResponse> responseEntity = restTemplate.exchange(businessFlightsURL, HttpMethod.GET, null, new ParameterizedTypeReference<BusinessFlightInfoResponse>() {
		});
		List<BusinessFlight> pojoObjList = ((BusinessFlightInfoResponse)responseEntity.getBody()).getBusinessFlights();
		return pojoObjList;
	}

}
