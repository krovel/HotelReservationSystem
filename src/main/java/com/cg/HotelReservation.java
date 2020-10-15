package com.cg;

import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class HotelReservation {
	final Hotel LAKEWOOD = new Hotel("Lakewood", 110,90);
	final Hotel BRIDGEWOOD = new Hotel("Bridgewood", 150,50);
	final Hotel RIDGEWOOD = new Hotel("Ridgewood", 220,150);
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
		System.out.println("Hotel : "+ cheapestHotel.getHotelName()+" Cost : "+ minRent);
		return cheapestHotel.getHotelName();
	 }

	public long hotelCost(Hotel hotel, long weekDay, long weekEnds) {
		return hotel.getRegularCustomerWeekdayRate() * weekDay + hotel.getRegularCustomerWeekendRate() * weekEnds;
	}
	
	public List<String> findCheapestHotelForRegularCustomersConsideringWeekdayAndWeekend(String start, String finish) throws ParseException {
		convert(start, finish);
		long days = getTotalDays(inDate, outDate);
		long weekendDays = getWeekendDays(inDate, outDate);
		long weekDays = days - weekendDays;
		List<Long> hotelRentList = hotel.stream()
				.map(hotel -> hotelCost(hotel, weekDays, weekendDays)).collect(Collectors.toList());
		long minRent = Collections.min(hotelRentList);
		List<String> cheapHotelList = hotel.stream()
				.filter(hotel -> hotelCost(hotel, weekDays, weekendDays) == minRent)
				.map(hotel -> hotel.getHotelName()).collect(Collectors.toList());
		for (String hotel : cheapHotelList)
			System.out.println("Hotel : " + hotel + " Cost : " + minRent);
        return cheapHotelList;
	}
	
	private long getWeekendDays(Date checkinDate, Date checkoutDate) {
		long weekendDays = 0;
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(checkinDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(checkoutDate);
		for (; startCalendar.compareTo(endCalendar) <= 0; startCalendar.add(Calendar.DATE, 1)) {
			int dayOfWeek = startCalendar.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == 0 || dayOfWeek == 6)
				weekendDays++;
		}
		return weekendDays;
	}
	
	private long getTotalDays(Date startDate, Date endDate) {
		return ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
	}
}