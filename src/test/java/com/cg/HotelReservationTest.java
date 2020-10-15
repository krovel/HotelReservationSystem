package com.cg;

import org.junit.Test;

import static org.junit.Assert.*;

import java.text.ParseException;

public class HotelReservationTest {
    
	@Test
	public void checkCheapestHotel() throws ParseException {
		HotelReservation hotelReservation = new HotelReservation();
		String hotel = hotelReservation.findCheapestHotel("9Oct2019","15Oct2019");
	    assertEquals("Lakewood", hotel);
	}
}