package com.flightprovider.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.flightprovider.flightproviderapp.FlightProviderApplication;
import com.flightprovider.service.FlightProviderService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestApiController.class)
@ContextConfiguration(classes={FlightProviderApplication.class})
public class FlightProviderRestControllerIntegrationTest {
	 @Autowired
	 private MockMvc mvc;
	 
	 @MockBean
	 private FlightProviderService service;
	 
	 @Test
	 public void retrieveFlightsByDeparture_thenReturnJsonArray()
	   throws Exception {
		 mvc.perform(get("/api/v1/retrieveFlights?sortBy=DEPARTURE")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());	 
	 }
	 
	 @Test
	 public void retrieveFlightsByArrival_thenReturnJsonArray()
	   throws Exception {
		 mvc.perform(get("/api/v1/retrieveFlights?sortBy=ARRIVAL")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());	 
	 }
	 
	 @Test
	 public void retrieveFlightsByRouteFilterDeparture_thenReturnJsonArray()
	   throws Exception {
		 mvc.perform(get("/api/v1/retrieveFlightsByRouteFilter?filterBy=DEPARTURE&value=Ankara")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());	 
	 }
	 
	 @Test
	 public void retrieveFlightsByRouteFilterARRIVAL_thenReturnJsonArray()
	   throws Exception {
		 mvc.perform(get("/api/v1/retrieveFlightsByRouteFilter?filterBy=ARRIVAL&value=Antalya")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());	 
	 }
	 
	 @Test
	 public void retrieveFlightsByDateFilterDeparture_thenReturnJsonArray()
	   throws Exception {
		 mvc.perform(get("/api/v1/retrieveFlightsByDateFilter?filterBy=DEPARTURE&value=1563678000")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());	 
	 }
	 
	 @Test
	 public void retrieveFlightsByDateFilterArrival_thenReturnJsonArray()
	   throws Exception {
		 mvc.perform(get("/api/v1/retrieveFlightsByDateFilter?filterBy=ARRIVAL&value=1563678000\"")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());	 
	 }
	 
	 @Test
	 public void findPaginated_thenReturnJsonArray()
	   throws Exception {
		 mvc.perform(get("/api/v1/findPaginated?page=1&size=2")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());	 
	 }

}
