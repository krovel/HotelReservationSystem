package com.cg;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

import java.text.ParseException;

public class HotelReservationTest {
    
	@Test
	public void checkCheapestHotel() throws ParseException {
		HotelReservation hotelReservation = new HotelReservation();
		String hotel = hotelReservation.findCheapestHotel("9Oct2019","15Oct2019");
	    assertEquals("Lakewood", hotel);
	}
	@Test
	public void checkCheapestHotelForRegularCustomers() throws ParseException {
		HotelReservation hotelReservation = new HotelReservation();
		List<String> hotel = hotelReservation.findCheapestHotelForRegularCustomersConsideringWeekdayAndWeekend("10Sep2020","11Sep2020");
	    List<String> expected = new ArrayList<>(Arrays.asList("Lakewood","Bridgewood"));
	    assertEquals(expected, hotel);
	}
	@Test
	public void checkCheapestBestRatedHotelForRegularCustomers() throws ParseException {
		HotelReservation hotelReservation = new HotelReservation();
		String hotel = hotelReservation.cheapestBestRatedHotel("10Sep2020","11Sep2020");
		assertEquals("Bridgewood", hotel);
	}
	@Test
	public void checkBestRatedHotelForRegularCustomers() throws ParseException {
		HotelReservation hotelReservationMain = new HotelReservation();
		String hotel = hotelReservationMain.findBestRatedHotel("10Sep2020","11Sep2020");
	    assertEquals("Ridgewood", hotel);
	}
}