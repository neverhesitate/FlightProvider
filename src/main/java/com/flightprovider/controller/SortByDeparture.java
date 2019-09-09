package com.flightprovider.controller;

import java.util.Comparator;
import com.flightprovider.model.Flight;

public class SortByDeparture implements Comparator<Flight>{

	@Override
	public int compare(Flight f1, Flight f2) {
		 return f1.getDeparture().compareTo(f2.getDeparture());
	}

}
