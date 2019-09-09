package com.flightprovider.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.flightprovider.model.BusinessFlight;
import com.flightprovider.model.CheapFlight;
import com.flightprovider.model.Flight;
import com.flightprovider.service.FlightProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.flightprovider.utils.CustomErrorType;

@Api(value="Flight Provider", description="Listing all avaliable flights")
@RestController
@RequestMapping("/api/v1")
public class RestApiController {
	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	FlightProviderService flightProviderService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "View a list of available flights", response = List.class)
	@GetMapping(value = "/retrieveFlights")
	public ResponseEntity<?> retrieveFlights(@RequestParam("sortBy") String sortBy) {
		List<BusinessFlight> businessFlights = flightProviderService.getAllBusinessFlights();
		List<CheapFlight> cheapFlights = flightProviderService.getAllCheapFlights();
		List<Flight> flights = getAllFlights(businessFlights, cheapFlights);
		if (flights == null || flights.size() == 0) {
			logger.error("Flights not found {}.", flights);
			return new ResponseEntity(new CustomErrorType("Flights " 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		switch(sortBy) {
			case "DEPARTURE":
				Collections.sort(flights, new SortByDeparture());
				break;
			case "ARRIVAL":
				Collections.sort(flights, new SortByArrival());
				break;
			case "DEPARTURETIME":
				Collections.sort(flights, new SortByDepartureTime());
				break;
			case "ARRIVALTIME":
				Collections.sort(flights, new SortByArrivalTime());
				break;
			default:
				Collections.sort(flights, new SortByDeparture());
		}
		
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "View a list of paginated flights", response = List.class)
	@GetMapping(value = "/findPaginated")
	public ResponseEntity<?> findPaginated(@RequestParam("page") int page, 
			  @RequestParam("size") int size) {
		List<BusinessFlight> businessFlights = flightProviderService.getAllBusinessFlights();
		List<CheapFlight> cheapFlights = flightProviderService.getAllCheapFlights();
		List<Flight> flights = getAllFlights(businessFlights, cheapFlights);
		int start = page;
		int end = (int) ((start + size) > flights.size() ? flights.size()
				  : (start + size));
		if (flights == null || flights.size() == 0) {
			logger.error("Flights not found {}.", flights);
			return new ResponseEntity(new CustomErrorType("Flights " 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Flight>>(flights.subList(start, end), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "View a list of filtered flights", response = List.class)
	@GetMapping(value = "/retrieveFlightsByRouteFilter")
	public ResponseEntity<?> retrieveFlightsByRouteFilter(@RequestParam("filterBy") String filterBy,@RequestParam("value") String value) {
		List<BusinessFlight> businessFlights = flightProviderService.getAllBusinessFlights();
		List<CheapFlight> cheapFlights = flightProviderService.getAllCheapFlights();
		List<Flight> flights = getAllFlights(businessFlights, cheapFlights);
		if (flights == null || flights.size() == 0) {
			logger.error("Flights not found {}.", flights);
			return new ResponseEntity(new CustomErrorType("Flights " 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		
		if(filterBy.equals("DEPARTURE")) {
			flights = getFlightsByDeparture(flights,value);		
		}else if(filterBy.equals("ARRIVAL")) {
			flights = getFlightsByArrival(flights,value);
		}
		
		if (flights == null || flights.size() == 0) {
			logger.error("Flights not found {}.", flights);
			return new ResponseEntity(new CustomErrorType("Flights " 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "View a list of filtered flights", response = List.class)
	@GetMapping(value = "/retrieveFlightsByDateFilter")
	public ResponseEntity<?> retrieveFlightsByDateFilter(@RequestParam("filterBy") String filterBy,@RequestParam("value") long value) {
		List<BusinessFlight> businessFlights = flightProviderService.getAllBusinessFlights();
		List<CheapFlight> cheapFlights = flightProviderService.getAllCheapFlights();
		List<Flight> flights = getAllFlights(businessFlights, cheapFlights);
		if (flights == null || flights.size() == 0) {
			logger.error("Flights not found {}.", flights);
			return new ResponseEntity(new CustomErrorType("Flights " 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		
		if(filterBy.equals("DEPARTURE")) {
			flights = getFlightsByDepartureTime(flights,value);		
		}else if(filterBy.equals("ARRIVAL")) {
			flights = getFlightsByArrivalTime(flights,value);
		}
		
		if (flights == null || flights.size() == 0) {
			logger.error("Flights not found {}.", flights);
			return new ResponseEntity(new CustomErrorType("Flights " 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}
	
	private List<Flight> getAllFlights(List<BusinessFlight> businessFlights, List<CheapFlight> cheapFlights){
		List<Flight> toReturn = new ArrayList<>();
		for(BusinessFlight businessFlight : businessFlights) {
			toReturn.add(new Flight(businessFlight.getDeparture(),businessFlight.getArrival(),businessFlight.getArrivalTime(),businessFlight.getArrivalTime()));
		}
		for(CheapFlight cheapFlight : cheapFlights) {
			String departure = cheapFlight.getRoute().substring(0, cheapFlight.getRoute().indexOf("-")).trim();
			String arrival = cheapFlight.getRoute().substring(cheapFlight.getRoute().indexOf("-")+1, cheapFlight.getRoute().length());
			toReturn.add(new Flight(departure,arrival,cheapFlight.getDeparture(),cheapFlight.getArrival()));
		}
		return toReturn;
	}
	
	private List<Flight> getFlightsByDeparture(List<Flight> flights, String departure){
		return flights
				  .stream()
				  .filter(f -> f.getDeparture().equals(departure))
				  .collect(Collectors.toList());
	}
	
	private List<Flight> getFlightsByArrival(List<Flight> flights, String arrival){
		return flights
				  .stream()
				  .filter(f -> f.getArrival().equals(arrival))
				  .collect(Collectors.toList());
	}
	
	private List<Flight> getFlightsByDepartureTime(List<Flight> flights, long departureTime){
		return flights
				  .stream()
				  .filter(f -> f.getDepartureTime() > departureTime)
				  .collect(Collectors.toList());
	}
	
	private List<Flight> getFlightsByArrivalTime(List<Flight> flights, long arrivalTime){
		return flights
				  .stream()
				  .filter(f -> f.getArrivalTime() > arrivalTime)
				  .collect(Collectors.toList());
	}
	
}
