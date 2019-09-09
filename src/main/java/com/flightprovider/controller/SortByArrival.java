package com.flightprovider.controller;

import java.util.Comparator;
import com.flightprovider.model.Flight;

public class SortByArrival  implements Comparator<Flight>{

	@Override
	public int compare(Flight f1, Flight f2) {
		 return f1.getArrival().compareTo(f2.getArrival());
	}

}
