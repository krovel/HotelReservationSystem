package com.cg;

import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class HotelReservation {
	final Hotel LAKEWOOD = new Hotel("Lakewood", 110);
	final Hotel BRIDGEWOOD = new Hotel("Bridgewood", 150);
	final Hotel RIDGEWOOD = new Hotel("Ridgewood", 220);
	Date inDate;
	Date outDate;

	List<Hotel> hotel = new ArrayList<>();
	public HotelReservation() {
		hotel.add(LAKEWOOD);
		hotel.add(BRIDGEWOOD);
		hotel.add(RIDGEWOOD);
	}
	public void convert(String checkin, String checkout) throws ParseException {
		this.inDate = new SimpleDateFormat("ddMMMyyyy").parse(checkin);
		this.outDate = new SimpleDateFormat("ddMMMyyyy").parse(checkout);
	}
	
	public String findCheapestHotel(String start, String finish) throws ParseException {
		convert(start, finish);
		long days = getTotalDays(inDate, outDate);
		List<Long> hotelRentList = hotel.parallelStream().map(hotel -> hotel.getRegularCustomerWeekdayRate() * days)
				 .collect(Collectors.toList());
		long minRent = Collections.min(hotelRentList);
		Hotel cheapestHotel = hotel.stream().filter(hotel -> hotel.getRegularCustomerWeekdayRate() * days == minRent).findFirst()
				.orElse(null);
		System.out.println("Cheapest Hotel : "+ cheapestHotel.getHotelName()+" Cost : "+ minRent);
		return cheapestHotel.getHotelName();
	 }
	
	private long getTotalDays(Date startDate, Date endDate) {
		return ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
	}
}
