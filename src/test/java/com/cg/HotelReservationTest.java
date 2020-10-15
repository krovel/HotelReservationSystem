package com.cg;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

import java.text.ParseException;

public class HotelReservationTest {
    
	@Test
	public void checkCheapestHotelForRegularCustomerInWeekdayRate() throws ParseException {
		HotelReservation hotelReservation = new HotelReservation();
		String hotel = hotelReservation.findCheapestHotelForRegularCustomersInWeekDayRate("10Sep2020","11Sep2020");
	    assertEquals("Lakewood", hotel);
	}

	@Test
	public void checkCheapestHotelForRegularCustomer() throws ParseException {
		HotelReservation hotelReservation = new HotelReservation();
		List<String> hotelList = hotelReservation.findCheapestHotels("Regular","10Sep2020","11Sep2020");
	    List<String> expectedList = new ArrayList<>(Arrays.asList("Lakewood","Bridgewood"));
	    assertEquals(expectedList, hotelList);
	}

	@Test
	public void checkCheapestBestRatedHotelForRegularCustomers() throws ParseException {
		HotelReservation hotelReservation = new HotelReservation();
		String hotel = hotelReservation.findCheapestBestRatedHotel("Regular", "10Sep2020","11Sep2020");
		assertEquals("Bridgewood", hotel);
	}

	@Test
	public void checkBestRatedHotelForRegularCustomers() throws ParseException {
		HotelReservation hotelReservation = new HotelReservation();
		String hotel = hotelReservation.findBestRatedHotel("Regular", "10Sep2020","11Sep2020");
	    assertEquals("Ridgewood", hotel);
	}
	
	@Test
	public void checkCheapestBestRatedHotelForRewardCustomers() throws ParseException{
		HotelReservation hotelReservation = new HotelReservation();
		String hotel = hotelReservation.findCheapestBestRatedHotel("Reward", "10Sep2020", "11Sep2020");
	    assertEquals("Ridgewood", hotel);
	}
	
	@Test
	public void checkWithInvalidDateFormat() {
		HotelReservation hotelReservation = new HotelReservation();
		try {
			String hotel = hotelReservation.findCheapestBestRatedHotel("Reward", "102020", "11Sep");
		} catch (Exception e) {
			assertEquals("Please enter date in proper format:", e.getMessage());
		}
	}
}