package com.flightprovider.controller;

import java.util.Comparator;

import com.flightprovider.model.Flight;

public class SortByArrivalTime implements Comparator<Flight>{

	@Override
	public int compare(Flight f1, Flight f2) {
		 if (f1.getArrivalTime() > f2.getArrivalTime())
			 return 1;
		 return 0;
	}

}
