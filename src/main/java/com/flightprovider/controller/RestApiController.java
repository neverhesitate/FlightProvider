package com.flightprovider.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flightprovider.model.BusinessFlight;
import com.flightprovider.model.CheapFlight;
import com.flightprovider.service.FlightProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Flight Provider", description="Listing all avaliable flights")
@RestController
@RequestMapping("/api/v1")
public class RestApiController {
	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	FlightProviderService flightProviderService;
	
	@ApiOperation(value = "View a list of available flights", response = List.class)
	@RequestMapping(value = "/retrieveFlights", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveFlights() {
		List<BusinessFlight> businessFlights = flightProviderService.getAllBusinessFlights();
		List<CheapFlight> cheapFlights = flightProviderService.getAllCheapFlights();
		//System.out.println("BusinessFlight count: "+businessFlights.size()+", CheapFlight count: "+cheapFlights.size());
		//return new ResponseEntity<List<BusinessFlight>>(businessFlights, HttpStatus.OK);
		return new ResponseEntity<List<CheapFlight>>(cheapFlights, HttpStatus.OK);
	}
}
