package com.cg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class HotelReservation {
	final Hotel LAKEWOOD = new Hotel("Lakewood", 110, 90, 3, 80, 80);
	final Hotel BRIDGEWOOD = new Hotel("Bridgewood", 150, 50, 4, 110, 50);
	final Hotel RIDGEWOOD = new Hotel("Ridgewood", 220, 150, 5, 100, 40);
	Date inDate;
	Date outDate;
	long days, weekDays, weekendDays;

	List<Hotel> hotelList = new ArrayList<>();
	public HotelReservation() {
		hotelList.add(LAKEWOOD);
		hotelList.add(BRIDGEWOOD);
		hotelList.add(RIDGEWOOD);
	}
	public void convert(String checkin, String checkout) throws ParseException {
		this.inDate = new SimpleDateFormat("ddMMMyyyy").parse(checkin);
		this.outDate = new SimpleDateFormat("ddMMMyyyy").parse(checkout);
		this.days = getTotalDays(inDate, outDate);
		this.weekendDays = getWeekendDays(inDate, outDate);
		this.weekDays = days - weekendDays;
	}
	
	public String findCheapestHotelForRegularCustomersInWeekDayRate(String startDate, String finishDate)
			throws ParseException {
		convert(startDate, finishDate);
		List<Long> hotelRentList = hotelList.parallelStream().map(hotel -> hotel.getRegularCustomerWeekdayRate() * days)
				.collect(Collectors.toList());
		long minRent = Collections.min(hotelRentList);
		Hotel cheapestHotel = hotelList.stream()
				.filter(hotel -> hotel.getRegularCustomerWeekdayRate() * days == minRent).findFirst().orElse(null);
		System.out.println("Cheapest Hotel : " + cheapestHotel.getHotelName() + " Cost : " + minRent);
		return cheapestHotel.getHotelName();
	}

	public long calculateHotelCost(String type, Hotel hotel, long weekDay, long weekEnds) {
		if (type.equals("Regular"))
			return hotel.getRegularCustomerWeekdayRate() * weekDay + hotel.getRegularCustomerWeekendRate() * weekEnds;
		else
			return hotel.getRewardCustomerWeekdayRate() * weekDay + hotel.getRewardCustomerWeekendRate() * weekEnds;
	}

	public List<String> findCheapestHotels(String type, String startDate, String finishDate) throws ParseException {
		convert(startDate, finishDate);
		List<Long> hotelRentList = hotelList.stream()
				.map(hotel -> calculateHotelCost(type, hotel, weekDays, weekendDays)).collect(Collectors.toList());
		long minRent = Collections.min(hotelRentList);
		List<String> cheapHotelList = hotelList.stream()
				.filter(hotel -> calculateHotelCost(type, hotel, weekDays, weekendDays) == minRent)
				.map(hotel -> hotel.getHotelName()).collect(Collectors.toList());
		for (String hotel : cheapHotelList)
			System.out.println("Cheapest Hotel : " + hotel + " Cost : " + minRent);
		return cheapHotelList;
	}

	public String findCheapestBestRatedHotel(String type, String startDate, String finishDate) {
		if(!(type.equals("Reward") || type.equals("Regular")))
			return "Reward or Regular:";
		try {
			convert(startDate, finishDate);
		} catch (ParseException e) {			
			return "Enter date in proper format: ";
		}
		List<Long> hotelRentList = hotelList.parallelStream()
				.map(hotel -> calculateHotelCost(type, hotel, weekDays, weekendDays)).collect(Collectors.toList());
		long minRent = Collections.min(hotelRentList);
		List<Hotel> cheapHotelList = hotelList.stream()
				.filter(hotel -> calculateHotelCost(type, hotel, weekDays, weekendDays) == minRent)
				.collect(Collectors.toList());
		Hotel hotel = cheapHotelList.stream().max(Comparator.comparing(Hotel::getRating))
				.orElseThrow(NoSuchElementException::new);
		System.out.println("Cheapest Best Rated Hotel : " + hotel.getHotelName() + " Rating : " + hotel.getRating() + " Cost : " + minRent);
		return hotel.getHotelName();
	}

	public String findBestRatedHotel(String type, String startDate, String finishDate) throws ParseException {
		convert(startDate, finishDate);
		Hotel hotel = hotelList.stream().max(Comparator.comparing(Hotel::getRating))
				.orElseThrow(NoSuchElementException::new);
		long cost = calculateHotelCost(type, hotel, weekDays, weekendDays);
		System.out.println("Best Rated Hotel : " + hotel.getHotelName() + " Rating : " + hotel.getRating() + " Cost : " + cost);
		return hotel.getHotelName();
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